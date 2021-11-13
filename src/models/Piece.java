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

    public abstract List<Move> getAllMoves(Square[][] board);

    public static Piece createPiece(int rank, int file, String color, String piece) {
        return null;
    }

}