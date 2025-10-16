// ... (imports remain the same)

public class StudentService {
    
    // Instantiate the validation helper
    private final StudentValidation validator = new StudentValidation();

    // Method to calculate the grade for a student object
    public String calculateGrade(Student studentObject) {
        
        // 1. Use the separate validator class to check for nulls
        String validationResult = validator.validate(studentObject);
        
        // If validation fails, return the error message
        if (!validationResult.equals("VALID")) {
            return validationResult;
        }

        // 2. If valid, proceed with grade calculation
        int sum = 0;
        for (int mark : studentObject.getMarks()) {
            if (mark < 35) {
                return "F"; // F grade if any mark is below 35
            }
            sum += mark;
        }

        if (sum >= 200) {
            return "A"; // TC1: A grade
        } else if (sum >= 100) {
            return "D"; // TC2: D grade
        } else {
            return "F"; // TC3: F grade
        }
    }
    
    // Note: The original 'validate' method is now removed from StudentService 
    //       since it has been moved to StudentValidation.
    
    // ... (The null counting methods remain here) ...
}
