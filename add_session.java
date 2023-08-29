import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class add_session extends JFrame {
	
	private JComboBox<String> comboBoxSession =  new JComboBox();
	private JComboBox<String> hallcombobox =  new JComboBox();

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					add_session frame = new add_session();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public add_session() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 527, 366);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 0, 0));
		panel.setBounds(177, 10, 322, 309);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel session_textfield = new JLabel("Session");
		session_textfield.setHorizontalAlignment(SwingConstants.CENTER);
		session_textfield.setForeground(Color.WHITE);
		session_textfield.setFont(new Font("Tahoma", Font.BOLD, 15));
		session_textfield.setBounds(33, 68, 109, 24);
		panel.add(session_textfield);
		
		JLabel hall_textfield = new JLabel("Hall");
		hall_textfield.setHorizontalAlignment(SwingConstants.CENTER);
		hall_textfield.setForeground(Color.WHITE);
		hall_textfield.setFont(new Font("Tahoma", Font.BOLD, 15));
		hall_textfield.setBounds(33, 138, 109, 24);
		panel.add(hall_textfield);
		
		JButton exit = new JButton("Exit");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
			}
		});
		exit.setFont(new Font("Tahoma", Font.BOLD, 15));
		exit.setBackground(Color.WHITE);
		exit.setBounds(101, 251, 145, 27);
		panel.add(exit);
		
		JButton addmovie_1 = new JButton("Add");
		addmovie_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String session = comboBoxSession.getSelectedItem().toString();
				int hall_id = Integer.parseInt(hallcombobox.getSelectedItem().toString());
				insertSessionMovie(session,hall_id);
		
			}
		});
		addmovie_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		addmovie_1.setBackground(Color.WHITE);
		addmovie_1.setBounds(101, 214, 145, 27);
		panel.add(addmovie_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(addmoviepage.class.getResource("/images/pngwing.com (32).png")));
		lblNewLabel.setBounds(-12, 52, 179, 193);
		contentPane.add(lblNewLabel);
		
		JButton btnBack = new JButton("<-");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				admin admin = new admin();
				admin.setVisible(true);
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnBack.setBackground(Color.WHITE);
		btnBack.setBounds(10, 10, 47, 21);
		contentPane.add(btnBack);
		
		comboBoxSession = new JComboBox<>();
		comboBoxSession.setBounds(142, 69, 122, 27);
		panel.add(comboBoxSession);
		comboBoxSession.setBackground(Color.WHITE);
		
	    
	    hallcombobox = new JComboBox<>();
	    hallcombobox.setBounds(142, 139, 122, 27);
	    panel.add(hallcombobox);
		hallcombobox.setBackground(Color.WHITE);
		
		loadSession();
		loadHall();
	}
	
	private void loadSession() {
	    dbconnection cnn = new dbconnection();
         
	    try (Connection connection = DriverManager.getConnection(cnn.db_url, cnn.user, cnn.password);
	         Statement statement = connection.createStatement()) {

	        // Film adlarını ComboBox'a yükle
	        String movieQuery = "SELECT movie_time FROM session";
	        ResultSet movieResultSet = statement.executeQuery(movieQuery);
	        
	        DefaultComboBoxModel<String> movieComboBoxModel = new DefaultComboBoxModel<>();
	          
	        while (movieResultSet.next()){
	            String movieTime = movieResultSet.getString("movie_time");
	            comboBoxSession.addItem(movieTime);
	        }
	        
	        movieResultSet.close();
	        
	        connection.close();
	    } catch (Exception exception) {
	        exception.printStackTrace();
	       
	    }

	}
	
	
	private void loadHall() {
	    dbconnection cnn = new dbconnection();
         
	    try (Connection connection = DriverManager.getConnection(cnn.db_url, cnn.user, cnn.password);
	         Statement statement = connection.createStatement()) {

	        // Film adlarını ComboBox'a yükle
	        String movieQuery = "SELECT hall_id FROM hall";
	        ResultSet movieResultSet = statement.executeQuery(movieQuery);
	        
	        DefaultComboBoxModel<String> movieComboBoxModel = new DefaultComboBoxModel<>();
	          
	        while (movieResultSet.next()){
	            String hall_id = movieResultSet.getString("hall_id");
	            hallcombobox.addItem(hall_id);
	        }
	        
	        movieResultSet.close();
	        
	        connection.close();
	    } catch (Exception exception) {
	        exception.printStackTrace();
	       
	    }

	}
	
	public void insertSessionMovie(String session, int hall_id) {

		dbconnection cnn = new dbconnection();

		try (Connection connection = DriverManager.getConnection(cnn.db_url, cnn.user, cnn.password);
				
				Statement statement = connection.createStatement();) {

			
			// Database Connection String
			
			int session_id = 0;
	        // Session tablosundan session_id'yi almak için SELECT sorgusu kullanın
	        String sessionQuery = "SELECT session_id FROM session WHERE movie_time = '" + session + "'";
	        
	        ResultSet sessionResult = statement.executeQuery(sessionQuery);
	        
	        if (sessionResult.next()) {
	            session_id = sessionResult.getInt("session_id");
	        }
	        
	        int movie_id = 0;
	        // Session tablosundan session_id'yi almak için SELECT sorgusu kullanın
	        String movieQuery = "SELECT MAX(movie_id) AS movie_id FROM movies";
	        
	        ResultSet movieResult = statement.executeQuery(movieQuery);
	        
	        if (movieResult.next()) {
	        	movie_id = movieResult.getInt("movie_id");
	        }
	          

			String sql = "INSERT INTO movie_session_hall(movie_id, session_id, hall_id) VALUES ('" + movie_id + "', '" + session_id + "', '"
					+ hall_id + "')";
			
			int result = statement.executeUpdate(sql);
			 
			 if(result > 0) {
	        	JOptionPane.showMessageDialog(null, "Movie added succesfully");
	        	
	        }else {
	        	JOptionPane.showMessageDialog(null, "Somethings wrong!");
	        }

			connection.close();

		} catch (Exception exception) {

			exception.printStackTrace();

		}

	}
	
}
