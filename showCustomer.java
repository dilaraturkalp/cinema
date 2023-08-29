import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class showCustomer extends JFrame{
   
	
	private JPanel contentPane;

    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	
                    showCustomer window = new showCustomer();
                    window.setLocationRelativeTo(null);
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public showCustomer() throws SQLException {
    	
       setForeground(new Color(128, 0, 0));
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setResizable(false);
       getContentPane().setBackground(new Color(128, 0, 0));

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(new Color(128, 0, 0));
        getContentPane().add(contentPane);

          

        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Name", "Surname", "Phone", "Eposta", "Registration Year", "Gold Customer"}, 0);
        JTable Table = new JTable(model);
        Table.setBackground(new Color(128, 0, 0));
        Table.setForeground(Color.WHITE);
        Table.setFont(new Font("Arial", Font.CENTER_BASELINE, 12));
        Table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        Table.getTableHeader().setBackground(new Color(255, 255, 200));
        Table.getTableHeader().setForeground(Color.BLACK);
        

        dbconnection cnn = new dbconnection();

        try (Connection connection = DriverManager.getConnection(cnn.db_url, cnn.user, cnn.password);
             Statement statement = connection.createStatement()) {

            String query = "SELECT customer_id, name, surname, phone, Eposta, registiration_year, "
                    + "CASE WHEN YEAR(CURDATE()) - registiration_year >= 10 THEN 'Yes' ELSE 'No' END AS 'gold_customer' "
                    + "FROM customers";

            String updateQuery = "UPDATE customers SET gold_customer = CASE WHEN YEAR(CURDATE()) - registiration_year >= 10 THEN 'Yes' ELSE 'No' END";

            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.executeUpdate();

            ResultSet resultSet = statement.executeQuery(query);

            // Veritabanından çekilen müşteri bilgilerini tabloya ekleme
            while (resultSet.next()) {
                int id = resultSet.getInt("customer_id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String phone = resultSet.getString("phone");
                String eposta = resultSet.getString("Eposta");
                int registrationYear = resultSet.getInt("registiration_year");
                String goldCustomer = resultSet.getString("gold_customer");

                Object[] rowData = {id, name, surname, phone, eposta, registrationYear, goldCustomer};
                model.addRow(rowData);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        scrollPane.setViewportView(panel);
        
        int rowHeight = 43;
        Table.setRowHeight(rowHeight);
        
        panel.add(new JScrollPane(Table));

        JLabel goldCountLabel = new JLabel();
        
        goldCountLabel.setBackground(new Color(128, 0, 0)); // Arka plan rengi
        goldCountLabel.setForeground(Color.BLACK); // Yazı rengi
        goldCountLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        goldCountLabel.setBounds(10, 246, 300, 24); // Pozisyonu güncellendi
        panel.add(goldCountLabel);

        try (Connection connection = DriverManager.getConnection(cnn.db_url, cnn.user, cnn.password);
             Statement statement = connection.createStatement()) {

            ResultSet countRs = statement.executeQuery("SELECT COUNT(*) AS gold_count \r\n"
                    + "FROM customers\r\n"
                    + "WHERE EXISTS (SELECT * FROM customers WHERE gold_customer = 'Yes') AND gold_customer = 'YES';");

            if (countRs.next()) {
                int goldCount = countRs.getInt("gold_count");
                goldCountLabel.setText("Total Gold Customer: " + goldCount);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        
       setSize(900, 500);
    }
}
