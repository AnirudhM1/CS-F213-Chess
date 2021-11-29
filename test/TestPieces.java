import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

import controllers.BoardController;
import models.Bishop;
import models.King;
import models.Knight;
import models.Pawn;
import models.Piece;
import models.Queen;
import models.Rook;
import models.Square;

public class TestPieces {

    private BoardController board;

    @Before
    public void setBoard() {
        String fen = "r2qkb1r/p1p2p1p/2n1bn2/1p2p3/3pP1p1/NPP2N2/P2PQPPP/R1B1KB1R b KQkq - 1 10";
        board = BoardController.createBoardFromFEN(fen);
    }

    @Test
    public void testKing() {
        King piece = (King) Piece.createPiece(7, 4, "BLACK", "KING");
        Square[][] board = this.board.getBoard();
        assertEquals(2, piece.getAllMoves(board).size());
    }

    @Test
    public void testQueen() {
        Queen piece = (Queen) Piece.createPiece(1, 4, "WHITE", "QUEEN");
        Square[][] board = this.board.getBoard();
        assertEquals(5, piece.getAllMoves(board).size());
    }

    @Test
    public void testRook() {
        Rook piece1 = (Rook) Piece.createPiece(7, 0, "BLACK", "ROOK");
        Square[][] board = this.board.getBoard();
        assertEquals(2, piece1.getAllMoves(board).size());
        Rook piece2 = (Rook) Piece.createPiece(3, 7, "WHITE", "ROOK");
        assertEquals(5, piece2.getAllMoves(board).size());
    }

    @Test
    public void testBishop() {
        Bishop piece = (Bishop) Piece.createPiece(5, 4, "BLACK", "BISHOP");
        Square[][] board = this.board.getBoard();
        assertEquals(6, piece.getAllMoves(board).size());
    }

    @Test
    public void testKnight() {
        Knight piece = (Knight) Piece.createPiece(2, 5, "WHITE", "KNIGHT");
        Square[][] board = this.board.getBoard();
        assertEquals(5, piece.getAllMoves(board).size());
    }

    @Test
    public void testPawn() {
        Pawn piece1 = (Pawn) Piece.createPiece(3, 3, "BLACK", "PAWN");
        Square[][] board = this.board.getBoard();
        assertEquals(2, piece1.getAllMoves(board).size());
        Pawn piece2 = (Pawn) Piece.createPiece(1, 7, "WHITE", "PAWN");
        assertEquals(2, piece2.getAllMoves(board).size());
        Pawn piece3 = (Pawn) Piece.createPiece(6, 2, "BLACK", "PAWN");
        assertEquals(0, piece3.getAllMoves(board).size());
        Pawn piece4 = (Pawn) Piece.createPiece(1, 6, "WHITE", "PAWN");
        assertEquals(1, piece4.getAllMoves(board).size());
    }

}
