import static org.junit.Assert.*;

import org.junit.Test;

public class testGames {

    @Test
    public void testMovementOne() {
        CheckerBoard checkerGame = new CheckerBoard();
        MovePiece move = new MovePiece();
        move.setMove(5, 5, 4, 4);
        checkerGame.makeMove(move);
        assertEquals("White piece should be at index (4,4)", 1, checkerGame.pieceAt(4, 4));
    }

}
