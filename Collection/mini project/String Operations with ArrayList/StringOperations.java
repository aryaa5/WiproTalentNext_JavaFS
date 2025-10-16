import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StringOperations {

    /**
     * Performs five specific string operations on S1 and S2 and returns the results
     * in an ArrayList. Assumes S2 is of smaller size.
     * * @param s1 The primary string.
     * @param s2 The secondary string (assumed to be smaller).
     * @return An ArrayList containing the results of the five operations.
     */
    public List<String> performStringOperations(String s1, String s2) {
        List<String> results = new ArrayList<>();

        // --- Operation 1: Alternate Index Replacement ---
        results.add(operation1(s1, s2));

        // --- Operation 2: Last Occurrence Replacement/Concatenation ---
        results.add(operation2(s1, s2));

        // --- Operation 3: First Occurrence Deletion/Return S1 ---
        results.add(operation3(s1, s2));

        // --- Operation 4: S2 Halves Insertion ---
        results.add(operation4(s1, s2));

        // --- Operation 5: Character Replacement with '*' ---
        results.add(operation5(s1, s2));

        return results;
    }

    // --- Helper Methods for Each Operation ---

    // 1. Character in each alternate index of S1 should be replaced with S2
    private String operation1(String s1, String s2) {
        StringBuilder sb = new StringBuilder(s1);
        int s2Length = s2.length();
        
        // Start from index 0 and increment by 2 (alternate indices: 0, 2, 4, ...)
        for (int i = 0; i < sb.length(); i += 2) {
            
            // Determine the character from S2 to use
            // The character is taken cyclically from S2 using modulo arithmetic
            char replacementChar = s2.charAt((i / 2) % s2Length);
            
            // Replace the character at the current alternate index
            sb.setCharAt(i, replacementChar);
        }
        return sb.toString();
    }

    // 2. If S2 appears more than once in S1, replace the last occurrence of S2 in S1 
    //    with the reverse of S2, else return S1 + S2
    private String operation2(String s1, String s2) {
        int count = 0;
        int index = s1.indexOf(s2);

        // Count occurrences of S2 in S1
        while (index != -1) {
            count++;
            // Move index past the current occurrence to find the next one
            index = s1.indexOf(s2, index + s2.length());
        }

        if (count > 1) {
            // S2 appears more than once: Replace last occurrence with reverse of S2
            String s2Reverse = new StringBuilder(s2).reverse().toString();
            
            // Find the index of the last occurrence
            int lastIndex = s1.lastIndexOf(s2);
            
            // Reconstruct the string
            return s1.substring(0, lastIndex) + s2Reverse + s1.substring(lastIndex + s2.length());
        } else {
            // S2 appears once or zero times: Return S1 + S2
            return s1 + s2;
        }
    }

    // 3. If S2 appears more than once in S1, delete the first occurrence of S2 in S1, else return S1
    private String operation3(String s1, String s2) {
        int count = 0;
        int index = s1.indexOf(s2);

        // Count occurrences (re-use logic from operation 2 or simplify)
        while (index != -1) {
            count++;
            index = s1.indexOf(s2, index + s2.length());
        }

        if (count > 1) {
            // S2 appears more than once: Delete the first occurrence
            int firstIndex = s1.indexOf(s2);
            return s1.substring(0, firstIndex) + s1.substring(firstIndex + s2.length());
        } else {
            // S2 appears once or less: Return S1
            return s1;
        }
    }

    // 4. Divide S2 into two halves and add the first half to the beginning of S1 
    //    and second half to the end of S1. (Handles odd/even S2 length)
    private String operation4(String s1, String s2) {
        int n = s2.length();
        
        // Calculate the split point for the first half
        // If n is odd, first half length is (n/2) + 1 (e.g., length 5 -> 3, 2 split)
        int firstHalfLength = (n % 2 != 0) ? (n / 2) + 1 : (n / 2);
        
        String s2FirstHalf = s2.substring(0, firstHalfLength);
        String s2SecondHalf = s2.substring(firstHalfLength);
        
        return s2FirstHalf + s1 + s2SecondHalf;
    }

    // 5. If S1 contains characters that is in S2 change all such characters to '*'
    private String operation5(String s1, String s2) {
        StringBuilder sb = new StringBuilder(s1);
        
        // Iterate through all characters in S1
        for (int i = 0; i < sb.length(); i++) {
            char currentChar = sb.charAt(i);
            
            // Check if the current character of S1 exists in S2
            if (s2.indexOf(currentChar) != -1) {
                // If it exists, replace it with '*'
                sb.setCharAt(i, '*');
            }
        }
        return sb.toString();
    }
    
    // --- Main Method for Demonstration ---

    public static void main(String[] args) {
        String s1 = "JAVAJAVAVA";
        String s2 = "VA";
        
        StringOperations operations = new StringOperations();
        List<String> results = operations.performStringOperations(s1, s2);

        System.out.println("S1=\"" + s1 + "\"");
        System.out.println("S2=\"" + s2 + "\"");
        System.out.println("--- Results ---");
        
        // Print results to match the required output format
        for (int i = 0; i < results.size(); i++) {
            System.out.println((i + 1) + ". " + results.get(i));
        }

        // Print final list output
        System.out.println("\nOutput: {\"" + String.join("\", \"", results) + "\"}");
    }
}
