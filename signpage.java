import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.Button;
import java.awt.Point;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class signpage extends JFrame {

	private JPanel contentPane;
	private JTextField textField_username;
	private JPasswordField textField_password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					signpage frame = new signpage();
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
	public signpage() {
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
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(signpage.class.getResource("/images/password.png")));
		lblNewLabel_3.setBounds(420, 232, 40, 35);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(signpage.class.getResource("/images/username.png")));
		lblNewLabel_4.setBounds(420, 143, 35, 35);
		contentPane.add(lblNewLabel_4);
		
		textField_username = new JTextField();
		textField_username.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		textField_username.setBackground(new Color(255, 255, 255));
		textField_username.setSelectedTextColor(new Color(255, 255, 255));
		textField_username.setBounds(465, 148, 200, 30);
		contentPane.add(textField_username);
		textField_username.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(465, 169, 200, 6);
		contentPane.add(separator);
		
		JLabel lblNewLabel_5 = new JLabel("USERNAME");
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5.setBounds(465, 118, 87, 23);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("PASSWORD");
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_6.setBounds(465, 215, 122, 17);
		contentPane.add(lblNewLabel_6);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(465, 265, 200, 2);
		contentPane.add(separator_1);
		
		JButton btnNewButton = new JButton("SIGN IN");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
				

				
			}
		});
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setBounds(508, 304, 122, 35);
		contentPane.add(btnNewButton);
		
		JButton btnSgnUp = new JButton("SIGN UP");
		btnSgnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				signup_page sign_up = new signup_page();
				setVisible(false);
				sign_up.setVisible(true);
			}
		});
		btnSgnUp.setForeground(Color.BLACK);
		btnSgnUp.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSgnUp.setBackground(new Color(255, 255, 255));
		btnSgnUp.setBounds(508, 349, 122, 35);
		contentPane.add(btnSgnUp);
		
		textField_password = new JPasswordField();
		textField_password.setBounds(465, 237, 200, 30);
		contentPane.add(textField_password);
	}
	
	public void login() {
		String username = textField_username.getText();
		String password = textField_password.getText();
		
		dbconnection dbconnection = new dbconnection();
		
      try (Connection connection = DriverManager.getConnection(dbconnection.db_url, dbconnection.user, dbconnection.password);
				
				Statement statement = connection.createStatement();) {

    	        PreparedStatement pstatement = connection.prepareStatement("SELECT*FROM users WHERE username=? AND password=?");
    	        
    	        pstatement.setString(1,username);
    	        pstatement.setString(2,password);
    	        
    	        ResultSet result = pstatement.executeQuery();
    	        
    	        if(username.equals("admin") && password.equals("358857")) {
    				admin admin = new admin();
    				setVisible(false);
    				admin.setVisible(true);
    			}else {
    				 if(result.next()) {
    	    	        	JOptionPane.showMessageDialog(null, "Login succesfull");
    	    	        	setVisible(false);
    	    	        	showMovie showMovie = new showMovie();
    	    	        	showMovie.frame.setVisible(true);
    	    	        	
    	    	        }else {
    	    	        	
    	    	        	JOptionPane.showMessageDialog(null, "Username or password is incorrect");
    	    	        }

    			} 	       
			connection.close();

		} catch (Exception exception) {

			exception.printStackTrace();

		}
	
	}
}
