import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.time.Clock;
import java.util.ArrayList;

/*********************************************************
 * Class to create the GUI for the menu.
 * Created by Christian Yap
 **********************************************************/
public class menuGUI extends JPanel implements ActionListener {

    //Instance Variables
    private JPanel top, main, middle, checkerPanel, connectFourPanel;
    private JLabel title;
    private JButton back, quit, checkers, connectFour, open, save;
    private ImageIcon checkerImage, connectFourImage;
    private ImageIcon arrow, boardWhite, boardRed, boardBlack;
    private ImageIcon whitePiece, orangePiece, kingWhite, kingOrange;
    private GameMode mode;
    private JButton[][] board;
    private JButton[][] boardTwo;
    private CheckerBoard checkerGame;
    private MovePiece[] moves, doubleJumps;

    //Movement
    private int turnCounter = 0;
    private MovePiece checkersMovement;
    private MovePiece aiMovement;
    private int origX = -1;
    private int origY = -1;

    //Connect Four:
    private ConnectFour connectF;
    
    //Create AI:
    private ConnectFourAI ai;
    private CheckersAI checkersAI;
    private String input;

    /*********************************************************
     * Constructor for GUI for both Games.
     **********************************************************/
    public menuGUI() throws IOException{
        //Initialize mode
        mode = GameMode.DEFAULT;
        //Initialize Panel: Add rows and columns to the JPanel:
        top = new JPanel(new GridLayout(1,1));
        middle = new JPanel(new GridLayout(1,1));
        main = new JPanel(new GridLayout(2,1));
        main.setBackground(Color.BLACK);
        //Add details to the variables:
        title = new JLabel("The Games");
        back = new JButton("Back");
        quit = new JButton("Quit");
        open = new JButton("Open");
        save = new JButton("Save");
        checkers = new JButton("",null);
        connectFour = new JButton("",null);

        //Add Images:
        checkerImage = new ImageIcon("src\\checkers.jpg");
        connectFourImage = new ImageIcon("src\\connect4.jpg");
        arrow = new ImageIcon("src\\arrow.jpg");
        boardWhite = new ImageIcon("src\\cFourW.jpg");
        boardBlack = new ImageIcon("src\\cFourB.jpg");
        boardRed = new ImageIcon("src\\cFourR.jpg");
        whitePiece = new ImageIcon("src\\whitePiece.png");
        orangePiece = new ImageIcon("src\\orangePiece.png");
        kingWhite = new ImageIcon("src\\kingWhite.png");
        kingOrange = new ImageIcon("src\\kingOrange.png");

        //Add ActionListeners:
        back.addActionListener(this);
        quit.addActionListener(this);
        open.addActionListener(this);
        save.addActionListener(this);
        checkers.addActionListener(this);
        connectFour.addActionListener(this);

        //Add images to main:
        checkers.setIcon(checkerImage);
        connectFour.setIcon(connectFourImage);
        //Add information on top:
        top.add(title, BorderLayout.PAGE_START);

        //Add the buttons for bottom:
        middle.add(back);
        middle.add(open);
        middle.add(save);
        middle.add(quit);

        //Add image to mid:
        main.add(checkers);
        main.add(connectFour);

        //Finalize Frame
        add(top, BorderLayout.NORTH);
        add(middle,BorderLayout.CENTER);
        add(main,BorderLayout.SOUTH);

        //Disable back button:
        back.setEnabled(false);
        open.setEnabled(false);
        save.setEnabled(false);

    }

    /*********************************************************
     * Method: Creates the checkers board.
     **********************************************************/
    public void createCheckers(){
        

        mode = GameMode.CHECKERSMODE;

        remove(main);
        checkerPanel =  new JPanel(new GridLayout(8,8));
        add(checkerPanel, BorderLayout.CENTER);

        //Initialize the board.
        board = new JButton[8][8];
        for(int x = 0; x < board.length; x++){
            for(int y = 0; y < board.length; y++){
                board[x][y] = new JButton("", null);
                if((y % 2 == 0 && x % 2 !=0) || (y % 2 != 0 && x % 2 == 0)){
                    board[x][y].setBackground(Color.BLACK);
                }else{
                    board[x][y].setBackground(Color.RED);
                }
                board[x][y].addActionListener(this);
                board[x][y].setPreferredSize(new Dimension(60,60));
                checkerPanel.add(board[x][y]);
            }
        }
        //Create the checkers game:
        turnCounter = 0;
        checkerGame = new CheckerBoard();
        checkersMovement = new MovePiece();
        refreshBoard();
        back.setEnabled(true);
        open.setEnabled(true);
        save.setEnabled(true);
        
        //Start the AI:
        checkersAI = new CheckersAI(checkerGame);
        checkersAI.setCurrentBoard(checkerGame);
        
        input = JOptionPane.showInputDialog ( "Type '1' for 1 player or '2' player"); 
        
    }

    /*********************************************************
     * Method: Refresh the Board.
     **********************************************************/
    public void refreshBoard(){

        int counterWhite = 0;
        int counterBlack = 0;
        //Checkers Mode
        if(mode == GameMode.CHECKERSMODE){
            int piece = -1;
            //Icons for the game:
            for(int x = 0; x < board.length; x ++){
                piece = -1;
                for(int y = 0; y < board.length; y++){
                    piece = checkerGame.pieceAt(x, y);
                    if(piece == 1){
                        //White Piece:
                        board[x][y].setIcon(whitePiece);
                        counterWhite++;
                    }else if(piece == 2){
                        //White Piece, King:
                        board[x][y].setIcon(kingWhite);
                        counterWhite++;
                    }else if(piece == 3){
                        //Orange Piece:
                        board[x][y].setIcon(orangePiece);
                        counterBlack++;
                    }else if(piece == 4){
                        board[x][y].setIcon(kingOrange);
                        counterBlack++;
                    }else{
                        board[x][y].setIcon(null);
                    }
                }
            }
            //Win Cases:
            if(counterBlack == 0){
                JOptionPane.showMessageDialog(null, "Black has lost!");
            }else if(counterWhite == 0){
                JOptionPane.showMessageDialog(null, "White has lost!");
            }
        }
        
        if(mode == GameMode.CONNECTFOURMODE){
            int piece = -1;
            //Icons for the game:
            for(int x = 1; x < 7; x++){
                piece = -1;
                for(int y = 0; y < 7; y++){
                    piece = connectF.pieceAt(x, y);
                    if(piece == 1){
                        //White Piece:
                        boardTwo[x+1][y].setIcon(boardRed);
                        counterWhite++;
                    }else if(piece == 2){
                        //Black Piece
                        boardTwo[x+1][y].setIcon(boardBlack);
                        counterWhite++;
                    }else if(piece == 0){
                        boardTwo[x+1][y].setIcon(boardWhite);
                    }
                }
            }
        }
    }

    /*********************************************************
     * Method: Creates the Connect Four Game.
     **********************************************************/
    public void createConnect(){

        mode = GameMode.CONNECTFOURMODE;
        connectF = new ConnectFour();
        remove(main);
        connectFourPanel =  new JPanel(new GridLayout(8,7));
        add(connectFourPanel, BorderLayout.CENTER);

        //Initialize the board.
        boardTwo = new JButton[8][7];
        for(int x = 0; x < 8; x++){
            for(int y = 0; y < 7; y++){
                boardTwo[x][y] = new JButton("", null);
                if(x == 0){
                    boardTwo[x][y].setIcon(arrow);
                    boardTwo[x][y].addActionListener(this);
                }else{
                    boardTwo[x][y].setIcon(boardWhite);
                }
                boardTwo[x][y].setPreferredSize(new Dimension(60,60));
                connectFourPanel.add(boardTwo[x][y]);
            }
        }
        connectF.setPlayer(1);
        back.setEnabled(true);
        open.setEnabled(true);
        save.setEnabled(true);
        
        //Start AI:
        ai = new ConnectFourAI();
    }

    /*********************************************************
     * Method: Save the Current Game
     **********************************************************/
    public void saveFile(){

        String fileName = "";
        String boardLayout = "";

        int piece = -1;
        //If playing Checkers:
        if(mode == GameMode.CHECKERSMODE){

            fileName = "";
            fileName = JOptionPane.showInputDialog ( "Save file as:"); 

            if(fileName != null){
                fileName = fileName.concat(".txt");
                //Get board Layout
                for(int x = 0; x < board.length; x ++){
                    piece = -1;
                    for(int y = 0; y < board.length; y++){
                        piece = checkerGame.pieceAt(x, y);
                        if(piece == 1){
                            //White Piece:
                            boardLayout = boardLayout.concat("1 ");
                        }else if(piece == 2){
                            //White Piece, King:
                            boardLayout = boardLayout.concat("2 ");
                        }else if(piece == 3){
                            //Orange Piece:
                            boardLayout = boardLayout.concat("3 ");
                        }else if(piece == 4){
                            //Orange Piece, King:
                            boardLayout = boardLayout.concat("4 ");
                        }else{
                            //Empty Board
                            boardLayout = boardLayout.concat("5 ");
                        }
                    }
                }

                try{
                    PrintStream out = new PrintStream(new FileOutputStream(fileName));
                    out.print(boardLayout);
                    out.close();
                }catch(FileNotFoundException e) {
                    e.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "File saved as " +  fileName);
            }
        }
        
        //Connect Four Gamemode:
        if(mode == GameMode.CONNECTFOURMODE){

            fileName = "";
            fileName = JOptionPane.showInputDialog ( "Save file as:"); 

            if(fileName != null){
                fileName = fileName.concat(".txt");
                //Get board Layout
                for(int x = 0; x < 7; x++){
                    piece = -1;
                    for(int y = 0; y < 7; y++){
                        piece = connectF.pieceAt(x, y);
                        if(piece == 1){
                            //Red:
                            boardLayout = boardLayout.concat("1 ");
                        }else if(piece == 2){
                            //Black:
                            boardLayout = boardLayout.concat("2 ");
                        }else{
                            //Empty Board
                            boardLayout = boardLayout.concat("0 ");
                        }
                    }
                }

                try{
                    PrintStream out = new PrintStream(new FileOutputStream(fileName));
                    out.print(boardLayout);
                    out.close();
                }catch(FileNotFoundException e) {
                    e.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "File saved as " +  fileName);
            }
        }
    }

    /*************************************************
     * Method to read the file
     **************************************************/
    public String readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }

    /*********************************************************
     * Method: Open a Game
     **********************************************************/
    public void openFile(){

        ArrayList<Integer> checkerPosition = new ArrayList <Integer>();
        ArrayList<Integer> connectPosition = new ArrayList <Integer>();
        String openFile = "";
        String input = "";

        //In Checkers mode
        if(mode == GameMode.CHECKERSMODE){
            openFile = JOptionPane.showInputDialog("Enter file name: " );

            if(openFile != null){
                openFile = openFile.concat(".txt");
                JOptionPane.showMessageDialog(null, "Trying to open file: " +  openFile);

                try{
                    input = readFile(openFile);
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Failed to open file");
                }

                for(int i = 0; i < input.length(); i++){
                    if(input.charAt(i) == '5'){
                        checkerPosition.add(0);
                        i++;
                    }else if(input.charAt(i) == '1'){
                        checkerPosition.add(1);
                        i++;
                    }else if(input.charAt(i) == '2'){
                        checkerPosition.add(2);
                        i++;
                    }else if(input.charAt(i) == '3'){
                        checkerPosition.add(3);
                        i++;
                    }else if(input.charAt(i) == '4'){
                        checkerPosition.add(4);
                        i++;
                    }
                }

                for(int x = 0; x < board.length; x ++){
                    for(int y = 0; y < board.length; y++){
                        board[x][y].setIcon(null);
                    }
                }              
                for(int x = 0; x < 8; x++){
                    for(int y = 0; y < 8; y++){
                        checkerGame.setPieceAt(x, y, checkerPosition.get( ((x*8)+y) ) );
                    }
                }
                refreshBoard();
                this.repaint();
                this.revalidate();
            }
        }
        //End checkers mode
        
      //In Connect Four mode
        if(mode == GameMode.CONNECTFOURMODE){
            openFile = JOptionPane.showInputDialog("Enter file name: " );

            if(openFile != null){
                openFile = openFile.concat(".txt");
                JOptionPane.showMessageDialog(null, "Trying to open file: " +  openFile);

                try{
                    input = readFile(openFile);
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Failed to open file");
                }

                for(int i = 0; i < input.length(); i++){
                    if(input.charAt(i) == '0'){
                        connectPosition.add(0);
                        i++;
                    }else if(input.charAt(i) == '1'){
                        connectPosition.add(1);
                        i++;
                    }else if(input.charAt(i) == '2'){
                        connectPosition.add(2);
                        i++;
                    }
                }

                for(int x = 1; x < 7; x ++){
                    for(int y = 0; y < 7; y++){
                        boardTwo[x][y].setIcon(boardWhite);
                    }
                }              
                
                
                int counter = 0;
                for(int x = 0; x < 7; x ++){
                    for(int y = 0; y < 7; y++){
                        connectF.setPieceAt(x, y, connectPosition.get(counter));
                        counter++;
                    }

                }
                
                for(int x = 0; x < 7; x ++){
                    for(int y = 0; y < 7; y++){
                        System.out.print(connectF.pieceAt(x, y));
                    }
                    System.out.println();
                }    
                
                refreshBoard();
                this.repaint();
                this.revalidate();
            }
        }
        //End checkers mode 
    }

    /*********************************************************
     * Method: Allows player to go back to main screen.
     **********************************************************/
    public void goBack(){

        if(mode == GameMode.CHECKERSMODE){
            checkerPanel.removeAll();
            remove(checkerPanel);
            main.add(checkers);
            main.add(connectFour);
            add(main, BorderLayout.CENTER);
        }else if(mode == GameMode.CONNECTFOURMODE){
            connectFourPanel.removeAll();
            remove(connectFourPanel);
            main.add(checkers);
            main.add(connectFour);
            add(main, BorderLayout.CENTER);
        }
        back.setEnabled(false);
        open.setEnabled(false);
        save.setEnabled(false);
    }

    /*********************************************************
     * Method: Decides what happens when buttons are clicked.
     **********************************************************/
    public void actionPerformed(ActionEvent event){
        Component buttonPressed = (JComponent) event.getSource();
        boolean jump = false;
        boolean end = false;

        if(buttonPressed == quit){
            System.exit(0);
        }else if(buttonPressed == checkers){
            main.remove(checkers);
            main.remove(connectFour);
            createCheckers();
            this.revalidate();
            this.repaint();
        }else if(buttonPressed == back){
            goBack();
            this.revalidate();
            this.repaint();
        }else if(buttonPressed == connectFour){
            main.remove(checkers);
            main.remove(connectFour);
            createConnect();
            this.revalidate();
            this.repaint();
        }
        //Game Mode: Checkers
        if(mode == GameMode.CHECKERSMODE){

            if(buttonPressed == save){
                saveFile();
            }

            if(buttonPressed == open){
                openFile();
            }

            //add action listeners
            for(int x = 0; x < 8; x++){
                for(int y = 0; y < 8; y++){
                    if(buttonPressed.equals(board[x][y])){
                        //First Click
                        if(origX == -1 && origY == -1){
                            origX = x;
                            origY = y;
                            board[x][y].setBackground(Color.yellow);
                        }
                        //Cancel Click
                        else if(origX == x && origY == y){
                            origX = -1;
                            origY = -1;
                            if((x % 2 == 1 && y %2 == 0) || (x % 2 == 0 && y %2 == 1)){
                                board[x][y].setBackground(Color.BLACK);
                            }else{
                                board[x][y].setBackground(Color.RED);
                            }
                        }
                        //Second Click
                        else{
                            checkersMovement.setMove(origX, origY, x, y);
                            System.out.println(origX + " " + origY + "" + x + "" + y);
                            //Check it's the player's turn:
                            if ((turnCounter % 2 == 0) && ((checkerGame.pieceAt(origX, origY) == 1)
                                    || (checkerGame.pieceAt(origX, origY) == 2))) {
                                // Check if the move is legal:
                                moves = checkerGame.getLegalMoves(1);
                                for (int i = 0; i < moves.length; i++) {
                                    if ((moves[i].fromCol == checkersMovement.fromCol)
                                            && (moves[i].fromRow == checkersMovement.fromRow)
                                            && (moves[i].toCol == checkersMovement.toCol)
                                            && (moves[i].toRow == checkersMovement.toRow)) {
                                        checkerGame.makeMove(checkersMovement);
                                        jump = checkersMovement.isJump();
                                        end = true;
                                    }
                                }
                                if (jump == false && end == true) {
                                    this.turnCounter++;
                                } else if (jump) {
                                    // Double Jump
                                    doubleJumps = checkerGame.getLegalJumpsFrom(1, x, y);
                                    if (doubleJumps == null) {
                                        this.turnCounter++;
                                    } else {
                                        for (int i = 0; i < doubleJumps.length; i++) {
                                            if ((doubleJumps[i].fromCol == checkersMovement.fromCol)
                                                    && (doubleJumps[i].fromRow == checkersMovement.fromRow)
                                                    && (doubleJumps[i].toCol == checkersMovement.toCol)
                                                    && (doubleJumps[i].toRow == checkersMovement.toRow)) {
                                                checkerGame.makeMove(checkersMovement);
                                            }
                                        }
                                    }
                                }
                            } //Black's (AI) turn:
                            else if (input.equalsIgnoreCase("2") && ((turnCounter % 2 == 1) && ((checkerGame.pieceAt(origX, origY) == 3)
                                    || (checkerGame.pieceAt(origX, origY) == 4)))) {
                                //reset value of jump
                                jump = false;
                                end = false;
                                // Check if the move is legal:
                                moves = checkerGame.getLegalMoves(3);
                                for (int i = 0; i < moves.length; i++) {
                                    if ((moves[i].fromCol == checkersMovement.fromCol)
                                            && (moves[i].fromRow == checkersMovement.fromRow)
                                            && (moves[i].toCol == checkersMovement.toCol)
                                            && (moves[i].toRow == checkersMovement.toRow)) {
                                        checkerGame.makeMove(checkersMovement);
                                        jump = checkersMovement.isJump();
                                        end = true;
                                    }
                                }
                                if(jump == false && end == true){
                                    this.turnCounter++;
                                } else if (jump){
                                    //Double Jump
                                    doubleJumps = checkerGame.getLegalJumpsFrom(3,  x,  y);
                                    if(doubleJumps == null){
                                        this.turnCounter++;
                                    } else{
                                        for (int i = 0; i < doubleJumps.length; i++) {
                                            if ((doubleJumps[i].fromCol == checkersMovement.fromCol)
                                                    && (doubleJumps[i].fromRow == checkersMovement.fromRow)
                                                    && (doubleJumps[i].toCol == checkersMovement.toCol)
                                                    && (doubleJumps[i].toRow == checkersMovement.toRow)) {
                                                checkerGame.makeMove(checkersMovement);
                                            }
                                        }
                                    }
                                }
                            }

                            checkersAI.setCurrentBoard(checkerGame);
                            aiMovement = checkersAI.makeRiskyMove();
                            
                            if ( input.equalsIgnoreCase("1") && ((turnCounter % 2 == 1) && ((checkerGame.pieceAt(aiMovement.fromRow, aiMovement.fromCol) == 3)
                                    || (checkerGame.pieceAt(aiMovement.fromRow, aiMovement.fromCol) == 4)))) {
                                //reset value of jump
                                jump = false;
                                end = false;
                                // Check if the move is legal:
                                moves = checkerGame.getLegalMoves(3);
                                for (int i = 0; i < moves.length; i++) {
                                    if ((moves[i].fromCol == aiMovement.fromCol)
                                            && (moves[i].fromRow == aiMovement.fromRow)
                                            && (moves[i].toCol == aiMovement.toCol)
                                            && (moves[i].toRow == aiMovement.toRow)) {
                                        
                                        checkerGame.makeMove(aiMovement);
                                        jump = aiMovement.isJump();
                                        end = true;
                                    }
                                }

                                if(jump == false && end == true){
                                    this.turnCounter++;
                                } else if (jump){
                                    //Double Jump
                                    checkersAI.setCurrentBoard(checkerGame);
                                    doubleJumps = checkerGame.getLegalJumpsFrom(3,  aiMovement.toRow,  aiMovement.toCol);
                                    if(doubleJumps == null){
                                        this.turnCounter++;
                                    } else{
                                       aiMovement = checkersAI.makeRiskyMove();
                                        for (int i = 0; i < doubleJumps.length; i++) {
                                                checkerGame.makeMove(aiMovement);
                                        }
                                    }
                                }
                            }

                            //Repaint
                            if((origX % 2 == 1 && origY %2 == 0) || (origX % 2 == 0 && origY %2 == 1)){
                                board[origX][origY].setBackground(Color.BLACK);
                            }else{
                                board[origX][origY].setBackground(Color.RED);
                            }
                            refreshBoard();
                            this.revalidate();
                            this.repaint();
                            //Housekeeping - update variables:
                            origX = -1;
                            origY = -1;
                        }
                    }
                }
            }
        }
        //Game Mode: Connect Four
        if(mode == GameMode.CONNECTFOURMODE){
            
            if(buttonPressed == save){
                saveFile();
            }
            
            if(buttonPressed == open){
                openFile();
            }
            
            for(int i = 0; i < 7; i++){
                if(buttonPressed == boardTwo[0][i]){
                    if(connectF.move(i)){
                        if(connectF.getPlayer() == 2){
                            boardTwo[connectF.getSpot(i)][i].setIcon(boardBlack);
                        }
                        else{
                            boardTwo[connectF.getSpot(i)][i].setIcon(boardRed);
                        }
                   connectF.changePlayer();   
                        //if(connectF.move(ai.aiMove(connectF.getBoard()))){
                         //   connectF.changePlayer();
                        //}
                    }
                }
            }

            if(connectF.checkWin()){
                if(connectF.getPlayer() == 1){
                    JOptionPane.showInputDialog("Black Won");
                }
                else{
                    JOptionPane.showInputDialog("Red Won");
                }
                connectFourPanel.removeAll();
                remove(connectFourPanel);
                main.remove(connectFour);
                createConnect();
                this.revalidate();
                this.repaint();
            }
        }
    }
}