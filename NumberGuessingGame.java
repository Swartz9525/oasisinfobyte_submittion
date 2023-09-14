import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class NumberGuessingGame extends JFrame {

    private JTextField box;
    private JLabel message;
    private int number;
    private int attempts;
    private int score;
    private JLabel Showscore;
    private JLabel showattempts;

    public NumberGuessingGame() {
        super("Number Guessing Game");

        // Set up of GUI
        box = new JTextField(10);
        box.setBounds(150, 100, 50, 40);
        message = new JLabel("");
        message.setBounds(150, 150, 200, 30);
        Showscore = new JLabel("Score: 0");
        Showscore.setBounds(150, 190, 100, 30);

        showattempts = new JLabel("Attempts: 0");
        showattempts.setBounds(150, 210, 100, 30);

        JButton Button = new JButton("Guess");
        Button.setBounds(150, 250, 80, 40);
        Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guess();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.add(box);
        panel.add(Button);
        panel.add(message);
        panel.add(Showscore);
        panel.add(showattempts);
        setContentPane(panel);

        number = (int) (Math.random() * 100) + 1;
        attempts = 0;
        score = 0;
        setSize(400, 500);
        setLocationRelativeTo(null);
    }

    private void guess() {
        // Get the user's number
        String guessText = box.getText();
        int guess = 0;

        try {
            guess = Integer.parseInt(guessText);
        } catch (NumberFormatException e) {
            message.setText("Invalid input. Please enter a number.");
            return;
        }

        // Check the user's number
        if (guess == number) {
            message.setText("Congratulations! You guessed the correct number in " + attempts + " attempts.");
            score += 10 - attempts;
        } else if (guess < number) {
            message.setText("Your guess is too low.");
        } else {
            message.setText("Your guess is too high.");
        }

        attempts++;

        // Show the score
        Showscore.setText("Score: " + score);
        // Show the Attempts
        showattempts.setText("Attempts: " + attempts);

        // Check number of attempts
        if (attempts >= 10) {
            // Ask the user want to continue
            int choice = JOptionPane.showConfirmDialog(this, "Do you want to continue?", "Game Over",
                    JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION) {
                attempts = 0;
                message.setText("");
                number = (int) (Math.random() * 100) + 1; // Generating the number
            } else {
                message.setText("You did not guess the number. The game is over.");
                box.setEditable(false);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                NumberGuessingGame game = new NumberGuessingGame();
                game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                game.setVisible(true);
            }
        });
    }
}
