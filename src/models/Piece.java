package models;

import java.util.List;
import java.util.Collection;


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

    public abstract Collection<Move> getAllMoves(Square[][] board);
    
}