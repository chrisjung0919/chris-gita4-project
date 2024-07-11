import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

//Name: Chris J
//Date: 8/21/23
//Project: BMI
/*
* This project calculates BMI
*/

public class bmi extends JFrame {
    private JTextField heightField, weightField;
    private JButton calculateButton;
    private JTextArea resultArea;
    private int totalPeople = 0;
    private double totalBMI = 0.0;

    public bmi() {
        setTitle("BMI Calculator");
        setSize(500, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel(new GridLayout(5, 1));
        JLabel heightLabel = new JLabel("Height (in inches):");
        heightField = new JTextField();
        JLabel weightLabel = new JLabel("Weight (in pounds):");
        weightField = new JTextField();
        inputPanel.add(heightLabel);
        inputPanel.add(heightField);
        inputPanel.add(weightLabel);
        inputPanel.add(weightField);

        calculateButton = new JButton("Calculate BMI");
        calculateButton.addActionListener(new CalculateButtonListener());

        resultArea = new JTextArea(10, 50);
        resultArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(resultArea);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(calculateButton, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private class CalculateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
                double heightInInches = Double.parseDouble(heightField.getText());
                double weightInPounds = Double.parseDouble(weightField.getText());

                double heightInMeters = heightInInches * 0.0254;
                double weightInKilograms = weightInPounds * 0.454;
                double bmi = weightInKilograms / (heightInMeters * heightInMeters);

                totalPeople++;
                totalBMI += bmi;

                DecimalFormat df = new DecimalFormat("#.00");
                double averageBMI = totalBMI / totalPeople;

                resultArea.append("BMI: " + df.format(bmi) + "\n");
                resultArea.append("Average BMI: " + df.format(averageBMI) + "\n");
                resultArea.append("Total People: " + totalPeople + "\n\n");

                heightField.setText("");
                weightField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	bmi app = new bmi();
            app.setVisible(true);
        });
    }
}
