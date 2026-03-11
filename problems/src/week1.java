import java.util.*;
public class week1 {
    static HashMap<String, Integer> inventory = new HashMap<>();
    static HashMap<String, LinkedHashMap<Integer, Integer>> waitingList = new HashMap<>();
    public static int checkStock(String productId) {
        return inventory.getOrDefault(productId, 0);
    }
    public static synchronized String purchaseItem(String productId, int userId) {
        int stock = inventory.getOrDefault(productId, 0);

        if (stock > 0) {
            inventory.put(productId, stock - 1);
            return "Success, " + (stock - 1) + " units remaining";
        }
        waitingList.putIfAbsent(productId, new LinkedHashMap<>());
        LinkedHashMap<Integer, Integer> list = waitingList.get(productId);
        int position = list.size() + 1;
        list.put(userId, position);
        return "Added to waiting list, position #" + position;
    }
    public static void main(String[] args) {
        inventory.put("IPHONE15_256GB", 100);
        System.out.println("Stock: " + checkStock("IPHONE15_256GB"));
        System.out.println(purchaseItem("IPHONE15_256GB", 12345));
        System.out.println(purchaseItem("IPHONE15_256GB", 67890));
        inventory.put("IPHONE15_256GB", 0);

        System.out.println(purchaseItem("IPHONE15_256GB", 99999));
    }
}