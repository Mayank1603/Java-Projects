import java.awt.*;
import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
public class loginForm extends JFrame implements ActionListener{
    JLabel userLabel, passLabel;
    JTextField user;
    JPasswordField pass;
    JButton loginButton, bookTicketButton, cancelTicketButton;
    public loginForm(){
        setTitle("Login Form");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.getHSBColor(300, 200, 300));

        setLayout(null);
        userLabel = new JLabel("Username");
        userLabel.setBounds(10, 20, 80, 25);
        passLabel = new JLabel("Password");
        passLabel.setBounds(10, 50, 80, 25);
        user = new JTextField();
        user.setBounds(100, 20, 165, 25);
        pass = new JPasswordField();
        pass.setBounds(100, 50, 165, 25);
        loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 100, 25);
        loginButton.addActionListener(this);
        bookTicketButton = new JButton("Book Ticket");
        bookTicketButton.setBounds(10, 110, 120, 25);
        bookTicketButton.setVisible(false); // Initially set to invisible
        cancelTicketButton = new JButton("Cancel Ticket");
        cancelTicketButton.setBounds(150, 110, 120, 25);
        cancelTicketButton.setVisible(false);
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
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE username='" + userN + "' AND password='" + passW + "'");
            if (resultSet.next()) {
                JOptionPane.showMessageDialog(this, "Login Successful!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                bookTicketButton.setVisible(true); // Show the buttons after successful login
                cancelTicketButton.setVisible(true);
                bookTicketButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ReservationForm r = new ReservationForm();
                        r.setVisible(true);
                    }
                });
                cancelTicketButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        CancellationForm r = new CancellationForm();
                        r.setVisible(true);
                    }
                });
                add(bookTicketButton);
                add(cancelTicketButton);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or Password!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
    public static void main(String[] args){
        loginForm login  = new loginForm();
        login.setVisible(true);
    }
}
