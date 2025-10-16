package com.app.dao;

import com.app.model.User;
import java.util.HashMap;
import java.util.Map;

public class UserDao {
    // Simulated Database: username -> User object
    private static final Map<String, User> USERS_DB = new HashMap<>();

    // Add a default user for testing login
    static {
        User defaultUser = new User();
        defaultUser.setUsername("testuser");
        defaultUser.setPassword("123456");
        defaultUser.setEmployeeNumber("A0001");
        defaultUser.setEmailAddress("test@example.com");
        USERS_DB.put("testuser", defaultUser);
    }

    /**
     * Checks if a username exists during registration.
     */
    public boolean userExists(String username) {
        return USERS_DB.containsKey(username);
    }

    /**
     * Stores a new registered user.
     */
    public boolean saveUser(User user) {
        if (userExists(user.getUsername())) {
            return false;
        }
        USERS_DB.put(user.getUsername(), user);
        return true;
    }

    /**
     * Validates login credentials.
     * Returns true if username/password combination is correct.
     */
    public boolean validateUser(String username, String password) {
        User user = USERS_DB.get(username);
        // Reason 3: Combination wrong
        return user != null && user.getPassword().equals(password);
    }
}
