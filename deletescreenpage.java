import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class deletescreenpage extends JFrame {
	
	private JComboBox<String> comboBoxMovieName =  new JComboBox();

	
	private JPanel contentPane;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					deletescreenpage frame = new deletescreenpage();
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
	public deletescreenpage() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 524, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 0, 0));
		panel.setBounds(178, 10, 322, 243);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Movie Name");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(38, 95, 109, 24);
		panel.add(lblNewLabel_2);
		
		
		JButton btnNewButton = new JButton("Delete Movie");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				String selectedMovie = comboBoxMovieName.getSelectedItem().toString();
		        deleteMovie(selectedMovie);
		        setVisible(false);
				admin admin = new admin();
				admin.setVisible(true);
			}
		});
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(100, 193, 145, 24);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(deletescreenpage.class.getResource("/images/pngwing.com (32).png")));
		lblNewLabel.setBounds(-21, 22, 189, 212);
		contentPane.add(lblNewLabel);
		
		comboBoxMovieName = new JComboBox<>();
	    comboBoxMovieName.setBackground(Color.WHITE);
	    comboBoxMovieName.setBounds(157, 96, 122, 27);
	    panel.add(comboBoxMovieName);
	    
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
		loadComboBoxData();
		
	
        
	}
	
	
	private void loadComboBoxData() {
	    dbconnection cnn = new dbconnection();
         
	    try (Connection connection = DriverManager.getConnection(cnn.db_url, cnn.user, cnn.password);
	         Statement statement = connection.createStatement()) {

	        // Film adlarını ComboBox'a yükle
	        String movieQuery = "SELECT movie_name FROM movies";
	        ResultSet movieResultSet = statement.executeQuery(movieQuery);
	        
	        DefaultComboBoxModel<String> movieComboBoxModel = new DefaultComboBoxModel<>();
	          
	        while (movieResultSet.next()) {
	            String movieName = movieResultSet.getString("movie_name");
	            comboBoxMovieName.addItem(movieName);
	        }
	        movieResultSet.close();
	        
	        connection.close();
	    } catch (Exception exception) {
	        exception.printStackTrace();
	       
	    }
	}


	public void deleteMovie(String movie_name) {

		dbconnection cnn = new dbconnection();

		try (Connection connection = DriverManager.getConnection(cnn.db_url, cnn.user, cnn.password);
				Statement statement = connection.createStatement();) {

			// Database Connection String
			

			int movie_id = 0;
	        // Session tablosundan session_id'yi almak için SELECT sorgusu kullanın
	        String MovieQuery = "SELECT movie_id FROM movies WHERE movie_name = '" + movie_name + "'";
	        
	        ResultSet MovieResult = statement.executeQuery(MovieQuery);
	        
	        if (MovieResult.next()) {
	            movie_id = MovieResult.getInt("movie_id");
	        }
	        
			
			PreparedStatement pstatement = connection.prepareStatement("DELETE FROM movies WHERE movie_name=?");
			PreparedStatement pstatement2 = connection.prepareStatement("DELETE FROM movie_session_hall WHERE movie_id=?");
			
			
	        
	        pstatement.setString(1,movie_name);		
	        pstatement2.setInt(1, movie_id);
	        
			int result = pstatement.executeUpdate();
			pstatement2.executeUpdate();

			if (result > 0) {
				JOptionPane.showMessageDialog(null, "Movie deleted succesfully");
				comboBoxMovieName.removeAllItems();
				loadComboBoxData();

			} else {
				JOptionPane.showMessageDialog(null, "Something went wrong!");
			}

			connection.close();

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
