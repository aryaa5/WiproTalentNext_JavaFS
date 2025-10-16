package com.mile1.test;

import com.mile1.Student;
import com.mile1.service.StudentService;

public class StudentServiceTest {
    
    // Create StudentService instance
    private static StudentService studentService = new StudentService();

    // Define a set of dummy students for TC7, TC8, TC9
    // Student[] data = {s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12};
    private static Student[] studentData = new Student[] {
        // TC8 Null Objects: s1, s6 are null
        null, 
        // s2: Valid A grade (200+ and all >= 35)
        new Student("A-Std", new int[]{80, 70, 75}), // Sum=225 >= 200 (A)
        // s3: Valid D grade (100+ and all >= 35)
        new Student("D-Std", new int[]{40, 35, 35}), // Sum=110 >= 100 (D)
        // s4: Valid F grade (Sum < 100, all >= 35)
        new Student("F-Sum", new int[]{35, 35, 20}), // Sum=90 < 100 (F)
        // s5: Valid F grade (One mark < 35)
        new Student("F-Mark", new int[]{50, 20, 50}), // One mark < 35 (F)
        
        // TC8 Null Objects: s6 is null
        null, 
        // TC7 Null Name: s7 has null name
        new Student(null, new int[]{100, 100, 100}),
        // TC9 Null Marks: s8 has null marks array
        new Student("NullMarks", null),
        
        // s9: Valid A grade
        new Student("Test9", new int[]{90, 90, 90}), 
        // s10: Valid D grade
        new Student("Test10", new int[]{40, 40, 40})
    };


    public static void main(String[] args) {
        System.out.println("--- STUDENT GRADE CALCULATION TEST ---");
        
        // --- GRADE CALCULATION FOR VALID OBJECT (TC1, TC2, TC3) ---
        System.out.println("\nGRADE CALCULATION FOR VALID OBJECTS:");
        
        // TC1: Check for A grade computation (s2: Sum=225)
        System.out.println("TC1 (A Grade): " + studentService.calculateGrade(studentData[1])); // Expected: A
        
        // TC2: Check for D grade computation (s3: Sum=110)
        System.out.println("TC2 (D Grade): " + studentService.calculateGrade(studentData[2])); // Expected: D
        
        // TC3: Check for F grade computation (s5: One mark < 35)
        System.out.println("TC3 (F Grade): " + studentService.calculateGrade(studentData[4])); // Expected: F


        // --- THROW ERROR MESSAGE FOR INVALID OBJECT (TC4, TC5, TC6) ---
        System.out.println("\nTHROW ERROR MESSAGE FOR INVALID OBJECT:");
        
        // TC4: If the Object is null, throw NullStudentException (s1)
        System.out.println("TC4 (Null Student): " + studentService.validate(studentData[0])); // Expected: TC4: Object is null

        // TC5: If the Name is null, throw NullNameException (s7)
        System.out.println("TC5 (Null Name): " + studentService.validate(studentData[6])); // Expected: TC5: Name is null

        // TC6: If the Marks array is null, throw NullMarksArrayException (s8)
        System.out.println("TC6 (Null Marks): " + studentService.validate(studentData[7])); // Expected: TC6: Marks array is null


        // --- COUNTING THE NULL (TC7, TC8, TC9) ---
        System.out.println("\nCOUNTING THE NULL:");
        
        // TC8: Test findNumberOfNullObjects function (s1, s6 are null)
        System.out.println("TC8 (Null Objects Count): " + studentService.findNumberOfNullObjects(studentData)); // Expected: 2

        // TC7: Test findNumberOfNullName function (s7 has null name)
        System.out.println("TC7 (Null Name Count): " + studentService.findNumberOfNullName(studentData)); // Expected: 1

        // TC9: Test findNumberOfNullMarks function (s8 has null marks array)
        System.out.println("TC9 (Null Marks Count): " + studentService.findNumberOfNullMarks(studentData)); // Expected: 1
    }
}
