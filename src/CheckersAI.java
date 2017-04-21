import java.util.Vector;
import java.util.Random;
/******************************************************************
 * This class is used to create a basic AI that will play you in
 * Checkers.
 * @author Jake
 *
 *****************************************************************/
public class CheckersAI {
	
   /****************************
   * rand value.
   * **************************/
	private Random rand = new Random();
	
	   /****************************
	   * Checkerboard value.
	   * **************************/
	private CheckerBoard currentBoard;
	
	   /****************************
	   * riskyMoves vector.
	   * **************************/
	private Vector<MovePiece> riskyMoves;
	
	/**************************************************************
     * Set the currentBoard.
     * @param updateBoard Sets the board
     **************************************************************/
	public void setCurrentBoard(CheckerBoard updateBoard){
	    currentBoard = updateBoard;
	}
	
	/**************************************************************
     * Get the current Board.
     * @return current Board setup
     **************************************************************/
	public CheckerBoard getCurrentBoard(){
	    return currentBoard;
	}
	
	/**************************************************************
	 * Constructor to set the current board state for the AI to work
	 * with.
	 * @param newBoard Constructs a new board
	 **************************************************************/
	public CheckersAI(CheckerBoard newBoard){
		// set current board to the desired board
		currentBoard = newBoard;
	}
	
	 /**************************************************************
     * Method to Move AI randomly.
     * @return Possible moves
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
