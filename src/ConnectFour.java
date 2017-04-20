/*******************************************************
* Connect Four Class
* This holds all methods and logic for playing the game
* @author Joel Brodzinski
********************************************************/

public class ConnectFour{
    /***************************************************
     * Instance variable for connect four.
     * Represents the contents of the board and keeps
     * track of the player.
     **************************************************/
    private static final int EMPTY = 0;
    private static final int RED = 1;
    private static final int BLACK = 2;
    private int[][] board;
    private int player;
    
    /***************************************************
     * Connect four constructor, instantiates a new array
     * of the board, and sets the spaces as empty
     **************************************************/
    public ConnectFour(){
        board = new int [7][7];
        
        setBoard();
        
    }
    
    /***************************************************
     * Set board- This method is called from the constructor
     * it sets all spaces empty for a fresh board.
     **************************************************/
    private void setBoard(){
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 7; j++){
                board[i][j] = EMPTY;
            }
        }
    }
    
    /***************************************************
     * Get Board Piece
     **************************************************/
    public int pieceAt(int x, int y){
        return board[x][y];
    }
    
    /***************************************************
     * Get Board Piece
     **************************************************/
    public void setPieceAt(int x, int y, int piece){
        this.board[x][y] = piece;
    }
    
    
    /***************************************************
     * Move- this method takes a column from the menu
     * class and places the current player's piece there,
     * @param int col - the column the user wishes to 
     * place the next piece.
     * @return boolean - whether the move was made.
     **************************************************/
    public boolean move(int col){
        if(col >= 0 && col < 7){
            for(int i = 6; i >= 0; i--){
                if(board[i][col] == EMPTY){
                    board[i][col] = player;
                    return true;
                }
            }
        }
        
        return false;
    }
    
    /***************************************************
     * change Player - this method changes the players
     * turn
     **************************************************/
    public void changePlayer(){
        if(player == RED){
            player = BLACK;
        }else
            player = RED;
    }
    
    /***************************************************
     * set Player - This method lets you set whose turn
     * @param int p - the player (1 for red, 2 for black).
     **************************************************/
    public void setPlayer(int p){
        if(p == 1 || p == 2){
            player = p;
        }
    }
    
    /***************************************************
     * get Player - returns the current player.
     * @return int player - returns the instance variable
     **************************************************/
    public int getPlayer(){
        return player;
    }
    
    /***************************************************
     * getSpot - this method takes a column, and returns
     * the highest pieces'(the last played) row
     * @param int col - the column the last move was made
     * @return int row - returns the row found plus 1
     * (incrimented for the board[][] in menu that has
     * the first row as arrows.
     **************************************************/
    public int getSpot(int col){
        for(int i = 0; i <= 6 ; i++){
            if(board[i][col] == RED || board[i][col] == BLACK){
                return i + 1;
            }
        }
        return 7;
    }
    
    /***********************************************************
     * Connect Four Method: Checks if a player has won the game.
     * @return boolean - if either red or black won.
     **********************************************************/
     public boolean checkWin(){
         //cycle through board
         for(int i = 1; i < 7; i++){
             for(int j = 0; j < 7; j++){
                 //if black piece found
                 if(board[i][j] == BLACK){
                     //cycle through neighbors
                     for(int a = -1; a < 2; a++){
                         for(int b = -1; b < 2; b++){
                             
                             if(a == 0 && b == 0); //if original spot, do nothing
                             
                             //checking to make sure it stays in bounds
                             else if(a+i > 6 || a+i < 0 || b+j > 6 || b+j < 0);
                             
                             //if another black piece is found
                             else if(board[i+a][j+b] == BLACK){
                                 
                                 //checking to make sure it stays in bounds
                                 if(2*a+i > 6 || 2*a+i < 0 || 2*b+j > 6 || 2*b+j < 0);
                                 
                                 //check if a third black piece is found
                                 else if(board[i+a+a][j+b+b] == BLACK){
                                     
                                     //checking to make sure it stays in bounds
                                     if(3*a+i > 6 || 3*a+i < 0 || 3*b+j > 6 || 3*b+j < 0);
                                     
                                     //check if a fourth black piece is found
                                     else if(board[i+a+a+a][j+b+b+b] == BLACK){
                                         //return true because black won!
                                         return true;
                                     }
                                 }
                             }
                         }
                     }
                 }
                 //mirrored to check if red won!
                 if(board[i][j] == RED){
                     for(int a = -1; a < 2; a++){
                         for(int b = -1; b < 2; b++){
                             if(a == 0 && b == 0){
                                 
                             }
                             else if(a+i > 6 || a+i < 0 || b+j > 6 || b+j < 0);
                             else if(board[i+a][j+b] == RED){
                                 if(2*a+i > 6 || 2*a+i < 0 || 2*b+j > 6 || 2*b+j < 0);
                                 else if(board[i+a+a][j+b+b] == RED){
                                     if(3*a+i > 6 || 3*a+i < 0 || 3*b+j > 6 || 3*b+j < 0);
                                     else if(board[i+a+a+a][j+b+b+b] == RED){
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
}