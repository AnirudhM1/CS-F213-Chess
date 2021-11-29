package models;

import controllers.BoardController;

import java.util.*;

public class Player {

    private final Square[][] board;

    private final boolean inCheck = false;

    private final String color;
    private final ArrayList<Piece> allPieces;
    private final ArrayList<Move> allMoves;
    private final ArrayList<Move> allOpponentMoves;

    // Fields to indicicate castling
    // These fields only indicate whether the respective pieces have moved or not.
    // They don't check for other conditions required.
    private final boolean whiteKingSideCastleAllowed;
    private final boolean whiteQueenSideCastleAllowed;
    private final boolean blackKingSideCastleAllowed;
    private final boolean blackQueenSideCastleAllowed;

    // Field to set enPassantSquare
    private final Square enPassantSquare;

    public Player(BoardController board, String color) {

        this.board = board.getBoard();
        this.color = color;
        this.allPieces = calculateAllPieces(color);
        this.allMoves = calculateAllMoves();
        this.allOpponentMoves = calculateAllOpponentMoves();
        this.whiteKingSideCastleAllowed = board.isWhiteKingCastleAllowed();
        this.whiteQueenSideCastleAllowed = board.isWhiteQueenCastleAllowed();
        this.blackKingSideCastleAllowed = board.isBlackKingCastleAllowed();
        this.blackQueenSideCastleAllowed = board.isBlackQueenCastleAllowed();
        this.enPassantSquare = board.getEnPassentSquare();
    }

    private static String opponent(String color) {
        if (color.equalsIgnoreCase("WHITE"))
            return "BLACK";
        else if (color.equalsIgnoreCase("BLACK"))
            return "WHITE";
        else
            throw new Error("Invalid color: " + color);
    }

    private ArrayList<Move> calculateAllMoves() {

        ArrayList<Move> allMoves = new ArrayList<>();

        for (Piece piece : allPieces) {

            allMoves.addAll(piece.getAllMoves(board));

        }

        return allMoves;

    }

    private ArrayList<Move> calculateAllOpponentMoves() {
        String color = opponent(this.color);
        ArrayList<Piece> opponentPieces = calculateAllPieces(color);

        ArrayList<Move> opponentMoves = new ArrayList<>();
        for (Piece piece : opponentPieces)
            opponentMoves.addAll(piece.getAllMoves(board));

        return opponentMoves;

    }

    private ArrayList<Piece> calculateAllPieces(String color) {
        ArrayList<Piece> allPieces = new ArrayList<>();

        for (int rank = 0; rank < 8; rank++) {
            for (int file = 0; file < 8; file++) {
                Piece piece = board[rank][file].getPiece();

                if (piece != null && piece.getColor().equals(color))
                    allPieces.add(piece);
            }
        }

        return allPieces;
    }

    public boolean isInCheck() {

        return this.inCheck;

    }

    public ArrayList<Move> getAllMoves() {

        return this.allMoves;

    }

    public ArrayList<Piece> getAllPiecesArrayList() {

        return this.allPieces;

    }

    public String getColor() {

        return this.color;

    }

    private void addCastleMove() {

    }

    private void addEnPassentMove() {

    }

}
