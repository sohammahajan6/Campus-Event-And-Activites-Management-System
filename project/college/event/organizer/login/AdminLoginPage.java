package project.college.event.organizer.login;
import project.college.event.organizer.display.AdminDashBoard;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminLoginPage extends JFrame {

    private JTextField textField;
    private JPasswordField passwordField;
    private JButton btnNewButton;
    private JButton btnNewAdmin;
    private JButton btnForgetPassword; // Declare the button
    private JPanel contentPane;

    public AdminLoginPage() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 600);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(26, 238, 118));

        JLabel lblNewLabel = new JLabel("Admin Login");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Segoe Print", Font.BOLD, 32));
        lblNewLabel.setBounds(423, 13, 500, 93);
        lblNewLabel.setForeground(new Color(148, 0, 211));
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setFont(new Font("Segoe Print", Font.BOLD, 20));
        textField.setBounds(481, 170, 281, 40);
        contentPane.add(textField);
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Segoe Print", Font.BOLD, 20));
        passwordField.setBounds(481, 286, 281, 40);
        contentPane.add(passwordField);

        JLabel lblUsername = new JLabel("Email ID :");
        lblUsername.setForeground(Color.BLACK);
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lblUsername.setBounds(250, 166, 193, 52);
        lblUsername.setForeground(new Color(178, 34, 34));
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password :");
        lblPassword.setForeground(Color.BLACK);
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lblPassword.setBounds(250, 286, 193, 52);
        lblPassword.setForeground(new Color(178, 34, 34));
        contentPane.add(lblPassword);

        btnNewButton = new JButton("Login");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnNewButton.setBounds(545, 392, 162, 73);
        btnNewButton.setForeground(new Color(174, 34, 34));
        btnNewButton.setBackground(new Color(0, 191, 255));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userName = textField.getText();
                String password = String.valueOf(passwordField.getPassword());
                try {
                    Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "hell9");

                    PreparedStatement st = connection
                            .prepareStatement("Select Email, Password from admin_table where Email=? and Password=?");

                    st.setString(1, userName);
                    st.setString(2, password);
                    ResultSet rs = st.executeQuery();
                    if (rs.next()) {
                        dispose();
                        AdminDashBoard adminDashBoard = new AdminDashBoard();
                        adminDashBoard.setVisible(true);
                        JOptionPane.showMessageDialog(btnNewButton, "You have successfully logged in");
                    } else if (userName.trim().equals("") || password.trim().equals("")) {
                        JOptionPane.showMessageDialog(null, "One Or More Fields Are Empty", "Empty Fields", 2);
                    } else {
                        JOptionPane.showMessageDialog(btnNewButton, "Wrong Admin Credentials !");
                    }
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        });
        contentPane.add(btnNewButton);

        btnNewAdmin = new JButton("New Admin Account");
        btnNewAdmin.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnNewAdmin.setBounds(100, 392, 250, 73);
        btnNewAdmin.setForeground(new Color(174, 34, 34));
        btnNewAdmin.setBackground(new Color(0, 191, 255));
        btnNewAdmin.addActionListener(e -> {
            // Open the NewAdminRegister frame
            NewAdminRegister newAdminRegister = new NewAdminRegister();
            newAdminRegister.setVisible(true);
        });
        contentPane.add(btnNewAdmin);

        btnForgetPassword = new JButton("Forget Password");
        btnForgetPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnForgetPassword.setBounds(750, 392, 250, 73);
        btnForgetPassword.setForeground(new Color(174, 34, 34));
        btnForgetPassword.setBackground(new Color(0, 191, 255));
        btnForgetPassword.addActionListener(e -> {
            // Open the ForgetPasswordAdmin frame
            ForgetPasswordAdmin forgetPasswordAdmin = new ForgetPasswordAdmin();
            forgetPasswordAdmin.setVisible(true);
        });
        contentPane.add(btnForgetPassword);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                AdminLoginPage frame = new AdminLoginPage();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
