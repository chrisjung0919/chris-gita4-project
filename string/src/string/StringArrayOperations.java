package string;
import javax.swing.*;

public class StringArrayOperations {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("String Array Operations");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextArea inputTextArea = new JTextArea(10, 30);
        JTextArea outputTextArea = new JTextArea(10, 30);
        outputTextArea.setEditable(false);

        JButton processButton = new JButton("Process");
        processButton.addActionListener(e -> processInput(inputTextArea.getText(), outputTextArea));

        JPanel panel = new JPanel();
        panel.add(new JScrollPane(inputTextArea));
        panel.add(processButton);
        panel.add(new JScrollPane(outputTextArea));

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void processInput(String input, JTextArea outputTextArea) {
        String[] stringArray = input.split("\n");

        // Output the array in all upper case
        outputTextArea.append("Array in all upper case:\n");
        for (String str : stringArray) {
            outputTextArea.append(str.toUpperCase() + "\n");
        }

        // Output the array in all lower case
        outputTextArea.append("\nArray in all lower case:\n");
        for (String str : stringArray) {
            outputTextArea.append(str.toLowerCase() + "\n");
        }

        // Output how many strings are equal in length
        int equalLengthCount = 0;
        for (int i = 0; i < stringArray.length - 1; i++) {
            if (stringArray[i].length() == stringArray[i + 1].length()) {
                equalLengthCount++;
            }
        }
        outputTextArea.append("\nNumber of strings with equal length: " + equalLengthCount + "\n");

        // Output the longest string
        String longestString = stringArray[0];
        for (String str : stringArray) {
            if (str.length() > longestString.length()) {
                longestString = str;
            }
        }
        outputTextArea.append("Longest string: " + longestString + "\n");

        // Output the shortest string
        String shortestString = stringArray[0];
        for (String str : stringArray) {
            if (str.length() < shortestString.length()) {
                shortestString = str;
            }
        }
        outputTextArea.append("Shortest string: " + shortestString + "\n");

        // Concatenate the longest and shortest string
        String concatenatedStrings = longestString + shortestString;
        outputTextArea.append("Concatenated strings: " + concatenatedStrings + "\n");
    }
}
