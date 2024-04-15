package project.college.event.organizer.display.eventmanage.culturals;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.ArrayList;

public class Manage_Culturals {
    private JPanel rootpanel;
    private JTable TableCul;
    private JButton btnDelete;
    private JButton btnExit;
    private JTextField tfUserID;
    private JTextField NameSearch;

    final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    final String USERNAME = "system";
    final String PASSWORD = "hell9";

    ResultSet rs;

    public Manage_Culturals() {
        createTable();
        show_user();
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the value from the tfUserID JTextField
                String userID = tfUserID.getText();

                try {
                    Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                    PreparedStatement pst;

                    // Use the appropriate column name instead of 'id'
                    pst = conn.prepareStatement("delete from culturals where StudentName=?");
                    pst.setString(1, userID);

                    // Execute the delete query
                    int rowsAffected = pst.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Record Deleted Successfully !!");
                        // Clear the text field after deletion
                        tfUserID.setText("");
                        // Reload the table to reflect the changes
                        load_table();
                    } else {
                        JOptionPane.showMessageDialog(null, "No record found with the provided Student Name.");
                    }
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        });

        tfUserID.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String id = tfUserID.getText();

                try {
                    Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                    PreparedStatement pst;

                    pst = conn.prepareStatement("select StudentName from culturals where StudentName = ?");
                    pst.setString(1, id);
                    ResultSet rs = pst.executeQuery();

                    if (rs.next()) {
                        String name = rs.getString(1);
                        NameSearch.setText(name);
                    } else {
                        NameSearch.setText("");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void load_table() {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            PreparedStatement pst;

            pst = conn.prepareStatement("select * from culturals");
            rs = pst.executeQuery();
            // TableCul.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ArrayList<CulturalsClass> culturalsList() {
        ArrayList<CulturalsClass> culturalsList = new ArrayList<CulturalsClass>();
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String query = "SELECT * FROM culturals";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            CulturalsClass culturals;

            while (rs.next()) {
                culturals = new CulturalsClass(
                        rs.getString("StudentName"),
                        rs.getString("Department"),
                        rs.getString("Year_Section"),
                        rs.getString("Age"),
                        rs.getString("CollegeName"),
                        rs.getString("ContactNumber"),
                        rs.getString("NameofFaculty"),
                        rs.getString("EmailID"),
                        rs.getString("EventsRegistered")
                );
                culturalsList.add(culturals);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return culturalsList;
    }

    private void show_user() {
        ArrayList<CulturalsClass> list = culturalsList();
        DefaultTableModel model = (DefaultTableModel) TableCul.getModel();
        Object[] row = new Object[10];

        for (int i = 0; i < list.size(); i++) {
            row[0] = i + 1;
            row[1] = list.get(i).getStudentName();
            row[2] = list.get(i).getDepartment();
            row[3] = list.get(i).getYearSection();
            row[4] = list.get(i).getAge();
            row[5] = list.get(i).getCollegeName();
            row[6] = list.get(i).getContactNumber();
            row[7] = list.get(i).getNameOfFaculty();
            row[8] = list.get(i).getEmailId();
            row[9] = list.get(i).getEventsRegistered();
            model.addRow(row);
        }
    }

    private void createTable() {
        TableCul.setModel(new DefaultTableModel(
                null,
                new String[]{"SNo", "Student Name", "Department", "Year and Section", "Age", "College Name", "Contact Number",
                        "Number of Faculty", "Email ID", "Events Registered"}
        ));

        TableColumnModel columns = TableCul.getColumnModel();
        columns.getColumn(0).setMinWidth(05);

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        columns.getColumn(1).setCellRenderer(cellRenderer);
        columns.getColumn(2).setCellRenderer(cellRenderer);
        columns.getColumn(3).setCellRenderer(cellRenderer);
        columns.getColumn(4).setCellRenderer(cellRenderer);
        columns.getColumn(5).setCellRenderer(cellRenderer);
        columns.getColumn(6).setCellRenderer(cellRenderer);
        columns.getColumn(7).setCellRenderer(cellRenderer);
        columns.getColumn(8).setCellRenderer(cellRenderer);
        columns.getColumn(9).setCellRenderer(cellRenderer);
    }

    private JPanel getRootPanel() {
        return rootpanel;
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
        Manage_Culturals ui = new Manage_Culturals();
        JPanel root = ui.getRootPanel();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(root);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
