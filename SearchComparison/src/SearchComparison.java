import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class SearchComparison {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Search Comparison");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);

        int arraySize = 5000;
        int numTrials = 5000;

        int sequentialTotalAttempts = 0;
        int binaryTotalAttempts = 0;

        for (int i = 0; i < numTrials; i++) {
            int[] array = generateRandomArray(arraySize);
            int sequentialAttempts = sequentialSearch(array, getRandomNumber());
            sequentialTotalAttempts += sequentialAttempts;

            int binaryAttempts = binarySearch(array, getRandomNumber());
            binaryTotalAttempts += binaryAttempts;
        }

        double sequentialAverageAttempts = (double) sequentialTotalAttempts / numTrials;
        double binaryAverageAttempts = (double) binaryTotalAttempts / numTrials;

        // Display results in the JTextArea
        textArea.append("Sequential Search Average Attempts: " + sequentialAverageAttempts + "\n");
        textArea.append("Binary Search Average Attempts: " + binaryAverageAttempts + "\n");
    }

    private static int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(size) + 1;
        }
        Arrays.sort(array);
        return array;
    }

    private static int getRandomNumber() {
        return new Random().nextInt(5000) + 1;
    }

    private static int sequentialSearch(int[] array, int target) {
        int attempts = 0;
        for (int i = 0; i < array.length; i++) {
            attempts++;
            if (array[i] == target) {
                return attempts;
            }
        }
        return attempts;
    }

    private static int binarySearch(int[] array, int target) {
        int attempts = 0;
        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            attempts++;
            int mid = (low + high) / 2;
            int midValue = array[mid];

            if (midValue == target) {
                return attempts;
            } else if (midValue < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return attempts;
    }
}
