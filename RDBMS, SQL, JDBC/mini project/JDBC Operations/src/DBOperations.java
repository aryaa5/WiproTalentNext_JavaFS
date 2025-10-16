import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBOperations {

    // --- Scenario 1: getUserType ---
    public String getUserType(String userID) {
        Connection con = DBConnection.getConnection();
        String userType = "User Not Found";
        String sql = "SELECT UserType FROM User WHERE UserID = ?";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, userID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    userType = rs.getString("UserType");
                }
            }
        } catch (SQLException e) {
            System.err.println("S1 Error: " + e.getMessage());
        } finally {
            closeConnection(con);
        }
        return userType;
    }

    // --- Scenario 2: getIncorrectAttempts ---
    public String getIncorrectAttempts(String userID) {
        Connection con = DBConnection.getConnection();
        String result = "User Not Found";
        String sql = "SELECT IncorrectAttempts FROM User WHERE UserID = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, userID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int attempts = rs.getInt("IncorrectAttempts");
                    if (attempts == 0) {
                        result = "No Incorrect Attempt";
                    } else if (attempts == 1) {
                        result = "One Time";
                    } else {
                        result = "Incorrect Attempt Exceeded";
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("S2 Error: " + e.getMessage());
        } finally {
            closeConnection(con);
        }
        return result;
    }

    // --- Scenario 3 (b): changeUserType ---
    public String changeUserType(String userID) {
        Connection con = DBConnection.getConnection();
        String result = "Update Failed";
        // Scenario 3b: Update the userType to 'Admin'.
        String sql = "UPDATE User SET UserType = 'Admin' WHERE UserID = ?";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, userID);
            int rowsUpdated = ps.executeUpdate();
            
            // If more than one row updated, return 'Update Success'.
            if (rowsUpdated >= 1) {
                result = "Update Success";
            }
        } catch (SQLException e) {
            System.err.println("S3 Error: " + e.getMessage());
        } finally {
            closeConnection(con);
        }
        return result;
    }
    
    // --- Scenario 4: getLockStatus ---
    public int getLockStatus() {
        Connection con = DBConnection.getConnection();
        int totalRows = 0;
        // Scenario 4b: Count total rows with lock status 0.
        String sql = "SELECT COUNT(*) FROM User WHERE LockStatus = 0";
        
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                totalRows = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("S4 Error: " + e.getMessage());
        } finally {
            closeConnection(con);
        }
        return totalRows;
    }

    // --- Scenario 5: changeName ---
    public String changeName(String id, String name) {
        Connection con = DBConnection.getConnection();
        String result = "Failed";
        String sql = "UPDATE User SET Name = ? WHERE UserID = ?";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, id);
            
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                result = "Success";
            }
        } catch (SQLException e) {
            System.err.println("S5 Error: " + e.getMessage());
        } finally {
            closeConnection(con);
        }
        return result;
    }

    // --- Scenario 6: changePassword ---
    public String changePassword(String id, String password) {
        Connection con = DBConnection.getConnection();
        String result = "Failed";
        // Scenario 6b: Change the password for the given ID with UserType 'Admin'.
        String sql = "UPDATE User SET Password = ? WHERE UserID = ? AND UserType = 'Admin'";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, password);
            ps.setString(2, id);
            
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                result = "Changed";
            }
        } catch (SQLException e) {
            System.err.println("S6 Error: " + e.getMessage());
        } finally {
            closeConnection(con);
        }
        return result;
    }
    
    // --- Scenario 7 & 8: addUser_1 and addUser_2 ---
    private String addUser(UserBean user, boolean lockStatusCheck) {
        Connection con = DBConnection.getConnection();
        String result = "Fail";
        // SQL for insert
        String sql = "INSERT INTO User (UserID, Password, Name, IncorrectAttempts, LockStatus, UserType) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            
            // Scenario 8: Only insert if lockStatus is 0
            if (lockStatusCheck && user.getLockStatus() != 0) {
                return "Fail (LockStatus not 0)";
            }
            
            // Set values from UserBean
            ps.setString(1, user.getID());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.setInt(4, user.getIncorrectAttempts());
            ps.setInt(5, user.getLockStatus());
            ps.setString(6, user.getUserType());

            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                result = "Success";
            }
        } catch (SQLException e) {
            System.err.println("S7/S8 Error: " + e.getMessage());
        } finally {
            closeConnection(con);
        }
        return result;
    }

    // Scenario 7: No LockStatus check
    public String addUser_1(UserBean user) {
        return addUser(user, false);
    }
    
    // Scenario 8: Insert only if lockStatus is 0
    public String addUser_2(UserBean user) {
        return addUser(user, true);
    }


    // --- Scenario 9: getUsers ---
    public List<UserBean> getUsers(String userType) {
        Connection con = DBConnection.getConnection();
        List<UserBean> userList = new ArrayList<>();
        String sql = "SELECT * FROM User WHERE UserType = ?";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, userType);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    userList.add(createUserBean(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("S9 Error: " + e.getMessage());
        } finally {
            closeConnection(con);
        }
        return userList;
    }

    // --- Scenario 10: storeAllRecords ---
    public List<UserBean> storeAllRecords() {
        Connection con = DBConnection.getConnection();
        List<UserBean> userList = new ArrayList<>();
        String sql = "SELECT * FROM User";
        
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                userList.add(createUserBean(rs));
            }
        } catch (SQLException e) {
            System.err.println("S10 Error: " + e.getMessage());
        } finally {
            closeConnection(con);
        }
        return userList;
    }

    // --- Scenario 11: getNames ---
    public String[] getNames() {
        Connection con = DBConnection.getConnection();
        List<String> nameList = new ArrayList<>();
        // Scenario 11c: Retrieve all names
        String sql = "SELECT Name FROM User";
        
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                nameList.add(rs.getString("Name"));
            }
        } catch (SQLException e) {
            System.err.println("S11 Error: " + e.getMessage());
        } finally {
            closeConnection(con);
        }
        return nameList.toArray(new String[0]);
    }
    
    // Helper method to map a ResultSet row to a UserBean
    private UserBean createUserBean(ResultSet rs) throws SQLException {
        UserBean bean = new UserBean();
        bean.setID(rs.getString("UserID"));
        bean.setPassword(rs.getString("Password"));
        bean.setName(rs.getString("Name"));
        bean.setIncorrectAttempts(rs.getInt("IncorrectAttempts"));
        bean.setLockStatus(rs.getInt("LockStatus"));
        bean.setUserType(rs.getString("UserType"));
        return bean;
    }
    
    // Helper method to close connection
    private void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println("Failed to close connection: " + e.getMessage());
            }
        }
    }

    // --- Main Method for Testing All Scenarios ---
    public static void main(String[] args) {
        DBOperations dbOps = new DBOperations();
        
        System.out.println("--- TESTING JDBC OPERATIONS ---");
        
        // Scenario 1: getUserType
        System.out.println("\nS1 (UserType AB1001): " + dbOps.getUserType("AB1001"));
        
        // Scenario 2: getIncorrectAttempts
        System.out.println("S2 (Attempts AB1001): " + dbOps.getIncorrectAttempts("AB1001"));
        
        // Scenario 3: changeUserType (Changing RS1003 to Admin)
        System.out.println("S3 (ChangeType RS1003): " + dbOps.changeUserType("RS1003"));
        // Check S1 again to verify change
        System.out.println("  Verified (S1): " + dbOps.getUserType("RS1003")); 
        
        // Scenario 4: getLockStatus
        System.out.println("S4 (LockStatus 0 Count): " + dbOps.getLockStatus()); // Expected: 3 or more

        // Scenario 5: changeName (Changing TA1002's name)
        System.out.println("S5 (ChangeName TA1002): " + dbOps.changeName("TA1002", "Tarun"));
        
        // Scenario 6: changePassword (Changing AB1001's password)
        System.out.println("S6 (ChangePwd AB1001): " + dbOps.changePassword("AB1001", "NewAdminPwd"));
        
        // Scenario 7: addUser_1 (No lock check)
        UserBean user7 = new UserBean("S7001", "S7001", "Sam", 0, 1, "Employee");
        System.out.println("S7 (AddUser_1 S7001): " + dbOps.addUser_1(user7));
        
        // Scenario 8: addUser_2 (With lock check - will fail if lockStatus is 1)
        UserBean user8 = new UserBean("S8001", "S8001", "Max", 0, 1, "Employee"); // LockStatus = 1
        System.out.println("S8 (AddUser_2 S8001): " + dbOps.addUser_2(user8)); 
        
        // Scenario 9: getUsers
        System.out.println("\nS9 (Get Employees):");
        List<UserBean> employees = dbOps.getUsers("Employee");
        employees.forEach(u -> System.out.println(" - " + u));
        
        // Scenario 10: storeAllRecords
        System.out.println("\nS10 (Store All Records):");
        List<UserBean> allUsers = dbOps.storeAllRecords();
        allUsers.forEach(u -> System.out.println(" - " + u));
        
        // Scenario 11: getNames
        System.out.println("\nS11 (Get Names):");
        String[] names = dbOps.getNames();
        System.out.print(" - Names: ");
        for (String name : names) {
            System.out.print(name + ", ");
        }
        System.out.println();
    }
}
