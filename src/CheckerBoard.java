package Checkers;

import java.util.Vector;

/******************************************************************
 * This class holds all information and logic required to play a
 * game of checkers.
 * @author Jake
 *
 *****************************************************************/
public class CheckerBoard {

	/* These Instance variables are used to represent the type of contents
	  	within the checkerboard */
	public static final int EMPTY = 0;
	public static final int RED = 1;
	public static final int RED_KING = 2;
	public static final int BLACK = 3;
	public static final int BLACK_KING = 4;

	/* The Checkerboard */
	private int[][] board;

	/*************************************************************
	 * Constructor to create checkerboard and place pieces in starting
	 * positions
	 ************************************************************/
	public CheckerBoard() {
		//set up board to be standard 8x8 checkerboard
		board = new int[8][8];
		
		// call method to set up pieces in starting positions
		setUpGame();
	}

	/*************************************************************
	 * Constructor to create checkerboard and place pieces in current
	 * game positions
	 ************************************************************/
	public void aiCheckerBoard(int[][] currentBoard) {
		//set board to be current game board
		board = currentBoard;
		
	}
	
	/*************************************************************
	 * Method to retrieve current board state
	 * 
	 ************************************************************/
	public int[][] getBoard(){
		// return the board in its current state
		return board;
	}
	
	/*************************************************************
	 * Method that places checkers pieces in proper starting positions
	 * with black on top 3 rows and red on the bottom 3 rows 
	 ************************************************************/
	public void setUpGame() {
		// for loop that will place black pieces in the first 3 rows
		// and red pieces in last 3 rows
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (row % 2 == col % 2) {
					if (row < 3)
						board[row][col] = BLACK;
					else if (row > 4)
						board[row][col] = RED;
					else
						board[row][col] = EMPTY;
				} else {
					board[row][col] = EMPTY;
				}
			}
		}
	}

	
	/*************************************************************
	 * Method used to identify what, if any, piece is currently in
	 * the specified tile
	 ************************************************************/
	public int pieceAt(int row, int col) {
		// Return the contents of the square in the specified row and column.
		return board[row][col];
	}

	
	/*************************************************************
	 * Method used to insert or change the contents of a specified
	 * tile
	 ************************************************************/
	public void setPieceAt(int row, int col, int piece) {
		// Set the contents of the square in the specified row and column.
		// piece must be one of the constants EMPTY, RED, BLACK, RED_KING,
		// BLACK_KING.
		if(piece == 0 || piece == 1 || piece == 2 || piece == 3 ||
			piece == 4){
		board[row][col] = piece;
		}
	}

	
	/*************************************************************
	 * Method used to make a move, whether it be a normal move,
	 * a jump, or turning a piece that reaches the opposite end
	 * into a king
	 ************************************************************/
	public void makeMove(int fromRow, int fromCol, int toRow, int toCol) {
		// Move the piece to new tile and delete it from old tile
		board[toRow][toCol] = board[fromRow][fromCol];
		board[fromRow][fromCol] = EMPTY;
		
		// Check if the move is a jump and if so, delete the piece
		// that was jumped
		if (fromRow - toRow == 2 || fromRow - toRow == -2) {
			int jumpRow = (fromRow + toRow) / 2;
			int jumpCol = (fromCol + toCol) / 2;
			board[jumpRow][jumpCol] = EMPTY;
		}
		
		// If red reaches top of board, assign it king
		if (toRow == 0 && board[toRow][toCol] == RED)
			board[toRow][toCol] = RED_KING;
		
		// If black reaches bottom of board, assign it king
		if (toRow == 7 && board[toRow][toCol] == BLACK)
			board[toRow][toCol] = BLACK_KING;
	}

	
	/*************************************************************
	 * Simpler method that can be called instead of using above
	 * method. This method uses a MovePiece object and calls the above
	 * method using that data.
	 ************************************************************/
	public void makeMove(MovePiece move) {
		// Make the specified move contained in MovePiece object
		makeMove(move.fromRow, move.fromCol, move.toRow, move.toCol);
	}
	
	
	/*************************************************************
	 * Private helper method used to determine if a move being attempted
	 * is actually legal
	 ************************************************************/
	private boolean canMove(int player, int r1, int c1, int r2, int c2) {
		// Make sure attempted move is in bounds
		if (r2 < 0 || r2 >= 8 || c2 < 0 || c2 >= 8)
			return false;
		
		// Make sure attempted move is to an empty tile
		if (board[r2][c2] != EMPTY)
			return false;
		
		// Make sure that non-king red pieces only
		//move upwards
		if (player == RED) {
			if (board[r1][c1] == RED && r2 > r1)
				return false;
			return true;
		}
		
		// Make sure that non-king black pieces
		// only move downwards
		else {
			if (board[r1][c1] == BLACK && r2 < r1)
				return false;
			return true;
		}

	}
	
	
	/*************************************************************
	 * Private method used to check whether a piece can legally jump
	 * an opposing piece
	 ************************************************************/
	private boolean canJump(int player, int r1, int c1, int r2, int c2, int r3, int c3) {
		// Make sure the jump does not go out of bounds
		if (r3 < 0 || r3 >= 8 || c3 < 0 || c3 >= 8)
			return false;

		// Make sure the jump doesnt land onto an occupied
		// tile
		if (board[r3][c3] != EMPTY)
			return false;

		// Make sure red moves up
		if (player == RED) {
			if (board[r1][c1] == RED && r3 > r1)
				return false;
			// Make sure there is a black piece to jump
			if (board[r2][c2] != BLACK && board[r2][c2] != BLACK_KING)
				return false;
			// Return true if all criteria is met
			return true;
		}
		
		// Make sure black moves down
		else {
			if (board[r1][c1] == BLACK && r3 < r1)
				return false;
			// Make sure there is a red piece to jump
			if (board[r2][c2] != RED && board[r2][c2] != RED_KING)
				return false;
			// Return true if all criteria is met
			return true;
		}

	}
	
	
	/*************************************************************
	 * Method used to analyze all legal moves that a player can make
	 * 
	 ************************************************************/
	public MovePiece[] getLegalMoves(int player) {
		// Return nothing if a player is not chosen
		if (player != RED && player != BLACK)
			return null;
		
		// Assign the appropriate king value to player
		int playerKing;
		if (player == RED)
			playerKing = RED_KING;
		else
			playerKing = BLACK_KING;
		
		// create the vector that holds all legal move information
		Vector<MovePiece> moves = new Vector<MovePiece>();

		/*
		 * First, check for any possible jumps. Look at each square on the
		 * board. If that square contains one of the player's pieces, look at a
		 * possible jump in each of the four directions from that square. If
		 * there is a legal jump in that direction, put it in the moves vector.
		 */
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (board[row][col] == player || board[row][col] == playerKing) {
					if (canJump(player, row, col, row + 1, col + 1, row + 2, col + 2))
						moves.addElement(new MovePiece(row, col, row + 2, col + 2));
					if (canJump(player, row, col, row - 1, col + 1, row - 2, col + 2))
						moves.addElement(new MovePiece(row, col, row - 2, col + 2));
					if (canJump(player, row, col, row + 1, col - 1, row + 2, col - 2))
						moves.addElement(new MovePiece(row, col, row + 2, col - 2));
					if (canJump(player, row, col, row - 1, col - 1, row - 2, col - 2))
						moves.addElement(new MovePiece(row, col, row - 2, col - 2));
				}
			}
		}

		/*
		 * If any jump moves were found, then the user must jump, so we don't
		 * add any regular moves. However, if no jumps were found, check for any
		 * legal regular moves. Look at each square on the board. If that
		 * square contains one of the player's pieces, look at a possible move
		 * in each of the four directions from that square. If there is a legal
		 * move in that direction, put it in the moves vector.
		 */
		if (moves.size() == 0) {
			for (int row = 0; row < 8; row++) {
				for (int col = 0; col < 8; col++) {
					if (board[row][col] == player || board[row][col] == playerKing) {
						if (canMove(player, row, col, row + 1, col + 1))
							moves.addElement(new MovePiece(row, col, row + 1, col + 1));
						if (canMove(player, row, col, row - 1, col + 1))
							moves.addElement(new MovePiece(row, col, row - 1, col + 1));
						if (canMove(player, row, col, row + 1, col - 1))
							moves.addElement(new MovePiece(row, col, row + 1, col - 1));
						if (canMove(player, row, col, row - 1, col - 1))
							moves.addElement(new MovePiece(row, col, row - 1, col - 1));
					}
				}
			}
		}

		/*
		 * If no legal moves have been found, return null. Otherwise, create an
		 * array just big enough to hold all the legal moves, copy the legal
		 * moves from the vector into the array, and return the array.
		 */
		
		if (moves.size() == 0)
			return null;
		else {
			MovePiece[] moveArray = new MovePiece[moves.size()];
			for (int i = 0; i < moves.size(); i++){
				moveArray[i] = (MovePiece) moves.elementAt(i);
			}
			return moveArray;
		}
	}
	
	/*************************************************************
	 * Method used to analyze all legal moves that the AI can make
	 * 
	 ************************************************************/
	public Vector<MovePiece> getLegalMovesAI(int player) {
		// Return nothing if a player is not chosen
		if (player != RED && player != BLACK)
			return null;
		
		// Assign the appropriate king value to player
		int playerKing;
		if (player == RED)
			playerKing = RED_KING;
		else
			playerKing = BLACK_KING;
		
		// create the vector that holds all legal move information
		Vector<MovePiece> moves = new Vector<MovePiece>();

		/*
		 * First, check for any possible jumps. Look at each square on the
		 * board. If that square contains one of the player's pieces, look at a
		 * possible jump in each of the four directions from that square. If
		 * there is a legal jump in that direction, put it in the moves vector.
		 */
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (board[row][col] == player || board[row][col] == playerKing) {
					if (canJump(player, row, col, row + 1, col + 1, row + 2, col + 2))
						moves.addElement(new MovePiece(row, col, row + 2, col + 2));
					if (canJump(player, row, col, row - 1, col + 1, row - 2, col + 2))
						moves.addElement(new MovePiece(row, col, row - 2, col + 2));
					if (canJump(player, row, col, row + 1, col - 1, row + 2, col - 2))
						moves.addElement(new MovePiece(row, col, row + 2, col - 2));
					if (canJump(player, row, col, row - 1, col - 1, row - 2, col - 2))
						moves.addElement(new MovePiece(row, col, row - 2, col - 2));
				}
			}
		}

		/*
		 * If any jump moves were found, then the user must jump, so we don't
		 * add any regular moves. However, if no jumps were found, check for any
		 * legal regular moves. Look at each square on the board. If that
		 * square contains one of the player's pieces, look at a possible move
		 * in each of the four directions from that square. If there is a legal
		 * move in that direction, put it in the moves vector.
		 */
		if (moves.size() == 0) {
			for (int row = 0; row < 8; row++) {
				for (int col = 0; col < 8; col++) {
					if (board[row][col] == player || board[row][col] == playerKing) {
						if (canMove(player, row, col, row + 1, col + 1))
							moves.addElement(new MovePiece(row, col, row + 1, col + 1));
						if (canMove(player, row, col, row - 1, col + 1))
							moves.addElement(new MovePiece(row, col, row - 1, col + 1));
						if (canMove(player, row, col, row + 1, col - 1))
							moves.addElement(new MovePiece(row, col, row + 1, col - 1));
						if (canMove(player, row, col, row - 1, col - 1))
							moves.addElement(new MovePiece(row, col, row - 1, col - 1));
					}
				}
			}
		}

		/*
		 * If no legal moves have been found, return null. Otherwise, create an
		 * array just big enough to hold all the legal moves, copy the legal
		 * moves from the vector into the array, and return the array.
		 */
		
		if (moves.size() == 0)
			return null;
		else {
			
			return moves;
		}
	}
	
	
	/*************************************************************
	 * Method similar to getLegalMoves above, is used to identify 
	 * all legal jumps that a player can make with a specific piece
	 * 
	 ************************************************************/
	public MovePiece[] getLegalJumpsFrom(int player, int row, int col) {
		// Return nothing if a player is not chosen
		if (player != RED && player != BLACK)
			return null;
		// Designate appropriate king value
		int playerKing;
		if (player == RED)
			playerKing = RED_KING;
		else
			playerKing = BLACK_KING;
		
		// create vector that holds all legal jumps
		Vector<MovePiece> moves = new Vector<MovePiece>();
		
		/* Look at the desired tile and make sure that it contains
		 * a piece belonging to the designated player. If it does,
		 * use the canJump method to check and see if said piece
		 * is able to legally make a jump move. If so, add this
		 * jump to the list of legal jumps.
		 */
		if (board[row][col] == player || board[row][col] == playerKing) {
			if (canJump(player, row, col, row + 1, col + 1, row + 2, col + 2))
				moves.addElement(new MovePiece(row, col, row + 2, col + 2));
			if (canJump(player, row, col, row - 1, col + 1, row - 2, col + 2))
				moves.addElement(new MovePiece(row, col, row - 2, col + 2));
			if (canJump(player, row, col, row + 1, col - 1, row + 2, col - 2))
				moves.addElement(new MovePiece(row, col, row + 2, col - 2));
			if (canJump(player, row, col, row - 1, col - 1, row - 2, col - 2))
				moves.addElement(new MovePiece(row, col, row - 2, col - 2));
		}
		
		// If no jumps can be made, return null
		if (moves.size() == 0)
			return null;
		
		// If at least one jump can be made, translate vector
		// into array and return array
		else {
			MovePiece[] moveArray = new MovePiece[moves.size()];
			for (int i = 0; i < moves.size(); i++){
				moveArray[i] = (MovePiece) moves.elementAt(i);
			}
			return moveArray;
		}
	}
}