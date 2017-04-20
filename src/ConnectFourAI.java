import java.util.Random;
public class ConnectFourAI {
    
    Random ran = new Random();
    ConnectFour connectF = new ConnectFour();
    
    private int[][] boardOne;
    private int[][] boardTwo;
    private static final int EMPTY = 0;
    private static final int RED = 1;
    private static final int BLACK = 2;
    
    
    public ConnectFourAI(){
        boardOne = new int[7][7];
        boardTwo = new int[7][7];
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 7; j++){
                boardOne[i][j] = EMPTY;
            }
        }
        boardTwo = boardOne;
    }
    
    public int aiMove(int[][] board){
        boardOne = board;
        for(int i = 0; i < 7; i++){
            boardTwo = boardOne;
            boardTwo = move(i, boardTwo);
            if(win(boardTwo)){
                return i;
            }
        }
        
        return ran.nextInt(7);
    }
    
    private int[][] move(int col, int[][] board){
        if(col >= 0 && col < 7){
            for(int i = 6; i >= 0; i--){
                if(board[i][col] == EMPTY){
                    board[i][col] = BLACK;
                }
            }
        }
        
        return board;
    }
    
    private boolean win(int[][] board){
        
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
