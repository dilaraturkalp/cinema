import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

public class addmoviepage extends JFrame {

	private JPanel contentPane;
	private JTextField textField_moviename;
	private JTextField textField_director;
	private JTextField textField_genre;
	private JTextField textField_language;
	private JTextField textField_duration;
	private JTextField textField_rating;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addmoviepage frame = new addmoviepage();
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
	public addmoviepage() {
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
		
		JLabel lblNewLabel_2 = new JLabel("Director");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(33, 68, 109, 24);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Duration");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_1.setBounds(33, 170, 109, 24);
		panel.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Rating");
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2.setForeground(Color.WHITE);
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_2.setBounds(33, 204, 109, 24);
		panel.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("Genre");
		lblNewLabel_2_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_2_1.setBounds(33, 102, 109, 24);
		panel.add(lblNewLabel_2_2_1);
		
		JLabel lblNewLabel_2_2_2 = new JLabel("Language");
		lblNewLabel_2_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2_2.setForeground(Color.WHITE);
		lblNewLabel_2_2_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_2_2.setBounds(33, 136, 109, 24);
		panel.add(lblNewLabel_2_2_2);
		
		JLabel lblNewLabel_2_2_3 = new JLabel("Movie Name");
		lblNewLabel_2_2_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2_3.setForeground(Color.WHITE);
		lblNewLabel_2_2_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_2_3.setBounds(33, 34, 109, 24);
		panel.add(lblNewLabel_2_2_3);
		
		JButton addmovie = new JButton("Continue");
		addmovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String moviename = textField_moviename.getText();
				String director = textField_director.getText();
				String genre = textField_genre.getText();
				String language = textField_language.getText();
				int  duration = Integer.parseInt(textField_duration.getText());
				int rating = Integer.parseInt(textField_rating.getText());
				
				insertMovie(moviename,director,genre,language,duration,rating);
				setVisible(false);
				add_session addsession = new add_session();
				addsession.setVisible(true);
				
			}
		});
		addmovie.setFont(new Font("Tahoma", Font.BOLD, 15));
		addmovie.setBackground(Color.WHITE);
		addmovie.setBounds(99, 264, 145, 24);
		panel.add(addmovie);
		
		textField_moviename = new JTextField();
		textField_moviename.setBackground(new Color(255, 255, 255));
		textField_moviename.setBounds(164, 34, 119, 24);
		panel.add(textField_moviename);
		textField_moviename.setColumns(10);
		
		textField_director = new JTextField();
		textField_director.setColumns(10);
		textField_director.setBackground(Color.WHITE);
		textField_director.setBounds(164, 73, 119, 24);
		panel.add(textField_director);
		
		textField_genre = new JTextField();
		textField_genre.setColumns(10);
		textField_genre.setBackground(Color.WHITE);
		textField_genre.setBounds(164, 107, 119, 24);
		panel.add(textField_genre);
		
		textField_language = new JTextField();
		textField_language.setColumns(10);
		textField_language.setBackground(Color.WHITE);
		textField_language.setBounds(164, 141, 119, 24);
		panel.add(textField_language);
		
		textField_duration = new JTextField();
		textField_duration.setColumns(10);
		textField_duration.setBackground(Color.WHITE);
		textField_duration.setBounds(164, 175, 119, 24);
		panel.add(textField_duration);
		
		textField_rating = new JTextField();
		textField_rating.setColumns(10);
		textField_rating.setBackground(Color.WHITE);
		textField_rating.setBounds(164, 209, 119, 24);
		panel.add(textField_rating);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(164, 56, 119, 2);
		panel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(164, 90, 119, 2);
		panel.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(164, 125, 119, 2);
		panel.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(164, 159, 119, 2);
		panel.add(separator_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(164, 192, 119, 2);
		panel.add(separator_4);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(164, 226, 119, 2);
		panel.add(separator_5);
		
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
	}
	
	public void insertMovie(String movie_name, String director,  String genre, String language, int duration, int rating) {

		dbconnection cnn = new dbconnection();

		try (Connection connection = DriverManager.getConnection(cnn.db_url, cnn.user, cnn.password);
				
				Statement statement = connection.createStatement();) {

			
			// Database Connection String

			String sql = "INSERT INTO movies(movie_name,director,genre,language,duration,rating) VALUES ('"+ movie_name + "', '" + director + "', '"
					+ genre + "', '" + language + "','" + duration + "','" + rating + "')";

			
			
			
			statement.executeUpdate(sql);

			connection.close();

		} catch (Exception exception) {

			exception.printStackTrace();

		}

	}
}
