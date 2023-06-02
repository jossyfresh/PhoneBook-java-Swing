package Session;

public class UserSession {
    private static String userId;
    private static String fullName;
    private static String username;

    public UserSession(String userId, String fullName, String username) {
        this.userId = userId;
        this.fullName = fullName;
        this.username = username;
    }

    public static String getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUsername() {
        return username;
    }
}