package project.college.event.organizer.events;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class FineArtsRegistration {
    public FineArtsRegistration() {
        // Creating the Frame and then setting the Title
        JFrame f = new JFrame("FINE ARTS REGISTRATION");
        f.getContentPane().setBackground(new Color(95, 158, 160));
        JLabel title = new JLabel("FINE ARTS EVENT REGISTRATION", JLabel.CENTER);
        title.setBounds(400, 05, 600, 25);
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        f.add(title);

        // Event Type Label and Dropdown Menu
        JLabel eventType = new JLabel("Event Type");
        eventType.setBounds(250, 50, 200, 25);
        eventType.setFont(new Font("Calibri", Font.ITALIC, 22));
        f.add(eventType);

        Choice eventTypeChoice = new Choice();
        eventTypeChoice.setBounds(600, 45, 250, 25);
        eventTypeChoice.add("Solo");
        eventTypeChoice.add("Duo");
        eventTypeChoice.add("Group");
        f.add(eventTypeChoice);

        // Category Label and Dropdown Menu
        JLabel category = new JLabel("Category");
        category.setBounds(250, 90, 200, 25);
        category.setFont(new Font("Calibri", Font.ITALIC, 22));
        f.add(category);

        Choice categoryChoice = new Choice();
        categoryChoice.setBounds(600, 85, 250, 25);
        String[] categories = {"Classical Dance", "Folk dance", "Singing", "Orchestra", "Art", "Painting", "Writing", "Taboo", "Mimicry", "Rangoli", "Pattimandram", "Elocution"};
        for (String cat : categories) {
            categoryChoice.add(cat);
        }
        f.add(categoryChoice);

        // Student Name Label and TextField
        JLabel studentName = new JLabel("Student Name ");
        studentName.setBounds(250, 130, 200, 25);
        studentName.setFont(new Font("Calibri", Font.ITALIC, 22));
        f.add(studentName);

        JTextField studentNameField = new JTextField();
        studentNameField.setBounds(600, 125, 250, 25);
        f.add(studentNameField);

        // Email Label and TextField
        JLabel email = new JLabel("Email ");
        email.setBounds(250, 170, 200, 25);
        email.setFont(new Font("Calibri", Font.ITALIC, 22));
        f.add(email);

        JTextField emailField = new JTextField();
        emailField.setBounds(600, 165, 250, 25);
        f.add(emailField);

        // Age Label and TextField
        JLabel age = new JLabel("Age ");
        age.setBounds(250, 210, 200, 25);
        age.setFont(new Font("Calibri", Font.ITALIC, 22));
        f.add(age);

        JTextField ageField = new JTextField();
        ageField.setBounds(600, 205, 250, 25);
        f.add(ageField);

        // Submit Button
        JButton submit = new JButton("Submit");
        submit.setBounds(600, 250, 100, 30);
        f.add(submit);

        submit.addActionListener(e -> {
            String eventTypeValue = eventTypeChoice.getSelectedItem();
            String categoryValue = categoryChoice.getSelectedItem();
            String studentNameValue = studentNameField.getText();
            String emailValue = emailField.getText();
            String ageValue = ageField.getText();

            try {
                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "hell9");
                PreparedStatement pst = connection.prepareStatement("INSERT INTO StudentRegistration (event_type, category, student_name, email, age) VALUES (?, ?, ?, ?, ?)");
                pst.setString(1, eventTypeValue);
                pst.setString(2, categoryValue);
                pst.setString(3, studentNameValue);
                pst.setString(4, emailValue);
                pst.setString(5, ageValue);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(submit, "Registration successful!");
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Reset Button
        JButton reset = new JButton("Reset");
        reset.setBounds(750, 250, 100, 30);
        f.add(reset);

        reset.addActionListener(e -> {
            eventTypeChoice.select(0);
            categoryChoice.select(0);
            studentNameField.setText("");
            emailField.setText("");
            ageField.setText("");
        });

        // Frame Properties
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1600, 350);
        f.setLayout(null);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        new FineArtsRegistration();
    }
}
