import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Main class to collect unique symbols from a set of cards.
 */
public class CardCollector {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // A Set is used to store unique symbols. Since we override equals() and hashCode()
        // in Card based on the symbol, the Set will only contain one Card object
        // for each unique symbol encountered (the first one added).
        Set<Card> uniqueSymbolCards = new HashSet<>();
        int cardsEntered = 0;
        final int TARGET_SYMBOLS = 4; // Assuming the target is 4 unique symbols based on the problem.

        System.out.println("Collect Unique Symbols From Set of Cards\n");

        // Loop until 4 unique symbols are collected
        while (uniqueSymbolCards.size() < TARGET_SYMBOLS) {
            System.out.println("Enter a card :");
            System.out.print("Enter a card symbol: ");
            String symbol = scanner.nextLine().trim();

            System.out.print("Enter a card number: ");
            int number;
            try {
                // Read the number, handling potential non-integer input
                number = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number entered. Skipping this card.");
                continue; // Skip the rest of the loop for this iteration
            }

            Card newCard = new Card(symbol, number);
            cardsEntered++;

            // If the set successfully adds the card, it means the symbol is unique/new.
            // If the symbol is already present, add() returns false and the newCard is ignored,
            // which fulfills the requirement of storing the *first* occurrence.
            uniqueSymbolCards.add(newCard);

            System.out.println(); // Newline for cleaner input separation
        }

        scanner.close();

        System.out.println("Four symbols gathered in " + cardsEntered + " cards.");
        
        // Convert the Set to a List so it can be sorted.
        List<Card> sortedCards = new ArrayList<>(uniqueSymbolCards);
        
        // Sort the list using the compareTo implementation in the Card class
        // (Alphabetical by symbol, then by number).
        Collections.sort(sortedCards); 

        System.out.println("Cards in Set are:");
        // Display the results
        for (Card card : sortedCards) {
            System.out.println(card);
        }
    }
}
