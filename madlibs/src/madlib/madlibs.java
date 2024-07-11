package madlib;
//Name: Chris J
//Date: 8/17/23
//Project:
/*
* This projects teaches how to use textboxes
* and a button to concatenate
*/

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class madlibs extends JFrame implements ActionListener {
//declare our components or fields

JTextField txtName = new JTextField(20);
JTextField txtPlace = new JTextField(20);
JTextField txtFood = new JTextField(20);
JTextField txtFood2 = new JTextField(20);
JTextField txtName2 = new JTextField(20);
JTextField txtAction = new JTextField(20);
JTextField txtPlace2 = new JTextField(20);
JTextField txtFood3 = new JTextField(20);
JTextField txtAction2 = new JTextField(20);
JTextField txtName3 = new JTextField(20);

JTextArea txaList = new JTextArea(
"Madlibs" +"\n",10,70);
JButton btnAdd = new JButton("Add to list");

//declare string variable to store information
String nameString;
String placeString;
String foodString;
String food2String;
String name2String;
String actionString;
String place2String;
String food3String;
String action2String;
String name3String;

public static void main(String[] args) {
 // TODO Auto-generated method stub
 //declare our frame
 madlibs frame = new madlibs();
 frame.setSize(700,700);
 frame.setVisible(true);
}

//Declare a constructor to initialize objects
 public madlibs()
 {
 //put a title bar on frame
 super("Button Application");
 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 setLayout(new FlowLayout());
 //add components to the frame
 add(new JLabel("Name: "));
 add(txtName);
 add(new JLabel("Food: "));
 add(txtFood);
 add(new JLabel("Place: "));
 add(txtPlace);
 add(new JLabel("Food 2: "));
 add(txtFood2);
 add(new JLabel("Name 2: "));
 add(txtName2);
 add(new JLabel("Action(ing): "));
 add(txtAction);
 add(new JLabel("Place 2: "));
 add(txtPlace2);
 add(new JLabel("Food 3: "));
 add(txtFood3);
 add(new JLabel("Action 2(ing): "));
 add(txtAction2);
 add(new JLabel("Name 3: "));
 add(txtName3);
 add(btnAdd);
 add(txaList);

 btnAdd.addActionListener(this);
 txtName.addActionListener(this);
 txtPlace.addActionListener(this);
 txtFood.addActionListener(this);
 txtFood2.addActionListener(this);
 txtName2.addActionListener(this);
 txtAction.addActionListener(this);
 txtPlace2.addActionListener(this);
 txtFood3.addActionListener(this);
 txtAction2.addActionListener(this);
 txtName3.addActionListener(this);  

 }
 
 public void actionPerformed(ActionEvent event)
  {
 
  Object objSource = event.getSource();
 
  if(objSource == btnAdd) {
 
  String outputString;
 
  nameString = txtName.getText();
  foodString =txtFood.getText();
  placeString = txtPlace.getText();
  food2String = txtFood2.getText();
  name2String = txtName2.getText();
  actionString = txtAction.getText();
  place2String = txtPlace2.getText();  
  food3String = txtFood3.getText();
  action2String = txtAction2.getText();
  name3String = txtName3.getText();  

 
 
  //concatenate text
  outputString = nameString + " eats " +
  foodString + " goes to " + placeString + " orders " + food2String + " hangs out with " + name2String + " who enjoys " + actionString +
  " walks to " + "\n" + place2String + " swallows " + food3String + " and enjoys " + action2String + " with " + name3String;
 
  //output to the textarea
  txaList.append(outputString);
 
  //clear textboxes
 
  txtName.setText("");
  txtFood.setText("");
  txtPlace.setText("");
  txtFood.setText("");
  txtFood2.setText("");
  txtName2.setText("");
  txtAction.setText("");
  txtPlace2.setText("");
  txtFood3.setText("");
  txtAction2.setText("");
  txtName3.setText("");
 
  txtName.requestFocus();
 
 
 
  }
 
}
 
}