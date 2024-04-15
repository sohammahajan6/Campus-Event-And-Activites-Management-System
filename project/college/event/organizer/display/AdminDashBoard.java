package project.college.event.organizer.display;

import project.college.event.organizer.events.ExtraCurricularEventReg;
import project.college.event.organizer.events.FineArtsReg;
import project.college.event.organizer.events.WorkshopReg;
import project.college.event.organizer.display.usersmanage.Admin_Manage_Users;

import javax.swing.*;
import java.awt.*;

public class AdminDashBoard extends JFrame {

    public AdminDashBoard() {
        setTitle("Manage Admin");
        getContentPane().setBackground(new Color(240, 128, 128));
        JLabel title = new JLabel("Welcome Admin :-) ", JLabel.CENTER);
        title.setBounds(350, 10, 600, 110);
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        add(title);

        JButton manageUsers = new JButton("Manage Users DataBase");
        manageUsers.setBounds(400, 118, 500, 60);
        manageUsers.addActionListener(e -> {
            dispose();
            Admin_Manage_Users adminManageUsers = new Admin_Manage_Users();
            adminManageUsers.setVisible(true);
        });
        add(manageUsers);

        JButton manageExtraCurricular = new JButton("Schedule Extra Curricular Events");
        manageExtraCurricular.setBounds(400, 220, 500, 60);
        manageExtraCurricular.addActionListener(e -> {
            dispose();
            ExtraCurricularEventReg extraCurricularEventReg = new ExtraCurricularEventReg();
            extraCurricularEventReg.setVisible(true); // This line is correct
        });
        add(manageExtraCurricular);

        JButton manageFineArts = new JButton("Schedule Fine Arts Events");
        manageFineArts.setBounds(400, 320, 500, 60);
        manageFineArts.addActionListener(e -> {
            dispose();
            FineArtsReg fineArtsReg = new FineArtsReg();
            fineArtsReg.setVisible(true); // This line is correct
        });
        add(manageFineArts);

        JButton manageWorkshop = new JButton("Schedule Workshops");
        manageWorkshop.setBounds(400, 420, 500, 60);
        manageWorkshop.addActionListener(e -> {
            dispose();
            WorkshopReg workshopReg = new WorkshopReg();
            workshopReg.setVisible(true); // This line is correct
        });
        add(manageWorkshop);

        JButton exit = new JButton("Exit Application");
        exit.setBounds(400, 520, 500, 60);
        exit.addActionListener(e -> System.exit(0));
        add(exit);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1600, 800);
        setLayout(null);
        setVisible(true); // Ensure this line is present
    }

    public static void main(String[] args) {
        new AdminDashBoard();
    }
}
