package models;

import java.util.List;

public abstract class Piece {

    private final int rank;
    private final int file;
    private final String color;

    protected Piece(int rank, int file, String color) {
        this.rank = rank;
        this.file = file;
        this.color = color;
    }

    public int getRank() {
        return this.rank;
    }

    public int getFile() {
        return this.file;
    }

    public String getColor() {
        return this.color;
    }

    public abstract String getImgUrl();

    public abstract List<Move> getAllMoves(Square[][] board);

    public static Piece createPiece(int rank, int file, String color, String piece) {
        if (piece.equalsIgnoreCase("King")) {
            Piece newpiece = new King(rank, file, color);
            return newpiece;
        }
        if (piece.equalsIgnoreCase("Knight")) {
            Piece newpiece = new Knight(rank, file, color);
            return newpiece;
        }
        if (piece.equalsIgnoreCase("Queen")) {
            Piece newpiece = new Queen(rank, file, color);
            return newpiece;
        }
        if (piece.equalsIgnoreCase("Bishop")) {
            Piece newpiece = new Bishop(rank, file, color);
            return newpiece;
        }
        if (piece.equalsIgnoreCase("Rook")) {
            Piece newpiece = new Rook(rank, file, color);
            return newpiece;
        } else {
            Piece newpiece = new Pawn(rank, file, color);
            return newpiece;
        }

    }

}
