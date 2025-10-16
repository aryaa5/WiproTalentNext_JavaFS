package com.mile1.service;

import com.mile1.Student;
import com.mile1.exception.NullMarksArrayException;
import com.mile1.exception.NullNameException;
import com.mile1.exception.NullStudentException;

public class StudentValidation {

    /**
     * Validates a Student object and returns a String indicating the result.
     * Throws custom exceptions if a null field is encountered, but catches them 
     * to return the required error message String.
     * * @param studentObject The Student object to validate.
     * @return "VALID" if no errors, otherwise the specific error message.
     */
    public String validate(Student studentObject) {
        try {
            if (studentObject == null) {
                // TC4: If the Object is null
                throw new NullStudentException("TC4: Object is null");
            }
            if (studentObject.getName() == null) {
                // TC5: If the Name is null
                throw new NullNameException("TC5: Name is null");
            }
            if (studentObject.getMarks() == null) {
                // TC6: If the Marks array is null
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
}
