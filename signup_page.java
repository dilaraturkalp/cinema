import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.Button;
import java.awt.Point;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class signup_page extends JFrame {

	private JPanel contentPane;
	private JTextField textField_name;
	private JTextField textField_surname;
	private JTextField textField_password;
	private JTextField textField_username;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					signup_page frame = new signup_page();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public signup_page() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 733, 473);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(100, 13, 15));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(100, 13, 15));
		panel_3.setBounds(10, 10, 400, 416);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("INCOM");
		lblNewLabel_2.setForeground(new Color(252, 249, 180));
		lblNewLabel_2.setFont(new Font("Myanmar Text", Font.BOLD, 40));
		lblNewLabel_2.setBounds(124, 93, 166, 47);
		panel_3.add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(signpage.class.getResource("/images/table.png")));
		lblNewLabel.setBounds(42, 20, 358, 186);
		panel_3.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(signpage.class.getResource("/images/seat.png")));
		lblNewLabel_1.setBounds(0, 66, 400, 350);
		panel_3.add(lblNewLabel_1);
		
		textField_name = new JTextField();
		textField_name.setBackground(new Color(255, 255, 255));
		textField_name.setSelectedTextColor(new Color(255, 255, 255));
		textField_name.setBounds(465, 129, 200, 30);
		contentPane.add(textField_name);
		textField_name.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(465, 155, 200, 6);
		contentPane.add(separator);
		
		textField_surname = new JTextField();
		textField_surname.setBackground(new Color(255, 255, 255));
		textField_surname.setBounds(465, 204, 200, 30);
		contentPane.add(textField_surname);
		textField_surname.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("USERNAME");
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5.setBounds(465, 36, 87, 23);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("PASSWORD");
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_6.setBounds(470, 262, 122, 17);
		contentPane.add(lblNewLabel_6);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(465, 232, 200, 2);
		contentPane.add(separator_1);
		
		JButton btnSgnUp = new JButton("SIGN UP");
		btnSgnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textField_username.getText();
				String name = textField_name.getText();
				String surname = textField_surname.getText();
				String password = textField_password.getText();
				
				insertSignupValues(username,name,surname,password);
				setVisible(false);
				signpage signpage = new signpage();
				signpage.setVisible(true);
				
			}
		});
		btnSgnUp.setForeground(Color.BLACK);
		btnSgnUp.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSgnUp.setBackground(new Color(255, 255, 255));
		btnSgnUp.setBounds(508, 349, 122, 35);
		contentPane.add(btnSgnUp);
		
		textField_password = new JTextField();
		textField_password.setSelectedTextColor(Color.WHITE);
		textField_password.setColumns(10);
		textField_password.setBackground(new Color(255, 255, 255));
		textField_password.setBounds(465, 280, 200, 30);
		contentPane.add(textField_password);
		
		textField_username = new JTextField();
		textField_username.setSelectedTextColor(Color.WHITE);
		textField_username.setColumns(10);
		textField_username.setBackground(new Color(255, 255, 255));
		textField_username.setBounds(465, 61, 200, 30);
		contentPane.add(textField_username);
		
		JLabel lblNewLabel_5_1 = new JLabel("NAME");
		lblNewLabel_5_1.setForeground(Color.WHITE);
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5_1.setBounds(465, 101, 87, 23);
		contentPane.add(lblNewLabel_5_1);
		
		JLabel lblNewLabel_6_1 = new JLabel("SURNAME");
		lblNewLabel_6_1.setForeground(Color.WHITE);
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_6_1.setBounds(465, 188, 122, 17);
		contentPane.add(lblNewLabel_6_1);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(465, 89, 200, 2);
		contentPane.add(separator_1_1);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setBounds(465, 307, 200, 2);
		contentPane.add(separator_1_2);
	}
	
	public void insertSignupValues(String username, String name, String surname, String password) {

		dbconnection cnn = new dbconnection();

		try (Connection connection = DriverManager.getConnection(cnn.db_url, cnn.user, cnn.password);
				
				Statement statement = connection.createStatement();) {

			
			// Database Connection String
			String sql = "INSERT INTO USERS(username,name,surname,password) VALUES ('" + username + "', '" + name + "', '"
					+ surname + "', '" + password + "')";

			statement.executeUpdate(sql);

			connection.close();

		} catch (Exception exception) {

			exception.printStackTrace();

		}

	}
	
}
