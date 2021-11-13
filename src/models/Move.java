package models;

public class Move {
    private final Square startSquare;
    private final Square endSquare;

    public Move(Square start, Square end) {
        startSquare = start;
        endSquare = end;
    }
    public Move(int i, int j, King king) {
		this.startSquare = null;
		this.endSquare = null;
	}

    public Square getStartSquare() {
        return startSquare;
    }

    public Square getEndSquare() {
        return endSquare;
    }
}