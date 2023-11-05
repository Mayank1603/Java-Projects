import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class GuessTheNumber extends JFrame implements ActionListener{
    JLabel label, c, resultLabel;
    JTextField textfield;
    JButton guessButton;
    int randomNumber;
    int count;
    GuessTheNumber(){
        setTitle("Guess The Number");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(240, 240, 240));
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(100, 100, 250));
        c = new JLabel("Enter Your Guessed Number:");
        label = new JLabel("Guess the number (1-100):");
        textfield = new JTextField(10);
        guessButton = new JButton("Guess");
        guessButton.addActionListener(this);
        centerPanel.add(Box.createVerticalStrut(10)); // Add vertical spacing
        centerPanel.add(c);
        centerPanel.add(Box.createVerticalStrut(10)); // Add vertical spacing
        centerPanel.add(label);
        centerPanel.add(textfield);
        centerPanel.add(guessButton);

        resultLabel = new JLabel("");
        resultLabel.setHorizontalAlignment(JLabel.CENTER);

        add(centerPanel, BorderLayout.CENTER);
        add(resultLabel, BorderLayout.SOUTH);

        randomNumber = (int) (Math.random() * 100) + 1;
        count = 0;
    }
    public void actionPerformed(ActionEvent e){
        textfield.setFont(new Font("Roboto", Font.BOLD, 16));
        int guess = Integer.parseInt(textfield.getText());
        String message = "";
        if (guess == randomNumber) {
            message = "Congratulation! You guessed the number after " + (count+1) + " tries";
            textfield.setText("You guessed correctly after ");
        } else if (guess < randomNumber) {
            ++count;
            message = "Too low. Try again.";
            textfield.setText("");
        } else {
            ++count;
            message = "Too high. Try again.";
            textfield.setText("");
        }
        JOptionPane.showMessageDialog(this, message);
        if(guess==randomNumber){
            randomNumber = (int)(Math.random()*100)+1;
            textfield.setText("");
            count = 0; // Reset count for the next round
        }
    }
    public static void main(String[] args){
        GuessTheNumber game = new GuessTheNumber();
        game.setVisible(true);
    }
}