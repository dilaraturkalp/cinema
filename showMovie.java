import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.sql.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class showMovie {

    public JFrame frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    showMovie window = new showMovie();
                    window.frame.setLocationRelativeTo(null);
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public showMovie() throws SQLException {
        frame = new JFrame();
        frame.setForeground(new Color(128, 0, 0));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.getContentPane().setBackground(new Color(128, 0, 0));

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(new Color(128, 0, 0));
        frame.getContentPane().add(contentPane);

        String[] columnNames = {"Movie Name", "Rating", "Director", "Duration", "Genre", "Language"};

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        JTable table = new JTable(model);
        table.setBackground(new Color(128, 0, 0));

        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(255, 255, 200));
        header.setForeground(Color.WHITE);

        header.setFont(new Font("Arial", Font.BOLD, 14));
        table.setFont(new Font("Arial", Font.CENTER_BASELINE, 12));
        table.setForeground(Color.WHITE);
        header.setForeground(Color.BLACK);


        DefaultTableModel genreModel = new DefaultTableModel(new String[]{"Genre", "Average Rating"}, 0);
        JTable genreTable = new JTable(genreModel);
        genreTable.setBackground(new Color(128, 0, 0));
        genreTable.setForeground(Color.WHITE);
        genreTable.setFont(new Font("Arial", Font.CENTER_BASELINE, 12));
        genreTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        genreTable.getTableHeader().setBackground(new Color(255, 255, 200));
        genreTable.getTableHeader().setForeground(Color.BLACK);


        DefaultTableModel countModel = new DefaultTableModel(new String[]{"Genre", "Total Movies"}, 0);
        JTable countTable = new JTable(countModel);
        countTable.setBackground(new Color(128, 0, 0));
        countTable.setForeground(Color.WHITE);
        countTable.setFont(new Font("Arial", Font.CENTER_BASELINE, 12));
        countTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        countTable.getTableHeader().setBackground(new Color(255, 255, 200));
        countTable.getTableHeader().setForeground(Color.BLACK);


        DefaultTableModel subtitlesModel = new DefaultTableModel(columnNames, 0);
        JTable subtitlesTable = new JTable(subtitlesModel);
        subtitlesTable.setBackground(new Color(128, 0, 0));
        subtitlesTable.setForeground(Color.WHITE);
        subtitlesTable.setFont(new Font("Arial", Font.CENTER_BASELINE, 12));
        subtitlesTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        subtitlesTable.getTableHeader().setBackground(new Color(255, 255, 200));
        subtitlesTable.getTableHeader().setForeground(Color.BLACK);
        
        
        DefaultTableModel durationModel = new DefaultTableModel(new String[]{"Movie Name", "Rating", "Director", "Duration", "Genre", "Language"}, 0);
        JTable durationTable = new JTable(durationModel);
        durationTable.setBackground(new Color(128, 0, 0));
        durationTable.setForeground(Color.WHITE);
        durationTable.setFont(new Font("Arial", Font.CENTER_BASELINE, 12));
        durationTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        durationTable.getTableHeader().setBackground(new Color(255, 255, 200));
        durationTable.getTableHeader().setForeground(Color.BLACK);


        dbconnection cnn = new dbconnection();

        try (Connection connection = DriverManager.getConnection(cnn.db_url, cnn.user, cnn.password);
             Statement statement = connection.createStatement()) {

        	ResultSet rs = statement.executeQuery("SELECT UPPER(movie_name) AS movie_name, rating, director, duration, genre, language FROM movies ORDER BY rating DESC");


            while (rs.next()) {
                String movieName = rs.getString("movie_name");
                float rating = rs.getFloat("rating");
                String director = rs.getString("director");
                int duration = rs.getInt("duration");
                String genre = rs.getString("genre");
                String language = rs.getString("language");

                model.addRow(new Object[]{movieName, rating, director, duration, genre, language});
            }
        }


        try (Connection connection = DriverManager.getConnection(cnn.db_url, cnn.user, cnn.password);
             Statement statement = connection.createStatement()) {

            ResultSet genreRs = statement.executeQuery("SELECT genre, AVG(rating) AS average_rating FROM movies WHERE rating BETWEEN 4 AND 9 GROUP BY genre HAVING average_rating > 5");

            while (genreRs.next()) {

                String genreName = genreRs.getString("genre");
                float avgRating = genreRs.getFloat("average_rating");
                genreModel.addRow(new Object[]{genreName, avgRating});
            }
        }


        try (Connection connection = DriverManager.getConnection(cnn.db_url, cnn.user, cnn.password);
             Statement statement = connection.createStatement()) {

            ResultSet countRs = statement.executeQuery("SELECT genre, COUNT(*) AS total_movies FROM movies GROUP BY genre");

            while (countRs.next()) {

                String genreName = countRs.getString("genre");
                int totalMovies = countRs.getInt("total_movies");
                countModel.addRow(new Object[]{genreName, totalMovies});
            }
        }

        try (Connection connection = DriverManager.getConnection(cnn.db_url, cnn.user, cnn.password);
             Statement statement = connection.createStatement()) {

            ResultSet subtitlesRs = statement.executeQuery("SELECT movie_name, rating, director, duration, genre, language\r\n"
                    + "FROM movies\r\n"
                    + "WHERE language LIKE '%altyazı%'\r\n"
                    + "OR language LIKE '%dublaj%'\r\n"
                    + "");

            while (subtitlesRs.next()) {
                String movieName = subtitlesRs.getString("movie_name");
                float rating = subtitlesRs.getFloat("rating");
                String director = subtitlesRs.getString("director");
                int duration = subtitlesRs.getInt("duration");
                String genre = subtitlesRs.getString("genre");
                String language = subtitlesRs.getString("language");

                subtitlesModel.addRow(new Object[]{movieName, rating, director, duration, genre, language});
            }
        }
        
        try (Connection connection = DriverManager.getConnection(cnn.db_url, cnn.user, cnn.password);
                Statement statement = connection.createStatement()) {

               ResultSet durationRs = statement.executeQuery("SELECT movie_name, rating, director, duration, genre, language\r\n"
                       + "FROM movies\r\n"
                       + "WHERE duration IN (123,124, 127,128)\r\n"
                       + "");

               while (durationRs.next()) {
                   String movieName = durationRs.getString("movie_name");
                   float rating = durationRs.getFloat("rating");
                   String director = durationRs.getString("director");
                   int duration = durationRs.getInt("duration");
                   String genre = durationRs.getString("genre");
                   String language = durationRs.getString("language");

                   durationModel.addRow(new Object[]{movieName, rating, director, duration, genre, language});
               }
           }
        

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        scrollPane.setViewportView(panel);

        JPanel topPanel = new JPanel();
        contentPane.add(topPanel, BorderLayout.NORTH);

        int rowHeight = 43;
        table.setRowHeight(rowHeight);
        genreTable.setRowHeight(rowHeight);
        countTable.setRowHeight(rowHeight);
        subtitlesTable.setRowHeight(rowHeight);
        durationTable.setRowHeight(rowHeight);

        JLabel label = new JLabel(" - - - - - - - - - - - - - - - - - -   M   O   V   I   E        L   I   S   T   - - - - - - - - - - - - - - - - - -");
        topPanel.add(label);

        JButton btnContinue = new JButton("Continue");
        btnContinue.setPreferredSize(new Dimension(10, 30));
        btnContinue.setBackground(Color.WHITE);
        btnContinue.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ticketpage ticket = new ticketpage();
                ticket.setVisible(true);
                frame.setVisible(false);
            }
        });
        contentPane.add(btnContinue, BorderLayout.SOUTH);

        panel.add(new JScrollPane(table));
        panel.add(new JScrollPane(genreTable));
        panel.add(new JScrollPane(countTable));
        panel.add(new JScrollPane(subtitlesTable));
        panel.add(new JScrollPane(durationTable));
        
        JLabel genreCountLabel = new JLabel();
       
        genreCountLabel.setBackground(new Color(128, 0, 0)); // Arka plan rengi
        genreCountLabel.setForeground(Color.BLACK); // Yazı rengi
        genreCountLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        genreCountLabel.setBounds(33, 208, 200, 24);
        panel.add(genreCountLabel);

        JLabel totalDurationLabel = new JLabel();
        
        totalDurationLabel.setBackground(new Color(128, 0, 0)); // Arka plan rengi
        totalDurationLabel.setForeground(Color.BLACK); // Yazı rengi
        totalDurationLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        totalDurationLabel.setBounds(33, 238, 200, 24);
        panel.add(totalDurationLabel);

        try (Connection connection = DriverManager.getConnection(cnn.db_url, cnn.user, cnn.password);
             Statement statement = connection.createStatement()) {

            ResultSet countRs = statement.executeQuery("SELECT COUNT(DISTINCT genre) AS genre_count FROM movies");

            if (countRs.next()) {
                int genreCount = countRs.getInt("genre_count");
                genreCountLabel.setText("Total Genres: " + genreCount);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(cnn.db_url, cnn.user, cnn.password);
                Statement statement = connection.createStatement()) {

               ResultSet durationRs = statement.executeQuery("SELECT SUM(duration) AS total_duration FROM movies");

               if (durationRs.next()) {
                   int totalDuration = durationRs.getInt("total_duration");
                   totalDurationLabel.setText("Total Duration: " + totalDuration + " minutes");
               }
           } catch (Exception exception) {
               exception.printStackTrace();
           }

        frame.setSize(900, 500);
    }
}
