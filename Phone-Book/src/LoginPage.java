import DB.LoginValidation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPage extends JFrame implements ActionListener {
    private JLabel titleLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private JLabel registerLabel;

    public LoginPage() {
        // Set up the frame
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        // Create the components
        titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 121, 107));
        usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Roboto", Font.PLAIN, 16));
        usernameField = new JTextField(20);
        usernameField.setFont(new Font("Roboto", Font.PLAIN, 16));
        usernameField.setBorder(BorderFactory.createLineBorder(new Color(0, 121, 107), 2, true));
        passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Roboto", Font.PLAIN, 16));
        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Roboto", Font.PLAIN, 16));
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(0, 121, 107), 2, true));
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Roboto", Font.BOLD, 16));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(new Color(0, 121, 107));
        loginButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        loginButton.addActionListener(this);
        registerButton = new JButton("Register");
        registerButton.setFont(new Font("Roboto", Font.BOLD, 16));
        registerButton.setForeground(Color.WHITE);
        registerButton.setBackground(new Color(0, 121, 107));
        registerButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        registerButton.addActionListener(this);
        registerLabel = new JLabel("Don't have an account?");
        registerLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        registerLabel.setForeground(new Color(0, 121, 107));

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
        add(usernameLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 20, 20, 20);
        add(usernameField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 20, 10, 20);
        add(passwordLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(0, 20, 20, 20);
        add(passwordField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 20, 20, 20);
        add(loginButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 20, 10, 20);
        add(registerButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 20, 20, 20);
        add(registerLabel, gbc);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            // Handle login button click
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (LoginValidation.validate(username, password)) {
                JOptionPane.showMessageDialog(LoginPage.this, "Login successful!");
                dispose(); // Close the login page
                new HomePage(); // Open the home page
            } else {
                JOptionPane.showMessageDialog(LoginPage.this, "Invalid username or password.");
            }
        } else if (e.getSource() == registerButton) {
            // Handle register button click
            SignUpPage signupPage = new SignUpPage();
            signupPage.setVisible(true);
            dispose();
        }
    }

    public static void main(String[] args) {
        LoginPage loginPage = new LoginPage();
        loginPage.setVisible(true);
    }
}

// Path: Phone-Book/src/SignUpPage.java
