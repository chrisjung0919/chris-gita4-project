import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dice extends JFrame implements ActionListener {
    private JTextArea txtAreaResult = new JTextArea(20, 30);
    private JButton btnRollDice = new JButton("Roll Dice");
    private CalcDice calcDice; // Declare as an instance variable
    private localformat localFormat;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Dice frame = new Dice();
            frame.setSize(400, 400);
            frame.setVisible(true);
        });
    }

    public Dice() {
        super("Dice Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        add(btnRollDice);
        add(new JScrollPane(txtAreaResult));

        btnRollDice.addActionListener(this);

        // Create an instance of CalcDice (only once)
        calcDice = new CalcDice();

        // Create an instance of the LocalFormat class
        localFormat = new localformat();
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == btnRollDice) {
            txtAreaResult.setText(""); // Clear the text area

            // Display the header
            txtAreaResult.append("Dice Simulation Results\n\n");

            // Simulate rolling the dice once
            calcDice.rollDice();

            // Calculate and display counts and probabilities for all sums from 2 to 12
            for (int i = 2; i <= 12; i++) {
                int count = calcDice.getSumCount(i);
                double probability = calcDice.getProbability(i);
                txtAreaResult.append("Sum " + i + ": Count = " + count + ", Probability = " + probability + "%\n");
            }
        }
    }
}
