package Checkers;

/******************************************************************
 * This class is used to hold an Object that can be used to identify
 * a movement for a piece in checkers.
 * @author Jake
 *
 *****************************************************************/
public class MovePiece {
	/* starting tile */
	int fromRow, fromCol;
	
	/* ending tile */
	int toRow, toCol;

	
	/*************************************************************
	 * Constructor used to set the instance variables to desired
	 * values
	 * 
	 ************************************************************/
	MovePiece(int r1, int c1, int r2, int c2) {
		fromRow = r1;
		fromCol = c1;
		toRow = r2;
		toCol = c2;
	}

	
	/*************************************************************
	 * Method used to identify whether or not the move is a jump
	 * 
	 ************************************************************/
	public boolean isJump() {
		if(fromRow - toRow == 2 || fromRow - toRow == -2)
			return true;
		else
			return false;
	}
}
