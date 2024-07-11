import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ArrayInsertionGUI extends JFrame {

    private int[] myArray;

    private JTextField textField;
    private JTextArea textArea;

    public ArrayInsertionGUI() {
        // Create an array of size 10 filled with random numbers 1-10
        myArray = new int[10];
        Random rand = new Random();
        for (int i = 0; i < myArray.length; i++) {
            myArray[i] = rand.nextInt(10) + 1;
        }

        // Sort the array in ascending order
        Arrays.sort(myArray);

        // Set up the GUI components
        textField = new JTextField(10);
        JButton insertButton = new JButton("Insert Number");
        textArea = new JTextArea(10, 20);
        textArea.setEditable(false);

        // ActionListener for the button
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertNumberAndDisplay();
            }
        });

        // Set up the layout
        setLayout(null);
        textField.setBounds(20, 20, 100, 25);
        insertButton.setBounds(140, 20, 150, 25);
        textArea.setBounds(20, 60, 270, 150);

        // Add components to the frame
        add(textField);
        add(insertButton);
        add(textArea);

        // Set frame properties
        setSize(320, 250);
        setTitle("Array Insertion GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void insertNumberAndDisplay() {
        try {
            int numberToInsert = Integer.parseInt(textField.getText());
            if (!containsNumber(myArray, numberToInsert)) {
                myArray = insertNumber(myArray, numberToInsert);
                Arrays.sort(myArray);
            }
            displayArray();
        } catch (NumberFormatException ex) {
            textArea.setText("Please enter a valid number.");
        }
    }

    private void displayArray() {
        textArea.setText("Sorted Array: " + Arrays.toString(myArray));
    }

    private static boolean containsNumber(int[] array, int number) {
        for (int value : array) {
            if (value == number) {
                return true;
            }
        }
        return false;
    }

    private static int[] insertNumber(int[] array, int number) {
        int[] newArray = Arrays.copyOf(array, array.length + 1);
        newArray[array.length] = number;
        return newArray;
    }

    public static void main(String[] args) {
        new ArrayInsertionGUI();
    }
}
