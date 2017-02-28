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
* Created by Christian Yap
**********************************************************/
public class menuGUI extends JPanel implements ActionListener {

	//Instance Variables
	private JPanel top, main, middle, checkerPanel, connectFourPanel;
	private JLabel title;
	private JButton back, quit, checkers, connectFour;
	private ImageIcon checkerImage, connectFourImage;
	private ImageIcon arrow, boardWhite, boardRed, boardBlack;
	private ImageIcon whitePiece, orangePiece, kingWhite, kingOrange;
	private GameMode mode;
	private JButton[][] board;
	private JButton[][] boardTwo;
	//Games
	private CheckerBoard checkerGame;
	
	//Movement
	private int turnCounter = 0;
	private MovePiece checkersMovement;
	private int origX = -1;
	private int origY = -1;
	
	/*********************************************************
	* Constructor for GUI for both Games
	**********************************************************/
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
		
		 whitePiece = new ImageIcon("src\\whitePiece.png");
		 orangePiece = new ImageIcon("src\\orangePiece.png");
		 kingWhite = new ImageIcon("src\\kingWhite.png");
		 kingOrange = new ImageIcon("src\\kingOrange.png");
		
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
	
	/*********************************************************
	* Method: Creates the checkers board
	**********************************************************/
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
		
		//Create the checkers game:
		checkerGame = new CheckerBoard();
		checkersMovement = new MovePiece();
		refreshBoard();
	}
	
	/*********************************************************
	* Method: Refresh the Board
	**********************************************************/
	public void refreshBoard(){
		
		int counterWhite = 0;
		int counterBlack = 0;
		//Checkers Mode
		if(mode == GameMode.CHECKERSMODE){
			int piece = -1;
			//Icons for the game:
			for(int x = 0; x < board.length; x ++){
					piece = -1;
				for(int y = 0; y < board.length; y++){
					 piece = checkerGame.pieceAt(x, y);
					if(piece == 1){
						//White Piece:
						board[x][y].setIcon(whitePiece);
						counterWhite++;
					}else if(piece == 2){
						//White Piece, King:
						board[x][y].setIcon(kingWhite);
						counterWhite++;
					}else if(piece == 3){
						//Orange Piece:
						board[x][y].setIcon(orangePiece);
						counterBlack++;
					}else if(piece == 4){
						board[x][y].setIcon(kingOrange);
						counterBlack++;
					}else{
						board[x][y].setIcon(null);
					}
				}
			}
			//Win Cases: 
			if(counterBlack == 0){
				JOptionPane.showMessageDialog(null, "Black has lost!");
			}else if(counterWhite == 0){
				JOptionPane.showMessageDialog(null, "White has lost!");
			}
		}
		
		
	}
	
	/*********************************************************
	* Method: Creates the Connect Four Game
	**********************************************************/
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
				}
				boardTwo[x][y].setPreferredSize(new Dimension(60,60));
				connectFourPanel.add(boardTwo[x][y]);
			}
		}
	}
	
	/*********************************************************
	* Method: Allows player to go back to main screen
	**********************************************************/
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
	
	/*********************************************************
	* Method: Decides what happens when buttons are clicked
	**********************************************************/
	public void actionPerformed(ActionEvent event){
		Component buttonPressed = (JComponent) event.getSource();
		
		if(buttonPressed == quit){
			 System.exit(0);
		}else if(buttonPressed == checkers){
			main.remove(checkers);
			main.remove(connectFour);
			createCheckers();
			this.revalidate();
			this.repaint();
		}else if(buttonPressed == back){
			goBack();
			this.revalidate();
			this.repaint();
		}else if(buttonPressed == connectFour){
			main.remove(checkers);
			main.remove(connectFour);
			createConnect();
			this.revalidate();
			this.repaint();
		}
		
		//Game Mode: Checkers
		if(mode == GameMode.CHECKERSMODE){
			//add action listeners
			for(int x = 0; x < 8; x++){
				for(int y = 0; y < 8; y++){
					if(buttonPressed.equals(board[x][y])){ 		
						//First Click
						if(origX == -1 && origY == -1){
						origX = x;
						origY = y;	
						board[x][y].setBackground(Color.yellow);
						}
						//Cancel Click
						else if(origX == x && origY == y){
							origX = -1;
							origY = -1;
							if((x % 2 == 1 && y %2 == 0) || (x % 2 == 0 && y %2 == 1)){
								board[x][y].setBackground(Color.BLACK);
							}else{
								board[x][y].setBackground(Color.RED);
							}
						}
						//Second Click
						else{
							checkersMovement.setMove(origX, origY, x, y);						
							
							
							//Check it's the player's turn:
							if((turnCounter % 2 == 0) && ((checkerGame.pieceAt(origX, origY) == 1) || (checkerGame.pieceAt(origX, origY) == 2)) ){
								if(checkerGame.getLegalMoves(1) != null){
								checkerGame.makeMove(checkersMovement);
								this.turnCounter++;
								}
							}else if(((turnCounter % 2 == 1) && ((checkerGame.pieceAt(origX, origY) == 3) || (checkerGame.pieceAt(origX, origY) == 4)) )){
								//if(checkerGame.getLegalMoves(turnCounter) != null){
									checkerGame.makeMove(checkersMovement);
									this.turnCounter++;
								//}					
							}

							//Repaint
							if((origX % 2 == 1 && origY %2 == 0) || (origX % 2 == 0 && origY %2 == 1)){
								board[origX][origY].setBackground(Color.BLACK);
							}else{
								board[origX][origY].setBackground(Color.RED);
							}
							refreshBoard();
							this.revalidate();
							this.repaint();
							
							//Housekeeping - update variables:
							origX = -1;
							origY = -1;
						}
					}
				}
			}
		}
	}
}


