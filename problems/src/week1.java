import java.util.*;
public class week1 {
    static class DNSEntry {
        String ip;
        long expiryTime;
        DNSEntry(String ip, int ttl) {
            this.ip = ip;
            this.expiryTime = System.currentTimeMillis() + ttl * 1000;
        }
    }
    static HashMap<String, DNSEntry> cache = new HashMap<>();
    static int hits = 0;
    static int misses = 0;
    public static String queryUpstream(String domain) {
        return "172.217.14." + new Random().nextInt(255);
    }
    public static String resolve(String domain) {
        DNSEntry entry = cache.get(domain);
        if (entry != null) {
            if (System.currentTimeMillis() < entry.expiryTime) {
                hits++;
                return "Cache HIT → " + entry.ip;
            } else {
                cache.remove(domain);
            }
        }
        misses++;
        String ip = queryUpstream(domain);
        cache.put(domain, new DNSEntry(ip, 5)); // TTL = 5 sec
        return "Cache MISS → Query upstream → " + ip;
    }
    public static void getCacheStats() {
        int total = hits + misses;
        double hitRate = (hits * 100.0) / total;
        System.out.println("Hit Rate: " + hitRate + "%");
        System.out.println("Hits: " + hits + " Misses: " + misses);
    }
    public static void main(String[] args) throws Exception {
        System.out.println(resolve("google.com"));
        System.out.println(resolve("google.com"));
        Thread.sleep(6000);
        System.out.println(resolve("google.com"));
        getCacheStats();
    }
}