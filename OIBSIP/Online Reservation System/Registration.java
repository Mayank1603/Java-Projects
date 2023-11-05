import java.awt.*;
import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
public class Registration extends JFrame implements ActionListener{
    JLabel userLabel, passLabel;
    JTextField user;
    JPasswordField pass;
    JButton loginButton, ReservationButton;
    public Registration(){
        setTitle("Registration Form");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.getHSBColor(300, 200, 300));

        setLayout(null);
        userLabel = new JLabel("Enter the Username:");
        userLabel.setBounds(10, 20, 150, 25);
        passLabel = new JLabel("Enter the Password:");
        passLabel.setBounds(10, 50, 150, 25);
        user = new JTextField();
        user.setBounds(160, 20, 120, 25);
        pass = new JPasswordField();
        pass.setBounds(160, 50, 120, 25);
        loginButton = new JButton("Register");
        loginButton.setBounds(10, 80, 100, 25);
        loginButton.addActionListener(this);
        ReservationButton = new JButton("Book Ticket");
        ReservationButton.setBounds(10, 110, 120, 25);
        ReservationButton.setVisible(false);
        add(userLabel);
        add(passLabel);
        add(user);
        add(pass);
        add(loginButton);
    }
    public void actionPerformed(ActionEvent e){
        String userN = user.getText();
        String passW = String.valueOf(pass.getPassword());
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "CSD73";
        String password = "mayank17032003";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String checkUserQuery = "SELECT COUNT(*) FROM users WHERE username = ?";
            PreparedStatement checkUserStatement = connection.prepareStatement(checkUserQuery);
            checkUserStatement.setString(1, userN);
            ResultSet resultSet = checkUserStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            resultSet.close();
            if (count > 0) {
                System.out.println("Username already exists. Please choose a different username.");
                return; // Exit the method, do not perform the insert
            }
            String insertUserQuery = "INSERT INTO users(username, password) VALUES (?, ?)";
            PreparedStatement insertUserStatement = connection.prepareStatement(insertUserQuery);
            insertUserStatement.setString(1, userN);
            insertUserStatement.setString(2, passW);
            insertUserStatement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Registration Successful!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            ReservationButton.setVisible(true);
            ReservationButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    ReservationForm r = new ReservationForm();
                    r.setVisible(true);
                }
            });
            add(ReservationButton);
            insertUserStatement.close();
            checkUserStatement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
    public static void main(String[] args){
        Registration r  = new Registration();
        r.setVisible(true);
    }
}
