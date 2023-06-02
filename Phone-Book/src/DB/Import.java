package DB;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DB.DatabaseConnection;
import Session.UserSession;

public class Import {
    public void importContactsFromFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] contactData = line.split(",");
                String name = contactData[0];
                String email = contactData[1];
                String address = contactData[2];
                String phone = contactData[3];

                if (!contactExists(name,email,address,phone)) {
                    insertContactIntoDatabase(name, email, address, phone);
                }
            }

            System.out.println("Contacts imported successfully!");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean contactExists(String name, String email, String address, String phone) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT COUNT(*) FROM Contact WHERE user_id = ? AND full_name = ? and email = ? And address = ? And phone_number = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, UserSession.getUserId());
            stmt.setString(2, name);
            stmt.setString(3, email);
            stmt.setString(4, address);
            stmt.setString(5, phone);

            ResultSet resultSet = stmt.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            return count > 0;
        }
    }

    private void insertContactIntoDatabase(String name, String email, String address, String phone) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO Contact (user_id, full_name, email, address, phone_number) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, UserSession.getUserId());
            stmt.setString(2, name);
            stmt.setString(3, email);
            stmt.setString(4, address);
            stmt.setString(5, phone);
            stmt.executeUpdate();
        }
    }
}