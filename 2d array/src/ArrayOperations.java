import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class ArrayOperations {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Array Operations");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JTextArea textArea = new JTextArea();
            JScrollPane scrollPane = new JScrollPane(textArea);

            frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

            // Run the array operations and display the results in the JTextArea
            performArrayOperations(textArea);

            frame.setSize(400, 300);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    private static void performArrayOperations(JTextArea textArea) {
        int rows = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of rows:"));
        int cols = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of columns:"));

        int[][] array = generateRandomArray(rows, cols);

        textArea.append("Original Array:\n");
        appendArrayToTextArea(array, textArea);

        int highest = findHighest(array);
        int lowest = findLowest(array);
        double average = findAverage(array);
        textArea.append("Highest number: " + highest + "\n");
        textArea.append("Lowest number: " + lowest + "\n");
        textArea.append("Average number: " + average + "\n");

        int[] occurrences = countOccurrences(array);
        textArea.append("Occurrences of each number:\n");
        appendArrayToTextArea(occurrences, textArea);
    }

    private static int[][] generateRandomArray(int rows, int cols) {
        Random random = new Random();
        int[][] array = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                array[i][j] = random.nextInt(100) + 1;
            }
        }

        return array;
    }

    private static void appendArrayToTextArea(int[][] array, JTextArea textArea) {
        for (int[] row : array) {
            for (int num : row) {
                textArea.append(num + " ");
            }
            textArea.append("\n");
        }
    }

    private static void appendArrayToTextArea(int[] array, JTextArea textArea) {
        for (int i = 1; i < array.length; i++) {
            textArea.append(i + ": " + array[i] + " occurrences\n");
        }
    }

    private static int findHighest(int[][] array) {
        return Arrays.stream(array)
                .flatMapToInt(Arrays::stream)
                .max()
                .orElse(0);
    }

    private static int findLowest(int[][] array) {
        return Arrays.stream(array)
                .flatMapToInt(Arrays::stream)
                .min()
                .orElse(0);
    }

    private static double findAverage(int[][] array) {
        return Arrays.stream(array)
                .flatMapToInt(Arrays::stream)
                .average()
                .orElse(0);
    }

    private static int[] countOccurrences(int[][] array) {
        int[] occurrences = new int[101];

        for (int[] row : array) {
            for (int num : row) {
                occurrences[num]++;
            }
        }

        return occurrences;
    }
}
