public class UserBean {
    private String ID;
    private String password;
    private String name;
    private int incorrectAttempts;
    private int lockStatus;
    private String userType;

    // Default Constructor (required for some frameworks/JDBC operations)
    public UserBean() {}

    // Constructor for insertion (Scenario 7)
    public UserBean(String ID, String password, String name, int incorrectAttempts, int lockStatus, String userType) {
        this.ID = ID;
        this.password = password;
        this.name = name;
        this.incorrectAttempts = incorrectAttempts;
        this.lockStatus = lockStatus;
        this.userType = userType;
    }

    // Getters and Setters
    public String getID() { return ID; }
    public void setID(String ID) { this.ID = ID; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getIncorrectAttempts() { return incorrectAttempts; }
    public void setIncorrectAttempts(int incorrectAttempts) { this.incorrectAttempts = incorrectAttempts; }

    public int getLockStatus() { return lockStatus; }
    public void setLockStatus(int lockStatus) { this.lockStatus = lockStatus; }

    public String getUserType() { return userType; }
    public void setUserType(String userType) { this.userType = userType; }

    @Override
    public String toString() {
        return String.format("ID:%s, Name:%s, Type:%s, Locked:%d, Attempts:%d", 
                             ID, name, userType, lockStatus, incorrectAttempts);
    }
}
