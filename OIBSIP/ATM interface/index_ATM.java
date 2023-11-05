import java.awt.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
public class index_ATM extends JFrame implements ActionListener {
    JLabel AccountNoLabel, PinLabel, Label;
    JTextField AccountNoField, PinField;
    JButton Submit, WithdrawButton, DepositButton, TransferButton, ExitButton, TransactionHistoryButton;
    index_ATM(){
        setTitle("ATM Interface");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(Color.getHSBColor(300, 200, 300));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        Label = new JLabel("Enter your details as follows: ");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(Label, gbc);
        AccountNoLabel = new JLabel("Enter Account No: ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(AccountNoLabel, gbc);
        AccountNoField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(AccountNoField, gbc);
        PinLabel = new JLabel("Enter Pin: ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(PinLabel, gbc);
        PinField = new JPasswordField(10);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(PinField, gbc);
        Submit = new JButton("Submit");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        Submit.setFont(new Font("Roboto", Font.BOLD, 16));
        add(Submit, gbc);
        Submit.addActionListener((this));
        DepositButton = new JButton("Deposit");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        DepositButton.setFont(new Font("Roboto", Font.BOLD, 16));
        add(DepositButton, gbc);
        DepositButton.setVisible(false);
        WithdrawButton = new JButton("Withdraw");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(WithdrawButton, gbc);
        WithdrawButton.setFont(new Font("Roboto", Font.BOLD, 16));
        WithdrawButton.setVisible(false);
        TransferButton = new JButton("Transfer");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        TransferButton.setFont(new Font("Roboto", Font.BOLD, 16));
        add(TransferButton, gbc);
        TransferButton.setVisible(false);
        ExitButton = new JButton("Exit");
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        ExitButton.setFont(new Font("Roboto", Font.BOLD, 16));
        add(ExitButton, gbc);
        ExitButton.setVisible(false);
        TransactionHistoryButton = new JButton("Transaction History");
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        TransactionHistoryButton.setFont(new Font("Roboto", Font.BOLD, 16));
        add(TransactionHistoryButton, gbc);
        TransactionHistoryButton.setVisible(false);
    }
    public void actionPerformed(ActionEvent e){
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "CSD73";
        String password = "mayank17032003";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT name, accountno, balance FROM ATM where accountno = "+AccountNoField.getText()+" and Pin = "+PinField.getText());
            if (resultSet.next()) {
                JOptionPane.showMessageDialog(this, "Authentication Successful!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                DepositButton.setVisible(true);
                WithdrawButton.setVisible(true);
                TransferButton.setVisible(true);
                ExitButton.setVisible(true);
                TransactionHistoryButton.setVisible(true);
                DepositButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Deposit d = new Deposit(AccountNoField.getText(), PinField.getText());
                        d.setVisible(true);
                    }
                });
                WithdrawButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Withdraw w = new Withdraw(AccountNoField.getText(), PinField.getText());
                        w.setVisible(true);
                    }
                });
                TransferButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Transfer t = new Transfer(AccountNoField.getText(), PinField.getText());
                        t.setVisible(true);
                    }
                });
                ExitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });
                TransactionHistoryButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        TransactionHistory th = new TransactionHistory(AccountNoField.getText(), PinField.getText());
                        th.setVisible(true);
                    }
                });
                add(DepositButton);
                add(WithdrawButton);
                add(TransferButton);
                add(TransactionHistoryButton);
                add(ExitButton);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Account Number or Pin! Access Denied!!!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static void main(String []args){
        index_ATM atm = new index_ATM();
        atm.setVisible(true);
    }
}
class Deposit extends JFrame implements ActionListener{
    String accountNo, pin;
    JLabel amountLabel;
    JTextField amountField;
    JButton depositButton;
    public Deposit(String accountNo, String pin) {
        this.accountNo = accountNo;
        this.pin = pin;
        setTitle("Deposit");
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        amountLabel = new JLabel("Enter Amount you want to add: ");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(amountLabel, gbc);
        amountField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(amountField, gbc);
        depositButton = new JButton("Deposit");
        depositButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(depositButton, gbc);
    }
    public void actionPerformed(ActionEvent e) {
            if (e.getSource() == depositButton) {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    if (amount > 0) {
                        String url = "jdbc:oracle:thin:@localhost:1521:xe";
                        String username = "CSD73";
                        String password = "mayank17032003";
                        Connection connection = DriverManager.getConnection(url, username, password);
                        Statement statement = connection.createStatement();
                        PreparedStatement updateStatement = connection.prepareStatement(
                                "UPDATE ATM SET balance = balance + ? WHERE accountno = ? AND pin = ?"
                        );
                        updateStatement.setDouble(1, amount);
                        updateStatement.setString(2, accountNo);
                        updateStatement.setString(3, pin);
                        int rowsUpdated = updateStatement.executeUpdate();
                        if (rowsUpdated > 0) {
                            PreparedStatement insertTransactionStatement = connection.prepareStatement(
                                    "INSERT INTO TransactionHistory (transaction_id, accountno, transaction_type, amount, transaction_date, pin) " +
                                            "VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP, ?)"
                            );
                            Random random = new Random();
                            int transactionId = random.nextInt(Integer.MAX_VALUE);
                            insertTransactionStatement.setInt(1, transactionId);
                            insertTransactionStatement.setString(2, accountNo);
                            insertTransactionStatement.setString(3, "Deposit");
                            insertTransactionStatement.setDouble(4, amount);
                            insertTransactionStatement.setString(5, pin);
                            int rowsInserted = insertTransactionStatement.executeUpdate();
                            if (rowsInserted > 0) {
                                JOptionPane.showMessageDialog(this, "Deposit successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(this, "An error occurred while recording the transaction. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            insertTransactionStatement.close();
                        } else {
                            JOptionPane.showMessageDialog(this, "Invalid Account Number or Pin! Access Denied!!!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        updateStatement.close();
                        statement.close();
                        connection.close();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Please enter a valid positive amount", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException | SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "An error occurred. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
class Withdraw extends JFrame implements ActionListener {
    String accountNo, pin;
    JLabel amountLabel;
    JTextField amountField;
    JButton WithdrawButton;
    public Withdraw(String accountNo, String pin) {
        this.accountNo = accountNo;
        this.pin = pin;
        setTitle("Withdraw");
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        amountLabel = new JLabel("Enter Amount you want to Withdraw: ");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(amountLabel, gbc);
        amountField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(amountField, gbc);
        WithdrawButton = new JButton("Withdraw");
        WithdrawButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(WithdrawButton, gbc);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == WithdrawButton) {
            try {
                double amount = Double.parseDouble(amountField.getText());
                if (amount > 0) {
                    String url = "jdbc:oracle:thin:@localhost:1521:xe";
                    String username = "CSD73";
                    String password = "mayank17032003";
                    Connection connection = DriverManager.getConnection(url, username, password);
                    PreparedStatement fetchBalanceStatement = connection.prepareStatement(
                            "SELECT balance FROM ATM WHERE accountno = ? AND pin = ?"
                    );
                    fetchBalanceStatement.setString(1, accountNo);
                    fetchBalanceStatement.setString(2, pin);
                    ResultSet balanceResult = fetchBalanceStatement.executeQuery();
                    double currentBalance = 0.0;
                    if (balanceResult.next()) {
                        currentBalance = balanceResult.getDouble("balance");
                    }
                    balanceResult.close();
                    fetchBalanceStatement.close();
                    if (amount <= currentBalance) {
                        PreparedStatement updateStatement = connection.prepareStatement(
                                "UPDATE ATM SET balance = balance - ? WHERE accountno = ? AND pin = ?"
                        );
                        updateStatement.setDouble(1, amount);
                        updateStatement.setString(2, accountNo);
                        updateStatement.setString(3, pin);
                        int rowsUpdated = updateStatement.executeUpdate();
                        if (rowsUpdated > 0) {
                            PreparedStatement insertTransactionStatement = connection.prepareStatement(
                                    "INSERT INTO TransactionHistory (transaction_id, accountno, transaction_type, amount, transaction_date, pin) " +
                                            "VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP, ?)"
                            );
                            Random random = new Random();
                            int transactionId = random.nextInt(Integer.MAX_VALUE);
                            insertTransactionStatement.setInt(1, transactionId);
                            insertTransactionStatement.setString(2, accountNo);
                            insertTransactionStatement.setString(3, "Withdraw");
                            insertTransactionStatement.setDouble(4, amount);
                            insertTransactionStatement.setString(5, pin);
                            int rowsInserted = insertTransactionStatement.executeUpdate();
                            if (rowsInserted > 0) {
                                JOptionPane.showMessageDialog(this, "Withdrawn successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(this, "An error occurred while recording the transaction. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            insertTransactionStatement.close();
                        } else {
                            JOptionPane.showMessageDialog(this, "Invalid Account Number or Pin! Access Denied!!!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        updateStatement.close();
                        connection.close();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Insufficient funds. Please enter a valid amount", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Please enter a valid positive amount", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException | SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "An error occurred. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
class Transfer extends JFrame implements ActionListener{
    String accountNo, pin;
    JLabel amountLabel, accountLabel;
    JTextField amountField, accountField;
    JButton TransferButton;
    public Transfer(String accountNo, String pin) {
        this.accountNo = accountNo;
        this.pin = pin;
        setTitle("Transfer");
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        accountLabel = new JLabel("Transfer to Account No: ");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(accountLabel, gbc);
        accountField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(accountField, gbc);
        amountLabel = new JLabel("Enter Amount you want to Transfer: ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(amountLabel, gbc);
        amountField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(amountField, gbc);
        TransferButton = new JButton("Transfer");
        TransferButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(TransferButton, gbc);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == TransferButton) {
            try {
                double amount = Double.parseDouble(amountField.getText());
                if (amount > 0) {
                    String url = "jdbc:oracle:thin:@localhost:1521:xe";
                    String username = "CSD73";
                    String password = "mayank17032003";
                    Connection connection = DriverManager.getConnection(url, username, password);
                    PreparedStatement fetchBalanceStatement = connection.prepareStatement(
                            "SELECT balance FROM ATM WHERE accountno = ? AND pin = ?"
                    );
                    fetchBalanceStatement.setString(1, accountNo);
                    fetchBalanceStatement.setString(2, pin);
                    ResultSet balanceResult = fetchBalanceStatement.executeQuery();
                    double currentBalance = 0.0;
                    if (balanceResult.next()) {
                        currentBalance = balanceResult.getDouble("balance");
                    }
                    balanceResult.close();
                    fetchBalanceStatement.close();
                    if (amount <= currentBalance) {
                        PreparedStatement updateStatement = connection.prepareStatement(
                                "UPDATE ATM SET balance = balance - ? WHERE accountno = ?"
                        );
                        updateStatement.setDouble(1, amount);
                        updateStatement.setString(2, accountField.getText());
                        int rowsUpdated = updateStatement.executeUpdate();
                        if (rowsUpdated > 0) {
                            PreparedStatement insertTransactionStatement = connection.prepareStatement(
                                    "INSERT INTO TransactionHistory (transaction_id, accountno, transaction_type, amount, transaction_date, pin) " +
                                            "VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP, ?)"
                            );
                            Random random = new Random();
                            int transactionId = random.nextInt(Integer.MAX_VALUE);
                            insertTransactionStatement.setInt(1, transactionId);
                            insertTransactionStatement.setString(2, accountNo);
                            insertTransactionStatement.setString(3, "Transfer to "+accountField.getText());
                            insertTransactionStatement.setDouble(4, amount);
                            insertTransactionStatement.setString(5, pin);
                            int rowsInserted = insertTransactionStatement.executeUpdate();
                            if (rowsInserted > 0) {
                                JOptionPane.showMessageDialog(this, "Transferred successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(this, "An error occurred while recording the transaction. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            insertTransactionStatement.close();
                        } else {
                            JOptionPane.showMessageDialog(this, "Invalid Account Number or Pin! Access Denied!!!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        updateStatement.close();
                        connection.close();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Insufficient funds. Please enter a valid amount", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Please enter a valid positive amount", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException | SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "An error occurred. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
class TransactionHistory extends JFrame{
    String accountNo, pin;
    JTextArea historyTextArea;
    public TransactionHistory(String accountNo, String pin) {
        this.accountNo = accountNo;
        this.pin = pin;
        setTitle("Transaction History");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.setBackground(Color.getHSBColor(300, 200, 300));
        JLabel headerLabel = new JLabel("Transaction History for Account No: " + accountNo);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        container.add(headerLabel, BorderLayout.NORTH);
        historyTextArea = new JTextArea();
        historyTextArea.setEditable(false);
        historyTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(historyTextArea);
        container.add(scrollPane, BorderLayout.CENTER);
        try {
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            String username = "CSD73";
            String password = "mayank17032003";
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement fetchTransactionsStatement = connection.prepareStatement(
                    "SELECT * FROM TransactionHistory WHERE accountno = ? AND pin = ?"
            );
            fetchTransactionsStatement.setString(1, accountNo);
            fetchTransactionsStatement.setString(2, pin);
            ResultSet resultSet = fetchTransactionsStatement.executeQuery();
            while (resultSet.next()) {
                String transactionId = resultSet.getString("transaction_id");
                String transactionType = resultSet.getString("transaction_type");
                String amount = resultSet.getString("amount");
                String transactionDate = resultSet.getString("transaction_date");
                String transactionInfo = String.format("Account No: %s       |       Transaction ID: %s       |       Type: %s       |       Amount: %s       |       Date: %s\n",
                        accountNo, transactionId, transactionType, amount, transactionDate);
                historyTextArea.append(transactionInfo);
            }
            resultSet.close();
            fetchTransactionsStatement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}