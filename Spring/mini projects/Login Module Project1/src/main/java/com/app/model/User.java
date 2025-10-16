package com.app.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User {

    @NotNull(message = "Username is mandatory")
    private String username;

    // 2. Password must be exactly 6 characters
    @NotNull(message = "Password is mandatory")
    @Size(min = 6, max = 6, message = "Password must be exactly 6 characters")
    private String password;

    // 3. Employee ID: single alphabet followed by exactly 4 digits (e.g., A1234)
    @NotNull(message = "Employee Number is mandatory")
    @Pattern(regexp = "[A-Za-z]\\d{4}", message = "Employee ID must be one letter followed by 4 digits (e.g., A1234)")
    private String employeeNumber;

    // 4. Email Address (standard email pattern check)
    @NotNull(message = "Email Address is mandatory")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email format")
    private String emailAddress;

    // --- Getters and Setters (Omitted for brevity) ---
    // ...
}
