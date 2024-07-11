//Name: Chris J
//Date: 8/23/23
//Project: Car Rental

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class carrental extends JFrame {
    private JTextField customerNameField, addressField, cityField, stateField, zipCodeField,
                        beginningOdometerField, endingOdometerField, daysUsedField;
    private JTextArea resultArea;
    private JButton calculateButton;

    private double totalAmountReceived = 0;
    private int totalCarsReturned = 0;

    public carrental () {
        setTitle("Car Rental Calculator");
        setSize(1300, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        // Initialize components
        customerNameField = new JTextField(20);
        addressField = new JTextField(20);
        cityField = new JTextField(20);
        stateField = new JTextField(20);
        zipCodeField = new JTextField(20);
        beginningOdometerField = new JTextField(20);
        endingOdometerField = new JTextField(20);
        daysUsedField = new JTextField(20);
        resultArea = new JTextArea();
        calculateButton = new JButton("Calculate");

        // Add components to the frame
        add(new JLabel("Customer Name:"));
        add(customerNameField);
        add(new JLabel("Address:"));
        add(addressField);
        add(new JLabel("City:"));
        add(cityField);
        add(new JLabel("State:"));
        add(stateField);
        add(new JLabel("ZIP Code:"));
        add(zipCodeField);
        add(new JLabel("Beginning Odometer:"));
        add(beginningOdometerField);
        add(new JLabel("Ending Odometer:"));
        add(endingOdometerField);
        add(new JLabel("Days Used:"));
        add(daysUsedField);
        add(calculateButton);

        add(new JLabel(""));
        add(resultArea);

        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateCharges();
            }
        });
    }

    private void calculateCharges() {
        String customerName = customerNameField.getText();
        double beginningOdometer = Double.parseDouble(beginningOdometerField.getText());
        double endingOdometer = Double.parseDouble(endingOdometerField.getText());
        int daysUsed = Integer.parseInt(daysUsedField.getText());

        double milesDriven = endingOdometer - beginningOdometer;
        double totalCharge = 150 * daysUsed + 1.25 * milesDriven;
        DecimalFormat df = new DecimalFormat("#.00");

        resultArea.setText("Customer Information:\n"
                + "Name: " + customerName
                + "\nMiles Driven: " + df.format(milesDriven)
                + "\nTotal Charge: $" + df.format(totalCharge));

        totalAmountReceived += totalCharge;
        totalCarsReturned++;

        double averageAmount = totalAmountReceived / totalCarsReturned;

        resultArea.append("\n\nSummary:\n"
                + "Total Amount Received: $" + df.format(totalAmountReceived)
                + "\nTotal Cars Returned: " + totalCarsReturned
                + "\nAverage Amount Spent: $" + df.format(averageAmount));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            carrental app = new carrental();
            app.setVisible(true);
        });
    }
}