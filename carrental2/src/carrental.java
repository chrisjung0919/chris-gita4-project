import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class carrental extends JFrame implements ActionListener {

    // Declare components
    JTextField txtCustomerName = new JTextField(20);
    JTextField txtAddress = new JTextField(20);
    JTextField txtCity = new JTextField(20);
    JTextField txtState = new JTextField(20);
    JTextField txtZipCode = new JTextField(20);
    JTextField txtBeginOdometer = new JTextField(20);
    JTextField txtEndOdometer = new JTextField(20);
    JTextField txtNumDays = new JTextField(20);
    JTextArea txtAreaResult = new JTextArea(10, 30);
    JButton btnCalculate = new JButton("Calculate");

    double rentalRate = 150.0;
    double mileageRate = 1.25;
    Calculator calculator = new Calculator();

    public static void main(String[] args) {
        carrental frame = new carrental();
        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    public carrental() {
        super("Car Rental Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Add components to the frame
        add(new JLabel("Customer Name: "));
        add(txtCustomerName);
        add(new JLabel("Address: "));
        add(txtAddress);
        add(new JLabel("City: "));
        add(txtCity);
        add(new JLabel("State: "));
        add(txtState);
        add(new JLabel("Zip Code: "));
        add(txtZipCode);
        add(new JLabel("Begin Odometer Reading: "));
        add(txtBeginOdometer);
        add(new JLabel("End Odometer Reading: "));
        add(txtEndOdometer);
        add(new JLabel("Number of Days: "));
        add(txtNumDays);
        add(btnCalculate);
        add(txtAreaResult);

        btnCalculate.addActionListener(this);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == btnCalculate) {
            // Get input values
            String customerName = txtCustomerName.getText();
            String address = txtAddress.getText();
            String city = txtCity.getText();
            String state = txtState.getText();
            String zipCode = txtZipCode.getText();
            double beginOdometer = Double.parseDouble(txtBeginOdometer.getText());
            double endOdometer = Double.parseDouble(txtEndOdometer.getText());
            int numDays = Integer.parseInt(txtNumDays.getText());

            // Set input values in the calculator
            calculator.setRentalRate(rentalRate);
            calculator.setMileageRate(mileageRate);
            calculator.setNumDays(numDays);
            calculator.setBeginOdometer(beginOdometer);
            calculator.setEndOdometer(endOdometer);

            // Calculate rental charges
            double totalCharge = calculator.calculateTotalCharge();

            // Update summary information
            calculator.updateSummary(totalCharge);

            // Create an instance of LocalFormat
            LocalFormat localFormat = new LocalFormat();

            // Format currency values
            String chargesInfo = "Total Charges: " + localFormat.FormatCurrency(totalCharge);

            // Display customer information and charges
            String customerInfo = "Customer Information:\n" +
                    "Name: " + customerName + "\n" +
                    "Address: " + address + ", " + city + ", " + state + " " + zipCode + "\n";
            String rentalInfo = "Rental Information:\n" +
                    "Days Rented: " + numDays + "\n" +
                    "Miles Driven: " + calculator.getMilesDriven() + " miles\n";

            // Display in the text area
            txtAreaResult.setText(customerInfo + rentalInfo + chargesInfo);

            // Display summary information
            String summaryInfo = "Summary:\n" +
                    "Total Amount Received: " + localFormat.FormatCurrency(calculator.getTotalAmountReceived()) + "\n" +
                    "Number of Cars Returned: " + calculator.getCarsReturned() + "\n" +
                    "Average Amount Spent: " + localFormat.FormatCurrency(calculator.getAverageAmountSpent());
            txtAreaResult.append("\n" + summaryInfo);
        }
    }
}