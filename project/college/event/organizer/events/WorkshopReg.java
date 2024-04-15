package project.college.event.organizer.events;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class WorkshopReg extends JFrame {
 public WorkshopReg() {
  //Creating Frame
  getContentPane().setBackground(new Color(95, 158, 160));
  setTitle("Register for Workshop");
  setSize(1400, 850);

  JLabel w_title = new JLabel("WORKSHOP REGISTRATION FORM", JLabel.CENTER);
  w_title.setBounds(550, 5, 400, 25);
  w_title.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
  add(w_title);

  //Declaration of variables
  JLabel w_name, w_description, w_conduct, w_duration, w_dept, w_student, w_year, w_start, w_end, w_eligibility, w_mode;
  JTextField w_name1, w_conduct1, w_duration1, w_student1;
  JButton schedule, reset;
  Choice start_day, start_month, start_year, end_day, end_month, end_year, w_duration2, w_year1;
  TextArea w_description1 = new TextArea();
  TextArea w_eligibility1 = new TextArea();
  JComboBox<String> w_dept1;
  CheckboxGroup cbg = new CheckboxGroup();
  Checkbox ck1 = new Checkbox("On-Site", false, cbg);
  Checkbox ck2 = new Checkbox("Virtual", false, cbg);

  //Creating name part
  w_name = new JLabel("Workshop Title");
  w_name.setFont(new Font("Calibri", Font.ITALIC, 22));
  w_name.setBounds(250, 55, 200, 25);

  w_name1 = new JTextField();
  w_name1.setBounds(600, 50, 300, 25);
  add(w_name);
  add(w_name1);

  //Creating description part
  w_description = new JLabel("Description");
  w_description.setFont(new Font("Calibri", Font.ITALIC, 22));
  w_description.setBounds(250, 105, 200, 25);

  w_description1.setBounds(600, 100, 300, 80);
  add(w_description);
  add(w_description1);

  //Creating conducted by part
  w_conduct = new JLabel("Conducted by");
  w_conduct.setFont(new Font("Calibri", Font.ITALIC, 22));
  w_conduct.setBounds(250, 205, 200, 25);

  w_conduct1 = new JTextField();
  w_conduct1.setBounds(600, 200, 300, 25);
  add(w_conduct);
  add(w_conduct1);

  //Creating duration part
  w_duration = new JLabel("Duration");
  w_duration.setFont(new Font("Calibri", Font.ITALIC, 22));
  w_duration.setBounds(250, 255, 200, 25);

  w_duration1 = new JTextField();
  w_duration1.setBounds(600, 250, 60, 25);

  w_duration2 = new Choice();
  w_duration2.add("Hours");
  w_duration2.add("Days");
  w_duration2.add("Months");
  w_duration2.setBounds(680, 250, 60, 25);
  add(w_duration);
  add(w_duration1);
  add(w_duration2);

  //Creating start date part
  w_start = new JLabel("Start date");
  w_start.setFont(new Font("Calibri", Font.ITALIC, 22));
  w_start.setBounds(250, 305, 200, 25);

  start_day = new Choice();
  for (int i = 1; i <= 31; i++) {
   start_day.add(String.format("%02d", i));
  }
  start_day.setBounds(600, 300, 50, 25);

  start_month = new Choice();
  String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
  for (String month : months) {
   start_month.add(month);
  }
  start_month.setBounds(665, 300, 80, 25);

  start_year = new Choice();
  for (int i = 2021; i <= 2025; i++) {
   start_year.add(String.valueOf(i));
  }
  start_year.setBounds(755, 300, 50, 25);
  add(start_day);
  add(start_month);
  add(start_year);
  add(w_start);

  //Creating end date part
  w_end = new JLabel("End date");
  w_end.setBounds(250, 355, 200, 25);
  w_end.setFont(new Font("Calibri", Font.ITALIC, 22));

  end_day = new Choice();
  for (int i = 1; i <= 31; i++) {
   end_day.add(String.format("%02d", i));
  }
  end_day.setBounds(600, 350, 50, 25);

  end_month = new Choice();
  for (String month : months) {
   end_month.add(month);
  }
  end_month.setBounds(665, 350, 80, 25);

  end_year = new Choice();
  for (int i = 2021; i <= 2025; i++) {
   end_year.add(String.valueOf(i));
  }
  end_year.setBounds(755, 350, 50, 25);
  add(end_day);
  add(end_month);
  add(end_year);
  add(w_end);

  //Creating department part
  w_dept = new JLabel("Department");
  w_dept.setFont(new Font("Calibri", Font.ITALIC, 22));
  w_dept.setBounds(250, 405, 200, 25);

  String[] depart = {
          " - - -",
          "B.E. Computer Science and Engineering",
          "B.Tech. Artificial Intelligence and Data Science",
          "B.Tech. BioTechnology",
          "B.E. BioMedical Engineering",
          "B.E. Electronics and Communication Engineering",
          "B.E. Electrical and Electronics Engineering",
          "B.Tech. Information Technology",
          "B.E. Mechanical Engineering",
          "B.E. Civil Engineering",
  };
  w_dept1 = new JComboBox<>(depart);
  w_dept1.setBounds(600, 400, 300, 30);
  add(w_dept1);
  add(w_dept);

  //Creating slots available part
  w_student = new JLabel("Slots Allocated");
  w_student.setFont(new Font("Calibri", Font.ITALIC, 22));
  w_student.setBounds(250, 455, 200, 25);

  w_student1 = new JTextField();
  w_student1.setBounds(600, 450, 100, 25);
  add(w_student);
  add(w_student1);

  //Creating Conducted for which year part
  w_year = new JLabel("Conducted For");
  w_year.setFont(new Font("Calibri", Font.ITALIC, 22));
  w_year.setBounds(250, 505, 200, 25);

  w_year1 = new Choice();
  w_year1.setBounds(600, 500, 200, 25);
  w_year1.add("All Years");
  w_year1.add("I st Year");
  w_year1.add("II nd Year");
  w_year1.add("III rd Year");
  w_year1.add("IV th Year");
  add(w_year);
  add(w_year1);

  //Creating Eligibility criteria part
  w_eligibility = new JLabel("Eligibility Criteria");
  w_eligibility.setFont(new Font("Calibri", Font.ITALIC, 22));
  w_eligibility.setBounds(250, 555, 200, 25);

  w_eligibility1.setBounds(600, 550, 300, 80);
  add(w_eligibility);
  add(w_eligibility1);

  //Creating mode of Workshop part
  w_mode = new JLabel("Mode of Workshop");
  w_mode.setFont(new Font("Calibri", Font.ITALIC, 22));
  w_mode.setBounds(250, 655, 200, 25);

  ck1.setBounds(600, 655, 100, 25);
  ck2.setBounds(700, 655, 100, 25);
  add(ck2);
  add(ck1);
  add(w_mode);

  //Creating buttons(Schedule event and reset)
  schedule = new JButton("Schedule Event");
  schedule.setBounds(500, 690, 200, 50);
  schedule.addActionListener(e -> {
   String workshop_title = w_name1.getText();
   String description = w_description1.getText();
   String conducted_by = w_conduct1.getText();
   String start_date = start_day.getSelectedItem() + "-" +
           start_month.getSelectedItem() + "-" +
           start_year.getSelectedItem();
   String slots_allocated = w_student1.getText();
   String duration = w_duration1.getText() + " " +
           w_duration2.getSelectedItem();
   String end_date = end_day.getSelectedItem() + "-" +
           end_month.getSelectedItem() + "-" +
           end_year.getSelectedItem();
   String conducted_for = w_year1.getSelectedItem().toString();
   String department = w_dept1.getSelectedItem().toString();
   String mode_of_workshop = cbg.getSelectedCheckbox().getLabel();
   String eligibility_criteria = w_eligibility1.getText();

   try {
    Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "hell9");
    PreparedStatement pst;
    pst = connection.prepareStatement("insert into workshop(workshop_title, description, conducted_by, duration, start_date, end_date, department, slots_allocated, conducted_for, eligibility_criteria, mode_of_workshop) values (?, ?, ?, ?, TO_DATE(?, 'DD-MON-YYYY'), TO_DATE(?, 'DD-MON-YYYY'), ?, ?, ?, ?, ?)");
    pst.setString(1, workshop_title);
    pst.setString(2, description);
    pst.setString(3, conducted_by);
    pst.setString(4, duration);
    pst.setString(5, start_date);
    pst.setString(6, end_date);
    pst.setString(7, department);
    pst.setString(8, slots_allocated);
    pst.setString(9, conducted_for);
    pst.setString(10, eligibility_criteria);
    pst.setString(11, mode_of_workshop);
    pst.executeUpdate();
    JOptionPane.showMessageDialog(schedule,
            "The workshop has been successfully scheduled !");
    connection.close();
   } catch (SQLException exception) {
    exception.printStackTrace();
   }
  });

  add(schedule);

  reset = new JButton("Reset");
  reset.setBounds(740, 690, 150, 50);
  reset.addActionListener(e -> {
   w_name1.setText("");
   w_conduct1.setText("");
   w_description1.setText("");
   w_duration1.setText("");
   w_eligibility1.setText("");
   w_student1.setText("");
  });
  add(reset);

  //Basic frame setup
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  setLayout(null);
  setVisible(true);
 }

 public static void main(String args[]) {
  new WorkshopReg();
 }
}
