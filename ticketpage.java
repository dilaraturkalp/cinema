import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.Dimension;
import javax.swing.JToggleButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Component;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;

public class ticketpage extends JFrame {

	private JPanel contentPane;
	private JComboBox<String> comboBoxMovieName =  new JComboBox();
	private JComboBox<String> comboBoxMovieTime =  new JComboBox();
	int count;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ticketpage frame = new ticketpage();
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
	public ticketpage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 845, 508);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 0, 0));
		contentPane.setMinimumSize(new Dimension(200, 200));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 0, 0));
		panel.setBounds(10, 10, 502, 451);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("S C R E E N");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(new Color(192, 192, 192));
		lblNewLabel.setBounds(55, 382, 413, 44);
		panel.add(lblNewLabel);
		JToggleButton[][] seatButtons = new JToggleButton[5][5];
		boolean[][] seatSold = new boolean[5][5]; // keeps track of sold seats
		char[] rows = {'A', 'B', 'C', 'D', 'E'};
		int[] columns = {1, 2, 3, 4, 5};
		for (int i = 0; i < rows.length; i++) {
		    for (int j = 0; j < columns.length; j++) {
		        JToggleButton seatButton = new JToggleButton(rows[i] + String.valueOf(columns[j]));
		        seatButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		        seatButton.setBackground(Color.WHITE);
		        seatButton.setBounds(55 + j * 87, 49 + i * 59, 58, 37);

		        int finalI = i;
		        int finalJ = j;
		        seatButton.addItemListener(new ItemListener() {
		            public void itemStateChanged(ItemEvent e) {

		                if (seatSold[finalI][finalJ]) {

		                    seatButton.setSelected(false);
		                    System.out.println(seatButton.getText() + " is sold");
		                } else {
		                    // if the seat is not sold
		                    if (e.getStateChange() == ItemEvent.SELECTED) {

		                        seatButton.setBackground(Color.GREEN);
		                        System.out.println(seatButton.getText() + " is selected");
		                    } else {

		                        seatButton.setBackground(Color.WHITE);
		                        System.out.println(seatButton.getText() + " is not selected");
		                    }
		                }
		            }
		        });

		        panel.add(seatButton);
		        seatButtons[i][j] = seatButton;
		    }
		}
		
		JLabel lblNewLabel_1 = new JLabel("A");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_1.setBounds(14, 60, 31, 27);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("B");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_1_1.setBounds(14, 118, 31, 27);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("C");
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_1_2.setBounds(14, 171, 31, 27);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("D");
		lblNewLabel_1_3.setForeground(Color.WHITE);
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_1_3.setBounds(14, 232, 31, 27);
		panel.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("E");
		lblNewLabel_1_4.setForeground(Color.WHITE);
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_1_4.setBounds(14, 295, 31, 27);
		panel.add(lblNewLabel_1_4);
		
		JPanel panel_1 = new JPanel();
		panel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel_1.setBackground(new Color(252, 249, 180));
		panel_1.setForeground(new Color(255, 255, 255));
		panel_1.setBounds(533, 10, 288, 451);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Movie Name");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(23, 82, 109, 24);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_2 = new JLabel("Movie Time");
		lblNewLabel_2_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_2.setBounds(23, 145, 109, 24);
		panel_1.add(lblNewLabel_2_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(23, 218, 240, 113);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("One Ticket Price");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(105, 17, 125, 19);
		panel_2.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(ticketpage.class.getResource("/images/pngwing.com (28).png")));
		lblNewLabel_4.setBounds(0, 25, 125, 62);
		panel_2.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("10 $");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(135, 46, 56, 41);
		panel_2.add(lblNewLabel_5);
		
		JButton btnNewButton = new JButton("BUY TICKET");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setBounds(92, 362, 117, 43);

		btnNewButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	count=0;
		        for (int i = 0; i < seatButtons.length; i++) {
		            for (int j = 0; j < seatButtons[i].length; j++) {
		                if (seatButtons[i][j].isSelected()) {
		                    seatSold[i][j] = true;  // mark seat as sold
		                    count++;
		                    seatButtons[i][j].setBackground(Color.RED); 
		                    seatButtons[i][j].setSelected(false);
		                    System.out.println(seatButtons[i][j].getText() + " is sold");
		                }
		            }
		        }
		        
		        JOptionPane.showMessageDialog(null, "Total price: "+(10*count)+" $");
		    }
		});

		panel_1.add(btnNewButton);
		
		comboBoxMovieName = new JComboBox<>();
		comboBoxMovieName.setBounds(142, 83, 122, 27);
		panel_1.add(comboBoxMovieName);
	    comboBoxMovieName.setBackground(Color.WHITE);
	    comboBoxMovieName.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadTime();
            }
        });
	    
		comboBoxMovieTime = new JComboBox<>();
		comboBoxMovieTime.setBounds(142, 146, 122, 27);
		panel_1.add(comboBoxMovieTime);
		comboBoxMovieTime.setBackground(Color.WHITE);
		
		loadmovieName();
        loadTime();
	       
		
	}
	
	
	private void loadmovieName() {
	    dbconnection cnn = new dbconnection();
         
	    try (Connection connection = DriverManager.getConnection(cnn.db_url, cnn.user, cnn.password);
	         Statement statement = connection.createStatement()) {

	        // Film adlarını ComboBox'a yükle
	        String movieQuery = "SELECT movie_name FROM movies WHERE duration IS NOT NULL";
	        ResultSet movieResultSet = statement.executeQuery(movieQuery);
	        
	        DefaultComboBoxModel<String> movieComboBoxModel = new DefaultComboBoxModel<>();
	          
	        while (movieResultSet.next()){
	            String movieName = movieResultSet.getString("movie_name");
	            comboBoxMovieName.addItem(movieName);
	        }
	        
	        movieResultSet.close();
	        
	        connection.close();
	    } catch (Exception exception) {
	        exception.printStackTrace();
	       
	    }

	}
	
	private void loadTime() {
	    dbconnection cnn = new dbconnection();

	    try (Connection connection = DriverManager.getConnection(cnn.db_url, cnn.user, cnn.password);
	         Statement statement = connection.createStatement()) {

	        String selectedMovie = comboBoxMovieName.getSelectedItem().toString();
   
	        String timeQuery = "SELECT S.movie_time FROM movies M, movie_session_hall MS, session S WHERE S.session_id = MS.session_id AND M.movie_id=MS.movie_id AND M.movie_name = ?";
	        		
	        PreparedStatement timeStatement = connection.prepareStatement(timeQuery);
	       
	        
	        timeStatement.setString(1, selectedMovie);
	        ResultSet timeResultSet = timeStatement.executeQuery();

	        DefaultComboBoxModel<String> timeComboBoxModel = new DefaultComboBoxModel<>();

	        while (timeResultSet.next()) {
	            String time = timeResultSet.getString("movie_time");
	            timeComboBoxModel.addElement(time);
	        }
	        timeResultSet.close();

	        comboBoxMovieTime.setModel(timeComboBoxModel);

	        connection.close();
	    } catch (Exception exception) {
	        exception.printStackTrace();
	    
	    }

	}
}
