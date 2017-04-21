/******************************************************************
 * This class is used to hold an Object that can be used to identify
 * a movement for a piece in checkers.
 * @author Jake
 *
 *****************************************************************/
public class MovePiece {
    /****************************
     * Starting Tile.
     * **************************/
	public int fromRow, fromCol;

    /****************************
     * Ending tile.
     * **************************/
	public int toRow, toCol;

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
	 * @param r1 row 1
	 * @param r2 row 2
	 * @param c1 column 1
	 * @param c2 column 2
	 ************************************************************/
	public MovePiece(int r1, int c1, int r2, int c2) {
		fromRow = r1;
		fromCol = c1;
		toRow = r2;
		toCol = c2;
	}

	/*************************************************************
	 * Set Movement.
     * @param r1 row 1
     * @param r2 row 2
     * @param c1 column 1
     * @param c2 column 2
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
		return fromRow - toRow == 2 || fromRow - toRow == -2;
	}

	 /*************************************************************
     * Setter Method for fromRow.
     * @param x row
     ************************************************************/
	public void setFromRow(int x){
	    this.fromRow = x;
	}

	 /*************************************************************
     * Setter Method for fromCol.
     * @param x col
     ************************************************************/
    public void setFromCol(int x){
        this.fromCol = x;
    }

    /*************************************************************
     * Setter Method for toRow.
     * @param x row
     ************************************************************/
    public void setToRow(int x){
        this.toRow = x;
    }

    /*************************************************************
     * Setter Method for toCol.
     * @param x to Column
     ************************************************************/
    public void setToCol(int x){
        this.toCol = x;
    }

    /*************************************************************
     * Geter Method for fromRow.
     * @return from Row
     ************************************************************/
	public int getFromRow(){
	    return this.fromRow;
	}

	 /*************************************************************
     * Setter Method for fromCol.
     * @return fromCol column
     ************************************************************/
    public int getFromCol(){
        return this.fromCol;
    }
    
    /*************************************************************
     * Getter Method for toRow.
     * @return toRow toRow
     ************************************************************/
    public int getToRow() {
        return this.toRow;
    }
    
    /*************************************************************
     * Getter Method for toColumn.
     * @return column column #
     ************************************************************/
    public int getToCol(){
        return this.toCol;
    }

}
