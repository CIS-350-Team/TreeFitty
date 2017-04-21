import java.util.Random;
/****************************
 * Connect Four AI.
 * @version 1
 * @author staddlez 
 * **************************/
public class ConnectFourAI {
/*********************************************************
 * This is the ai class for connect four.
 * @author Joel Brodzinski
 ********************************************************/
    private Random ran = new Random();
    /****************************
     * Connect Four.
     * **************************/
    private ConnectFour connectF;
    /****************************
     * Temp variable.
     * **************************/
    private ConnectFour temp;
    
    /****************************************************
     * Instance variables for the ai.
     ***************************************************/
    private static final int seven = 7;
    /****************************
     * board.
     * **************************/
    private int[][] boardOne = new int[seven][seven];
    /****************************
     * Empty value.
     * **************************/
    private static final int EMPTY = 0;


    /****************************************************
     * Constructor, sets the board uses the Connect four.
     * (the same instance from the menuGUI) and keeps it's
     * own reference.
     * @param f Type of Connect4 Game.
     ***************************************************/
    public ConnectFourAI(ConnectFour f){
        for(int i = 0; i < seven; i++){
            for(int j = 0; j < seven; j++){
                boardOne[i][j] = EMPTY;
            }
        }
        connectF = f;
        temp = new ConnectFour();
    }


    /****************************************************
     * ai Move- uses copy of the instance, and trys out 
     * different moves before making a move.
     * @return boolean returns if a move was made.
     ***************************************************/
    public boolean aiMove(){
//        for(int i = 0; i < seven; i++){
//            temp.setBoard(connectF.getBoard());
//            temp.setPlayer(2);
//            if(temp.move(i)){
//                if(temp.checkWin() == BLACK){
//                    connectF.move(i);
//                    return true;
//                }
//            }
//        }
        int x = ran.nextInt(seven);
        while(!connectF.move(x)){
            x = ran.nextInt(seven);
        }
        
       
        return true;

    }
}
