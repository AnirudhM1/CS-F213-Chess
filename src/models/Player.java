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

    // Fields to indicate castling
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
        addEnPassantMove();
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

    // -
    private King findKing() {
        for (Piece piece : allPieces) {
            if (((piece.getFile() == 4 && piece.getRank() == 0) || (piece.getFile() == 4 && piece.getRank() == 7))
                    && (piece.toString().equalsIgnoreCase("K"))) {
                return (King) piece;
            }
        }
        return null;

    }

    // Rook1 is for long castle('a' rook)
    private Rook findRook1() {
        for (Piece piece : allPieces) {
            if (((piece.getFile() == 0 && piece.getRank() == 0) || (piece.getFile() == 0 && piece.getRank() == 7))
                    && (piece.toString().equalsIgnoreCase("R"))) {
                return (Rook) piece;
            }
        }
        return null;

    }

    private Rook findRook2() {
        for (Piece piece : allPieces) {
            if (((piece.getFile() == 7 && piece.getRank() == 0) || (piece.getFile() == 7 && piece.getRank() == 7))
                    && (piece.toString().equalsIgnoreCase("R"))) {
                return (Rook) piece;
            }
        }
        return null;
    }

    public boolean underAttack(Square square) {
        for (Move move : allOpponentMoves) {
            if (move.getEndSquare() == square) {
                return true;
            }
        }
        return false;
    }

    private void addCastleMove() {
        King king = findKing();
        Rook rook1 = findRook1();
        Rook rook2 = findRook2();
        // rook1 is for long castle('a'rook) and rook2 ('h' rook) is for short castle

        // short castle for white king
        if (whiteKingSideCastleAllowed) {
            Square startSquareK = Square.createSquare(0, 4, king);
            if (underAttack(board[4][0]) == false && underAttack(board[0][5]) == false
                    && (board[0][5]).isOccupied() == false && underAttack(board[0][6]) == false
                    && (board[0][6]).isOccupied() == false) {
                Square endSquareK = board[0][6];
                allMoves.add(new Move(startSquareK, endSquareK));// add king move
            }
        }

        // short castle for Black king
        if (blackKingSideCastleAllowed) {
            Square startSquareK = Square.createSquare(7, 4, king);
            if (underAttack(board[4][7]) == false && underAttack(board[7][5]) == false
                    && (board[7][5]).isOccupied() == false && underAttack(board[7][6]) == false
                    && (board[7][6]).isOccupied() == false) {
                Square endSquareK = board[7][6];
                allMoves.add(new Move(startSquareK, endSquareK));// add king move
            }
        }

        // long castle for White king
        if (whiteQueenSideCastleAllowed) {
            Square startSquareK = Square.createSquare(0, 4, king);
            if (underAttack(board[4][0]) == false && underAttack(board[0][3]) == false
                    && (board[0][3]).isOccupied() == false && underAttack(board[0][2]) == false
                    && (board[0][2]).isOccupied() == false) {
                Square endSquareK = board[0][2];
                allMoves.add(new Move(startSquareK, endSquareK));// add king move
            }
        }

        // long castle for Black king
        if (blackQueenSideCastleAllowed) {
            Square startSquareK = Square.createSquare(7, 4, king);
            if (underAttack(board[7][4]) == false && underAttack(board[7][3]) == false
                    && (board[7][3]).isOccupied() == false && underAttack(board[7][2]) == false
                    && (board[7][2]).isOccupied() == false) {
                Square endSquareK = board[7][2];
                allMoves.add(new Move(startSquareK, endSquareK));// add king move
            }
        }

    }

    // -
    private void addEnPassantMove() {
        if (enPassantSquare == null) {
            return;
        }

        if (this.color.equalsIgnoreCase("White") && enPassantSquare.getRank() == 5) {

            int pawnRank = 4;
            int pawnFile1 = enPassantSquare.getFile() - 1;
            int pawnFile2 = enPassantSquare.getFile() + 1;

            if (pawnFile1 >= 0 && pawnFile1 < 8) {
                Square square = board[pawnRank][pawnFile1];
                if (square.isOccupied()) {
                    Piece piece = square.getPiece();
                    if (piece.getColor().equalsIgnoreCase("WHITE") && piece.toString().equalsIgnoreCase("P")) {
                        Move move = new Move(square, enPassantSquare);
                        allMoves.add(move);
                    }
                }
            }

            if (pawnFile2 >= 0 && pawnFile2 < 8) {
                Square square = board[pawnRank][pawnFile2];
                if (square.isOccupied()) {
                    Piece piece = square.getPiece();
                    if (piece.getColor().equalsIgnoreCase("WHITE") && piece.toString().equalsIgnoreCase("P")) {
                        Move move = new Move(square, enPassantSquare);
                        allMoves.add(move);
                    }
                }
            }

        }

        if (this.color.equalsIgnoreCase("black") && enPassantSquare.getRank() == 5) {

            int pawnRank = 4;
            int pawnFile1 = enPassantSquare.getFile() - 1;
            int pawnFile2 = enPassantSquare.getFile() + 1;

            if (pawnFile1 >= 0 && pawnFile1 < 8) {
                Square square = board[pawnRank][pawnFile1];
                if (square.isOccupied()) {
                    Piece piece = square.getPiece();
                    if (piece.getColor().equalsIgnoreCase("WHITE") && piece.toString().equalsIgnoreCase("P")) {
                        Move move = new Move(square, enPassantSquare);
                        allOpponentMoves.add(move);
                    }
                }
            }

            if (pawnFile2 >= 0 && pawnFile2 < 8) {
                Square square = board[pawnRank][pawnFile2];
                if (square.isOccupied()) {
                    Piece piece = square.getPiece();
                    if (piece.getColor().equalsIgnoreCase("WHITE") && piece.toString().equalsIgnoreCase("P")) {
                        Move move = new Move(square, enPassantSquare);
                        allOpponentMoves.add(move);
                    }
                }
            }

        }

        if (this.color.equalsIgnoreCase("white") && enPassantSquare.getRank() == 2) {

            int pawnRank = 3;
            int pawnFile1 = enPassantSquare.getFile() - 1;
            int pawnFile2 = enPassantSquare.getFile() + 1;

            if (pawnFile1 >= 0 && pawnFile1 < 8) {
                Square square = board[pawnRank][pawnFile1];
                if (square.isOccupied()) {
                    Piece piece = square.getPiece();
                    if (piece.getColor().equalsIgnoreCase("WHITE") && piece.toString().equalsIgnoreCase("P")) {
                        Move move = new Move(square, enPassantSquare);
                        allOpponentMoves.add(move);
                    }
                }
            }

            if (pawnFile2 >= 0 && pawnFile2 < 8) {
                Square square = board[pawnRank][pawnFile2];
                if (square.isOccupied()) {
                    Piece piece = square.getPiece();
                    if (piece.getColor().equalsIgnoreCase("WHITE") && piece.toString().equalsIgnoreCase("P")) {
                        Move move = new Move(square, enPassantSquare);
                        allOpponentMoves.add(move);
                    }
                }
            }

        }

        if (this.color.equalsIgnoreCase("black") && enPassantSquare.getRank() == 2) {

            int pawnRank = 3;
            int pawnFile1 = enPassantSquare.getFile() - 1;
            int pawnFile2 = enPassantSquare.getFile() + 1;

            if (pawnFile1 >= 0 && pawnFile1 < 8) {
                Square square = board[pawnRank][pawnFile1];
                if (square.isOccupied()) {
                    Piece piece = square.getPiece();
                    if (piece.getColor().equalsIgnoreCase("WHITE") && piece.toString().equalsIgnoreCase("P")) {
                        Move move = new Move(square, enPassantSquare);
                        allMoves.add(move);
                    }
                }
            }

            if (pawnFile2 >= 0 && pawnFile2 < 8) {
                Square square = board[pawnRank][pawnFile2];
                if (square.isOccupied()) {
                    Piece piece = square.getPiece();
                    if (piece.getColor().equalsIgnoreCase("WHITE") && piece.toString().equalsIgnoreCase("P")) {
                        Move move = new Move(square, enPassantSquare);
                        allMoves.add(move);
                    }
                }
            }

        }
    }

}
