package com.mile1;

public class Student {
    private String name;
    private int[] marks;

    // Constructor
    public Student(String name, int[] marks) {
        this.name = name;
        this.marks = marks;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int[] getMarks() {
        return marks;
    }
}
