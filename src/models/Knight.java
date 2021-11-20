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
        boolean checkNextMove = true;
        for (int i = -2; i <= +2; i += 4) {
            if (super.getRank() + i <= 7) {
                endSquare = board[super.getRank() + i][super.getFile()];
            }
            for (int j = -1; j <= +1; j += 2) {
                if (super.getFile() + j <= 7) {
                    endSquare = board[super.getRank()][super.getFile() + j];
                }
                if (Check(endSquare)) {
                    legalMoves.add(new Move(startSquare, endSquare));
                }
            }
        }
        for (int i = -2; i <= +2; i += 4) {
            if (super.getFile() + i <= 7) {
                endSquare = board[super.getRank()][super.getFile() + i];
            }

            for (int j = -1; j <= +1; j += 2) {
                if (super.getRank() + j <= 7) {
                    endSquare = board[super.getRank() + j][super.getFile()];

                }
                if (Check(endSquare)) {
                    legalMoves.add(new Move(startSquare, endSquare));
                }
            }
        }
        return legalMoves;
    }

}
