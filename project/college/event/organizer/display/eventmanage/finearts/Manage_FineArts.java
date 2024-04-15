package project.college.event.organizer.display.eventmanage.finearts;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class Manage_FineArts {
    private JPanel mainpanel;
    private JTable TableFineArts;

    final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    final String USERNAME = "system";
    final String PASSWORD = "hell9";

    ResultSet rs;

    public Manage_FineArts() {
        createTable();
        loadTableData();
    }

    private void loadTableData() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             PreparedStatement pst = conn.prepareStatement("select * from StudentRegistration")) {

            rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) TableFineArts.getModel();
            model.setRowCount(0); // Clear existing data

            while (rs.next()) {
                Object[] row = {
                        rs.getString("event_type"),
                        rs.getString("category"),
                        rs.getString("student_name"),
                        rs.getString("email"),
                        rs.getInt("age")
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createTable() {
        TableFineArts.setModel(new DefaultTableModel(
                null,
                new String[]{"Event Type", "Category", "Student Name", "Email", "Age"}
        ));

        TableColumnModel columns = TableFineArts.getColumnModel();
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < 5; i++) {
            columns.getColumn(i).setCellRenderer(cellRenderer);
        }
    }

    private JPanel getRootPanel() {
        return mainpanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Manage_FineArts::createGUI);
    }

    private static void createGUI() {
        Manage_FineArts ui = new Manage_FineArts();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(ui.getRootPanel());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
