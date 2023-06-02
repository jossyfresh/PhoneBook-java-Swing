package DB;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Session.UserSession;

public class Export {
    private Connection connection;

    public Export(Connection connection) {
        this.connection = connection;
    }

    public void exportContacts(File file) throws IOException {
        exportContactsToFile(file);
    }

    private void exportContactsToFile(File file) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            // Write the header row

            // Query the database for the contacts
            String sql = "SELECT full_name, phone_number, email,address FROM Contact WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, UserSession.getUserId());
            ResultSet resultSet = statement.executeQuery();

            // Write each contact to the file
            while (resultSet.next()) {
                String name = resultSet.getString("full_name");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone_number");

                String line = name + "," + "," + email + "," + address+","+ phone ;
                writer.write(line);
                writer.newLine();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}