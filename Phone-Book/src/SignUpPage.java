import DB.Registration;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignUpPage extends JFrame implements ActionListener {
    private JLabel titleLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel confirmPasswordLabel;
    private JLabel fullNameLabel; // New label for full name
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JTextField fullNameField; // New text field for full name
    private JButton signUpButton;
    private JButton loginButton;

    public SignUpPage() {
        // Set up the frame
        setTitle("Sign Up");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        // Create the components
        titleLabel = new JLabel("Sign Up");
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 121, 107));
        fullNameLabel = new JLabel("Full Name"); // New label for full name
        fullNameLabel.setFont(new Font("Roboto", Font.PLAIN, 16));
        fullNameField = new JTextField(20); // New text field for full name
        fullNameField.setFont(new Font("Roboto", Font.PLAIN, 16));
        fullNameField.setBorder(BorderFactory.createLineBorder(new Color(0, 121, 107), 2, true));
        fullNameField.setHorizontalAlignment(JTextField.CENTER); // Center the contents of the text field horizontally
        usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Roboto", Font.PLAIN, 16));
        usernameField = new JTextField(20);
        usernameField.setFont(new Font("Roboto", Font.PLAIN, 16));
        usernameField.setBorder(BorderFactory.createLineBorder(new Color(0, 121, 107), 2, true));
        usernameField.setHorizontalAlignment(JTextField.CENTER); // Center the contents of the text field horizontally
        passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Roboto", Font.PLAIN, 16));
        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Roboto", Font.PLAIN, 16));
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(0, 121, 107), 2, true));
        passwordField.setHorizontalAlignment(JTextField.CENTER); // Center the contents of the text field horizontally
        confirmPasswordLabel = new JLabel("Confirm Password");
        confirmPasswordLabel.setFont(new Font("Roboto", Font.PLAIN, 16));
        confirmPasswordField = new JPasswordField(20);
        confirmPasswordField.setFont(new Font("Roboto", Font.PLAIN, 16));
        confirmPasswordField.setBorder(BorderFactory.createLineBorder(new Color(0, 121, 107), 2, true));
        confirmPasswordField.setHorizontalAlignment(JTextField.CENTER); // Center the contents of the text field horizontally
        signUpButton = new JButton("Sign Up");
        signUpButton.setFont(new Font("Roboto", Font.BOLD, 16));
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setBackground(new Color(0, 121, 107));
        signUpButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        signUpButton.addActionListener(this);
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Roboto", Font.BOLD, 16));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(new Color(0, 121, 107));
        loginButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        loginButton.addActionListener(this);
        JLabel loginLabel = new JLabel("Already have an account?"); // Change label text
        loginLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        loginLabel.setForeground(new Color(0, 121, 107));
        loginLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Set up the layout
        // Set up the layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 0, 20, 0);
        add(titleLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 20, 10, 20);
        gbc.anchor = GridBagConstraints.WEST;
        add(fullNameLabel, gbc); // Add the full name label
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 10, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make the text field fill the available horizontal space
        fullNameField.setHorizontalAlignment(JTextField.CENTER); // Center the contents of the text field horizontally
        add(fullNameField, gbc); // Add the full name text field
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 20, 10, 20);
        add(usernameLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 10, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make the text field fill the available horizontal space
        usernameField.setHorizontalAlignment(JTextField.CENTER); // Center the contents of the text field horizontally
        add(usernameField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 20, 10, 20);
        add(passwordLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 10, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make the text field fill the available horizontal space
        passwordField.setHorizontalAlignment(JTextField.CENTER); // Center the contents of the text field horizontally
        add(passwordField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(0, 20, 10, 20);
        add(confirmPasswordLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.insets = new Insets(0, 0, 10, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make the text field fill the available horizontal space
        confirmPasswordField.setHorizontalAlignment(JTextField.CENTER); // Center the contents of the text field horizontally
        add(confirmPasswordField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 20, 20, 20);
        add(signUpButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 20, 20, 10);
        add(loginButton, gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.insets = new Insets(0, 0, 20, 20);
        add(loginLabel, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signUpButton) {
            // Handle sign up button click
            String fullName = fullNameField.getText();
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());
            if (fullName.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(SignUpPage.this, "Please fill in all fields.");
            } else if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(SignUpPage.this, "Passwords do not match.");
            } else if (Registration.register(fullName, username, password)) {
                JOptionPane.showMessageDialog(SignUpPage.this, "Registration successful!");
                dispose(); // Close the sign up page
                new HomePage(); // Open the home page
            } else {
                JOptionPane.showMessageDialog(SignUpPage.this, "Registration failed.");
            }
        } else if (e.getSource() == loginButton) {
            // Handle login button click
            LoginPage loginPage = new LoginPage();
            loginPage.setVisible(true);
            dispose();
        }
    }
    public static void main(String[] args) {
        SignUpPage signUpPage = new SignUpPage();
        signUpPage.setVisible(true);
    }
}