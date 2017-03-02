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
	 * values.
	 ************************************************************/
	public MovePiece() {
		fromRow = -1;
		fromCol = -1;
		toRow = -1;
		toCol = -1;
	}

	/*************************************************************
	 * Constructor used to set the instance variables to desired
	 * values.
	 * @param r1
	 * @param r2
	 * @param c1
	 * @param c2
	 ************************************************************/
	public MovePiece(int r1, int c1, int r2, int c2) {
		fromRow = r1;
		fromCol = c1;
		toRow = r2;
		toCol = c2;
	}

	/*************************************************************
	 * Set Movement.
	 * @param r1
     * @param r2
     * @param c1
     * @param c2
	 ************************************************************/
	public void setMove(int r1, int c1, int r2, int c2){
		this.fromRow = r1;
		this.fromCol = c1;
		this.toRow = r2;
		this.toCol = c2;
	}

	/*************************************************************
	 * Method used to identify whether or not the move is a jump.
	 * @return boolean
	 ************************************************************/
	public boolean isJump() {
		if(fromRow - toRow == 2 || fromRow - toRow == -2){
			return true;
		}else{
			return false;
		}
	}

	 /*************************************************************
     * Setter Method for fromRow.
     * @param x
     ************************************************************/
	public void setFromRow(int x){
	    this.fromRow = x;
	}

	 /*************************************************************
     * Setter Method for fromCol.
     * @param x
     ************************************************************/
    public void setFromCol(int x){
        this.fromCol = x;
    }

    /*************************************************************
     * Setter Method for toRow.
     * @param x
     ************************************************************/
    public void setToRow(int x){
        this.toRow = x;
    }

    /*************************************************************
     * Setter Method for toCol.
     * @param x
     ************************************************************/
    public void setToCol(int x){
        this.toCol = x;
    }

    /*************************************************************
     * Geter Method for fromRow
     ************************************************************/
	public int getFromRow(){
	    return this.fromRow;
	}

	 /*************************************************************
     * Setter Method for fromCol.
     ************************************************************/
    public int getFromCol(){
        return this.fromCol;
    }
    
    /*************************************************************
     * Getter Method for toRow.
     ************************************************************/
    public int getToRow(){
        return this.toRow;
    }
    
    /*************************************************************
     * Getter Method for toColumn.
     ************************************************************/
    public int getToCol(){
        return this.toCol;
    }

}
