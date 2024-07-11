import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TestScoresProgram extends JFrame {
    private JTextArea textArea;

    public TestScoresProgram() {
        setTitle("Test Scores Program");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textArea = new JTextArea(20, 40);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processTestScores();
            }
        });

        add(startButton, BorderLayout.SOUTH);

        pack();
    }

    public void processTestScores() {
        textArea.setText(""); // Clear the text area

        int numScores = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of test scores:"));

        int[] testScores = new int[numScores];
        char[] letterGrades = new char[numScores];
        Random random = new Random();

        // Fill the testScores array with random scores between 0 and 100
        for (int i = 0; i < numScores; i++) {
            testScores[i] = random.nextInt(101); // 0 to 100
            letterGrades[i] = TestScoreCalculator.calculateLetterGrade(testScores[i]);
        }

        // Output original test scores and their letter grade equivalents
        textArea.append("Original Test Scores and Letter Grades:\n");
        for (int i = 0; i < numScores; i++) {
            textArea.append("Test Score: " + testScores[i] + " Letter Grade: " + letterGrades[i] + "\n");
        }

        // Output the letter grade array in reverse
        textArea.append("\nLetter Grades in Reverse Order:\n");
        for (int i = numScores - 1; i >= 0; i--) {
            textArea.append(letterGrades[i] + " ");
        }

        // Calculate and output the average score
        int sum = 0;
        for (int score : testScores) {
            sum += score;
        }
        double average = (double) sum / numScores;
        textArea.append("\nAverage Test Score: " + average + "\n");

        // Find and output the highest and lowest test scores
        int highest = testScores[0];
        int lowest = testScores[0];
        for (int score : testScores) {
            if (score > highest) {
                highest = score;
            }
            if (score < lowest) {
                lowest = score;
            }
        }
        textArea.append("Highest Test Score: " + highest + "\n");
        textArea.append("Lowest Test Score: " + lowest + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	TestScoresProgram gui = new TestScoresProgram();
            gui.setVisible(true);
        });
    }
}
