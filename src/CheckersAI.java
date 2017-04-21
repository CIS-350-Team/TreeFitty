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
	
	/* an array to save all the safe moves the player can make */
	private Vector<MovePiece> riskyMoves;
	
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
	public CheckersAI(CheckerBoard newBoard){
		// set current board to the desired board
		currentBoard = newBoard;
	}
	
	 /**************************************************************
     * Method to Move AI randomly
     **************************************************************/
	public MovePiece makeRiskyMove(){
	    
	    int theMove = 0;
	    riskyMoves = new Vector<MovePiece>();
	    riskyMoves = currentBoard.getLegalMovesAI(3);
	    
	    if(riskyMoves.size() > 0){
	    theMove = rand.nextInt(riskyMoves.size());
	       return riskyMoves.get(theMove);
	    }
	    
	    
	   return null;
	}
}
