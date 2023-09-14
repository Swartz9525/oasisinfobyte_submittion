import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Atm {
    private double balance = 10000.0;
    private final int correctPin = 9525;
    private boolean pinEntered = false;
    private List<String> transactionHistory = new ArrayList<>();

    private JFrame frame;
    private JPanel mainPanel;
    private JLabel balanceLabel;
    private JTextField pinn;
    private JButton Trans_his;
    private JButton enter;
    private JButton withdraw;
    private JButton deposit;
    private JButton transfer;
    private JButton quit;

    public Atm() {
        frame = new JFrame("ATM Interface");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        balanceLabel = new JLabel("Balance:" + balance);
        pinn = new JTextField();
        pinn.setPreferredSize(new Dimension(100, 25));
        enter = new JButton("Enter PIN");
        Trans_his=new JButton("Transaction History");
        withdraw = new JButton("Withdraw Money");
        deposit = new JButton("Deposit Money");
        transfer = new JButton("Transfer Money");
        quit = new JButton("Quit");
        JPanel pinPanel = new JPanel();
        pinPanel.add(new JLabel("Enter PIN: "));
        pinPanel.add(pinn);
        pinPanel.add(enter);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1));
        buttonPanel.add(Trans_his);
        buttonPanel.add(withdraw);
        buttonPanel.add(deposit);
        buttonPanel.add(transfer);
        buttonPanel.add(quit);
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(pinPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        Trans_his.setEnabled(false);
        withdraw.setEnabled(false);
        deposit.setEnabled(false);
        transfer.setEnabled(false);
        quit.setEnabled(false);


        Trans_his.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TransactionHistory();
            }
        });
        enter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int pin = Integer.parseInt(pinn.getText());
                    if (pin == correctPin) {
                        pinn.setEnabled(false);
                        enter.setEnabled(false);
                        updateBalanceLabel();
                        pinEntered = true;
                        Trans_his.setEnabled(true);
                        withdraw.setEnabled(true);
                        deposit.setEnabled(true);
                        transfer.setEnabled(true);
                        quit.setEnabled(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrect PIN. Please try again.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid PIN format. Please enter a valid PIN.");
                }
            }
        });

        withdraw.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                withdrawMoney();
            }
        });

        deposit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                depositMoney();
            }
        });

        transfer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                transferMoney();
            }
        });

        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void updateBalanceLabel() {
        balanceLabel.setText("Balance: " + balance);
    }

    private void withdrawMoney() {
        if (!pinEntered) {
            JOptionPane.showMessageDialog(null, "Please enter the PIN first.");
            return;
        }

        String amountStr = JOptionPane.showInputDialog("Enter the amount to withdraw:");
        try {
            double amount = Double.parseDouble(amountStr);
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                updateBalanceLabel();
                recordTransaction("Withdrawal:-" + amount);
                JOptionPane.showMessageDialog(null, "Withdrawal successful. New balance: " + balance);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid withdrawal amount or insufficient funds.");
            }
        } catch (NumberFormatException | NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Invalid amount. Please enter a valid number.");
        }
    }

    private void depositMoney() {
        if (!pinEntered) {
            JOptionPane.showMessageDialog(null, "Please enter the PIN first.");
            return;
        }

        String amountStr = JOptionPane.showInputDialog("Enter the amount to deposit:");
        try {
            double amount = Double.parseDouble(amountStr);
            if (amount > 0) {
                balance += amount;
                updateBalanceLabel();
                recordTransaction("Deposit: +" + amount);
                JOptionPane.showMessageDialog(null, "Deposit successful. New balance: $" + balance);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid deposit amount.");
            }
        } catch (NumberFormatException | NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Invalid amount. Please enter a valid number.");
        }
    }

    private void transferMoney() {
        if (!pinEntered) {
            JOptionPane.showMessageDialog(null, "Please enter the PIN first.");
            return;
        }

        String amountStr = JOptionPane.showInputDialog("Enter the amount to transfer:");
        try {
            double amount = Double.parseDouble(amountStr);
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                updateBalanceLabel();
                recordTransaction("Transfer: -" + amount);
                JOptionPane.showMessageDialog(null, "Transfer successful. New balance: " + balance);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid transfer amount or insufficient funds.");
            }
        } catch (NumberFormatException | NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Invalid amount. Please enter a valid number.");
        }
    }

    private void TransactionHistory() {
        StringBuilder transactionLog = new StringBuilder();
        transactionLog.append("Transaction History:\n");
        for (String transaction : transactionHistory) {
            transactionLog.append(transaction).append("\n");
        }

        JTextArea textArea = new JTextArea(transactionLog.toString(), 20, 40);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JOptionPane.showMessageDialog(null, scrollPane, "Transaction History", JOptionPane.PLAIN_MESSAGE);
    }

    private void recordTransaction(String transaction) {
        transactionHistory.add(transaction);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Atm atm = new Atm();
            }
        });
    }
}