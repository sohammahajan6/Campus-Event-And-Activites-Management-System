package project.college.event.organizer.events;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class FineArtsReg extends JFrame {
    public FineArtsReg() {
        setTitle("FINE ARTS REGISTRATION");
        getContentPane().setBackground(new Color(95, 158, 160));
        JLabel title = new JLabel("FINE ARTS EVENT REGISTRATION", JLabel.CENTER);
        title.setBounds(400, 5, 600, 25);
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        add(title);

        /** Declaring the initial variables of the type JLabel for all the necessary labels */
        JLabel event_name, event_description, start_time;
        JLabel duration, event_per, num_of_participants;
        JLabel schedule_event_id, college_id, email_id;

        /** "Event Name" Label and TextField Definition */
        event_name = new JLabel("Event Name ", JLabel.LEFT);
        event_name.setBounds(250, 50, 200, 25);
        event_name.setFont(new Font("Calibri", Font.ITALIC, 22));
        JTextField event_name_field = new JTextField();
        event_name_field.setBounds(600, 45, 250, 25);
        add(event_name);
        add(event_name_field);

        /** "Event Description" Label and TextArea Definition */
        event_description = new JLabel("Event Description ", JLabel.LEFT);
        event_description.setBounds(250, 90, 200, 25);
        event_description.setFont(new Font("Calibri", Font.ITALIC, 22));
        add(event_description);
        TextArea eventdescrip = new TextArea("", 180, 90, TextArea.SCROLLBARS_BOTH);
        eventdescrip.setBounds(600, 90, 250, 70);
        add(eventdescrip);

        /** "Start Time" Label and HH, MM, AM/PM Choice List Declaration */
        start_time = new JLabel("Start Time");
        start_time.setBounds(250, 173, 200, 30);
        start_time.setFont(new Font("Calibri", Font.ITALIC, 22));
        add(start_time);

        /** Choice List Declaration for Hours, Minutes, and AM/PM */
        Choice HH = new Choice();
        Choice MM = new Choice();
        Choice AM_PM = new Choice();
        HH.setBounds(600, 170, 45, 30);
        MM.setBounds(655, 170, 45, 30);
        AM_PM.setBounds(705, 170, 45, 30);
        add(HH);
        add(MM);
        add(AM_PM);

        /** Choice List Options for Hours */
        for (int i = 1; i <= 12; i++) {
            HH.add(String.format("%02d", i));
        }

        /** Choice List Options for Minutes */
        for (int i = 0; i <= 59; i++) {
            MM.add(String.format("%02d", i));
        }

        /** Choice List Options for AM / PM */
        AM_PM.add("AM");
        AM_PM.add("PM");

        /** "Duration" Label and Text Field and Mins/Hours ChoiceList Declaration */
        duration = new JLabel("Duration");
        duration.setBounds(250, 220, 200, 25);
        duration.setFont(new Font("Calibri", Font.ITALIC, 22));
        add(duration);
        // TextField that takes the input (Hours or Mins)
        JTextField duration_field;
        duration_field = new JTextField();
        duration_field.setBounds(600, 216, 100, 25);
        add(duration_field);
        // Choice List Declaration that shows Mins / Hours Options
        Choice dur = new Choice();
        dur.setBounds(725, 216, 70, 25);
        dur.add("MINS");
        dur.add("HOURS");
        add(dur);

        /** "Start Date" Label and Month, Date and Year Choice Declaration */
        JLabel event_date = new JLabel("Start Date");
        event_date.setBounds(250, 280, 200, 25);
        event_date.setFont(new Font("Calibri", Font.ITALIC, 22));
        add(event_date);

        /** Choice List Options for Month */
        Choice start_Month = new Choice();
        start_Month.setBounds(600, 276, 90, 25);
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        for (String month : months) {
            start_Month.add(month);
        }
        add(start_Month);

        /** Choice List Options for Date */
        Choice event_t1 = new Choice();
        event_t1.setBounds(700, 276, 45, 25);
        for (int i = 1; i <= 31; i++) {
            event_t1.add(String.format("%02d", i));
        }
        add(event_t1);

        /** Choice List Options for Year */
        Choice start_Year = new Choice();
        start_Year.setBounds(750, 276, 100, 25);
        for (int i = 2021; i <= 2025; i++) {
            start_Year.add(Integer.toString(i));
        }
        add(start_Year);

        /** "Meeting Options" Label and Radio-Button Declaration */
        event_per = new JLabel("Event Type ");
        event_per.setBounds(250, 330, 200, 25);
        event_per.setFont(new Font("Calibri", Font.ITALIC, 22));
        add(event_per);

        /** Radio Button Declarations for Meeting Options */
        JRadioButton single, weekly, month;
        single = new JRadioButton("Solo");
        single.setBounds(600, 330, 100, 25);

        weekly = new JRadioButton("Duo");
        weekly.setBounds(700, 330, 100, 25);

        month = new JRadioButton("Group");
        month.setBounds(800, 330, 100, 25);

        ButtonGroup bg = new ButtonGroup();
        bg.add(single);
        bg.add(weekly);
        bg.add(month);
        add(single);
        add(weekly);
        add(month);

        /** "Number of Participants" Label and TextField Declaration */
        num_of_participants = new JLabel("Number of Participants(If Group)");
        num_of_participants.setBounds(250, 435, 350, 25);
        JTextField participants_field = new JTextField();
        participants_field.setBounds(600, 430, 90, 25);
        num_of_participants.setFont(new Font("Calibri", Font.ITALIC, 22));
        add(num_of_participants);
        add(participants_field);

        /** Category of event declaration */
        JLabel category = new JLabel("Category");
        category.setBounds(250, 376, 200, 30);
        category.setFont(new Font("Calibri", Font.ITALIC, 22));
        add(category);

        Choice category1 = new Choice();
        category1.setBounds(600, 380, 120, 25);
        String[] categories = {"Classical Dance", "Folk dance", "Singing", "Orchestra", "Art", "Painting", "Writing", "Taboo", "Mimicry", "Rangoli", "Pattimandram", "Elocution"};
        for (String cat : categories) {
            category1.add(cat);
        }
        add(category1);

        /** "Schedule Meeting with ID" Label and TextField Declaration */
        schedule_event_id = new JLabel("Schedule Event with ID");
        schedule_event_id.setBounds(250, 490, 400, 25);
        schedule_event_id.setFont(new Font("Calibri", Font.ITALIC, 22));
        add(schedule_event_id);
        JTextField meeting_id_field = new JTextField();
        meeting_id_field.setBounds(600, 485, 90, 25);
        add(meeting_id_field);

        /** "College ID" Label and TextField Declaration */
        college_id = new JLabel("College ID");
        college_id.setBounds(250, 545, 200, 25);
        college_id.setFont(new Font("Calibri", Font.ITALIC, 22));
        add(college_id);
        JTextField id = new JTextField();
        id.setBounds(600, 545, 150, 25);
        add(id);

        /** "Email ID" Label and TextField Declaration */
        email_id = new JLabel("Email ID");
        email_id.setBounds(250, 595, 200, 25);
        email_id.setFont(new Font("Calibri", Font.ITALIC, 22));
        add(email_id);
        JTextField email = new JTextField();
        email.setBounds(600, 595, 200, 25);
        add(email);

        /** Reset Button and its Listener Declaration */
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

        /** Submit Button Declaration */
        JButton submit = new JButton("Schedule Event");
        submit.setBounds(650, 670, 170, 30);
        add(submit);

        submit.addActionListener(e -> {

            String fevent_name = event_name_field.getText();
            String fevent_description = eventdescrip.getText();
            String fduration = duration_field.getText() + " " +
                    dur.getSelectedItem().toString();
            String start_date = event_t1.getSelectedItem().toString() + "-" +
                    start_Month.getSelectedItem().toString() + "-" +
                    start_Year.getSelectedItem().toString();
            String event_type = bg.getSelection().getActionCommand();
            String fcategory = category1.getSelectedItem();
            String fstart_time = HH.getSelectedItem() + "-" +
                    MM.getSelectedItem() + "-" +
                    AM_PM.getSelectedItem();
            String number_of_participants = participants_field.getText();
            String fevent_id = meeting_id_field.getText();
            String fcollege_id = id.getText();
            String femail_id = email.getText();
            try {
                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "hell9");
                PreparedStatement pst;
                pst = connection.prepareStatement("insert into FineArtsRegistration(event_name, event_description, start_time, duration, start_date, event_type, category, number_of_participants, schedule_event_id, college_id, email_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                pst.setString(1, fevent_name);
                pst.setString(2, fevent_description);
                pst.setString(3, fstart_time);
                pst.setString(4, fduration);
                pst.setString(5, start_date);
                pst.setString(6, event_type);
                pst.setString(7, fcategory);
                pst.setString(8, number_of_participants);
                pst.setString(9, fevent_id);
                pst.setString(10, fcollege_id);
                pst.setString(11, femail_id);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(submit,
                        "The fine arts event has been successfully scheduled !");
                connection.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        /** Frame Properties Declarations */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1600, 775);
        setLayout(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new FineArtsReg();
    }
}
