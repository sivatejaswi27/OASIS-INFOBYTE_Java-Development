import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BankAccount {
    private double accountBalance;

    public BankAccount(double initialBalance) {
        accountBalance = initialBalance;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void depositAmount(double amount) {
        if (amount > 0) {
            accountBalance += amount;
        }
    }

    public boolean withdrawAmount(double amount) {
        if (amount > 0 && amount <= accountBalance) {
            accountBalance -= amount;
            return true;
        }
        return false;
    }
}

class ATM extends JFrame {
    private BankAccount userAccount;
    private JTextField amountField;
    private JTextArea messageArea;

    public ATM(BankAccount account) {
        userAccount = account;
        setTitle("ATM Machine");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(255, 255, 204)); // Pale Yellow Color

        JPanel panel = new JPanel();
        panel.setBackground(new Color(198, 90, 117));
        panel.setLayout(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel amountLabel = new JLabel("Enter Amount:");
        amountLabel.setForeground(Color.WHITE);
        amountField = new JTextField();

        Font labelFont = new Font("Times New Roman", Font.PLAIN, 20);
        amountLabel.setFont(labelFont);
        amountField.setFont(labelFont);

        JButton checkBalanceButton = new JButton("Check Balance");
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        messageArea = new JTextArea();

        Font textAreaFont = new Font("Times New Roman", Font.PLAIN, 16);
        messageArea.setFont(textAreaFont);

        Font buttonFont = new Font("Times New Roman", Font.BOLD, 16);
        checkBalanceButton.setFont(buttonFont);
        depositButton.setFont(buttonFont);
        withdrawButton.setFont(buttonFont);

        checkBalanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                messageArea.setText("Current balance: $" + String.format("%.2f", userAccount.getAccountBalance()));
            }
        });

        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(amountField.getText());
                userAccount.depositAmount(amount);
                messageArea.setText("Deposit successful. New balance: $" + String.format("%.2f", userAccount.getAccountBalance()));
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(amountField.getText());
                if (userAccount.withdrawAmount(amount)) {
                    messageArea.setText("Withdrawal successful. New balance: $" + String.format("%.2f", userAccount.getAccountBalance()));
                } else {
                    messageArea.setText("Invalid withdrawal amount or insufficient balance.");
                }
            }
        });

        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(checkBalanceButton);
        panel.add(depositButton);
        panel.add(withdrawButton);

        JScrollPane scrollPane = new JScrollPane(messageArea);
        panel.add(scrollPane);

        add(panel);
        setVisible(true);
    }
}

public class Task3_ATMInterface {
    public static void main(String[] args) {
        double initialBalance = 1000.0;
        BankAccount userAccount = new BankAccount(initialBalance);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new ATM(userAccount);
            }
        });
    }
}
