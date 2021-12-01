import org.junit.Test;

import controllers.BoardController;
import models.Move;
import models.Square;

import static org.junit.Assert.assertEquals;

public class TestSpecialMoves {
    @Test
    public void testEnPassant() {
        String fen = "rnbqkb1r/pppppppp/7n/4P3/8/8/PPPP1PPP/RNBQKBNR b KQkq - 0 2";
        BoardController board = BoardController.createBoardFromFEN(fen);

        Square startSquare = board.getBoard()[6][5];
        Square endSquare = board.getBoard()[4][5];
        Move move = new Move(startSquare, endSquare);
        board = board.executeMove(move);

        // Extra move due to en-passant
        assertEquals(31, board.getCurrentLegalMoves().size());
    }
}
