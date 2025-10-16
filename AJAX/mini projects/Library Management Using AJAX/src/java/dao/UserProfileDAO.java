package com.xyzlibrary.dao;

import java.util.HashMap;
import java.util.Map;

public class UserProfileDAO {
    // Simulated Database Table: Email -> Password
    private static final Map<String, String> USERS_DB = new HashMap<>();

    /**
     * Checks if an email already exists in the database.
     * @param email The email to check.
     * @return true if the email exists, false otherwise.
     */
    public boolean emailExists(String email) {
        // Simulating a delay for a realistic database lookup
        try {
            Thread.sleep(100); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return USERS_DB.containsKey(email);
    }

    /**
     * Registers a new user.
     * @param email The user's email.
     * @param password The user's password.
     * @return true on successful registration, false if email already exists.
     */
    public boolean registerUser(String email, String password) {
        if (emailExists(email)) {
            return false;
        }
        USERS_DB.put(email, password);
        // In a real app, all other profile fields (Name, DOB, etc.) would be saved here.
        System.out.println("Registered User: " + email);
        return true;
    }

    /**
     * Checks user credentials for login.
     */
    public boolean validateUser(String email, String password) {
        String storedPassword = USERS_DB.get(email);
        return storedPassword != null && storedPassword.equals(password);
    }
}
