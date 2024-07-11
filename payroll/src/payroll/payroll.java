package payroll;
//Name: Mister S
//Date: 8/21/23
//Project:
/*
* This projects teaches how to use textboxes
* and a button to do calculations
*/
import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class payroll extends JFrame implements ActionListener{

//declare our components or fields

JTextField txtName = new JTextField(20);
JTextField txtHours = new JTextField(20);
JTextField txtRate = new JTextField(20);

JTextArea txaPayRoll = new JTextArea(
"Name Your Pay:" +"\n",10,30);
JButton btnAdd = new JButton("Calculate");

String nameString ="";

public static void main(String[] args) {
//TODO Auto-generated method stub
payroll frame = new payroll();
frame.setSize(500,500);
frame.setVisible(true);
}

//constructor method
public payroll()
{
super("Button Application");
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setLayout(new FlowLayout());
//add components to the frame
add(new JLabel("Name: "));
add(txtName);

add(new JLabel("Hours Worked "));
add(txtHours);
add(new JLabel("Wage Rate: "));
add(txtRate);
add(btnAdd);
add(txaPayRoll);

btnAdd.addActionListener(this);
txtName.addActionListener(this);
txtHours.addActionListener(this);
txtRate.addActionListener(this);
}

public void actionPerformed(ActionEvent event)
{
double rateDouble;
double hoursDouble;
double payDouble;
String outputString = "";

Object objSource = event.getSource();


if(objSource == btnAdd) {
nameString = txtName.getText();
rateDouble = Double.parseDouble(txtRate.getText());
hoursDouble = Double.parseDouble(txtHours.getText());

//connect the two classes
CalculationClass calcPay = new CalculationClass(rateDouble,hoursDouble);

payDouble = calcPay.getPay();

LocalFormat fatLocal = new LocalFormat();
outputString = nameString + "\t" + fatLocal.FormatCurrency(payDouble);

txaPayRoll.append(outputString);

}
}//end


//public double calculatePay(double theHours, double theRate)
//{
//double theirPay = theHours * theRate;
//return theirPay;
//
//}
}