public class RaceController {

    public static void main(String[] args) throws InterruptedException {
        
        // --- Part a & b: Threads with Priority Difference ---
        runRace(true); // Part b: Set high priority for Hare
        
        System.out.println("\n============================================\n");
        
        // --- Part c: Hare Sleeps at 60m (Priorities reset to normal) ---
        runRace(false); // Part c: Do not set priority to show impact of sleep
    }

    private static void runRace(boolean usePriority) throws InterruptedException {
        
        // 1. Reset the race condition
        Race.resetRace();
        
        // 2. Setup the runners
        Race hareRunner = new Race("Hare", true);
        Race tortoiseRunner = new Race("Tortoise", false);

        Thread hareThread = new Thread(hareRunner, "Hare");
        Thread tortoiseThread = new Thread(tortoiseRunner, "Tortoise");
        
        System.out.println("--- Starting Race: " + (usePriority ? "Part b (High Priority Hare)" : "Part c (Hare Sleeps) ---"));

        // 3. Set priorities based on the requirement (Part b)
        if (usePriority) {
            // Part b: Since the Hare is faster, set a high priority.
            hareThread.setPriority(Thread.MAX_PRIORITY);
            tortoiseThread.setPriority(Thread.MIN_PRIORITY);
            System.out.println("Priority set: Hare (" + hareThread.getPriority() + "), Tortoise (" + tortoiseThread.getPriority() + ")");
        } else {
            // Part c: Use default priority (5) to clearly show the effect of sleep.
            hareThread.setPriority(Thread.NORM_PRIORITY);
            tortoiseThread.setPriority(Thread.NORM_PRIORITY);
            System.out.println("Priority set: Hare (" + hareThread.getPriority() + "), Tortoise (" + tortoiseThread.getPriority() + ")");
        }

        // 4. Start the race (Part a)
        hareThread.start();
        tortoiseThread.start();
        
        // Wait for both threads to finish before starting the next race scenario
        hareThread.join();
        tortoiseThread.join();
    }
}
