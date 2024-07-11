import javax.swing.*;

public class RecursionMethods extends JFrame {

    private JTextArea textArea;

    public RecursionMethods() {
        setTitle("Recursion Methods Output");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);

        setVisible(true);
    }

    // Method to divide a number by 3 recursively until result is less than 1
    public void divideByThree(double number) {
        if (number < 1) {
            return;
        } else {
            textArea.append(number + "\n");
            divideByThree(number / 3);
        }
    }

    // Method to output n odd numbers recursively
    public void printOddNumbers(int n, int start) {
        if (n == 0) {
            return;
        } else {
            textArea.append(start + "\n");
            printOddNumbers(n - 1, start + 2);
        }
    }

    // Method to calculate the greatest common factor or determine if a number is prime
    public int gcdOrPrime(int num1, int num2) {
        if (num2 == 0) {
            return num1;
        } else {
            return gcdOrPrime(num2, num1 % num2);
        }
    }

    public static void main(String[] args) {
        RecursionMethods gui = new RecursionMethods();

        // Testing the methods
        gui.textArea.append("Dividing by 3 recursively:\n");
        gui.divideByThree(27);

        gui.textArea.append("\nPrinting 5 odd numbers recursively:\n");
        gui.printOddNumbers(5, 1);

        gui.textArea.append("\nGreatest common factor of 12 and 18 is: " + gui.gcdOrPrime(12, 18) + "\n");
        gui.textArea.append("Greatest common factor of 17 and 23 is: " + gui.gcdOrPrime(17, 23) + "\n");
    }
}
