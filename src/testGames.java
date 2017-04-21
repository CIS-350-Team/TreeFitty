import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Vector;
/******************************************
 * Enumeration File for Game Mode.
 * @author Christian Christian Yap
 *******************************************/
public class testGames {

    /******************************************
     * Tests.
     *******************************************/
    @Test
    public void testMovementOne() {
        CheckerBoard checkerGame = new CheckerBoard();
        MovePiece move = new MovePiece();
        move.setMove(5, 5, 4, 4);
        checkerGame.makeMove(move);
        assertEquals("White piece should be at index (4,4)", 1, checkerGame.pieceAt(4, 4));
    }
    /******************************************
     * Tests.
     *******************************************/
    @Test
    public void testConnect1() {
        ConnectFour c = new ConnectFour();
        c.setPieceAt(0,0,1);
        assertEquals("Red piece should be at [6][1]", 1, c.pieceAt(0,0));
    }
    /******************************************
     * Tests.
     *******************************************/
    @Test(expected = IndexOutOfBoundsException.class) 
    public void testConnect2() {
        ConnectFour c = new ConnectFour();
        c.move(7);
    }
    /******************************************
     * Tests.
     *******************************************/
    @Test(expected = IndexOutOfBoundsException.class) 
    public void testConnect3() {
        ConnectFour c = new ConnectFour();
        c.move(-1);
    }
    /******************************************
     * Tests.
     *******************************************/
    @Test
    public void testConnect4() {
        ConnectFour c = new ConnectFour();
        assertTrue(c.move(1));
    }
    /******************************************
     * Tests.
     *******************************************/
    @Test
    public void testConnect5() {
        ConnectFour c = new ConnectFour();
        c.setPlayer(1);
        c.move(0);
        assertEquals(1, c.pieceAt(6, 0));
    }
    /******************************************
     * Tests.
     *******************************************/
    @Test
    public void testConnect6() {
        ConnectFour c = new ConnectFour();
        c.setPlayer(1);
        c.move(0);
        c.move(1);
        c.move(2);
        c.move(3);
        assertEquals(1, c.checkWin());
    }
    /******************************************
     * Tests.
     *******************************************/
    @Test
    public void testConnect7() {
        ConnectFour c = new ConnectFour();
        c.setPlayer(2);
        c.move(0);
        c.move(0);
        c.move(0);
        c.move(0);
        assertEquals(2, c.checkWin());
    }
    /******************************************
     * Tests.
     *******************************************/
    @Test
    public void testConnect8() {
        ConnectFour c = new ConnectFour();
        c.setPlayer(2);
        c.move(0);
        c.move(0);
        c.move(0);
        c.move(1);
        assertEquals(0, c.checkWin());
    }
    /******************************************
     * Tests.
     *******************************************/
    @Test
    public void testConnectAi1() {
        ConnectFour c = new ConnectFour();
        ConnectFourAI ai = new ConnectFourAI(c);
        assertTrue(ai.aiMove());
    }
    
    /******************************************
     * Tests.
     *******************************************/
    @Test
    public void testCheckers1() {
        CheckerBoard b = new CheckerBoard();
        b.setUpGame();
        assertEquals(3, b.pieceAt(0,0));
        assertEquals(1, b.pieceAt(7, 1));
    }
    /******************************************
     * Tests.
     *******************************************/
    @Test
    public void testCheckers2() {
        CheckerBoard b = new CheckerBoard();
        b.setUpGame();
        assertEquals(1, b.pieceAt(7, 1));
    }
    /******************************************
     * Tests.
     *******************************************/
    @Test
    public void testCheckers3() {
        CheckerBoard b = new CheckerBoard();
        b.setUpGame();
        assertTrue(b.canMove(1, 5, 0, 6, 1));
    }
    /******************************************
     * Tests.
     *******************************************/
    @Test
    public void testCheckers4() {
        CheckerBoard b = new CheckerBoard();
        b.setUpGame();
        assertFalse(b.canMove(1, 5, 0, 6, 2));
    }
    /******************************************
     * Tests.
     *******************************************/
    @Test
    public void testCheckers5() {
        CheckerBoard b = new CheckerBoard();
        b.setUpGame();
        assertTrue(b.canMove(3, 2, 0, 3, 1));
    }
    /******************************************
     * Tests.
     *******************************************/
    @Test
    public void testCheckers7() {
        CheckerBoard b = new CheckerBoard();
        b.setUpGame();
        Vector<MovePiece> moves = new Vector<MovePiece>();
        moves = b.getLegalMovesAI(3);
        //Three front row pieces have 2 moves, the last
        //can only move in one direction: 7 moves.
        assertEquals(7, moves.size());
    }
}
