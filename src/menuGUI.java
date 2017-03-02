import java.awt.*;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/*********************************************************
* Class to create the GUI for the menu.
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
	private CheckerBoard checkerGame;
	private MovePiece[] moves, doubleJumps;

	//Movement
	private int turnCounter = 0;
	private MovePiece checkersMovement;
	private int origX = -1;
	private int origY = -1;

	//Connect Four:
	private String player;

	/*********************************************************
	* Constructor for GUI for both Games.
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
	* Method: Creates the checkers board.
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
		turnCounter = 0;
		checkerGame = new CheckerBoard();
		checkersMovement = new MovePiece();
		refreshBoard();
	}

	/*********************************************************
	* Method: Refresh the Board.
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
	* Method: Creates the Connect Four Game.
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
		player = "red";
	}

	/*********************************************************
	* Method: Allows player to go back to main screen.
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
	* Method: Decides what happens when buttons are clicked.
	**********************************************************/
	public void actionPerformed(ActionEvent event){
		Component buttonPressed = (JComponent) event.getSource();
		boolean jump = false;
		boolean end = false;

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
							if ((turnCounter % 2 == 0) && ((checkerGame.pieceAt(origX, origY) == 1)
									|| (checkerGame.pieceAt(origX, origY) == 2))) {
								// Check if the move is legal:
								moves = checkerGame.getLegalMoves(1);
								for (int i = 0; i < moves.length; i++) {
									if ((moves[i].fromCol == checkersMovement.fromCol)
											&& (moves[i].fromRow == checkersMovement.fromRow)
											&& (moves[i].toCol == checkersMovement.toCol)
											&& (moves[i].toRow == checkersMovement.toRow)) {
										checkerGame.makeMove(checkersMovement);
										jump = checkersMovement.isJump();
										end = true;
									}
								}
								if (jump == false && end == true) {
									this.turnCounter++;
								} else if (jump) {
									// Double Jump
									doubleJumps = checkerGame.getLegalJumpsFrom(1, x, y);
									if (doubleJumps == null) {
										this.turnCounter++;
									} else {
										for (int i = 0; i < doubleJumps.length; i++) {
											if ((doubleJumps[i].fromCol == checkersMovement.fromCol)
													&& (doubleJumps[i].fromRow == checkersMovement.fromRow)
													&& (doubleJumps[i].toCol == checkersMovement.toCol)
													&& (doubleJumps[i].toRow == checkersMovement.toRow)) {
												checkerGame.makeMove(checkersMovement);
											}
										}
									}
								}
							}
							//Black's turn:
							else if (((turnCounter % 2 == 1) && ((checkerGame.pieceAt(origX, origY) == 3)
									|| (checkerGame.pieceAt(origX, origY) == 4)))) {
								//reset value of jump
								jump = false;
								end = false;
								// Check if the move is legal:
								moves = checkerGame.getLegalMoves(3);
								for (int i = 0; i < moves.length; i++) {
									if ((moves[i].fromCol == checkersMovement.fromCol)
											&& (moves[i].fromRow == checkersMovement.fromRow)
											&& (moves[i].toCol == checkersMovement.toCol)
											&& (moves[i].toRow == checkersMovement.toRow)) {
										checkerGame.makeMove(checkersMovement);
										jump = checkersMovement.isJump();
										end = true;
									}
								}
								if(jump == false && end == true){
									this.turnCounter++;
								} else if (jump){
									//Double Jump
									doubleJumps = checkerGame.getLegalJumpsFrom(3,  x,  y);
									if(doubleJumps == null){
										this.turnCounter++;
									} else{
										for (int i = 0; i < doubleJumps.length; i++) {
											if ((doubleJumps[i].fromCol == checkersMovement.fromCol)
													&& (doubleJumps[i].fromRow == checkersMovement.fromRow)
													&& (doubleJumps[i].toCol == checkersMovement.toCol)
													&& (doubleJumps[i].toRow == checkersMovement.toRow)) {
												checkerGame.makeMove(checkersMovement);
											}
										}
									}
								}
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
							System.out.println(turnCounter);
						}
					}
				}
			}
		}
		//Game Mode: Connect Four
		if(mode == GameMode.CONNECTFOURMODE){
			if(buttonPressed == boardTwo[0][0]){
				if(move(player, 0)){
					if(player.equalsIgnoreCase("red")){
						player = "black";
					}else
						player = "red";
				}
			}
			if(buttonPressed == boardTwo[0][1]){
				if(move(player, 1)){
					if(player.equalsIgnoreCase("red")){
						player = "black";
					}else
						player = "red";
				}
			}
			if(buttonPressed == boardTwo[0][2]){
				if(move(player, 2)){
					if(player.equalsIgnoreCase("red")){
						player = "black";
					}else
						player = "red";
				}
			}
			if(buttonPressed == boardTwo[0][3]){
				if(move(player, 3)){
					if(player.equalsIgnoreCase("red")){
						player = "black";
					}else
						player = "red";
				}
			}
			if(buttonPressed == boardTwo[0][4]){
				if(move(player, 4)){
					if(player.equalsIgnoreCase("red")){
						player = "black";
					}else
						player = "red";
				}
			}
			if(buttonPressed == boardTwo[0][5]){
				if(move(player, 5)){
					if(player.equalsIgnoreCase("red")){
						player = "black";
					}else
						player = "red";
				}
			}
			if(buttonPressed == boardTwo[0][6]){
				if(move(player, 6)){
					if(player.equalsIgnoreCase("red")){
						player = "black";
					}else
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
	}

	/*********************************************************
	* Connect Four Method: Checks if a player has won the game.
	* @return boolean
	**********************************************************/
	public boolean checkWin(){
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

	/*********************************************************
	* Method: Movement for Connect Four.
	* @return boolean
	**********************************************************/
	public boolean move(String p, int col){
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