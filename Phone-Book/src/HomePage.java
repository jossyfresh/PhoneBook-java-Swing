import DB.*;
import Session.UserSession;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class HomePage extends JFrame {
    private JPanel contentPane;
    private JToolBar toolBar;
    private JPopupMenu menu;
    private ViewContacts viewContacts;
    private AddContact addContact;

    public HomePage() {
        // Set up the frame
        setTitle("Home Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));

        // Set up the content pane
        contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);

        // Set up the toolbar
        toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setBackground(Color.WHITE);
        contentPane.add(toolBar, BorderLayout.PAGE_START);

        // Add dropdown to the toolbar
        String[] menuItems = {"View Contacts", "Add Contact", "Import"};
        for (String item : menuItems) {
            JButton button = new JButton(item);
            button.setPreferredSize(new Dimension(120, 40));
            button.setMargin(new Insets(10, 10, 10, 10));
            button.setBackground(Color.WHITE);
            button.setForeground(Color.BLACK);
            button.setFont(new Font("Arial", Font.BOLD, 14));
            button.addActionListener(e -> {
                String selectedItem = ((JButton) e.getSource()).getText();
                if (selectedItem.equals("View Contacts")) {
                    if (viewContacts == null) {
                        String userId = UserSession.getUserId();
                        viewContacts = new ViewContacts();
                    }
                    contentPane.removeAll();
                    contentPane.add(toolBar, BorderLayout.PAGE_START);
                    contentPane.add(viewContacts, BorderLayout.CENTER);
                    contentPane.revalidate();
                    contentPane.repaint();
                } else if (selectedItem.equals("Add Contact")) {
                    if (addContact == null) {
                        addContact = new AddContact();
                    }
                    contentPane.removeAll();
                    contentPane.add(toolBar, BorderLayout.PAGE_START);
                    contentPane.add(addContact, BorderLayout.CENTER);
                    contentPane.revalidate();
                    contentPane.repaint();
                } else if (selectedItem.equals("Import")) {
                    JFileChooser fileChooser = new JFileChooser();
                    int result = fileChooser.showOpenDialog(HomePage.this);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();
                        Import importer = new Import();
                        importer.importContactsFromFile(file);
                        // navigate to home page
                        HomePage homePage = new HomePage();
                        dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(HomePage.this, "Selected item: " + selectedItem);
                }
            });
            toolBar.add(button);
        }

        // Add Export button to the toolbar
        JButton exportButton = new JButton("Export");
        exportButton.setPreferredSize(new Dimension(120, 40));
        exportButton.setMargin(new Insets(10, 10, 10, 10));
        exportButton.setBackground(Color.WHITE);
        exportButton.setForeground(Color.BLACK);
        exportButton.setFont(new Font("Arial", Font.BOLD, 14));
        exportButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            try {
                int result = fileChooser.showSaveDialog(HomePage.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    Export exporter = new Export(DatabaseConnection.getConnection());
                    exporter.exportContacts(file);
                }
            }catch (SQLException | IOException Ex){
                System.out.println(Ex);
            }
        });
        toolBar.add(exportButton);

        // Add Notifications button to the toolbar
        // Modify the notificationsButton ActionListener in the HomePage class
        // Modify the notificationsButton ActionListener in the HomePage class
        JButton notificationsButton = new JButton("Notifications");
        notificationsButton.setPreferredSize(new Dimension(120, 40));
        notificationsButton.setMargin(new Insets(10, 10, 10, 10));
        notificationsButton.setBackground(Color.WHITE);
        notificationsButton.setForeground(Color.BLACK);
        notificationsButton.setFont(new Font("Arial", Font.BOLD, 14));
        notificationsButton.addActionListener(e -> {
            try {
                Notification notification = new Notification();
                List<Contact> contacts = notification.getContactsByBirthdate();
                if (!contacts.isEmpty()) {
                    String message = "Today is their birthday!";
                    JLabel label = new JLabel(message);
                    label.setFont(new Font("Arial", Font.BOLD, 16));
                    String[] columnNames = {"Name", "Birthdate"};
                    Object[][] data = new Object[contacts.size()][2];
                    for (int i = 0; i < contacts.size(); i++) {
                        Contact contact = contacts.get(i);
                        data[i][0] = contact.getName();
                        data[i][1] = contact.getDate();
                    }
                    JTable table = new JTable(data, columnNames);
                    JOptionPane.showMessageDialog(HomePage.this, new Object[]{label, new JScrollPane(table)});
                } else {
                    JOptionPane.showMessageDialog(HomePage.this, "No birthdays today");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        toolBar.add(notificationsButton);
        toolBar.add(notificationsButton);
        toolBar.add(notificationsButton);
        toolBar.add(notificationsButton);
        toolBar.add(notificationsButton);
        // Set up the layout
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 20);
        JLabel welcomeLabel = new JLabel("Welcome to PhoneBook!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setForeground(Color.BLACK);
        panel.add(welcomeLabel, gbc);
        contentPane.add(panel, BorderLayout.CENTER);

        // Show the frame
        setVisible(true);
    }

    public static void main(String[] args) {
        // Run the application
        SwingUtilities.invokeLater(HomePage::new);
    }
}