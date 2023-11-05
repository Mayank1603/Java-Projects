import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
public class CancellationForm extends JFrame implements ActionListener{
    JLabel NameLabel, PhoneNoLabel, DateLabel;
    JTextField nameField, PhoneNoField, DateField;
    JButton submitButton;

    CancellationForm() {
        setTitle("Cancellation Page");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.getHSBColor(300, 200, 300));

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        NameLabel = new JLabel("Name:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(NameLabel, gbc);

        nameField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(nameField, gbc);

        PhoneNoLabel = new JLabel("Phone No:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(PhoneNoLabel, gbc);

        PhoneNoField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(PhoneNoField, gbc);

        DateLabel = new JLabel("Date Of Journey:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(DateLabel, gbc);

        DateField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(DateField, gbc);

        submitButton = new JButton("Submit");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(submitButton, gbc);
        submitButton.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e){
        String name = nameField.getText();
        String phoneno = PhoneNoField.getText();
        String date = DateField.getText();
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "CSD73";
        String password = "mayank17032003";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "DELETE FROM reservation WHERE name = ? AND mobile_no = ? AND dates = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, phoneno);
            preparedStatement.setString(3, date);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Ticket Cancelled Successfully!!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Ticket Cancelled Failed!!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static void main(String[] args){
        CancellationForm res = new CancellationForm();
        res.setVisible(true);
    }
}
