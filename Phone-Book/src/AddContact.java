import Session.UserSession;
import DB.Import;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class AddContact extends JPanel {
    private JTextField fullNameField;
    private JTextField emailField;
    private JTextField addressField;
    private JTextField phoneField;
    private JTextField birthdayField;

    public AddContact() {
        // Set up the panel
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Set up the form
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 20);
        JLabel fullNameLabel = new JLabel("Full Name:");
        fullNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        formPanel.add(fullNameLabel, gbc);
        gbc.gridx++;
        fullNameField = new JTextField(20);
        fullNameField.setFont(new Font("Arial", Font.PLAIN, 16));
        formPanel.add(fullNameField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel emailLabel = new JLabel("Email-address:");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 16));
        formPanel.add(emailLabel, gbc);
        gbc.gridx++;
        emailField = new JTextField(20);
        emailField.setFont(new Font("Arial", Font.PLAIN, 16));
        formPanel.add(emailField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setFont(new Font("Arial", Font.BOLD, 16));
        formPanel.add(addressLabel, gbc);
        gbc.gridx++;
        addressField = new JTextField(20);
        addressField.setFont(new Font("Arial", Font.PLAIN, 16));
        formPanel.add(addressField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel phoneLabel = new JLabel("Phone no:");
        phoneLabel.setFont(new Font("Arial", Font.BOLD, 16));
        formPanel.add(phoneLabel, gbc);
        gbc.gridx++;
        phoneField = new JTextField(20);
        phoneField.setFont(new Font("Arial", Font.PLAIN, 16));
        formPanel.add(phoneField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel birthdateLabel = new JLabel("Birthdate(yyyy-mm-dd):");
        birthdateLabel.setFont(new Font("Arial", Font.BOLD, 16));
        formPanel.add(birthdateLabel, gbc);
        gbc.gridx++;
        birthdayField = new JTextField(20);
        birthdayField.setFont(new Font("Arial", Font.PLAIN, 16));
        formPanel.add(birthdayField, gbc);
        add(formPanel, BorderLayout.CENTER);

        // Set up the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.WHITE);
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Arial", Font.BOLD, 16));
        cancelButton.addActionListener(e -> {
            // Navigate back to the home page
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.getContentPane().removeAll();
            frame.getContentPane().add(new HomePage());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
        buttonPanel.add(cancelButton);
        JButton saveButton = new JButton("Save");
        saveButton.setFont(new Font("Arial", Font.BOLD, 16));
        saveButton.addActionListener(e -> {
            String fullName = fullNameField.getText();
            String email = emailField.getText();
            String address = addressField.getText();
            String phone = phoneField.getText();
            String birthday = birthdayField.getText();

            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/AP", "root", "")) {
                String sql = "INSERT INTO Contact (user_id,full_name, email, address, phone_number, BIrthdate) VALUES ( ?, ?, ?, ?,?,?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                String userId = UserSession.getUserId();
                stmt.setString(1, userId);
                stmt.setString(2, fullName);
                stmt.setString(3, email);
                stmt.setString(4, address);
                stmt.setString(5, phone);
                stmt.setString(6, birthday);
                if (!Import.contactExists(fullName,email,address,phone)) {
                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Contact saved successfully!");
                }
                else {
                    JOptionPane.showMessageDialog(this, "Contact already Exists!");
                }
                // Navigate back to the home page
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new HomePage());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            // TODO: Add the new contact to the table
        });
        buttonPanel.add(saveButton);
        add(buttonPanel, BorderLayout.PAGE_END);
    }
}