package project.college.event.organizer.events;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ExtraCurricularEventReg extends JFrame {

    public ExtraCurricularEventReg() {
        setTitle("SCHEDULE AN EXTRA CURRICULAR EVENT");
        getContentPane().setBackground(new Color(95, 158, 160));
        JLabel title = new JLabel("SCHEDULE AN EXTRA CURRICULAR EVENT", JLabel.CENTER);
        title.setBounds(400, 05, 600, 25);
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        add(title);
        setBackground(Color.cyan);
        setForeground(Color.black);

        JLabel event_name, event_description, start_time;
        JLabel duration, start_date, event_options, num_of_participants;
        JLabel schedule_event_id, college_id, email_id;

        event_name = new JLabel("Event Name ", JLabel.LEFT);
        event_name.setBounds(250, 50, 200, 25);
        event_name.setFont(new Font("Calibri", Font.ITALIC, 22));
        JTextField event_name_field = new JTextField();
        event_name_field.setBounds(600, 45, 250, 25);
        add(event_name);
        add(event_name_field);

        event_description = new JLabel("Event Description ", JLabel.LEFT);
        event_description.setBounds(250, 90, 200, 25);
        event_description.setFont(new Font("Calibri", Font.ITALIC, 22));
        add(event_description);
        TextArea eventdescrip = new TextArea("", 180, 90, TextArea.SCROLLBARS_BOTH);
        eventdescrip.setBounds(600, 90, 250, 70);
        add(eventdescrip);

        start_time = new JLabel("Start Time");
        start_time.setBounds(250, 165, 200, 30);
        start_time.setFont(new Font("Calibri", Font.ITALIC, 22));
        add(start_time);

        Choice HH = new Choice();
        Choice MM = new Choice();
        Choice AM_PM = new Choice();
        HH.setBounds(600, 165, 45, 30);
        MM.setBounds(655, 165, 45, 30);
        AM_PM.setBounds(705, 165, 45, 30);
        add(HH);
        add(MM);
        add(AM_PM);

        for (int i = 1; i <= 12; i++) {
            HH.add(String.format("%02d", i));
        }
        for (int i = 0; i <= 59; i++) {
            MM.add(String.format("%02d", i));
        }
        AM_PM.add("AM");
        AM_PM.add("PM");

        duration = new JLabel("Duration");
        duration.setBounds(250, 250, 200, 25);
        duration.setFont(new Font("Calibri", Font.ITALIC, 22));
        add(duration);

        JTextField duration_field;
        duration_field = new JTextField();
        duration_field.setBounds(600, 250, 100, 25);
        add(duration_field);
        Choice dur = new Choice();
        dur.setBounds(725, 250, 70, 25);
        dur.add("MINS");
        dur.add("HOURS");
        add(dur);

        start_date = new JLabel("Start Date");
        start_date.setBounds(250, 300, 200, 25);
        start_date.setFont(new Font("Calibri", Font.ITALIC, 22));
        add(start_date);

        Choice start_Month = new Choice();
        start_Month.setBounds(600, 300, 90, 25);
        start_Month.add("January");
        start_Month.add("February");
        start_Month.add("March");
        start_Month.add("April");
        start_Month.add("May");
        start_Month.add("June");
        start_Month.add("July");
        start_Month.add("August");
        start_Month.add("September");
        start_Month.add("October");
        start_Month.add("November");
        start_Month.add("December");
        add(start_Month);

        Choice start_Date = new Choice();
        start_Date.setBounds(700, 300, 45, 25);
        for (int i = 1; i <= 31; i++) {
            start_Date.add(String.format("%02d", i));
        }
        add(start_Date);

        Choice start_Year = new Choice();
        start_Year.setBounds(750, 300, 100, 25);
        for (int i = 2021; i <= 2025; i++) {
            start_Year.add(Integer.toString(i));
        }
        add(start_Year);

        event_options = new JLabel("Event Options ");
        event_options.setBounds(250, 345, 200, 25);
        event_options.setFont(new Font("Calibri", Font.ITALIC, 22));
        add(event_options);

        CheckboxGroup cbg = new CheckboxGroup();
        Checkbox ck1 = new Checkbox("Single", false, cbg);
        Checkbox ck2 = new Checkbox("Weekly", false, cbg);
        Checkbox ck3 = new Checkbox("Monthly", false, cbg);
        ck1.setBounds(600, 345, 100, 25);
        ck2.setBounds(700, 345, 100, 25);
        ck3.setBounds(800, 345, 100, 25);
        add(ck1);
        add(ck2);
        add(ck3);

        num_of_participants = new JLabel("Number of Participants");
        num_of_participants.setBounds(250, 395, 250, 25);
        JTextField participants_field = new JTextField();
        participants_field.setBounds(600, 395, 90, 25);
        num_of_participants.setFont(new Font("Calibri", Font.ITALIC, 22));
        add(num_of_participants);
        add(participants_field);

        schedule_event_id = new JLabel("Schedule Event with ID");
        schedule_event_id.setBounds(250, 445, 400, 25);
        schedule_event_id.setFont(new Font("Calibri", Font.ITALIC, 22));
        add(schedule_event_id);
        JTextField meeting_id_field = new JTextField();
        meeting_id_field.setBounds(600, 445, 90, 25);
        add(meeting_id_field);

        college_id = new JLabel("College ID");
        college_id.setBounds(250, 545, 200, 25);
        college_id.setFont(new Font("Calibri", Font.ITALIC, 22));
        add(college_id);
        JTextField id = new JTextField();
        id.setBounds(600, 545, 150, 25);
        add(id);

        email_id = new JLabel("Email ID");
        email_id.setBounds(250, 595, 200, 25);
        email_id.setFont(new Font("Calibri", Font.ITALIC, 22));
        add(email_id);
        JTextField email = new JTextField();
        email.setBounds(600, 595, 200, 25);
        add(email);

        JButton reset = new JButton("Reset");
        reset.setBounds(500, 670, 100, 30);
        reset.addActionListener(e -> {
            event_name_field.setText("");
            eventdescrip.setText("");
            duration_field.setText("");
            participants_field.setText("");
            meeting_id_field.setText("");
            id.setText("");
            email.setText("");
        });
        add(reset);

        JButton submit = new JButton("Schedule Event");
        submit.setBounds(650, 670, 170, 30);
        submit.addActionListener(e -> {
            String event_title = event_name_field.getText();
            String description = eventdescrip.getText();
            String e_duration = duration_field.getText() + " " +
                    dur.getSelectedItem().toString();
            String e_start_time = HH.getSelectedItem().toString() + "-" +
                    MM.getSelectedItem().toString() + "-" +
                    AM_PM.getSelectedItem().toString();
            String e_start_date = start_Date.getSelectedItem().toString() +
                    start_Month.getSelectedItem().toString() +
                    start_Year.getSelectedItem().toString();
            String e_options = cbg.getSelectedCheckbox().getLabel();
            String p_field = participants_field.getText();
            String event_id = meeting_id_field.getText();
            String c_id = id.getText();
            String e_id = email.getText();

            try {
                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "hell9");
                PreparedStatement pst;
                pst = connection.prepareStatement("insert into extracurricular(event_name,description,start_time,duration,start_date,event_options,number_of_participants,event_id,college_id,email_id)values(?,?,?,?,?,?,?,?,?,?)");
                pst.setString(1, event_title);
                pst.setString(2, description);
                pst.setString(3, e_start_time);
                pst.setString(4, e_duration);
                pst.setString(5, e_start_date);
                pst.setString(6, e_options);
                pst.setString(7, p_field);
                pst.setString(8, event_id);
                pst.setString(9, c_id);
                pst.setString(10, e_id);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(submit,
                        "You have been successfully registered for the extra curricular event !");
                connection.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        add(submit);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1600, 775);
        setLayout(null);
        setVisible(true);
    }

    public static void main(String argv[]) {
        new ExtraCurricularEventReg();
    }
}
