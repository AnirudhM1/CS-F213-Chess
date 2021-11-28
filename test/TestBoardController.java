import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import controllers.BoardController;
import models.Move;
import models.Square;

public class TestBoardController {

    @Test
    public void testStartingMoveLength() {
        BoardController board = BoardController.initialize();
        assertEquals("20 moves are possible", 20, board.getCurrentLegalMoves().size());
    }

    @Test
    public void testCurrentPlayer() {
        BoardController board = BoardController.initialize();
        assertEquals("White starts the game", "WHITE", board.getCurrentPlayer().toUpperCase());
    }

    @Test
    public void testFenToBoard() {
        String fen = "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b";
        BoardController board = BoardController.createBoardFromFEN(fen);

        // Check Piece placement
        // Check if e2 square is empty
        assertFalse("e2 is empty", board.getBoard()[1][4].isOccupied());

        // Check if e4 square is occupied
        assertTrue("e4 is occupied", board.getBoard()[3][4].isOccupied());

        // Check the total number of moves possible
        assertEquals("20 moves are possible", 20,
                board.getCurrentLegalMoves().size());

        // Check current player
        assertEquals("Black's turn", "BLACK", board.getCurrentPlayer().toUpperCase());
    }

    @Test
    public void testExecuteBoard() {
        String fen = "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b";
        BoardController initialBoard = BoardController.createBoardFromFEN(fen);
        Square[][] board = initialBoard.getBoard();

        Square startSquare = board[6][4];
        Square endSquare = board[4][4];

        Move move = new Move(startSquare, endSquare);
        BoardController executedBoard = initialBoard.executeMove(move);

        // Test to check if executedBoard is working or not

        // Check current Player
        assertEquals("Current player is white", "WHITE", executedBoard.getCurrentPlayer().toUpperCase());

        // Check number of moves
        assertEquals(25, executedBoard.getCurrentLegalMoves().size());

        // Check Pieces
        assertFalse("e7 is empty", executedBoard.getBoard()[6][4].isOccupied());
        assertTrue("e5 is occupied", executedBoard.getBoard()[4][4].isOccupied());
        assertEquals("Black piece on e5", "BLACK", executedBoard.getBoard()[4][4].getPiece().getColor().toUpperCase());
        assertEquals("Pawn on e5", "PAWN",
                executedBoard.getBoard()[4][4].getPiece().getClass().getSimpleName().toUpperCase());
    }
}
