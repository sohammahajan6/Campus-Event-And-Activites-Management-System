package project.college.event.organizer.login;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NewAdminRegister extends JFrame {

    private JTextField tfName;
    private JTextField tfAge;
    private JTextField tfDepartment;
    private JTextField tfPhoneNumber;
    private JTextField tfEmail;
    private JComboBox<String> cbSubject;
    private JComboBox<String> cbDivision;
    private JPasswordField pfPassword;
    private JComboBox<String> cbSecurityQuestion;
    private JTextField tfSecurityAnswer;
    private JButton btnRegister;
    private JButton btnReset;

    // Define security questions
    private String[] securityQuestions = {
            "What is your mother's maiden name?",
            "What is the name of your first pet?",
            "What is your favorite movie?",
            "In what city were you born?",
            "What is your favorite color?"
    };

    public NewAdminRegister() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 750);
        setResizable(false);
        setTitle("New Admin Registration");

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("New Admin Registration");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblNewLabel.setBounds(250, 30, 300, 40);
        contentPane.add(lblNewLabel);

        JLabel lblName = new JLabel("Name:");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblName.setBounds(100, 100, 100, 30);
        contentPane.add(lblName);

        tfName = new JTextField();
        tfName.setBounds(220, 100, 200, 30);
        contentPane.add(tfName);
        tfName.setColumns(10);

        JLabel lblAge = new JLabel("Age:");
        lblAge.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblAge.setBounds(100, 150, 100, 30);
        contentPane.add(lblAge);

        tfAge = new JTextField();
        tfAge.setBounds(220, 150, 200, 30);
        contentPane.add(tfAge);
        tfAge.setColumns(10);

        JLabel lblDepartment = new JLabel("Department:");
        lblDepartment.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblDepartment.setBounds(100, 200, 100, 30);
        contentPane.add(lblDepartment);

        tfDepartment = new JTextField();
        tfDepartment.setBounds(220, 200, 200, 30);
        contentPane.add(tfDepartment);
        tfDepartment.setColumns(10);

        JLabel lblPhoneNumber = new JLabel("Phone Number:");
        lblPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblPhoneNumber.setBounds(100, 250, 120, 30);
        contentPane.add(lblPhoneNumber);

        tfPhoneNumber = new JTextField();
        tfPhoneNumber.setBounds(220, 250, 200, 30);
        contentPane.add(tfPhoneNumber);
        tfPhoneNumber.setColumns(10);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblEmail.setBounds(100, 300, 100, 30);
        contentPane.add(lblEmail);

        tfEmail = new JTextField();
        tfEmail.setBounds(220, 300, 200, 30);
        contentPane.add(tfEmail);
        tfEmail.setColumns(10);

        JLabel lblSubject = new JLabel("Subject:");
        lblSubject.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblSubject.setBounds(100, 350, 100, 30);
        contentPane.add(lblSubject);

        cbSubject = new JComboBox<>();
        cbSubject.setModel(new DefaultComboBoxModel<>(new String[] {"DBMS", "SE", "MA", "CN"}));
        cbSubject.setBounds(220, 350, 200, 30);
        contentPane.add(cbSubject);

        JLabel lblDivision = new JLabel("Division:");
        lblDivision.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblDivision.setBounds(100, 400, 100, 30);
        contentPane.add(lblDivision);

        cbDivision = new JComboBox<>();
        cbDivision.setModel(new DefaultComboBoxModel<>(new String[] {"A", "B", "C", "D"}));
        cbDivision.setBounds(220, 400, 200, 30);
        contentPane.add(cbDivision);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblPassword.setBounds(100, 450, 100, 30);
        contentPane.add(lblPassword);

        pfPassword = new JPasswordField();
        pfPassword.setBounds(220, 450, 200, 30);
        contentPane.add(pfPassword);

        JLabel lblSecurityQuestion = new JLabel("Security Question:");
        lblSecurityQuestion.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblSecurityQuestion.setBounds(100, 500, 150, 30);
        contentPane.add(lblSecurityQuestion);

        cbSecurityQuestion = new JComboBox<>();
        cbSecurityQuestion.setModel(new DefaultComboBoxModel<>(securityQuestions));
        cbSecurityQuestion.setBounds(260, 500, 300, 30);
        contentPane.add(cbSecurityQuestion);

        JLabel lblSecurityAnswer = new JLabel("Security Answer:");
        lblSecurityAnswer.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblSecurityAnswer.setBounds(100, 550, 150, 30);
        contentPane.add(lblSecurityAnswer);

        tfSecurityAnswer = new JTextField();
        tfSecurityAnswer.setBounds(260, 550, 300, 30);
        contentPane.add(tfSecurityAnswer);

        btnRegister = new JButton("Register");
        btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnRegister.setBounds(150, 600, 120, 40);
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registerAdmin();
            }
        });
        contentPane.add(btnRegister);

        btnReset = new JButton("Reset");
        btnReset.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnReset.setBounds(350, 600, 120, 40);
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
        contentPane.add(btnReset);
    }

    private void registerAdmin() {
        String name = tfName.getText();
        int age = Integer.parseInt(tfAge.getText());
        String department = tfDepartment.getText();
        String phoneNumber = tfPhoneNumber.getText();
        String email = tfEmail.getText();
        String subject = (String) cbSubject.getSelectedItem();
        String division = (String) cbDivision.getSelectedItem();
        String password = new String(pfPassword.getPassword());
        String securityQuestion = (String) cbSecurityQuestion.getSelectedItem();
        String securityAnswer = tfSecurityAnswer.getText();

        try {
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "hell9");
            PreparedStatement statement = connection.prepareStatement("INSERT INTO admin_table (name, age, department, phone_number, email, subject, division, password, security_question, security_answer) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, name);
            statement.setInt(2, age);
            statement.setString(3, department);
            statement.setString(4, phoneNumber);
            statement.setString(5, email);
            statement.setString(6, subject);
            statement.setString(7, division);
            statement.setString(8, password);
            statement.setString(9, securityQuestion);
            statement.setString(10, securityAnswer);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Admin registered successfully!");
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to register admin!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        tfName.setText("");
        tfAge.setText("");
        tfDepartment.setText("");
        tfPhoneNumber.setText("");
        tfEmail.setText("");
        cbSubject.setSelectedIndex(0);
        cbDivision.setSelectedIndex(0);
        pfPassword.setText("");
        cbSecurityQuestion.setSelectedIndex(0);
        tfSecurityAnswer.setText("");
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                NewAdminRegister frame = new NewAdminRegister();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
