package project.college.event.organizer.login;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class UsernameAvailability extends JFrame {

    private static final long serialVersionUID = 1L;

    public UsernameAvailability() {
        setTitle("Check Username Availability");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(230, 230, 0));
        add(panel);

        JLabel titleLabel = new JLabel("Username Availability");
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        titleLabel.setBounds(200, 10, 400, 50);
        panel.add(titleLabel);

        JLabel usernameLabel = new JLabel("Enter Username:");
        usernameLabel.setFont(new Font("Calibri", Font.BOLD, 20));
        usernameLabel.setBounds(50, 100, 200, 30);
        panel.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setFont(new Font("Calibri", Font.PLAIN, 18));
        usernameField.setBounds(250, 100, 300, 30);
        panel.add(usernameField);

        JButton checkButton = new JButton("Check Availability");
        checkButton.setFont(new Font("Calibri", Font.BOLD, 18));
        checkButton.setBounds(250, 150, 250, 40);
        checkButton.addActionListener(e -> {
            String userName = usernameField.getText();
            String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
            String PASS = "system";
            String USER = "hell9";
            try {
                Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT Email FROM userdb WHERE Email = ?");
                preparedStatement.setString(1, userName);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(null, "This Username(Email) is Already Registered with us! Please choose another one.", "Username Failed", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Username is Available! You can proceed with registration.", "Username Available", JOptionPane.INFORMATION_MESSAGE);
                }
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        panel.add(checkButton);

        setVisible(true);
    }
}
