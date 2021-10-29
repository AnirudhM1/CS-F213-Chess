package models;

public abstract class Square {

    private final int rank;
    private final int file;

    Square(int rank, int file) {
        this.rank = rank;
        this.file = file;
    }

    public abstract boolean isOccupied(); 

    public abstract Piece getPiece();

    public static final class EmptySquare extends Square {

        EmptySquare(int rank, int file) {
            super(rank, file);
        }

        @Override
        public Piece getPiece() {
            return null;
        }

        @Override
        public boolean isOccupied() {
            return false;
        }
    }

    public static final class OccupiedSquare extends Square {

        Piece piece;

        OccupiedSquare(int rank, int file) {
            super(rank, file);
        }

        @Override
        public Piece getPiece() {
            return this.piece;
        }

        @Override
        public boolean isOccupied() {
            return true;
        }
    }
}