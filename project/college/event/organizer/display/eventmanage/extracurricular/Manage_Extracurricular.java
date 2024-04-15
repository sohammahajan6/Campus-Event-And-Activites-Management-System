package project.college.event.organizer.display.eventmanage.extracurricular;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.sql.*;

public class Manage_Extracurricular {
    private JTable TableExtra;
    private JPanel panel;
    private JTextField NameSearch;

    final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    final String USERNAME = "system";
    final String PASSWORD = "hell9";

    ResultSet rs;

    Manage_Extracurricular() {
        createTable();
        show_user();
    }

    private void load_table() {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            PreparedStatement pst;

            pst = conn.prepareStatement("select * from extracurricular");
            rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) TableExtra.getModel();
            model.setRowCount(0); // Clear table before adding new data

            while (rs.next()) {
                Object[] row = {
                        rs.getString("EVENT_NAME"),
                        rs.getString("DESCRIPTION"),
                        rs.getString("STUDENT_NAME"),
                        rs.getString("EMAIL"),
                        rs.getString("PHONE_NO"),
                        rs.getString("DEPARTMENT"),
                        rs.getString("AGE")
                };
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void show_user() {
        load_table();
    }

    private void createTable() {
        TableExtra.setModel(new DefaultTableModel(
                null,
                new String[]{"Event Name", "Description", "Student Name", "Email", "Phone No", "Department", "Age"}
        ));

        TableColumnModel columns = TableExtra.getColumnModel();
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < columns.getColumnCount(); i++) {
            columns.getColumn(i).setCellRenderer(cellRenderer);
        }
    }

    private JPanel getRootPanel() {
        return panel;
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
        Manage_Extracurricular ui = new Manage_Extracurricular();
        JPanel root = ui.getRootPanel();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(root);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
