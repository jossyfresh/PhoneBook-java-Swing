import DB.DatabaseConnection;
import Session.UserSession;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewContacts extends JPanel {
    private static JTable table;

    public ViewContacts() {
        // Set up the panel
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Set up the table
        String[] columnNames = {"Full Name", "Email-address", "Address", "Phone no", "Actions"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4;
            }
        };
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT full_name, email, address, phone_number FROM Contact WHERE user_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, UserSession.getUserId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String fullName = rs.getString("full_name");
                String emailAddress = rs.getString("email");
                String address = rs.getString("address");
                String phoneNo = rs.getString("phone_number");
                Object[] row = {fullName, emailAddress, address, phoneNo, null};
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        table = new JTable(model);
        table.setRowHeight(40);
        table.setFillsViewportHeight(true);
        table.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JCheckBox()));
        table.setDefaultRenderer(Object.class, new StyledTableCellRenderer());
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Add mouse listener to delete rows
    }

    private static class ButtonRenderer extends JPanel implements TableCellRenderer {
        private JButton editButton;
        private JButton deleteButton;

        public ButtonRenderer() {
            setOpaque(true);
            setLayout(new FlowLayout(FlowLayout.CENTER, 5, 2));
            editButton = new JButton("Edit");
            editButton.setFont(new Font("Arial", Font.PLAIN, 12));
            add(editButton);
            deleteButton = new JButton("Delete");
            deleteButton.setFont(new Font("Arial", Font.PLAIN, 12));
            add(deleteButton);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

    public class ButtonEditor extends DefaultCellEditor {
        protected JPanel panel;
        protected JButton editButton;
        protected JButton deleteButton;

        private String label;

        private boolean isPushed;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            panel = new JPanel();
            panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 2));
            editButton = new JButton("Edit");
            editButton.setFont(new Font("Arial", Font.PLAIN, 12));
            editButton.addActionListener(e -> fireEditingStopped());
            panel.add(editButton);
            deleteButton = new JButton("Delete");
            deleteButton.setFont(new Font("Arial", Font.PLAIN, 12));
            deleteButton.addActionListener(e -> {
                int row = Integer.parseInt(e.getActionCommand());
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                String fullName = (String) model.getValueAt(row, 0);
                String phoneNo = (String) model.getValueAt(row, 3);
                try (Connection conn = DatabaseConnection.getConnection()) {
                    String sql = "DELETE FROM Contact WHERE full_name = ? AND phone_number = ?";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, fullName);
                    stmt.setString(2, phoneNo);
                    stmt.executeUpdate();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                model.removeRow(row);
                fireEditingCanceled();
            });
            panel.add(deleteButton);
        }

        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            label = (value == null) ? "" : value.toString();
            isPushed = true;
            editButton.setActionCommand(String.valueOf(row));
            deleteButton.setActionCommand(String.valueOf(row));
            return panel;
        }

        public Object getCellEditorValue() {
            if (isPushed) {
                // Perform edit action
                int row = Integer.parseInt(editButton.getActionCommand());
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                String fullName = (String) model.getValueAt(row, 0);
                String emailAddress = (String) model.getValueAt(row, 1);
                String address = (String) model.getValueAt(row, 2);
                String phoneNo = (String) model.getValueAt(row, 3);

                // Create a popup with a form to update the row values
                JTextField fullNameField = new JTextField(fullName);
                JTextField emailAddressField = new JTextField(emailAddress);
                JTextField addressField = new JTextField(address);
                JTextField phoneNoField = new JTextField(phoneNo);

                JPanel formPanel = new JPanel(new GridLayout(0, 1));
                formPanel.add(new JLabel("Full Name:"));
                formPanel.add(fullNameField);
                formPanel.add(new JLabel("Email Address:"));
                formPanel.add(emailAddressField);
                formPanel.add(new JLabel("Address:"));
                formPanel.add(addressField);
                formPanel.add(new JLabel("Phone No:"));
                formPanel.add(phoneNoField);

                int result = JOptionPane.showConfirmDialog(null, formPanel, "Edit Contact", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    String updatedFullName = fullNameField.getText();
                    String updatedEmailAddress = emailAddressField.getText();
                    String updatedAddress = addressField.getText();
                    String updatedPhoneNo = phoneNoField.getText();

                    // Update the row values in the table
                    model.setValueAt(updatedFullName, row, 0);
                    model.setValueAt(updatedEmailAddress, row, 1);
                    model.setValueAt(updatedAddress, row, 2);
                    model.setValueAt(updatedPhoneNo, row, 3);

                    // Update the row values in the database
                    try (Connection conn = DatabaseConnection.getConnection()) {
                        String sql = "UPDATE Contact SET full_name = ?, email = ?, address = ?, phone_number = ? WHERE full_name = ? AND phone_number = ?";
                        PreparedStatement stmt = conn.prepareStatement(sql);
                        stmt.setString(1, updatedFullName);
                        stmt.setString(2, updatedEmailAddress);
                        stmt.setString(3, updatedAddress);
                        stmt.setString(4, updatedPhoneNo);
                        stmt.setString(5, fullName);
                        stmt.setString(6, phoneNo);
                        stmt.executeUpdate();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            isPushed = false;
            return label;
        }
    }
    private static class StyledTableCellRenderer extends DefaultTableCellRenderer {
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(240, 240, 240));
            c.setForeground(Color.BLACK);
            c.setFont(new Font("Arial", Font.PLAIN, 14));
            return c;
        }
    }
}