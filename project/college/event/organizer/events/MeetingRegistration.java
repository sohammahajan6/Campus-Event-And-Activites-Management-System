package project.college.event.organizer.events;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.swing.*;

public class MeetingRegistration
{
    public MeetingRegistration()
    {
        // Creating the Frame and then setting the Title
        JFrame f =new JFrame("SCHEDULE A MEETING FORM");
        f.getContentPane().setBackground(new Color(95,158,160));
        JLabel title = new JLabel("SCHEDULE A MEETING",JLabel.CENTER);
        title.setBounds(500,05,400,25);
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        f.add(title);
        f.setBackground(Color.cyan);
        f.setForeground(Color.black);

        /** Declaring the initial variables of the type JLabel for all the necessary labels */
        JLabel meeting_name,meeting_description,meeting_type;
        JLabel start_time,duration,start_date,meeting_options,num_of_participants;
        JLabel schedule_meeting_id,meeting_password,college_id,email_id;

        /** "Meeting Name" Label and TextField Definition */
        meeting_name = new JLabel("Meeting Name ",JLabel.LEFT);
        meeting_name.setBounds(250,50,200,25);
        meeting_name.setFont(new Font("Calibri", Font.ITALIC, 22));
        JTextField meeting_name_field = new JTextField();
        meeting_name_field.setBounds(600,45,250,25);
        f.add(meeting_name);f.add(meeting_name_field);

        /** "Meeting Description" Label and TextArea Definition */
        meeting_description = new JLabel("Meeting Description ",JLabel.LEFT);
        meeting_description.setBounds(250,90,200,25);
        meeting_description.setFont(new Font("Calibri", Font.ITALIC, 22));
        f.add(meeting_description);
        TextArea meetingdescrip = new TextArea("",180,90,TextArea.SCROLLBARS_BOTH);
        meetingdescrip.setBounds(600,90,250,70);
        f.add(meetingdescrip);

        /** "Start Time" Label and HH , MM , AM/PM Choice List Declaration */
        start_time = new JLabel("Start Time");
        start_time.setBounds(250,165,200,30);
        start_time.setFont(new Font("Calibri", Font.ITALIC, 22));
        f.add(start_time);

        /** Choice List Declaration for Hours, Minutes and AM/PM */
        Choice HH = new Choice();
        Choice MM = new Choice();
        Choice AM_PM = new Choice();
        HH.setBounds(600,165,45,30);
        MM.setBounds(655,165,45,30);
        AM_PM.setBounds(705,165,45,30);
        f.add(HH);f.add(MM);f.add(AM_PM);

        /** Choice List Options for Hours */
        HH.add("01"); HH.add("02"); HH.add("03"); HH.add("04"); HH.add("05");
        HH.add("06"); HH.add("07"); HH.add("08"); HH.add("09"); HH.add("10");
        HH.add("11"); HH.add("12");
        f.add(HH);

        /** Choice List Options for Minutes */
        MM.add("00");
        MM.add("01"); MM.add("02"); MM.add("03"); MM.add("04"); MM.add("05");
        MM.add("06"); MM.add("07"); MM.add("08"); MM.add("09"); MM.add("10");
        MM.add("11"); MM.add("12"); MM.add("13"); MM.add("14"); MM.add("15");
        MM.add("16");MM.add("17");MM.add("18");MM.add("19");MM.add("20");
        MM.add("21");MM.add("22");MM.add("23");MM.add("24");MM.add("25");
        MM.add("26");MM.add("27");MM.add("28");MM.add("29");MM.add("25");
        MM.add("31");MM.add("32");MM.add("33");MM.add("34");MM.add("35");
        MM.add("36");MM.add("37");MM.add("38");MM.add("39");MM.add("40");
        MM.add("41");MM.add("42");MM.add("43");MM.add("44");MM.add("45");
        MM.add("46");MM.add("47");MM.add("48");MM.add("49");MM.add("50");
        MM.add("51");MM.add("52");MM.add("53");MM.add("54");MM.add("55");
        MM.add("56");MM.add("57");MM.add("58");MM.add("59");
        f.add(MM);

        /** Choice List Options for AM / PM */
        AM_PM.add("AM");AM_PM.add("PM");
        f.add(AM_PM);

        /** "Meeting Type" Label and Text Field Declaration */
        meeting_type= new JLabel("Meeting Type");
        meeting_type.setBounds(250,200,200,30);
        meeting_type.setFont(new Font("Calibri", Font.ITALIC, 22));
        f.add(meeting_type);
        JTextField meeting_type_field = new JTextField();
        meeting_type_field.setBounds(600,200,200,30);
        f.add(meeting_type_field);

        /** "Duration" Label and Text Field and Mins/Hours ChoiceList Declaration */
        duration = new JLabel("Duration");
        duration.setBounds(250,250,200,25);
        duration.setFont(new Font("Calibri", Font.ITALIC, 22));
        f.add(duration);
        // TextField that takes the input (Hours or Mins)
        JTextField duration_field;
        duration_field = new JTextField();
        duration_field.setBounds(600,250,100,25);
        f.add(duration_field);
        // Choice List Declaration that shows Mins / Hours Options
        Choice dur = new Choice();
        dur.setBounds(725,250,70,25);
        dur.add("MINS"); dur.add("HOURS");
        f.add(dur);

        /** "Start Date" Label and Month, Date and Year Choice Declaration */
        start_date = new JLabel("Start Date");
        start_date.setBounds(250,300,200,25);
        start_date.setFont(new Font("Calibri", Font.ITALIC, 22));
        f.add(start_date);

        /** Choice List Options for Month */
        Choice start_Month = new Choice();
        start_Month.setBounds(600,300,90,25);
        start_Month.add("January");
        start_Month.add("February");start_Month.add("March");start_Month.add("April");start_Month.add("May");
        start_Month.add("June");start_Month.add("July");start_Month.add("August");start_Month.add("September");
        start_Month.add("October");start_Month.add("November");start_Month.add("December");
        f.add(start_Month);

        /** Choice List Options for Date */
        Choice start_Date = new Choice();
        start_Date.setBounds(700,300,45,25);
        start_Date.add("01"); start_Date.add("02"); start_Date.add("03"); start_Date.add("04"); start_Date.add("05");
        start_Date.add("06"); start_Date.add("07"); start_Date.add("08"); start_Date.add("09"); start_Date.add("10");
        start_Date.add("11"); start_Date.add("12"); start_Date.add("13"); start_Date.add("14"); start_Date.add("15");
        start_Date.add("16");start_Date.add("17");start_Date.add("18");start_Date.add("19");start_Date.add("20");
        start_Date.add("21");start_Date.add("22");start_Date.add("23");start_Date.add("24");start_Date.add("25");
        start_Date.add("26");start_Date.add("27");start_Date.add("28");start_Date.add("29");start_Date.add("30");
        start_Date.add("31");
        f.add(start_Date);

        /** Choice List Options for Year */
        Choice start_Year = new Choice();
        start_Year.setBounds(750,300,100,25);
        start_Year.add("2021");start_Year.add("2022");start_Year.add("2023");start_Year.add("2024");
        start_Year.add("2025");
        f.add(start_Year);

        /** "Meeting Options" Label and Radio-Button Declaration */
        meeting_options = new JLabel("Meeting Options ");
        meeting_options.setBounds(250,345,200,25);
        meeting_options.setFont(new Font("Calibri", Font.ITALIC, 22));
        f.add(meeting_options);
        /** Radio Button Declarations for Meeting Options */
        JRadioButton single,weekly,month;
        single = new JRadioButton("Single");
        single.setBounds(600,345,100,25);

        weekly = new JRadioButton("Weekly");
        weekly.setBounds(700,345,100,25);

        month = new JRadioButton("Monthly");
        month.setBounds(800,345,100,25);

        ButtonGroup bg=new ButtonGroup();
        bg.add(single);bg.add(weekly);bg.add(month);
        f.add(single);f.add(weekly);f.add(month);

        /** "Number of Participants" Label and TextField Declaration */
        num_of_participants=new JLabel("Number of Participants");
        num_of_participants.setBounds(250,395,250,25);
        JTextField participants_field =new JTextField();
        participants_field.setBounds(600,395,90,25);
        num_of_participants.setFont(new Font("Calibri", Font.ITALIC, 22));
        f.add(num_of_participants);f.add(participants_field);

        /** "Schedule Meeting with ID" Label and TextField Declaration */
        schedule_meeting_id = new JLabel("Schedule Meeting with ID");
        schedule_meeting_id.setBounds(250,445,400,25);
        schedule_meeting_id.setFont(new Font("Calibri", Font.ITALIC, 22));
        f.add(schedule_meeting_id);
        JTextField meeting_id_field =new JTextField();
        meeting_id_field.setBounds(600,445,90,25);
        f.add(meeting_id_field);

        /** "Set Meeting Password" Label and TextField Declaration */
        meeting_password = new JLabel("Set Meeting Password");
        meeting_password.setBounds(250,495,200,25);
        meeting_password.setFont(new Font("Calibri", Font.ITALIC, 22));
        f.add(meeting_password);
        JTextField password_field = new JTextField();
        password_field.setBounds(600,495,250,25);
        f.add(password_field);

        /** "College ID" Label and TextField Declaration */
        college_id = new JLabel("College ID");
        college_id.setBounds(250,545,200,25);
        college_id.setFont(new Font("Calibri", Font.ITALIC, 22));
        f.add(college_id);
        JTextField id = new JTextField();
        id.setBounds(600,545,150,25);
        f.add(id);

        /** "Email ID" Label and TextField Declaration */
        email_id = new JLabel("Email ID");
        email_id.setBounds(250,595,200,25);
        email_id.setFont(new Font("Calibri", Font.ITALIC, 22));
        f.add(email_id);
        JTextField email = new JTextField();
        email.setBounds(600,595,200,25);
        f.add(email);

        /** Reset Button and its Listener Declaration */
        JButton reset = new JButton("Reset");
        reset.setBounds(500,670,100,30);
        reset.addActionListener(e -> {
            meeting_name_field.setText("");
            meetingdescrip.setText("");
            meeting_type_field.setText("");
            duration_field.setText("");
            participants_field.setText("");
            meeting_id_field.setText("");
            password_field.setText("");
            id.setText("");
            email.setText("");
        });
        f.add(reset);

        /** Submit Button Declaration */
        JButton submit = new JButton("Schedule Meeting");
        submit.setBounds(650,670,170,30);

        submit.addActionListener(e -> {


            String meeting_title = meeting_name_field.getText();
            String description = meetingdescrip.getText();
            String m_duration = duration_field.getText() + " " +
                    dur.getSelectedItem().toString();
            String m_start_time = HH.getSelectedItem().toString() + "-" +
                    MM.getSelectedItem().toString() + "-" +
                    AM_PM.getSelectedItem().toString();
            String m_type = meeting_type_field.getText();
            String m_start_date = start_Date.getSelectedItem().toString()+
                    start_Month.getSelectedItem().toString() +
                    start_Year.getSelectedItem().toString();
            String m_options = bg.getSelection().getActionCommand();
            String p_field = participants_field.getText();
            String m_id = meeting_id_field.getText();
            String pass_field = password_field.getText();
            String c_id = id.getText();
            String e_id = email.getText();

            try
            {
                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "hell9");
                PreparedStatement pst;
                pst = connection.prepareStatement("insert into meetingdb(MeetingName,MeetingDescription,StartTime,MeetingType,Duration,MeetingOptions,NumofParticipants,MeetingID,MeetingPassword,CollegeID,EmailID) values(?,?,?,?,?,?,?,?,?,?,?)");
                pst.setString(1,meeting_title);
                pst.setString(2,description);
                pst.setString(3,m_start_time);
                pst.setString(4,m_type);
                pst.setString(5,m_duration);
                pst.setString(6,m_options);
                pst.setString(7,p_field);
                pst.setString(8,m_id);
                pst.setString(9,pass_field);
                pst.setString(10,c_id);
                pst.setString(11,e_id);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(submit,
                        "You have been successfully registered for the meeting !");
                connection.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        });



        f.add(submit);

        /**  Frame Properties Declarations  */
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1600,775);
        f.setLayout(null);
        f.setVisible(true);
        submit.setFocusable(false);
        reset.setFocusable(false);
        single.setFocusable(false);
        weekly.setFocusable(false);
        month.setFocusable(false);
        start_Date.setFocusable(false);
        start_Month.setFocusable(false);
        start_Year.setFocusable(false);
        HH.setFocusable(false);
        MM.setFocusable(false);
        AM_PM.setFocusable(false);
        dur.setFocusable(false);
    }

    // Driver Code
    public  static void  main(String[] args)
    {
        new MeetingRegistration();
    }

}
