/**
 * Represents a single playing card with a symbol (String) and a number (int).
 */
class Card {
    private String symbol;
    private int number;

    // Constructor
    public Card(String symbol, int number) {
        this.symbol = symbol;
        this.number = number;
    }

    // Getters
    public String getSymbol() {
        return symbol;
    }

    public int getNumber() {
        return number;
    }

    // Overriding toString() for easy card detail printing
    @Override
    public String toString() {
        return symbol + " " + number;
    }
}
