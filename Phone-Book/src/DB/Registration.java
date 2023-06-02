package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class Registration {
    public static boolean register(String fullName, String username, String password) {
        boolean isRegistered = false;
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO account (id, full_name, username, password) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            UUID uuid = UUID.randomUUID();
            stmt.setString(1, uuid.toString());
            stmt.setString(2, fullName);
            stmt.setString(3, username);
            stmt.setString(4, password);
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                isRegistered = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isRegistered;
    }
}