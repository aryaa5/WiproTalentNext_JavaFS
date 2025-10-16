package com.crud.model;

import jakarta.persistence.*; // Use javax.persistence.* if using older Jakarta/Java EE

@Entity
@Table(name = "EMPLOYEE_PROFILE")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_seq")
    @SequenceGenerator(name = "emp_seq", sequenceName = "EMPLOYEE_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private int id; // Automatically generated

    @Column(name = "NAME")
    private String name;
    
    @Column(name = "GENDER")
    private String gender;

    @Column(name = "DESIGNATION")
    private String designation;
    
    @Column(name = "SALARY")
    private double salary;
    
    @Column(name = "CITY")
    private String city;
    
    @Column(name = "EMAIL_ID", unique = true)
    private String emailId;
    
    @Column(name = "MOBILE_NO")
    private String mobileNo;

    @Column(name = "PASSWORD")
    private String password; // For login functionality

    // --- Constructors ---
    public Employee() {}

    // Constructor for self-modification (only modifiable fields)
    public Employee(int id, String name, String city, String emailId, String mobileNo) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.emailId = emailId;
        this.mobileNo = mobileNo;
    }

    // Constructor for adding a new employee
    public Employee(String name, String gender, String designation, double salary, String city, String emailId, String mobileNo, String password) {
        this.name = name;
        this.gender = gender;
        this.designation = designation;
        this.salary = salary;
        this.city = city;
        this.emailId = emailId;
        this.mobileNo = mobileNo;
        this.password = password;
    }

    // --- Getters and Setters (Omitted for brevity, but necessary) ---
    // Example:
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    // ... all other getters/setters ...
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
