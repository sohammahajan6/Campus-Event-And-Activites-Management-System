package project.college.event.organizer.events;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class WorkshopRegistration {
    public WorkshopRegistration() {
        // Creating Frame
        JFrame f = new JFrame();
        f.getContentPane().setBackground(new Color(95, 158, 160));
        f.setTitle("Register for Workshop");
        f.setSize(1400, 850);

        JLabel w_title = new JLabel("WORKSHOP REGISTRATION FORM", JLabel.CENTER);
        w_title.setBounds(550, 5, 400, 25);
        w_title.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        f.add(w_title);

        // Declaration of variables
        JLabel w_name, w_mode, w_student, w_age, w_dept, w_email, w_duration;
        JTextField w_student1, w_age1, w_email1;
        JButton submit, reset;
        JComboBox<String> w_name1, w_mode1, w_duration1, w_dept1;

        // Creating workshop name part
        w_name = new JLabel("Workshop Title");
        w_name.setFont(new Font("Calibri", Font.ITALIC, 22));
        w_name.setBounds(250, 55, 200, 25);

        String[] workshopNames = {"Workshop on DevOps", "Python 3D Printing", "Recent Trends in Mechanical Engineering"};
        w_name1 = new JComboBox<>(workshopNames);
        w_name1.setBounds(600, 50, 300, 30);
        f.add(w_name);
        f.add(w_name1);

        // Creating mode of Workshop part
        w_mode = new JLabel("Mode of Workshop");
        w_mode.setFont(new Font("Calibri", Font.ITALIC, 22));
        w_mode.setBounds(250, 105, 200, 25);

        String[] workshopModes = {"On-Site", "Virtual"};
        w_mode1 = new JComboBox<>(workshopModes);
        w_mode1.setBounds(600, 100, 100, 30);
        f.add(w_mode);
        f.add(w_mode1);

        // Creating student name part
        w_student = new JLabel("Student Name");
        w_student.setFont(new Font("Calibri", Font.ITALIC, 22));
        w_student.setBounds(250, 155, 200, 25);

        w_student1 = new JTextField();
        w_student1.setBounds(600, 150, 300, 25);
        f.add(w_student);
        f.add(w_student1);

        // Creating age part
        w_age = new JLabel("Age");
        w_age.setFont(new Font("Calibri", Font.ITALIC, 22));
        w_age.setBounds(250, 205, 200, 25);

        w_age1 = new JTextField();
        w_age1.setBounds(600, 200, 100, 25);
        f.add(w_age);
        f.add(w_age1);

        // Creating department part
        w_dept = new JLabel("Department");
        w_dept.setFont(new Font("Calibri", Font.ITALIC, 22));
        w_dept.setBounds(250, 255, 200, 25);

        String[] departments = {"Computer Engineering", "Mechanical Engineering", "Civil Engineering"};
        w_dept1 = new JComboBox<>(departments);
        w_dept1.setBounds(600, 250, 300, 30);
        f.add(w_dept);
        f.add(w_dept1);

        // Creating email part
        w_email = new JLabel("Email");
        w_email.setFont(new Font("Calibri", Font.ITALIC, 22));
        w_email.setBounds(250, 305, 200, 25);

        w_email1 = new JTextField();
        w_email1.setBounds(600, 300, 300, 25);
        f.add(w_email);
        f.add(w_email1);

        // Creating duration part
        w_duration = new JLabel("Duration (hours)");
        w_duration.setFont(new Font("Calibri", Font.ITALIC, 22));
        w_duration.setBounds(250, 355, 200, 25);

        String[] durations = {"1", "2", "3", "4", "5"};
        w_duration1 = new JComboBox<>(durations);
        w_duration1.setBounds(600, 350, 100, 30);
        f.add(w_duration);
        f.add(w_duration1);

        // Creating submit button
        submit = new JButton("Submit");
        submit.setBounds(500, 690, 200, 50);
        submit.addActionListener(e -> {
            String workshopTitle = w_name1.getSelectedItem().toString();
            String workshopMode = w_mode1.getSelectedItem().toString();
            String studentName = w_student1.getText();
            int age = Integer.parseInt(w_age1.getText());
            String department = w_dept1.getSelectedItem().toString();
            String email = w_email1.getText();
            int duration = Integer.parseInt(w_duration1.getSelectedItem().toString());

            try {
                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "hell9");
                PreparedStatement pst = connection.prepareStatement("INSERT INTO WorkshopRegistration (workshop_title, workshop_mode, student_name, age, department, email, duration) VALUES (?, ?, ?, ?, ?, ?, ?)");
                pst.setString(1, workshopTitle);
                pst.setString(2, workshopMode);
                pst.setString(3, studentName);
                pst.setInt(4, age);
                pst.setString(5, department);
                pst.setString(6, email);
                pst.setInt(7, duration);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(submit, "Registration successful!");
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        f.add(submit);

        // Creating reset button
        reset = new JButton("Reset");
        reset.setBounds(740, 690, 150, 50);
        reset.addActionListener(e -> {
            w_student1.setText("");
            w_age1.setText("");
            w_email1.setText("");
        });
        f.add(reset);

        // Basic frame setup
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLayout(null);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        new WorkshopRegistration();
    }
}
