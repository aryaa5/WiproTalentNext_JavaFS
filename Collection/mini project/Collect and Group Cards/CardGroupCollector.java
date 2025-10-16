import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class CardGroupCollector {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter Number of Cards: ");
        int totalCards = 0;
        try {
            totalCards = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.err.println("Invalid input for number of cards. Exiting.");
            return;
        }

        // Use a TreeMap to store cards. TreeMap automatically sorts keys (symbols) alphabetically,
        // fulfilling the requirement to print symbols in alphabetical order.
        Map<String, List<Card>> cardMap = new TreeMap<>();

        // 1. Collect all card details
        for (int i = 1; i <= totalCards; i++) {
            System.out.println("Enter card " + i + ":");
            
            System.out.print("Symbol: ");
            String symbol = scanner.nextLine().trim();
            
            System.out.print("Number: ");
            int number;
            try {
                number = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.err.println("Invalid number entered. Skipping card " + i + ".");
                i--; // Decrement counter to re-enter this card
                continue;
            }

            Card newCard = new Card(symbol, number);
            
            // Map the card:
            // computeIfAbsent checks if the symbol key exists. 
            // If it doesn't, it creates a new ArrayList as the value, and then adds the newCard.
            // If it exists, it gets the existing list and adds the newCard to it.
            cardMap.computeIfAbsent(symbol, k -> new ArrayList<>()).add(newCard);
        }
        
        scanner.close();

        // 2. Print Distinct Symbols
        List<String> distinctSymbols = new ArrayList<>(cardMap.keySet());
        // Since we used a TreeMap, the keys are already sorted.
        System.out.print("\nDistinct Symbols are : ");
        for (String symbol : distinctSymbols) {
            System.out.print(symbol + (distinctSymbols.indexOf(symbol) == distinctSymbols.size() - 1 ? "" : " "));
        }
        System.out.println("\n");

        // 3. Process and print details for each symbol (sorted alphabetically by TreeMap)
        for (Map.Entry<String, List<Card>> entry : cardMap.entrySet()) {
            String symbol = entry.getKey();
            List<Card> cards = entry.getValue();

            System.out.println("Cards in " + symbol + " Symbol");
            
            int sumOfNumbers = 0;
            
            // Print individual card details and calculate the sum
            for (Card card : cards) {
                System.out.println(card);
                sumOfNumbers += card.getNumber();
            }

            // Print the aggregation details
            System.out.println("Number of cards : " + cards.size());
            System.out.println("Sum of Numbers : " + sumOfNumbers);
        }
    }
}
