import java.util.Objects;

/**
 * Represents a playing card with a symbol (String) and a number (int).
 * It implements Comparable<Card> to allow sorting based on symbol and then number.
 */
class Card implements Comparable<Card> {
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

    // Overriding toString() for easy output formatting
    @Override
    public String toString() {
        return symbol + " " + number;
    }

    // Implementing equals() and hashCode() is crucial when using a Set.
    // We define equality based ONLY on the symbol, as the goal is to store unique symbols.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        // Cards are considered equal if their symbols are the same.
        return symbol.equals(card.symbol);
    }

    @Override
    public int hashCode() {
        // The hash code is generated ONLY from the symbol.
        return Objects.hash(symbol);
    }

    // Implementing compareTo for default sorting (though a custom Comparator is often clearer for specific needs)
    // The requirement is to display the output in alphabetical order of the card details (symbol and number).
    // The main sort is by symbol, secondary by number.
    @Override
    public int compareTo(Card other) {
        int symbolComparison = this.symbol.compareTo(other.symbol);
        if (symbolComparison != 0) {
            return symbolComparison;
        }
        return Integer.compare(this.number, other.number);
    }
}
