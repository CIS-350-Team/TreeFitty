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
 * UPDATED WITH CONNECT FOUR
 **********************************************************/
public class menuGUI extends JPanel implements ActionListener {

	//Instance Variables
	private JPanel top, main, middle, checkerPanel, connectFourPanel;
	private JLabel title;
	private JButton back, quit, checkers, connectFour;
	private ImageIcon checkerImage, connectFourImage;
	private ImageIcon arrow, boardWhite, boardRed, boardBlack;
	private GameMode mode;
	private JButton[][] board;
	private JButton[][] boardTwo;
	private String player;
	private boolean success;

	public menuGUI() throws IOException{

		//Initialize mode
		mode = GameMode.DEFAULT;

		//Initialize Panel: Add rows and columns to the JPanel:
		top = new JPanel(new GridLayout(1,1));
		middle = new JPanel(new GridLayout(1,1));
		main = new JPanel(new GridLayout(2,1));
		main.setBackground(Color.BLACK);


		//Add details to the variables:
		title = new JLabel("The Games");
		back = new JButton("Back");
		quit = new JButton("Quit");
		checkers = new JButton("",null);
		connectFour = new JButton("",null);

		//Add Images:
		checkerImage = new ImageIcon("src\\checkers.jpg");
		connectFourImage = new ImageIcon("src\\connect4.jpg");
		arrow = new ImageIcon("src\\arrow.jpg");
		boardWhite = new ImageIcon("src\\cFourW.jpg");
		boardBlack = new ImageIcon("src\\cFourB.jpg");
		boardRed = new ImageIcon("src\\cFourR.jpg");

		//Add ActionListeners:
		back.addActionListener(this);
		quit.addActionListener(this);
		checkers.addActionListener(this);
		connectFour.addActionListener(this);

		//Add images to main:
		checkers.setIcon(checkerImage);
		connectFour.setIcon(connectFourImage);

		//Add information on top:
		top.add(title, BorderLayout.PAGE_START);

		//Add the buttons for bottom:
		middle.add(back);
		middle.add(quit);

		//Add image to mid:
		main.add(checkers);
		main.add(connectFour); 


		//Finalize Frame
		add(top, BorderLayout.NORTH);
		add(middle,BorderLayout.CENTER);	
		add(main,BorderLayout.SOUTH);

	}

	public void createCheckers(){

		mode = GameMode.CHECKERSMODE;

		remove(main);
		checkerPanel =  new JPanel(new GridLayout(8,8));
		add(checkerPanel, BorderLayout.CENTER);

		//Initialize the board.
		board = new JButton[8][8];
		for(int x = 0; x < board.length; x++){
			for(int y = 0; y < board.length; y++){
				board[x][y] = new JButton("", null);
				if((y % 2 == 0 && x % 2 !=0) || (y % 2 != 0 && x % 2 == 0)){
					board[x][y].setBackground(Color.BLACK);
				}else{
					board[x][y].setBackground(Color.RED);
				}
				board[x][y].addActionListener(this);
				board[x][y].setPreferredSize(new Dimension(60,60));
				checkerPanel.add(board[x][y]);
			}
		}
	}

	public void createConnect(){

		mode = GameMode.CONNECTFOURMODE;

		remove(main);
		connectFourPanel =  new JPanel(new GridLayout(8,7));
		add(connectFourPanel, BorderLayout.CENTER);

		//Initialize the board.
		boardTwo = new JButton[8][7];
		for(int x = 0; x < 8; x++){
			for(int y = 0; y < 7; y++){
				boardTwo[x][y] = new JButton("", null);
				if(x == 0){
					boardTwo[x][y].setIcon(arrow);
					boardTwo[x][y].addActionListener(this);
				}else{
					boardTwo[x][y].setIcon(boardWhite);
					//boardTwo[x][y].setEnabled(false);
				}
				boardTwo[x][y].setPreferredSize(new Dimension(60,60));
				connectFourPanel.add(boardTwo[x][y]);
			}
		}
		player = "red";
	}

	public void goBack(){

		if(mode == GameMode.CHECKERSMODE){
			checkerPanel.removeAll();
			remove(checkerPanel);
			main.add(checkers);
			main.add(connectFour);
			add(main, BorderLayout.CENTER);
		}else if(mode == GameMode.CONNECTFOURMODE){
			connectFourPanel.removeAll();
			remove(connectFourPanel);
			main.add(checkers);
			main.add(connectFour);
			add(main, BorderLayout.CENTER);
		}
	}

	//Add actionPerformed to clicks:
	public void actionPerformed(ActionEvent event){
		Component buttonPressed = (JComponent) event.getSource();

		if(buttonPressed == quit){
			System.exit(0);
		}
		else if(buttonPressed == checkers){
			main.remove(checkers);
			main.remove(connectFour);
			createCheckers();
			this.revalidate();
			this.repaint();
		}
		else if(buttonPressed == back){
			goBack();
			this.revalidate();
			this.repaint();
		}
		else if(buttonPressed == connectFour){
			main.remove(checkers);
			main.remove(connectFour);
			createConnect();
			this.revalidate();
			this.repaint();
		}


		if(buttonPressed == boardTwo[0][0]){
			if(move(player, 0)){
				if(player.equalsIgnoreCase("red")){
					player = "black";
				}
				else
					player = "red";
			}
		}
		if(buttonPressed == boardTwo[0][1]){
			if(move(player, 1)){
				if(player.equalsIgnoreCase("red")){
					player = "black";
				}
				else
					player = "red";
			}
		}
		if(buttonPressed == boardTwo[0][2]){
			if(move(player, 2)){
				if(player.equalsIgnoreCase("red")){
					player = "black";
				}
				else
					player = "red";
			}
		}
		if(buttonPressed == boardTwo[0][3]){
			if(move(player, 3)){
				if(player.equalsIgnoreCase("red")){
					player = "black";
				}
				else
					player = "red";
			}
		}
		if(buttonPressed == boardTwo[0][4]){
			if(move(player, 4)){
				if(player.equalsIgnoreCase("red")){
					player = "black";
				}
				else
					player = "red";
			}
		}
		if(buttonPressed == boardTwo[0][5]){
			if(move(player, 5)){
				if(player.equalsIgnoreCase("red")){
					player = "black";
				}
				else
					player = "red";
			}
		}
		if(buttonPressed == boardTwo[0][6]){
			if(move(player, 6)){
				if(player.equalsIgnoreCase("red")){
					player = "black";
				}
				else
					player = "red";
			}
		}
		if(checkWin()){
			if(player.equals("red")) player = "black";
			else player = "red";
			JOptionPane.showInputDialog(player + " Won");
			connectFourPanel.removeAll();
			remove(connectFourPanel);
			main.remove(connectFour);
			createConnect();
			this.revalidate();
			this.repaint();
		}


	}

	boolean checkWin(){
		for(int i = 1; i<8; i++){
			for(int j = 0; j < 7; j++){
				
				if(boardTwo[i][j].getIcon().equals(boardBlack)){
					for(int a = -1; a < 2; a++){
						for(int b = -1; b < 2; b++){
							if(a == 0 && b == 0);
							else if(a+i > 7 || a+i < 0 || b+j > 6 || b+j < 0);
							else if(boardTwo[i+a][j+b].getIcon().equals(boardBlack)){
								if(2*a+i > 7 || 2*a+i < 0 || 2*b+j > 6 || 2*b+j < 0);
								else if(boardTwo[i+a+a][j+b+b].getIcon().equals(boardBlack)){
									if(3*a+i > 7 || 3*a+i < 0 || 3*b+j > 6 || 3*b+j < 0);
									else if(boardTwo[i+a+a+a][j+b+b+b].getIcon().equals(boardBlack)){
										return true;
									}
								}

							}
						}
					}
				}
				if(boardTwo[i][j].getIcon().equals(boardRed)){
					for(int a = -1; a < 2; a++){
						for(int b = -1; b < 2; b++){
							if(a == 0 && b == 0);
							else if(a+i > 7 || a+i < 0 || b+j > 6 || b+j < 0);
							else if(boardTwo[i+a][j+b].getIcon().equals(boardRed)){
								if(2*a+i > 7 || 2*a+i < 0 || 2*b+j > 6 || 2*b+j < 0);
								else if(boardTwo[i+a+a][j+b+b].getIcon().equals(boardRed)){
									if(3*a+i > 7 || 3*a+i < 0 || 3*b+j > 6 || 3*b+j < 0);
									else if(boardTwo[i+a+a+a][j+b+b+b].getIcon().equals(boardRed)){
										return true;
									}
								}

							}
						}
					}
				}
			}
		}
		return false;
	}

	boolean move(String p, int col){
		for(int i = 7; i >=0; i--){
			if(boardTwo[i][col].getIcon().equals(boardWhite)){
				if(p.equalsIgnoreCase("black")){
					boardTwo[i][col].setIcon(boardBlack);
				}
				if(p.equalsIgnoreCase("red")){
					boardTwo[i][col].setIcon(boardRed);
				}
				return true;
			}
		}
		return false;
	}
}



