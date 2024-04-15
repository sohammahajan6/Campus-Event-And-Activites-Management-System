package project.college.event.organizer.display.eventmanage.workshop;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.*;

public class Manage_Workshop {
    private JTable TableWork;
    private JPanel panel;

    final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    final String USERNAME = "system";
    final String PASSWORD = "hell9";

    public Manage_Workshop() {
        createTable();
        load_table();
    }

    private void load_table() {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            PreparedStatement pst = conn.prepareStatement("select workshop_title, workshop_mode, student_name, age, department, email, duration from WorkshopRegistration");
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) TableWork.getModel();
            model.setRowCount(0); // Clear existing rows
            while (rs.next()) {
                Object[] row = new Object[7];
                for (int i = 0; i < 7; i++) {
                    row[i] = rs.getObject(i + 1);
                }
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JPanel getRootPanel() {
        return panel;
    }

    private void createTable() {
        TableWork.setModel(new DefaultTableModel(
                null,
                new String[]{"Workshop Title", "Workshop Mode", "Student Name", "Age", "Department", "Email", "Duration"}
        ));

        TableColumnModel columns = TableWork.getColumnModel();
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < 7; i++) {
            columns.getColumn(i).setCellRenderer(cellRenderer);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createGUI();
            }
        });
    }

    private static void createGUI() {
        Manage_Workshop ui = new Manage_Workshop();
        JPanel root = ui.getRootPanel();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(root);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
