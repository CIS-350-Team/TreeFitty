/*********************************************************
 * Main Function to run GUI
 * Christian Yap
 *********************************************************/
import java.io.IOException;

import javax.swing.JFrame;

public class MainGUI {
	
	public static void main(String[] args) throws IOException{
		JFrame frame = new JFrame("The Games");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Create a panel from chess panel.
		menuGUI panel = new menuGUI();
				
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setSize(350, 600);
		frame.setVisible(true);
	}
	
}
