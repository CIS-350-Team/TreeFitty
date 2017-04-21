import static org.junit.Assert.*;

import org.junit.Test;

public class testGames {

    @Test
    public void testCheckersMovementOne() {
        CheckerBoard checkerGame = new CheckerBoard();
        MovePiece move = new MovePiece();
        move.setMove(5, 5, 4, 4);
        checkerGame.makeMove(move);
        assertEquals("White piece should be at index (4,4)", 1, checkerGame.pieceAt(4, 4));
    }
    
    @Test
    public void testConnect1() {
        ConnectFour c = new ConnectFour();
        c.setPieceAt(0,0,1);
        assertEquals("Red piece should be at [6][1]", 1, c.pieceAt(0,0));
    }
    
    @Test(expected = IndexOutOfBoundsException.class) 
    public void testConnect2() {
        ConnectFour c = new ConnectFour();
        c.move(7);
    }
    
    @Test(expected = IndexOutOfBoundsException.class) 
    public void testConnect3() {
        ConnectFour c = new ConnectFour();
        c.move(-1);
    }

    @Test
    public void testConnect4() {
        ConnectFour c = new ConnectFour();
        assertTrue(c.move(1));
    }
    
    @Test
    public void testConnect5() {
        ConnectFour c = new ConnectFour();
        c.setPlayer(1);
        c.move(0);
        assertEquals(1, c.pieceAt(6, 0));
    }
    
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
    
    @Test
    public void testConnectAi1() {
    	ConnectFour c = new ConnectFour();
        ConnectFourAI ai = new ConnectFourAI(c);
        assertTrue(ai.aiMove());
    }

}
