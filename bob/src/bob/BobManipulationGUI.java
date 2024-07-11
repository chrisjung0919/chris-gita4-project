package bob;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BobManipulationGUI extends JFrame {
    private JTextArea outputTextArea;
    private JTextField inputTextField;

    public BobManipulationGUI() {
        // Set up the JFrame
        setTitle("Bob Manipulation Program");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Create JTextArea for output
        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        outputTextArea.setBounds(10, 10, 370, 100);
        add(outputTextArea);

        // Create JTextField for user input
        inputTextField = new JTextField();
        inputTextField.setBounds(10, 120, 200, 30);
        add(inputTextField);

        // Create JButton for processing input
        JButton processButton = new JButton("Process");
        processButton.setBounds(220, 120, 160, 30);
        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processInput();
            }
        });
        add(processButton);

        // Make the JFrame visible
        setVisible(true);
    }

    private void processInput() {
        // Get the input from the JTextField
        String inputWord = inputTextField.getText();

        // Check if "Bob" is in the input word
        int bobIndex = inputWord.indexOf("Bob");
        if (bobIndex != -1) {
            // Remove "Bob" from the input word using substring
            String partBeforeBob = inputWord.substring(0, bobIndex);
            String partAfterBob = inputWord.substring(bobIndex + 3); // 3 is the length of "Bob"

            // Concatenate "Bob" at the end
            inputWord = partBeforeBob + partAfterBob + "Bob";
        }

        // Display the modified word in the JTextArea
        outputTextArea.setText("Modified word: " + inputWord);
    }

    public static void main(String[] args) {
        // Create an instance of the program
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BobManipulationGUI();
            }
        });
    }
}
