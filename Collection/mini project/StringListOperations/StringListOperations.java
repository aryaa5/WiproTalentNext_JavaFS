import java.util.*;

public class StringListOperations {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> items = new ArrayList<>();

        while (true) {
            System.out.println("\n1. Insert");
            System.out.println("2. Search");
            System.out.println("3. Delete");
            System.out.println("4. Display");
            System.out.println("5. Exit");
            System.out.print("Enter your choice : ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter the item to be inserted: ");
                    String itemToAdd = sc.nextLine();
                    items.add(itemToAdd);
                    System.out.println("Inserted successfully");
                    break;

                case 2:
                    System.out.print("Enter the item to search : ");
                    String searchItem = sc.nextLine();
                    if (items.contains(searchItem)) {
                        System.out.println("Item found in the list.");
                    } else {
                        System.out.println("Item not found in the list.");
                    }
                    break;

                case 3:
                    System.out.print("Enter the item to delete : ");
                    String itemToDelete = sc.nextLine();
                    if (items.contains(itemToDelete)) {
                        items.remove(itemToDelete);
                        System.out.println("Deleted successfully");
                    } else {
                        System.out.println("Item does not exist.");
                    }
                    break;

                case 4:
                    if (items.isEmpty()) {
                        System.out.println("The list is empty.");
                    } else {
                        System.out.println("The Items in the list are : ");
                        for (String s : items) {
                            System.out.println(s);
                        }
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
