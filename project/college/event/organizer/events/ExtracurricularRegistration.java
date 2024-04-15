package project.college.event.organizer.events;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ExtracurricularRegistration {
    public ExtracurricularRegistration() {
        JFrame f = new JFrame("SCHEDULE AN EVENT");
        f.getContentPane().setBackground(new Color(95, 158, 160));
        JLabel title = new JLabel("EXTRACURRICULAR EVENT REGISTRATION FORM", JLabel.CENTER);
        title.setBounds(300, 5, 600, 25);
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        f.add(title);

        // Labels and TextFields
        JLabel eventNameLbl, descriptionLbl, studentNameLbl, emailLbl, phoneLbl, departmentLbl, ageLbl;
        eventNameLbl = new JLabel("Event Name ");
        eventNameLbl.setBounds(50, 50, 200, 25);
        JComboBox<String> eventNameBox = new JComboBox<>(new String[]{"Life Skills", "Personality Grooming", "Learning Web Tools"});
        eventNameBox.setBounds(200, 45, 250, 25);
        f.add(eventNameLbl);
        f.add(eventNameBox);

        descriptionLbl = new JLabel("Description ");
        descriptionLbl.setBounds(50, 90, 200, 25);
        JTextField descriptionTxt = new JTextField();
        descriptionTxt.setBounds(200, 85, 600, 25);
        descriptionTxt.setEditable(false); // Description field will be non-editable
        f.add(descriptionLbl);
        f.add(descriptionTxt);

        studentNameLbl = new JLabel("Student Name ");
        studentNameLbl.setBounds(50, 130, 200, 25);
        JTextField studentNameTxt = new JTextField();
        studentNameTxt.setBounds(200, 125, 250, 25);
        f.add(studentNameLbl);
        f.add(studentNameTxt);

        emailLbl = new JLabel("Email ");
        emailLbl.setBounds(50, 170, 200, 25);
        JTextField emailTxt = new JTextField();
        emailTxt.setBounds(200, 165, 250, 25);
        f.add(emailLbl);
        f.add(emailTxt);

        phoneLbl = new JLabel("Phone No ");
        phoneLbl.setBounds(50, 210, 200, 25);
        JTextField phoneTxt = new JTextField();
        phoneTxt.setBounds(200, 205, 250, 25);
        f.add(phoneLbl);
        f.add(phoneTxt);

        departmentLbl = new JLabel("Department ");
        departmentLbl.setBounds(50, 250, 200, 25);
        JComboBox<String> departmentBox = new JComboBox<>(new String[]{"Computer Science", "Mechanical"});
        departmentBox.setBounds(200, 245, 250, 25);
        f.add(departmentLbl);
        f.add(departmentBox);

        ageLbl = new JLabel("Age ");
        ageLbl.setBounds(50, 290, 200, 25);
        JTextField ageTxt = new JTextField();
        ageTxt.setBounds(200, 285, 100, 25);
        f.add(ageLbl);
        f.add(ageTxt);

        // Action listener for event name selection
        eventNameBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedEvent = (String) eventNameBox.getSelectedItem();
                // Display description based on selected event
                switch (selectedEvent) {
                    case "Life Skills":
                        descriptionTxt.setText("Life Skills event description");
                        break;
                    case "Personality Grooming":
                        descriptionTxt.setText("Personality Grooming event description");
                        break;
                    case "Learning Web Tools":
                        descriptionTxt.setText("Learning Web Tools event description");
                        break;
                    default:
                        descriptionTxt.setText("");
                        break;
                }
            }
        });

        JButton submitBtn = new JButton("Submit");
        submitBtn.setBounds(500, 330, 100, 30);
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eventName = (String) eventNameBox.getSelectedItem();
                String description = descriptionTxt.getText();
                String studentName = studentNameTxt.getText();
                String email = emailTxt.getText();
                String phone = phoneTxt.getText();
                String department = (String) departmentBox.getSelectedItem();
                String age = ageTxt.getText();

                try {
                    Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "hell9");
                    PreparedStatement pstmt = conn.prepareStatement("INSERT INTO extracurricular (EVENT_NAME, DESCRIPTION, STUDENT_NAME, EMAIL, PHONE_NO, DEPARTMENT, AGE) VALUES (?, ?, ?, ?, ?, ?, ?)");
                    pstmt.setString(1, eventName);
                    pstmt.setString(2, description);
                    pstmt.setString(3, studentName);
                    pstmt.setString(4, email);
                    pstmt.setString(5, phone);
                    pstmt.setString(6, department);
                    pstmt.setString(7, age);

                    pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(submitBtn, "Event has been successfully registered.");
                    conn.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        });

        f.add(submitBtn);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(900, 400);
        f.setLayout(null);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        new ExtracurricularRegistration();
    }
}
