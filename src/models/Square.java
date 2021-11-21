package models;

public abstract class Square {

    private final int rank;
    private final int file;

    protected Square(int rank, int file) {
        this.rank = rank;
        this.file = file;
    }

    public int getRank() {
        return this.rank;
    }

    public int getFile() {
        return this.file;
    }

    public abstract boolean isOccupied();

    public abstract Piece getPiece();

    public static final class EmptySquare extends Square {

        public EmptySquare(int rank, int file) {
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

        private final Piece piece;

        public OccupiedSquare(int rank, int file, Piece piece) {
            super(rank, file);
            this.piece = piece;
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

    public static Square createSquare(int rank, int file, Piece piece) {
        return (piece == null) ? new EmptySquare(rank, file) : new OccupiedSquare(rank, file, piece);
    }

    public boolean equals(Square square) {
        return this.rank == square.rank && this.file == square.file;
    }
}
