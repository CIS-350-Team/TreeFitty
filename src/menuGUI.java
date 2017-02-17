import java.awt.*;
import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*********************************************************
* Class to create the GUI for the menu
**********************************************************/
public class menuGUI extends JPanel implements ActionListener {

	//Instance Variables
	private JPanel top, main, middle;
	private JLabel title;
	private JButton quit, checkers, connectFour;
	private ImageIcon checkerImage, connectFourImage;

	
	public menuGUI() throws IOException{
		
		//Initialize Panel: Add rows and columns to the JPanel:
		top = new JPanel(new GridLayout(1,1));
		middle = new JPanel(new GridLayout(1,1));
		main = new JPanel(new GridLayout(2,1));
		main.setBackground(Color.BLACK);
		
		
		//Add details to the variables:
		title = new JLabel("The Games");
		quit = new JButton("Quit");
		checkers = new JButton("",null);
		connectFour = new JButton("",null);
		
		//Add Images:
		checkerImage = new ImageIcon("src\\checkers.jpg");
		connectFourImage = new ImageIcon("src\\connect4.jpg");
		
		//Add ActionListeners:
		quit.addActionListener(this);
		checkers.addActionListener(this);
		connectFour.addActionListener(this);
		
		//Add images to main:
		checkers.setIcon(checkerImage);
		connectFour.setIcon(connectFourImage);
		
		//Add information on top:
		top.add(title, BorderLayout.PAGE_START);
		
		//Add the buttons for bottom:
		middle.add(quit);
		
		//Add image to mid:
		main.add(checkers);
		main.add(connectFour); 
		 
		
		//Finalize Frame
		add(top, BorderLayout.NORTH);
		add(main,BorderLayout.CENTER);
		add(middle,BorderLayout.SOUTH);
		
		
	}
	
	//Add actionPerformed to clicks:
	public void actionPerformed(ActionEvent event){
		Component buttonPressed = (JComponent) event.getSource();
		
		if(buttonPressed == quit){
			 System.exit(0);
		}else if(buttonPressed == checkers){
			main.removeAll();
			main.revalidate();
		}
		
	}
}


