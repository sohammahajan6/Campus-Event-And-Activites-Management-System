package project.college.event.organizer.login;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class NewUserRegister extends JFrame {

    public NewUserRegister() {
        // Set up the frame
        setTitle("Registration FORM");
        getContentPane().setBackground(new Color(255,128,102));
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add components
        JLabel title = new JLabel("New User Register", SwingConstants.CENTER);
        title.setBounds(500,10,400,110);
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        add(title);

        // Add JLabels for name, mobile, email, etc.
        JLabel name, mobile, email, address, question_2 ,question_1, college_id, course, _class, section;

        name = new JLabel("Name :");
        name.setBounds(400,150,80,80);
        name.setFont(new Font("Calibri", Font.ITALIC, 24));
        add(name);

        mobile = new JLabel("Mobile Number :");
        mobile.setBounds(400,200,200,80);
        mobile.setFont(new Font("Calibri", Font.ITALIC, 24));
        add(mobile);

        email = new JLabel("E-Mail ID :");
        email.setBounds(400,250,120,80);
        email.setFont(new Font("Calibri", Font.ITALIC, 24));
        add(email);

        address = new JLabel("Address :");
        address.setBounds(400,300,120,80);
        address.setFont(new Font("Calibri", Font.ITALIC, 24));
        add(address);

        question_1 = new JLabel("Security Question - 1  : ");
        question_1.setBounds(400,420,200,80);
        question_1.setFont(new Font("Calibri", Font.ITALIC, 20));
        add(question_1);

        // You can directly define the array in the JComboBox constructor for Oracle SQL
        JComboBox<String> qno1 = new JComboBox<>(new String[] {
                "Please Select",
                "What is the name of your first pet ?",
                "What was the first thing you learned to cook ?",
                "What was the first film you saw in the theatre ?",
                "Where did you go the first time when you flew in a plane ?",
                "What is the last name of your favourite elementary school teacher ?"
        });
        qno1.setBounds(600,440,400,30);
        add(qno1);

        JTextField answer1 = new JTextField();
        answer1.setBounds(1040,440,300,30);
        add(answer1);

        college_id = new JLabel("College ID :");
        college_id.setBounds(400,520,120,80);
        college_id.setFont(new Font("Calibri", Font.ITALIC, 24));
        add(college_id);

        course = new JLabel("Birthday :");
        course.setBounds(400,570,200,80);
        course.setFont(new Font("Calibri", Font.ITALIC, 24));
        add(course);

        _class = new JLabel("Department :");
        _class.setBounds(400,620,150,80);
        _class.setFont(new Font("Calibri", Font.ITALIC, 24));
        add(_class);

        section = new JLabel("Section :");
        section.setBounds(400,670,150,80);
        section.setFont(new Font("Calibri", Font.ITALIC, 24));
        add(section);

        // Add JTextFields for name, mobile, email, etc.
        JTextField name1, mobile1, email1, college_id1;

        name1 = new JTextField();
        name1.setBounds(600,163,300,30);
        add(name1);

        mobile1 = new JTextField();
        mobile1.setBounds(600,218,180,30);
        add(mobile1);

        email1 = new JTextField();
        email1.setBounds(600,270,210,30);
        add(email1);

        TextArea address1 = new TextArea("",180,90,TextArea.SCROLLBARS_BOTH);
        address1.setBounds(600,327,400,90);
        add(address1);

        college_id1 = new JTextField();
        college_id1.setBounds(600,540,180,30);
        add(college_id1);

        Choice birthday_Date = new Choice();
        birthday_Date.setBounds(600,595,45,30);
        Choice birthday_Month = new Choice();
        birthday_Month.setBounds(660,595,120,30);
        Choice birthday_Year = new Choice();
        birthday_Year.setBounds(800,595,100,30);

        birthday_Date.add("DD");
        for (int i = 1; i <= 31; i++) {
            birthday_Date.add(Integer.toString(i));
        }

        birthday_Month.add("Month");
        birthday_Month.add("January");
        birthday_Month.add("February");
        birthday_Month.add("March");
        birthday_Month.add("April");
        birthday_Month.add("May");
        birthday_Month.add("June");
        birthday_Month.add("July");
        birthday_Month.add("August");
        birthday_Month.add("September");
        birthday_Month.add("October");
        birthday_Month.add("November");
        birthday_Month.add("December");

        birthday_Year.add("YYYY");
        for (int i = 1985; i <= 2008; i++) {
            birthday_Year.add(Integer.toString(i));
        }

        add(birthday_Date);
        add(birthday_Month);
        add(birthday_Year);

        String[] question_course_stream = {
                "Select your Course",
                "B.E. Computer Science and Engineering",
                "B.Tech. Artificial Intelligence and Data Science",
                "B.Tech. BioTechnology",
                "B.E. BioMedical Engineering",
                "B.E. Electronics and Communication Engineering",
                "B.E. Electrical and Electronics Engineering",
                "B.Tech. Information Technology",
                "B.E. Mechanical Engineering",
                "B.E. Civil Engineering"
        };
        JComboBox<String> department = new JComboBox<>(question_course_stream);
        department.setBounds(600,647,300,30);
        add(department);

        CheckboxGroup cbg = new CheckboxGroup();
        Checkbox ck1 = new Checkbox("A",false,cbg);
        ck1.setBounds(600,690,45,30);
        Checkbox ck2 = new Checkbox("B",false,cbg);
        ck2.setBounds(670,690,45,30);
        Checkbox ck3 = new Checkbox("C",false,cbg);
        ck3.setBounds(740,690,45,30);
        Checkbox ck4 = new Checkbox("D",false,cbg);
        ck4.setBounds(810,690,45,30);

        add(ck1); add(ck2); add(ck3); add(ck4);

        // Add JLabels for password and confirm password
        JLabel passwordLabel = new JLabel("Password :");
        passwordLabel.setBounds(400,720,120,80);
        passwordLabel.setFont(new Font("Calibri", Font.ITALIC, 24));
        add(passwordLabel);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password :");
        confirmPasswordLabel.setBounds(400,770,200,80);
        confirmPasswordLabel.setFont(new Font("Calibri", Font.ITALIC, 24));
        add(confirmPasswordLabel);

        // Add JPasswordFields for password and confirm password
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(600,740,300,30);
        add(passwordField);

        JPasswordField confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(600,790,300,30);
        add(confirmPasswordField);

        JButton submit = new JButton("Register");
        submit.setBounds(520,117,100,30);
        submit.addActionListener(e -> {
            String Name = name1.getText();
            String Mobile = mobile1.getText();
            String emailID = email1.getText();
            String Address = address1.getText();
            String Answer1 = answer1.getText();
            String CollegeID = college_id1.getText();
            String Section = cbg.getSelectedCheckbox().getLabel();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());
            int len = Mobile.length();
            String msg = "" + Name;
            String BDay = birthday_Date.getSelectedItem() + "-" + birthday_Month.getSelectedItem() + "-" + birthday_Year.getSelectedItem();
            String Department = department.getSelectedItem().toString();
            String SecurityQuestion = qno1.getSelectedItem().toString();

            msg += " \n";

            // Password validation
            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(submit, "Passwords do not match.");
                return; // Stop execution if passwords don't match
            }

            // Check the length of the Mobile Number
            if (len != 10) {
                JOptionPane.showMessageDialog(submit, "Enter a valid (10-digit) mobile number ");
                return; // Stop execution if mobile number is invalid
            }

            try {
                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "hell9");

                // Hashing the password (You should use a secure hashing algorithm)
                String hashedPassword = hashPassword(password);

                String query = "INSERT INTO students (Name, Mobile, Email, Address, SecurityQuestion, Answer1, CollegeID, BDay, Department, Section, Password) " +
                        "VALUES ('" + Name + "','" + Mobile + "','" + emailID + "','" +
                        Address + "','" + SecurityQuestion + "','" + Answer1 + "','" + CollegeID + "','" + BDay + "','" + Department + "','" + Section + "','" + hashedPassword + "')";

                Statement sta = connection.createStatement();
                int x = sta.executeUpdate(query);
                if (x == 0) {
                    JOptionPane.showMessageDialog(submit, "An account with these details already exists! Sign in instead! You will now be redirected to the Login Page...");
                    new RedirectToLogin();
                    RedirectToLogin.main();
                } else {
                    JOptionPane.showMessageDialog(submit, "Welcome, " + msg + "Your account has been successfully created! You will now be redirected to the Login Page...");
                    MainLoginPage object = new MainLoginPage();
                    object.main(null);
                }
                connection.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        add(submit);

        JButton reset = new JButton("Reset");
        reset.setBounds(720,117,100,30);
        reset.addActionListener(e -> {
            name1.setText("");
            mobile1.setText("");
            email1.setText("");
            address1.setText("");
            qno1.setSelectedItem("Please Select");
            college_id1.setText("");
            department.setSelectedItem("Select your Course");
        });
        add(reset);

        setSize(1600,800);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Hashing the password (You should use a secure hashing algorithm)
    private String hashPassword(String password) {
        // Implement your password hashing algorithm here (e.g., using BCrypt)
        return password;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(NewUserRegister::new);
    }
}
