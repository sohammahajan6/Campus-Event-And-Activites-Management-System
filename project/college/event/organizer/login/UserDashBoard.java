package project.college.event.organizer.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserDashBoard extends JFrame {

    private JPanel contentPane;
    private String userSession;

    public UserDashBoard(String userSession) {
        this.userSession = userSession;
        initialize();
    }

    private void initialize() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(221, 160, 221));

        JButton btnRegisterEvents = new JButton("Register Events");
        btnRegisterEvents.setForeground(Color.BLACK);
        btnRegisterEvents.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnRegisterEvents.setBackground(new Color(255, 127, 80));
        btnRegisterEvents.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                EventsRegister eventsRegister = new EventsRegister();
                eventsRegister.main(null);
            }
        });
        btnRegisterEvents.setBounds(400, 150, 200, 30);
        contentPane.add(btnRegisterEvents);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UserDashBoard frame = new UserDashBoard("User123"); // Passing a sample user session
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
