import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Represents the runner (Hare or Tortoise) and implements the race logic.
 */
class Race implements Runnable {
    
    // Shared flag to stop the race as soon as the first thread finishes.
    // 'volatile' ensures visibility across all threads.
    private static volatile AtomicBoolean raceOver = new AtomicBoolean(false);
    
    private final String runnerName;
    private final int totalDistance = 100;
    private final int sleepDistance = 60; // Distance at which Hare sleeps (for part c)
    private final int sleepTime = 1000;  // 1000 milliseconds = 1 second (for part c)

    private final boolean isHare;

    // Constructor
    public Race(String runnerName, boolean isHare) {
        this.runnerName = runnerName;
        this.isHare = isHare;
    }
    
    // Reset the static flag for a new race
    public static void resetRace() {
        raceOver.set(false);
    }

    @Override
    public void run() {
        System.out.println(runnerName + " starts running.");
        
        for (int distance = 1; distance <= totalDistance; distance++) {
            
            // Check if the race is already won by another thread
            if (raceOver.get()) {
                break; // Stop running
            }

            // --- Part c: Hare sleeps at 60 meters ---
            if (isHare && distance == sleepDistance) {
                System.out.println(runnerName + " reached " + distance + "m and decides to take a nap for " + sleepTime + "ms.");
                try {
                    Thread.sleep(sleepTime);
                    System.out.println(runnerName + " wakes up and continues the race.");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            // End of Part c logic
            
            // Print progress periodically (e.g., every 10 meters)
            if (distance % 10 == 0) {
                System.out.println(runnerName + " is at " + distance + "m.");
            }
        }
        
        // After the loop, check if this thread is the winner
        if (!raceOver.getAndSet(true)) {
            // This thread won, as it successfully set the flag to true
            System.out.println("\n-------------------------------------------");
            System.out.println("🎉 The winner is: " + runnerName + "!");
            System.out.println("-------------------------------------------");
        } else {
            // This thread finished but was not the first one
            System.out.println(runnerName + " finished the race, but the winner was already decided.");
        }
    }
}
