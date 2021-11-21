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

    public boolean equals(Move moveObj) {
        return this.startSquare.equals(moveObj.startSquare) && this.endSquare.equals(moveObj.endSquare);
    }
}
