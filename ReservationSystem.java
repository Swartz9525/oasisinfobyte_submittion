import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;

public class ReservationSystem extends JFrame {

    private JLabel trainNumber;
    private JTextField trfield;
    private JLabel passengerName;
    private JTextField pnfield;
    private JLabel from;
    private JTextField fField;
    private JLabel to;
    private JTextField toField;
    private JLabel date;
    private JDateChooser dChooser;
    private JButton reserveButton;

    public ReservationSystem() {
        super("Reservation System");

        // Set up the GUI
        trainNumber = new JLabel("Train number:");
        trainNumber.setBounds(10, 10, 100, 20);
        trfield = new JTextField(10);
        trfield.setBounds(110, 10, 200, 20);
        passengerName = new JLabel("Passenger name:");
        passengerName.setBounds(10, 40, 100, 20);
        pnfield = new JTextField(10);
        pnfield.setBounds(110, 40, 200, 20);
        from = new JLabel("From:");
        from.setBounds(10, 70, 100, 20);
        fField = new JTextField(10);
        fField.setBounds(110, 70, 200, 20);
        to = new JLabel("To:");
        to.setBounds(10, 100, 100, 20);
        toField = new JTextField(10);
        toField.setBounds(110, 100, 200, 20);
        date = new JLabel("Date:");
        date.setBounds(10, 130, 100, 20);
        dChooser = new JDateChooser();
        dChooser.setBounds(110, 130, 200, 20);
        dChooser.setDateFormatString("yyyy-MM-dd");
        reserveButton = new JButton("Submit");
        reserveButton.setBounds(10, 160, 100, 20);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.add(trainNumber);
        panel.add(trfield);
        panel.add(passengerName);
        panel.add(pnfield);
        panel.add(from);
        panel.add(fField);
        panel.add(to);
        panel.add(toField);
        panel.add(date);
        panel.add(dChooser);
        panel.add(reserveButton);

        setContentPane(panel);

        // Register the focus listeners for input validation
        trfield.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                validateTrainNumber();
            }
        });

        pnfield.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                validateString(pnfield);
            }
        });

        fField.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                validateString(fField);
            }
        });

        toField.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                validateString(toField);
            }
        });

        // Register the event listener for the submit button
        reserveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get the user input
                String trainNumber = trfield.getText();
                String passengerName = pnfield.getText();
                String from = fField.getText();
                String to = toField.getText();
                
                // Get the selected date from dateChooser
                Date selectedDate = dChooser.getDate();
                
                // Validate input before submission
                if (validateTrainNumber() && validateString(pnfield) && validateString(fField) && validateString(toField)) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String date = dateFormat.format(selectedDate);
                    
                    // Reserve the ticket
                    // Show a dialog box to confirm the reservation
                    JOptionPane.showMessageDialog(null, "Reservation successful!\nDate: " + date);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please check your input values.");
                }
            }
        });

        // Set the size of the window
        setSize(400, 500);

        // Center the window on the screen
        setLocationRelativeTo(null);
    }

    // Function to validate if a string contains only letters and is not empty
    private boolean validateString(JTextField field) {
        String s = field.getText();
        boolean isValid = !s.isEmpty() && s.matches("[a-zA-Z]+");
        if (!isValid) {
            field.setBorder(BorderFactory.createLineBorder(Color.RED));
        } else {
            field.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        }
        return isValid;
    }

    // Function to validate if a string is a valid integer within 1 to 5 digits
    private boolean validateTrainNumber() {
        String s = trfield.getText();
        boolean isValid = s.matches("\\d{1,5}");
        if (!isValid) {
            trfield.setBorder(BorderFactory.createLineBorder(Color.RED));
        } else {
            trfield.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        }
        return isValid;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ReservationSystem reservationSystem = new ReservationSystem();
                reservationSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                reservationSystem.setVisible(true);
            }
        });
    }
}