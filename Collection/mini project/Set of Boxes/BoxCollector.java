import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BoxCollector {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of Box: ");
        int numberOfBoxes = 0;
        try {
            numberOfBoxes = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.err.println("Invalid input for number of boxes. Exiting.");
            return;
        }

        // Use a HashSet to store unique boxes. Uniqueness is defined by the Box.equals() method (volume).
        Set<Box> uniqueBoxes = new HashSet<>();

        // Loop to get details for the specified number of boxes
        for (int i = 1; i <= numberOfBoxes; i++) {
            System.out.println("Enter the Box " + i + " details");

            try {
                System.out.print("Enter Length: ");
                double length = Double.parseDouble(scanner.nextLine().trim());

                System.out.print("Enter Width: ");
                double width = Double.parseDouble(scanner.nextLine().trim());

                System.out.print("Enter Height: ");
                double height = Double.parseDouble(scanner.nextLine().trim());
                
                // Create the Box object
                Box newBox = new Box(length, width, height);
                
                // The Set.add() method returns false if an equivalent object (by volume) is already present.
                uniqueBoxes.add(newBox);
                
            } catch (NumberFormatException e) {
                System.err.println("Invalid numeric input. Skipping Box " + i + ".");
                i--; // Decrement counter to ensure the loop runs for the intended number of times
            }
        }
        
        scanner.close();

        System.out.println("\nUnique Boxes in the Set are");
        
        // Print the details of all unique boxes in the Set
        // Note: The order of output in a HashSet is not guaranteed. 
        // If sorting were required, a TreeSet or an ArrayList combined with Collections.sort() would be used.
        for (Box box : uniqueBoxes) {
            System.out.println(box);
        }
    }
}
