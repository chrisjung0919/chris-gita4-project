//Programmer: Christopher Jung
//Date: 8/15/23
//Project: How to make labels and change properties

import javax.swing.*;
import java.awt.*;

public class HelloWorld extends JFrame{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		//Create a frame
		JFrame aFrame = new JFrame("First Frame");
		//set the size of frame
		aFrame.setSize(500,500);
		aFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Create some labels
		JLabel hello1 = new JLabel("Hello World");
		JLabel hello2 = new JLabel("Hello too!");
		
		//Create some fonts
		Font headlineFont = new Font("Arial",Font.BOLD,36);
		Font headlineFont2 = new Font("Arial",Font.BOLD,20);
		
		aFrame.setLayout(new FlowLayout());
		
		//Attach font to the label
		hello1.setOpaque(true);
		hello1.setBackground(Color.black);
		hello1.setForeground(Color.blue);
		
		hello2.setFont(headlineFont2);
		hello2.setForeground(Color.orange);
		
		//add to the form
		aFrame.add(hello1);
		aFrame.add(hello2);
		
		aFrame.setVisible(true);
		
		//add content to frame
		aFrame.getContentPane().setBackground(Color.red);
		
	}

}
