import java.util.Vector;
import java.util.Random;
/******************************************************************
 * This class is used to create a basic AI that will play you in
 * Checkers.
 * @author Jake
 *
 *****************************************************************/
public class CheckersAI {
	
	/* Random generator used to pick which move the AI makes */
	Random rand = new Random();
	
	/* the board that the AI uses to decide what move it will make */
	private CheckerBoard currentBoard;
	
	/* a copy of just the board from currentBoard */
	private  int playerAI;
	
	/* an array to save all the safe moves the player can make */
	private Vector<MovePiece> safeMoves;
	
	/**************************************************************
     * Set the currentBoard
     **************************************************************/
	public void setCurrentBoard(CheckerBoard updateBoard){
	    currentBoard = updateBoard;
	}
	
	/**************************************************************
     * Get the current Board
     **************************************************************/
	public CheckerBoard getCurrentBoard(){
	    return currentBoard;
	}
	
	/**************************************************************
	 * Constructor to set the current board state for the AI to work
	 * with.
	 * 
	 **************************************************************/
	public CheckersAI(CheckerBoard newBoard, int playerAI){
		// set current board to the desired board
		currentBoard = newBoard;
		this.playerAI = playerAI;
	}
	
	/**************************************************************
	 * Method to set the AI as desired player
	 **************************************************************/
	public void setArrayHold(int player){
		// set AI as desired player
		playerAI = player;
	}
	
	/**************************************************************
	 * This method attempts to find all safe moves (if any exist).
	 * A "safe move" is a move that does not result in the opponent
	 * immediately being able to jump your pieces. If no safe moves
	 * exist, the array safeMoves will remain empty.
	 * 
	 **************************************************************/
	private void findSafeMoves(int player){
		// method variable to hold opposing player integer value
		int oPlayer;
		
		if (player == 1)
			oPlayer = 3;
		else if (player == 3)
			oPlayer = 1;
		else
			oPlayer = 0;
		// method variables to hold the legal moves from currentBoard
		// one is used for the for loop to loop through below, the other
		// is actually manipulated. This is done to avoid any potential 
		// looping errors that may occur from changing the size of the
		// vector mid-loop
		Vector<MovePiece> legalMoves = currentBoard.getLegalMovesAI(player);
		Vector<MovePiece> legalMoves2 = currentBoard.getLegalMovesAI(player);
		
		
		// begin looping through all of the legal moves the desired
		//player can make
		for(int x = 0; x < legalMoves2.size(); x++){
			// method variable that holds currentBoard state
			CheckerBoard boardHold = currentBoard;
			
			// make the move at position x in the vector
			boardHold.makeMove((MovePiece) legalMoves2.elementAt(x));
			
			// get the list of legal moves the opposing player can now
			// make with this new board state
			Vector<MovePiece> oLegalMoves = boardHold.getLegalMovesAI(oPlayer);
			
			// get the first element in the list of legal moves
			MovePiece oMove = oLegalMoves.elementAt(0);
			
			// check to see if the first element is a jump
			if(oMove.isJump()){
				// if the element is a jump, delete the move that was
				// originally made by the AI from the legalMoves list
				// because it results in an immediate jump
				legalMoves.removeElementAt(x);
			}
				
		}
		
		// check to see if legalMoves has been emptied
		if (legalMoves.size() == 0){
			// if it has, all legal moves the AI can make will
			// result in a jump, so just set the safeMoves
			// instance variable to all possible legal moves
			safeMoves = legalMoves2;
		}
		
		// if legalMoves is not empty, then moves exist that will not
		// immediately result in a jump. fill the safeMoves vector with
		// those safe moves.
		else {

			// set safeMoves to legalMoves
			safeMoves = legalMoves;
		}
	}
	
	/**************************************************************
	 * Method used to have the AI further narrow down its safe moves.
	 * We look for any safe moves that also result in gaining a King
	 * 
	 **************************************************************/
	private void findKingMe(int player){
		// create two vectors to store values in
		Vector<MovePiece> safeMovesHold = new Vector();
		Vector<MovePiece> safeMovesHold2 = safeMoves;
		
		// create copy of currentBoard
		CheckerBoard boardHold = currentBoard;
		
		// begin looking through all the safe moves
		for(int x = 0; x < safeMovesHold2.size(); x++ ){
			// get the current board state
			int[][] board = boardHold.getBoard();
			
			// create another copy of currentBoard
			CheckerBoard boardHold2 = boardHold;
			
			// keep track of kings before and after the move
			int kingBefore = 0;
			int kingAfter = 0;
			// loop through the board and count current number of kings
			for(int row = 0; row < board.length; row++){
				for(int col = 0; col < board[row].length; col++){
					// count appropriate kings for input team
					if(player == 1){
						if(board[row][col] == 2)
							kingBefore++;
					}
					if(player == 3){
						if(board[row][col] == 4)
							kingBefore++;
					}
				}
			}
			// make the move
			boardHold2.makeMove((MovePiece) safeMovesHold2.elementAt(x));
			// set the board to the new board state
			board = boardHold2.getBoard();
			
			// count new number of kings
			for(int row = 0; row < board.length; row++){
				for(int col = 0; col < board[row].length; col++){
					// count appropriate kings for input team
					if(player == 1){
						if(board[row][col] == 2)
							kingAfter++;
					}
					if(player == 3){
						if(board[row][col] == 4)
							kingAfter++;
					}
				}
			}
			// check to see if the move results in a king me
			if (kingAfter > kingBefore)
				safeMovesHold.addElement((MovePiece) safeMovesHold2.elementAt(x));
		}
		
		// if safeMovesHold is empty, no moves restult in a king
		// so leave safeMoves unchanged. If safeMovesHold has any
		// elements, then at least 1 move will result in a king, 
		// so change safeMoves to safeMovesHold
		if(safeMovesHold.size() > 0){
			safeMoves = safeMovesHold;
		}
	}
	
	/**************************************************************
	 * Method used to have the AI actually choose a move from the
	 * list of safe moves and then make it.
	 **************************************************************/
	public MovePiece makeMove(){
		// call findSafeMoves method to narrow search
		findSafeMoves(playerAI);
		
		// call findKingMe to narrow search further
		findKingMe(playerAI);
		
		// randomly generate a number
		int theMove = rand.nextInt(safeMoves.size());
		
		// pick that move and return it
		return safeMoves.elementAt(theMove);
			
	}
}
