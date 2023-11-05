import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class index extends JFrame {
    JButton loginButton, RegistrationButton;
    JLabel label;
    public index() {
        setTitle("Home Page");
        setSize(500, 400); // Set an initial size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().setBackground(Color.getHSBColor(300, 200, 300));

        label = new JLabel("Click On Register, if account does not exist!!");
        label.setAlignmentX(CENTER_ALIGNMENT); // Center the label
        label.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Add space around the label
        add(label);
        loginButton = new JButton("Login");
        loginButton.setAlignmentX(CENTER_ALIGNMENT); // Center the button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginForm f = new loginForm();
                f.setVisible(true);
            }
        });
        loginButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Add space around the button
        add(loginButton);
        add(Box.createRigidArea(new java.awt.Dimension(0, 10)));
        RegistrationButton = new JButton("Register");
        RegistrationButton.setAlignmentX(CENTER_ALIGNMENT); // Center the button
        RegistrationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Registration f = new Registration();
                f.setVisible(true);
            }
        });
        RegistrationButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Add space around the button
        add(RegistrationButton);
    }
    public static void main(String[] args) {
        index i = new index();
        i.setVisible(true);
    }
}
