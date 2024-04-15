package project.college.event.organizer.display.usersmanage;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

public class Admin_Manage_Users extends JFrame {

    private JTextField tfName;
    private JTextField tfEmail;
    private JPasswordField tfPassword;
    private JButton btnAdd;
    private JButton btnClear;
    private JButton deleteButton;
    private JTable showTable;
    private JButton btnDelete;
    private JPanel rootPanel;
    private JTextField tfSNO;
    private JButton btnExit;
    private JButton btnUpdate;


    final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    final String USERNAME = "system";
    final String PASSWORD = "hell9";

    ResultSet rs;

    public Admin_Manage_Users() {
        createTable();
        show_user();
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Email = tfEmail.getText();
                String Password = String.valueOf(tfPassword.getPassword());
                String Name = tfName.getText();

                if (Name.isEmpty() || Email.isEmpty() || Password.isEmpty()) {
                    JOptionPane.showMessageDialog(btnAdd, "Please enter all fields", "Empty Fields", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                        PreparedStatement pst;

                        pst = conn.prepareStatement("insert into userdb(Email,Password,Name) values(?,?,?)");
                        pst.setString(1, Email);
                        pst.setString(2, Password);
                        pst.setString(3, Name);
                        pst.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Record Added Successfully !!");
                        load_table();
                        tfEmail.setText("");
                        tfPassword.setText("");
                        tfName.setText("");
                        tfSNO.requestFocus();
                    } catch (SQLException exception) {
                        exception.printStackTrace();
                    }
                }
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = showTable.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(rootPane, "Select a row to delete.", "No row selected", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        String email = (String) showTable.getValueAt(row, 0);
                        Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                        PreparedStatement pst = conn.prepareStatement("DELETE FROM userdb WHERE Email=?");
                        pst.setString(1, email);
                        pst.executeUpdate();
                        JOptionPane.showMessageDialog(rootPane, "Record deleted successfully.");
                        load_table();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = showTable.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(rootPane, "Select a row to update.", "No row selected", JOptionPane.ERROR_MESSAGE);
                } else {
                    String email = (String) showTable.getValueAt(row, 0);
                    String newName = tfName.getText();
                    String newEmail = tfEmail.getText();
                    String newPassword = String.valueOf(tfPassword.getPassword());

                    if (newName.isEmpty() || newEmail.isEmpty() || newPassword.isEmpty()) {
                        JOptionPane.showMessageDialog(rootPane, "Please enter all fields", "Empty Fields", JOptionPane.ERROR_MESSAGE);
                    } else {
                        try {
                            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                            PreparedStatement pst = conn.prepareStatement("UPDATE userdb SET Email=?, Password=?, Name=? WHERE Email=?");
                            pst.setString(1, newEmail);
                            pst.setString(2, newPassword);
                            pst.setString(3, newName);
                            pst.setString(4, email);
                            pst.executeUpdate();
                            JOptionPane.showMessageDialog(rootPane, "Record updated successfully.");
                            load_table();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(rootPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void load_table() {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            PreparedStatement pst;

            pst = conn.prepareStatement("select * from userdb");
            rs = pst.executeQuery();
            // Set the model for the table with the ResultSet
            showTable.setModel(buildTableModel(rs));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to convert ResultSet to DefaultTableModel
    public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();

        // Create column names array
        String[] columnNames = new String[metaData.getColumnCount()];
        for (int column = 0; column < metaData.getColumnCount(); column++) {
            columnNames[column] = metaData.getColumnName(column + 1);
        }

        // Create data array
        Object[][] data = new Object[0][];
        ArrayList<Object[]> rows = new ArrayList<>();
        while (rs.next()) {
            Object[] newRow = new Object[metaData.getColumnCount()];
            for (int i = 0; i < metaData.getColumnCount(); i++) {
                newRow[i] = rs.getObject(i + 1);
            }
            rows.add(newRow);
        }
        data = rows.toArray(data);

        return new DefaultTableModel(data, columnNames);
    }

    public ArrayList<UserClass> userList() {
        ArrayList<UserClass> usersList = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String query = "SELECT Email, Password, Name FROM userdb";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            UserClass user;

            while(rs.next()) {
                String email = rs.getString("Email");
                String password = rs.getString("Password");
                String name = rs.getString("Name");
                user = new UserClass(email, password, name);
                usersList.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return usersList;
    }

    public void show_user() {
        ArrayList<UserClass> list = userList();
        DefaultTableModel model = (DefaultTableModel) showTable.getModel();
        Object[] row  = new Object[3];

        for(int i=0 ; i<list.size() ; i++) {
            row[0]=list.get(i).getEmail();
            row[1]=list.get(i).getPassword();
            row[2]=list.get(i).getName();
            model.addRow(row);
        }

    }

    public void createTable() {
        showTable.setModel(new DefaultTableModel(
                null,
                new String[] {"Email","Password","Name"}
        ));

        TableColumnModel columns = showTable.getColumnModel();

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        columns.getColumn(0).setCellRenderer(cellRenderer);
        columns.getColumn(1).setCellRenderer(cellRenderer);
        columns.getColumn(2).setCellRenderer(cellRenderer);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Admin_Manage_Users();
            }
        });
    }
}
