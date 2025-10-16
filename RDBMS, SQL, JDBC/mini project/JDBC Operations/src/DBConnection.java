import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    
    // !!! CHANGE THESE TO YOUR ACTUAL DB CREDENTIALS !!!
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe"; // Example for Oracle
    private static final String USER = "system";
    private static final String PASS = "password";

    // You may need to load the driver class for older JDBC versions
    /*
    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver"); // Example for Oracle
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    */

    /**
     * Obtains and returns the Connection Object. (Required by all scenarios)
     * @return Connection object or null if connection fails.
     */
    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            System.err.println("Database Connection Failed: " + e.getMessage());
            e.printStackTrace();
        }
        return con;
    }
}
