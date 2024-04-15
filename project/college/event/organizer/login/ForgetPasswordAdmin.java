package project.college.event.organizer.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ForgetPasswordAdmin extends JFrame {
    private JTextField tfAdminName;
    private JTextField tfAnswer;
    private JPasswordField pfPassword;
    private JButton saveButton;
    private JButton resetButton;
    private JButton searchButton;
    private JPanel root;
    private JComboBox<String> cbSecurityQuestion;

    final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    final String USERNAME = "system";
    final String PASSWORD = "hell9";

    public ForgetPasswordAdmin() {
        searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String adminName = tfAdminName.getText();
                try {
                    Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                    PreparedStatement pst;

                    pst = conn.prepareStatement("select SECURITYQUESTION from admin_table where ADMIN_NAME = ?");
                    pst.setString(1, adminName);
                    ResultSet rs = pst.executeQuery();

                    if (rs.next()) {
                        String securityQuestion = rs.getString("SECURITYQUESTION");
                        cbSecurityQuestion.setSelectedItem(securityQuestion);
                    } else if (adminName.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Admin Name Field is Empty! ", "Empty Fields", JOptionPane.WARNING_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Admin name doesn't match with our records ! ", " Retry Again", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String adminName = tfAdminName.getText();
                String newPassword = String.valueOf(pfPassword.getPassword());
                if (newPassword.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Password Field is Empty ! ", "Empty Fields", JOptionPane.WARNING_MESSAGE);
                } else {
                    try {
                        Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                        PreparedStatement pst;
                        pst = conn.prepareStatement("update admin_table set PASSWORD = ? where ADMIN_NAME = ?");

                        pst.setString(1, newPassword);
                        pst.setString(2, adminName);
                        int rowsAffected = pst.executeUpdate();
                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(null, "Password Reset Successfully !!");
                            dispose();
                            MainLoginPage ob = new MainLoginPage();
                            ob.main(null);
                        } else {
                            JOptionPane.showMessageDialog(null, "Admin not found. Please enter a valid admin name.", "Admin Not Found", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });
        resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfAdminName.setText("");
                tfAnswer.setText("");
                pfPassword.setText("");
            }
        });

        tfAdminName = new JTextField();
        tfAnswer = new JTextField();
        pfPassword = new JPasswordField();
        cbSecurityQuestion = new JComboBox<>();
        cbSecurityQuestion.addItem("What is your pet's name?");
        cbSecurityQuestion.addItem("What is your favorite food?");
        cbSecurityQuestion.addItem("What is the name of your first school?");
        cbSecurityQuestion.addItem("What is your mother's maiden name?");

        // Initialize JPanel and add components
        root = new JPanel();
        root.setLayout(new GridLayout(5, 2));
        root.add(new JLabel("Admin Name:"));
        root.add(tfAdminName);
        root.add(new JLabel("Security Question:"));
        root.add(cbSecurityQuestion);
        root.add(new JLabel("Security Answer:"));
        root.add(tfAnswer);
        root.add(new JLabel("New Password:"));
        root.add(pfPassword);
        root.add(saveButton);
        root.add(searchButton);
        root.add(resetButton);
    }

    private JPanel getRootPanel() {
        return root;
    }

    public static void main(String[] args) {
        ForgetPasswordAdmin ui = new ForgetPasswordAdmin();
        JPanel root = ui.getRootPanel();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(root);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
