import java.util.Objects;

/**
 * Represents a Box with length, width, and height.
 * Uniqueness in a Set is determined solely by the volume.
 */
class Box {
    private double length;
    private double width;
    private double height;
    private double volume;

    // Constructor
    public Box(double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;
        // Calculate volume immediately upon creation
        this.volume = length * width * height;
    }

    // Getters
    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
    
    public double getVolume() {
        return volume;
    }

    /**
     * Overrides equals() to compare Boxes based ONLY on their volume.
     * Use a small tolerance (epsilon) for double comparison to account for 
     * floating-point inaccuracies, although for this specific problem 
     * direct comparison might suffice if input is precise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Box box = (Box) o;
        
        // Define equality based on volume
        final double EPSILON = 0.0001; 
        return Math.abs(volume - box.volume) < EPSILON;
    }

    /**
     * Overrides hashCode() to be consistent with equals().
     * Since equals() is based on volume, the hashCode must also be based on volume.
     */
    @Override
    public int hashCode() {
        // Use the double's hash code for the volume
        return Objects.hash(volume);
    }
    
    // Custom toString() method for the required output format
    @Override
    public String toString() {
        // Format the output to two decimal places for volume display
        return String.format("Length =%.1f Width =%.1f Height =%.1f Volume =%.2f", 
                             length, width, height, volume);
    }
}
