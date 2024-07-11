package javaapp;
import javax.imageio.ImageIO;
 import javax.swing.*;

 import java.awt.*;
 import java.awt.event.ActionListener;
 import java.awt.event.ActionEvent;
 import java.awt.image.BufferedImage;
 import java.io.File;
 import java.io.IOException;
 
 public class javaapp extends JFrame implements ActionListener {
	 
	  //declare our components or fields
	 
	  JTextField txtName = new JTextField(20);
	  JTextField txtDepartment = new JTextField(20);
	  JTextField txtPhone = new JTextField(20);
	 
	  JTextArea txaPhoneList = new JTextArea(
	  "Name Department Phone" +"\n",10,30);
	  JButton btnAdd = new JButton("Add to list");
	 
	  //declare string variable to store information
	  String nameString;
	  String departmentString;
	  String phoneString;
	 
	 
	 
	 
	 
	 
	  public static void main(String[] args) {
	  // TODO Auto-generated method stub
	  //declare our frame
	  javaapp frame = new javaapp();
	  frame.setSize(500,500);
	  frame.setVisible(true);
	  }
	 
	  //Declare a constructor to initialize objects
	  public javaapp()
	   {
	   //put a title bar on frame
	   super("Button Application");
	   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   setLayout(new FlowLayout());
	   //add components to the frame
	   add(new JLabel("Name: "));
	   add(txtName);
	   add(new JLabel("Department: "));
	   add(txtDepartment);
	   add(new JLabel("Phone: "));
	   add(txtPhone);
	   add(btnAdd);
	   add(txaPhoneList);
	  
	   btnAdd.addActionListener(this);
	   txtName.addActionListener(this);
	   txtDepartment.addActionListener(this);
	   txtPhone.addActionListener(this);
	  

	  
	   }
	  public void actionPerformed(ActionEvent event)
	   {
	  
	   Object objSource = event.getSource();
	  
	   if(objSource == btnAdd) {
	  
	   String outputString;
	  
	   nameString = txtName.getText();
	   phoneString =txtPhone.getText();
	   departmentString = txtDepartment.getText();
	  
	   //concatenate text
	   outputString = nameString + "\t" +
	   phoneString +"\t"+
	   departmentString + "\n";
	  
	   //output to the textarea
	   txaPhoneList.append(outputString);
	  
	   //clear textboxes
	  
	   txtName.setText("");
	   txtPhone.setText("");
	   txtDepartment.setText("");
	  
	   txtName.requestFocus();
	  
	  
	  
	   }
	  
	   }
 }