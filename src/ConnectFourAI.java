import java.util.Random;
public class ConnectFourAI {
/*********************************************************
 * This is the ai class for connect four.
 * @author Joel Brodzinski
 ********************************************************/
    Random ran = new Random();
    ConnectFour connectF;
    ConnectFour temp;
    
    /****************************************************
     * Instance variables for the ai.
     ***************************************************/
    private int[][] boardOne = new int[7][7];
    private static final int EMPTY = 0;
    private static final int RED = 1;
    private static final int BLACK = 2;

    /****************************************************
     * Constructor, sets the board uses the Connect four
     * @param ConnectFour- takes a connect four instance
     * (the same instance from the menuGUI) and keeps it's
     * own reference.
     ***************************************************/
    public ConnectFourAI(ConnectFour f){
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 7; j++){
                boardOne[i][j] = EMPTY;
            }
        }
        connectF = f;
        temp = new ConnectFour();
    }
    
    /****************************************************
     * set Board- this method sets the game board to be 
     * used in the ai.
     * @param int[][]- takes a board in to set the board
     ***************************************************/
    private void setBoard(int[][] b){
        boardOne = b;
    }

    /****************************************************
     * ai Move- uses copy of the instance, and trys out 
     * different moves before making a move.
     * @return boolean returns if a move was made.
     ***************************************************/
    public boolean aiMove(){
//        for(int i = 0; i < 7; i++){
//            temp.setBoard(connectF.getBoard());
//            temp.setPlayer(2);
//            if(temp.move(i)){
//                if(temp.checkWin() == BLACK){
//                    connectF.move(i);
//                    return true;
//                }
//            }
//        }
        int x = ran.nextInt(7);
        while(connectF.move(x) == false){
            x = ran.nextInt(7);
        }
        
       
        return true;

    }
}
