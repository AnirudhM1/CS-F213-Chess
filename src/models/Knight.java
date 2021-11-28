package models;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    protected Knight(int rank, int file, String color) {
        super(rank, file, color);
    }

    public boolean Check(Square End) {
        if (End.getRank() >= 0 && End.getFile() >= 0 && End.getRank() <= 7 && End.getFile() <= 7) {
            if (End.isOccupied()) {
                if (this.getColor().equals(End.getPiece().getColor())) {
                    return false;
                } else {
                    return true;
                }

            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    public List<Move> getAllMoves(Square[][] board) {
        List<Move> legalMoves = new ArrayList<>();
        Square startSquare = Square.createSquare(super.getRank(), super.getFile(), this);
        Square endSquare = board[super.getRank()][super.getFile()];

        for (int i = -2; i <= +2; i += 4) {
            int nextRank = super.getRank() + i;
            if (nextRank <= 7 && nextRank >= 0) {
                for (int j = -1; j <= +1; j += 2) {
                    int nextFile = super.getFile() + j;
                    if (nextFile <= 7 && nextFile >= 0) {
                        endSquare = board[nextRank][nextFile];
                        if (Check(endSquare)) {
                            legalMoves.add(new Move(startSquare, endSquare));
                        }
                    }
                }
            }
        }
        for (int i = -2; i <= +2; i += 4) {
            int nextFile = super.getFile() + i;
            if (nextFile <= 7 && nextFile >= 0) {
                for (int j = -1; j <= +1; j += 2) {
                    int nextRank = super.getRank() + j;
                    if (nextRank <= 7 && nextRank >= 0) {
                        endSquare = board[nextRank][nextFile];

                        if (Check(endSquare)) {
                            legalMoves.add(new Move(startSquare, endSquare));
                        }
                    }
                }
            }
        }
        return legalMoves;
    }

    @Override
    public String toString() {
        String piece_name = "n";
        // piece_name is capital if color is white
        if (getColor().equalsIgnoreCase("WHITE"))
            piece_name = piece_name.toUpperCase();
        return piece_name;
    }

    public String getImgUrl() {
        String color = this.getColor().substring(0, 1).toUpperCase();
        return color + "N";
    }

}
