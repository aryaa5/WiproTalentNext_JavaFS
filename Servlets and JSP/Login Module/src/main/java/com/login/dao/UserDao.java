package com.example.login.dao;

import java.util.HashMap;
import java.util.Map;

public class UserDao {
    // Simulated Database: username -> password
    private static final Map<String, String> USERS = new HashMap<>();

    static {
        // Add a default user for testing
        USERS.put("admin", "password123");
    }

    /**
     * Checks if a username/password combination is valid.
     */
    public boolean isValidUser(String username, String password) {
        String storedPassword = USERS.get(username);
        return storedPassword != null && storedPassword.equals(password);
    }

    /**
     * Registers a new user. Returns true on success, false if username already exists.
     */
    public boolean registerUser(String username, String password) {
        if (USERS.containsKey(username)) {
            return false; // Username already taken
        }
        USERS.put(username, password);
        return true;
    }

    /**
     * Checks if a user exists.
     */
    public boolean userExists(String username) {
        return USERS.containsKey(username);
    }

    /**
     * Updates the password for an existing user.
     */
    public boolean updatePassword(String username, String newPassword) {
        if (USERS.containsKey(username)) {
            USERS.put(username, newPassword);
            return true;
        }
        return false;
    }
}
