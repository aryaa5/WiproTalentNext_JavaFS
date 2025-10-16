package com.mile1.service;

import com.mile1.Student;
import com.mile1.exception.NullMarksArrayException;
import com.mile1.exception.NullNameException;
import com.mile1.exception.NullStudentException;

public class StudentService {

    // --- Validation and Grade Calculation (for TC1, TC2, TC3, TC4, TC5, TC6) ---

    // Method to calculate the grade for a student object
    public String calculateGrade(Student studentObject) {
        // Validation check before calculation (handles TC4, TC5, TC6)
        String validationResult = validate(studentObject);
        
        // If validation fails, return the error message
        if (!validationResult.equals("VALID")) {
            return validationResult;
        }

        // If validation passes, proceed with grade calculation (handles TC1, TC2, TC3)
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
            return "F"; // TC3: F grade (If all marks >= 35 but sum < 100)
        }
    }

    // Method to validate a student object and throw custom exceptions (for TC4, TC5, TC6)
    // Returns "VALID" if no exception is thrown, otherwise returns the error message
    public String validate(Student studentObject) {
        try {
            if (studentObject == null) {
                throw new NullStudentException("TC4: Object is null");
            }
            if (studentObject.getName() == null) {
                throw new NullNameException("TC5: Name is null");
            }
            if (studentObject.getMarks() == null) {
                throw new NullMarksArrayException("TC6: Marks array is null");
            }
        } catch (NullStudentException e) {
            return e.getMessage();
        } catch (NullNameException e) {
            return e.getMessage();
        } catch (NullMarksArrayException e) {
            return e.getMessage();
        }
        return "VALID";
    }

    // --- Null Counting Functions (for TC7, TC8, TC9) ---

    // TC8: Test findNumberOfNullObjects function
    public int findNumberOfNullObjects(Student[] students) {
        int nullCount = 0;
        if (students == null) return 0; // Handle case where the array itself is null
        
        for (Student s : students) {
            if (s == null) {
                nullCount++;
            }
        }
        return nullCount;
    }

    // TC7: Test findNumberOfNullName function
    public int findNumberOfNullName(Student[] students) {
        int nullNameCount = 0;
        if (students == null) return 0;
        
        for (Student s : students) {
            if (s != null && s.getName() == null) {
                nullNameCount++;
            }
        }
        return nullNameCount;
    }

    // TC9: Test findNumberOfNullMarks function
    public int findNumberOfNullMarks(Student[] students) {
        int nullMarksCount = 0;
        if (students == null) return 0;
        
        for (Student s : students) {
            if (s != null && s.getMarks() == null) {
                nullMarksCount++;
            }
        }
        return nullMarksCount;
    }
}
