/*********************************************************
 * Main Function to run GUI.
 * Christian Yap
 *********************************************************/
import java.io.IOException;
import javax.swing.JFrame;
/**********************************
 * Main Function to run the GUI.
 * @author Christian Yap
 * CIS 350 Project Release 1
 **********************************/
public class MainGUI {
    public static void main(String[] args) throws IOException{
		JFrame frame = new JFrame("The Games");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Create a panel from chess panel.
		menuGUI panel = new menuGUI();
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setSize(500, 600);
		frame.setVisible(true);
	}
}
