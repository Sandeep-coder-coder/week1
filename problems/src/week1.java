import java.util.*;
public class week1 {
    static HashMap<String, Integer> users = new HashMap<>();
    static HashMap<String, Integer> attempts = new HashMap<>();
    public static boolean checkAvailability(String username) {
        attempts.put(username, attempts.getOrDefault(username, 0) + 1);
        return !users.containsKey(username);
    }
    public static List<String> suggestAlternatives(String username) {
        List<String> suggestions = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            String name = username + i;
            if (!users.containsKey(name)) {
                suggestions.add(name);
            }
        }
        String alt = username.replace("_", ".");
        if (!users.containsKey(alt)) {
            suggestions.add(alt);
        }
        return suggestions;
    }
    public static String getMostAttempted() {
        String maxUser = "";
        int max = 0;
        for (Map.Entry<String, Integer> e : attempts.entrySet()) {
            if (e.getValue() > max) {
                max = e.getValue();
                maxUser = e.getKey();
            }
        }
        return maxUser + " (" + max + " attempts)";
    }
    public static void main(String[] args) {

        users.put("john_doe", 1);
        users.put("admin", 2);

        System.out.println(checkAvailability("john_doe"));
        System.out.println(checkAvailability("jane_smith"));

        System.out.println(suggestAlternatives("john_doe"));
        checkAvailability("admin");
        checkAvailability("admin");
        System.out.println(getMostAttempted());
    }
}