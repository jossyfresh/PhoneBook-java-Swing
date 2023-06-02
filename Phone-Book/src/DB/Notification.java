package DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import Session.UserSession;

public class Notification {
    public List<Contact> getContactsByBirthdate() throws SQLException {
    List<Contact> contacts = new ArrayList<>();
    try (Connection conn = DatabaseConnection.getConnection()) {
        String sql = "SELECT * FROM Contact WHERE user_id = ? AND DAY(birthdate) = ? AND MONTH(birthdate) = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, UserSession.getUserId());
        stmt.setInt(2, LocalDate.now().getDayOfMonth());
        stmt.setInt(3, LocalDate.now().getMonthValue());
        ResultSet resultSet = stmt.executeQuery();
        while (resultSet.next()) {
            Contact contact = new Contact();
            contact.setName(resultSet.getString("full_name"));
            contact.setPhone(resultSet.getString("phone_number"));
            contact.setEmail(resultSet.getString("email"));
            contact.setDate(resultSet.getDate("birthdate").toLocalDate());
            contacts.add(contact);
        }
    }
    return contacts;
}
}
