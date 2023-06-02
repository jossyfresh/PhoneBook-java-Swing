package DB;

import Session.UserSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginValidation {
    public static boolean validate(String username, String password) {
        boolean isValid = false;
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM account WHERE username  = ? AND password  = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                isValid = true;
                UserSession userSession = new UserSession(rs.getString("id"), rs.getString("full_name"), rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isValid;
    }
}