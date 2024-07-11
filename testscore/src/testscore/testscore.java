package testscore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class testscore extends JFrame {
    private JTextArea resultArea;
    private JButton calculateButton;
    private JTextField testScoreField;
    private calc calculation;
    private int numberOfTests = 0;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            testscore app = new testscore();
            app.setVisible(true);
        });
    }

    public testscore() {
        setTitle("Test Score Calculator");
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        resultArea = new JTextArea(10, 30);
        calculateButton = new JButton("Calculate");
        testScoreField = new JTextField(10);

        add(new JLabel("Test Score:"));
        add(testScoreField);
        add(calculateButton);

        calculation = new calc(); // Create a single instance of calc

        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double testScore = Double.parseDouble(testScoreField.getText());

                numberOfTests++;

                calculation.calculateScores(testScore); // Reuse the same calculation instance

                // Append the result to the result area
                resultArea.setText("");

                char letterGrade = calculation.calculateLetterGrade(testScore);
                resultArea.append("Test Letter Grade: " + letterGrade + "\n");

                // Create an instance of your LocalFormat class
                LocalFormat localFormat = new LocalFormat();

                // Use the LocalFormat class to format numbers
                String formattedTotalScore = localFormat.FormatDecimal(calculation.getTotalScore(), 2);
                String formattedHighestScore = localFormat.FormatDecimal(calculation.getHighestScore(), 2);
                String formattedAverageScore = localFormat.FormatDecimal(calculation.getTotalScore() / numberOfTests, 2);

                resultArea.append("Highest Score: " + formattedHighestScore + "\n");
                resultArea.append("Average Score: " + formattedAverageScore + "\n");
                resultArea.append("Number of Tests: " + numberOfTests);
            }
        });

        add(new JScrollPane(resultArea));
    }
}
