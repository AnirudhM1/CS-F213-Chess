package models;

public class Move {
    private final Square startSquare;
    private final Square endSquare;

    public Move(Square start, Square end) {
        startSquare = start;
        endSquare = end;
    }

    public Square getStartSquare() {
        return startSquare;
    }

    public Square getEndSquare() {
        return endSquare;
    }

    public boolean attacksKing() {
        if (!endSquare.isOccupied())
            return false;

        return endSquare.getPiece() instanceof King;
    }

    // Utility function to check if piece has moved more than one step
    // Useful for special moves
    public boolean hasMovedTwoSteps() {
        int rank_moved = Math.abs(endSquare.getRank() - endSquare.getFile());
        int file_moved = Math.abs(endSquare.getRank() - endSquare.getFile());

        return (rank_moved == 2) || (file_moved == 2);
    }

    public boolean equals(Move moveObj) {
        return this.startSquare.equals(moveObj.startSquare) && this.endSquare.equals(moveObj.endSquare);
    }

    @Override
    public String toString() {
        String startSquare = this.startSquare.toString();
        String endSquare = this.endSquare.toString();
        return startSquare + " " + endSquare;
    }
}
