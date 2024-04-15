package project.college.event.organizer.login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ForgetPassword extends JFrame {
    private JTextField tfUserName;
    private JTextField tfQues_Sec;
    private JTextField tfAnswer;
    private JPasswordField pfPassword;
    private JButton saveButton;
    private JButton resetButton;
    private JButton searchButton;
    private JPanel root;
    private JButton btnConfirm;

    final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    final String USERNAME = "system";
    final String PASSWORD = "hell9";

    public ForgetPassword() {
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = tfUserName.getText();
                try {
                    Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                    PreparedStatement pst;

                    pst = conn.prepareStatement("select SECURITYQUESTION from students where EMAIL = ?");
                    pst.setString(1, username);
                    ResultSet rs = pst.executeQuery();

                    if (rs.next() == true) {
                        String SecurityQuestion = rs.getString("SECURITYQUESTION");
                        tfQues_Sec.setText(SecurityQuestion);
                    }
                    else if (username.trim().equals(""))
                        JOptionPane.showMessageDialog(null, "User Name Field is Empty! ", "Empty Fields", 2);
                    else
                        JOptionPane.showMessageDialog(null, "Username doesn't match with our records ! ", " Retry Again",2);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });


        saveButton.addActionListener(e ->
        {
            String email = tfUserName.getText();
            String newPassword = String.valueOf(pfPassword.getPassword());
            if (newPassword.trim().equals(""))
                JOptionPane.showMessageDialog(null, "Password Field is Empty ! ", "Empty Fields", 2);
            else {
                try
                {
                    Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                    PreparedStatement pst;
                    pst = conn.prepareStatement("update students set PASSWORD = ? where EMAIL = ?");

                    pst.setString(1, newPassword);
                    pst.setString(2,email);
                    int rowsAffected = pst.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null,"Password Reset Successfully !!");
                        dispose();
                        MainLoginPage ob = new MainLoginPage();
                        ob.main(null);
                    } else {
                        JOptionPane.showMessageDialog(null, "User not found. Please enter a valid email address.", "User Not Found", JOptionPane.ERROR_MESSAGE);
                    }
                } catch(SQLException throwables)
                {
                    throwables.printStackTrace();
                }
            }

        });
        btnConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String answer = tfAnswer.getText();
                if (answer.trim().equals(""))
                    JOptionPane.showMessageDialog(null, "Security Answer Field is Empty ! ", "Empty Fields", 2);
                else
                {
                    try {
                        Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                        PreparedStatement preparedStatement = connection
                                .prepareStatement("Select ANSWER1 from students where EMAIL = ?");
                        preparedStatement.setString(1, tfUserName.getText());
                        ResultSet resultSet = preparedStatement.executeQuery();
                        if (resultSet.next()) {
                            String dbAnswer = resultSet.getString("ANSWER1");
                            if (answer.equals(dbAnswer)) {
                                JOptionPane.showMessageDialog(null, "You have confirmed your account identity... Enter the new Password","Authentication Successful",1);
                            } else {
                                JOptionPane.showMessageDialog(null, "You have entered Incorrect Answer.... Your Account Identity Confirmation Failed !! ", "Authentication Failed",2);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Username not found.", "User Not Found", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfUserName.setText("");
                tfAnswer.setText("");
                tfQues_Sec.setText("");
                pfPassword.setText("");
            }
        });
    }

    private JPanel getRootPanel()
    {
        return root;
    }

    public static void main(String[] args)
    {
        ForgetPassword ui = new ForgetPassword();
        JPanel root = ui.getRootPanel();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(root);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
