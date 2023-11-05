import java.awt.*;
import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.List;
public class ReservationForm extends JFrame implements ActionListener{
    JLabel NameLabel, AgeLabel, PhoneNoLabel, GenderLabel, TrainLabel, TicketsLabel, SourceLabel, DestinationLabel, DateLabel, ClassLabel;
    JTextField nameField, ageField, ticketField, PhoneNoField, SourceField, DestinationField, DateField;
    JComboBox<String> genderDropdown, trainDropdown, classDropdown;
    JButton submitButton;
    String[] genders  = {"Male", "Female", "Other"};
    String [] classtype = {"SL", "AC3", "AC2", "AC1", "GN"};
    List<String> trains = new ArrayList<>();
    ReservationForm(){
        setTitle("Reservation Page");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.getHSBColor(300, 200, 300));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "CSD73";
        String password = "mayank17032003";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT train_name FROM trains");
            while (resultSet.next()) {
                String trainName = resultSet.getString("train_name");
                trains.add(trainName);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        NameLabel = new JLabel("Name:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(NameLabel, gbc);
        nameField = new JTextField(25);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(nameField, gbc);
        PhoneNoLabel = new JLabel("Phone No:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(PhoneNoLabel, gbc);
        PhoneNoField = new JTextField(25);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(PhoneNoField, gbc);
        AgeLabel = new JLabel("Age:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(AgeLabel, gbc);
        ageField = new JTextField(25);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(ageField, gbc);
        GenderLabel = new JLabel("Gender:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(GenderLabel, gbc);
        genderDropdown = new JComboBox<>(genders);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(genderDropdown, gbc);
        TrainLabel = new JLabel("Train number or Train name:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(TrainLabel, gbc);
        String[] trainArray = trains.toArray(new String[0]);
        trainDropdown = new JComboBox<>(trainArray);
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(trainDropdown, gbc);

        TicketsLabel = new JLabel("Number of Tickets:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(TicketsLabel, gbc);

        ticketField = new JTextField(25);
        gbc.gridx = 1;
        gbc.gridy = 5;
        add(ticketField, gbc);

        SourceLabel = new JLabel("From:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(SourceLabel, gbc);

        SourceField = new JTextField(25);
        gbc.gridx = 1;
        gbc.gridy = 6;
        add(SourceField, gbc);

        DestinationLabel = new JLabel("To:");
        gbc.gridx = 0;
        gbc.gridy = 7;
        add(DestinationLabel, gbc);

        DestinationField = new JTextField(25);
        gbc.gridx = 1;
        gbc.gridy = 7;
        add(DestinationField, gbc);

        DateLabel = new JLabel("Date Of Journey:");
        gbc.gridx = 0;
        gbc.gridy = 8;
        add(DateLabel, gbc);

        DateField = new JTextField(25);
        gbc.gridx = 1;
        gbc.gridy = 8;
        add(DateField, gbc);

        ClassLabel = new JLabel("Class Type:");
        gbc.gridx = 0;
        gbc.gridy = 9;
        add(ClassLabel, gbc);

        classDropdown = new JComboBox<>(classtype);
        gbc.gridx = 1;
        gbc.gridy = 9;
        add(classDropdown, gbc);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        add(submitButton, gbc);
    }
    public void actionPerformed(ActionEvent e){
        String selectedTrain = (String) trainDropdown.getSelectedItem();
        String selectedclass = (String) classDropdown.getSelectedItem();
        String s = SourceField.getText();
        String d = DestinationField.getText();
        String name = nameField.getText();
        String age = ageField.getText();
        String phoneno = PhoneNoField.getText();
        String gender = (String) genderDropdown.getSelectedItem();
        String date = DateField.getText();
        int numTickets = Integer.parseInt(ticketField.getText());
        JTextArea detailsTextArea = new JTextArea();
        detailsTextArea.setEditable(false);
        detailsTextArea.append("Name: " + name + "\n");
        detailsTextArea.append("Mobile no: " + phoneno + "\n");
        detailsTextArea.append("Age: " + age + "\n");
        detailsTextArea.append("Gender: " + gender + "\n");
        detailsTextArea.append("Selected Train: " + selectedTrain + "\n");
        detailsTextArea.append("Source Station: " + s + "\n");
        detailsTextArea.append("Destination Station: " + d + "\n");
        detailsTextArea.append("Date Of Journey: " + date + "\n");
        detailsTextArea.append("Selected Class Type: " + selectedclass + "\n");
        detailsTextArea.append("Number of Tickets: " + numTickets + "\n");

        // Create a JScrollPane to add scrolling functionality
        JScrollPane scrollPane = new JScrollPane(detailsTextArea);

        // Create a new JFrame to display the details
        JFrame detailsFrame = new JFrame("Booking Details");
        detailsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        detailsFrame.add(scrollPane);
        detailsFrame.setSize(400, 300);
        detailsFrame.setVisible(true);
        JOptionPane.showMessageDialog(this, "Reservation Successful!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "CSD73";
        String password = "mayank17032003";
        String query = "INSERT INTO reservation (Name, mobile_no, age, gender, train_name, source_station, destination_station, dates, class, no_tickets) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, phoneno);
            preparedStatement.setString(3, age);
            preparedStatement.setString(4, gender);
            preparedStatement.setString(5, selectedTrain);
            preparedStatement.setString(6, s);
            preparedStatement.setString(7, d);
            preparedStatement.setString(8, date);
            preparedStatement.setString(9, selectedclass);
            preparedStatement.setInt(10, numTickets);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Insert successful!");
            } else {
                System.out.println("Insert failed!");
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static void main(String[] args){
        ReservationForm res = new ReservationForm();
        res.setVisible(true);
    }
}
