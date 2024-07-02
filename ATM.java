
import javax.swing.*;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class AtmImp {
    private int balance;

    public AtmImp(int Intbalance) {
        this.balance = Intbalance;
    }

    public int check_balance() {
        return balance;
    }

    public boolean withdraw(int amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void deposit(int amount) {
        if (amount > 0) {
            balance += amount;
        }
    }
}

public class ATM {
    private AtmImp atm;
    private JFrame frame;
    private JTextField amountField;
    private JLabel balanceLabel;
    private JLabel messageLabel;

    public ATM(AtmImp atm) {
        this.atm = atm;
        createGUI();
    }

    private void createGUI() {
        frame = new JFrame("ATM");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);
        Label user = new Label("Amount");
        user.setBounds(10, 20, 80, 25);
        panel.add(user);
        amountField = new JTextField(20);
        amountField.setBounds(100, 20, 165, 25);
        panel.add(amountField);
        JButton checkBalanceButton = new JButton("Balance");
        checkBalanceButton.setBounds(10, 90, 165, 25);
        panel.add(checkBalanceButton);
        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(10, 130, 165, 25);
        panel.add(withdrawButton);
        JButton depositButton = new JButton("Deposit");
        depositButton.setBounds(10, 170, 165, 25);
        panel.add(depositButton);

        balanceLabel = new JLabel("Your current balance: rs." + atm.check_balance());
        balanceLabel.setBounds(10, 200, 300, 25);
        panel.add(balanceLabel);
        messageLabel = new JLabel("");
        messageLabel.setBounds(10, 220, 300, 25);
        panel.add(messageLabel);

        checkBalanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Balance button pressed");
                checkBalance();
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                try {
                    int amount = Integer.parseInt(amountField.getText());
                    withdraw(amount);
                } catch (NumberFormatException ex) {
                    messageLabel.setText("Invalid input");
                }
            }
        });

        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                try {
                    int amount = Integer.parseInt(amountField.getText());
                    deposit(amount);
                } catch (NumberFormatException ex) {
                    messageLabel.setText("Invalid input");
                }
            }
        });
    }

    private void checkBalance() {
        System.out.println("Checking balance");
        balanceLabel.setText("Your current balance: rs." + atm.check_balance());
        messageLabel.setText("");
    }

    private void withdraw(int amount) {
        System.out.println("Withdrawing amount: " + amount);
        if (atm.withdraw(amount)) {
            messageLabel.setText("rs." + amount + " withdrawed successfully.");
        } else {
            messageLabel.setText("Insufficient balance. Withdrawal failed.");
        }
        checkBalance();
    }

    private void deposit(int amount) {
        System.out.println("Depositing amount: " + amount);
        atm.deposit(amount);
        messageLabel.setText("rs." + amount + " deposited successfully.");
        checkBalance();
    }

    public static void main(String[] args) {
        AtmImp account = new AtmImp(1000);
        new ATM(account);
    }
}
