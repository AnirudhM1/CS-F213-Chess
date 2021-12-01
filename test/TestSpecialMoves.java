import org.junit.Test;

import controllers.BoardController;
import models.Move;
import models.Piece;
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

    @Test
    public void testCastling() {
        String fen = "r1bqk1nr/pppp1ppp/2n5/2b1p3/2B1P3/5N2/PPPP1PPP/RNBQK2R w KQkq - 4 4";
        BoardController board = BoardController.createBoardFromFEN(fen);
        assertEquals(33, board.getCurrentLegalMoves().size());

        fen = "rnb1k1nr/ppp1qppp/3p4/2b1p3/2B1PP2/5N2/PPPP2PP/RNBQK2R w KQkq - 2 5";
        board = BoardController.createBoardFromFEN(fen);
        assertEquals("Castling is not allowed when endSquare under attack", 34, board.getCurrentLegalMoves().size());

        fen = "rn1qkbnr/ppp1pppp/8/1b1p4/4P3/5N2/PPPP1PPP/RNBQK2R w KQkq - 0 4";
        board = BoardController.createBoardFromFEN(fen);
        assertEquals("Castling is not allowed when path is under attack", 24, board.getCurrentLegalMoves().size());

        fen = "r1bqk1nr/pppp1ppp/2n5/4p3/1bB1P3/3P1N2/PPP2PPP/RNBQK2R w KQkq - 1 5";
        board = BoardController.createBoardFromFEN(fen);
        assertEquals("Castling is not allowed when king is in check", 8, board.getCurrentLegalMoves().size());

        // Check for piece placement after castling
        fen = "r1bqk2r/pppp1ppp/2n2n2/2b1p3/2B1P3/5N2/PPPP1PPP/RNBQ1RK1 w kq - 6 5";
        board = BoardController.createBoardFromFEN(fen);
        Square startSquare = board.getBoard()[1][3];
        Square endSquare = board.getBoard()[2][3];
        Move move = new Move(startSquare, endSquare);
        board = board.executeMove(move);
        startSquare = board.getBoard()[7][4];
        endSquare = board.getBoard()[7][6];
        move = new Move(startSquare, endSquare);
        board = board.executeMove(move);
        Piece king = board.getBoard()[7][6].getPiece();
        Piece rook = board.getBoard()[7][5].getPiece();
        assertEquals("k", king.toString());
        assertEquals("r", rook.toString());
    }
}
