package models;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    protected King(int rank, int file, String color) {
        super(rank, file, color);
    }

    public boolean Check(Square End) {
        if (End.isOccupied()) {
            if (this.getColor().equals(End.getPiece().getColor())) {
                return false;
            } else {
                return true;
            }

        } else {
            return true;
        }
    }

    @Override
    public List<Move> getAllMoves(Square[][] board) {

        List<Move> legalMoves = new ArrayList<>();
        Square startSquare = Square.createSquare(super.getRank(), super.getFile(), this);

        if (super.getRank() + 1 >= 0 && super.getRank() + 1 <= 7 && super.getFile() >= 0 && super.getFile() <= 7) {
            Square endSquare1 = board[super.getRank() + 1][super.getFile()];
            if (Check(endSquare1)) {
                legalMoves.add(new Move(startSquare, endSquare1));
            }
        }
        if (super.getRank() - 1 >= 0 && super.getRank() - 1 <= 7 && super.getFile() >= 0 && super.getFile() <= 7) {
            Square endSquare2 = board[super.getRank() - 1][super.getFile()];
            if (Check(endSquare2)) {
                legalMoves.add(new Move(startSquare, endSquare2));
            }
        }

        if (super.getRank() >= 0 && super.getRank() <= 7 && super.getFile() + 1 >= 0 && super.getFile() + 1 <= 7) {
            Square endSquare3 = board[super.getRank()][super.getFile() + 1];
            if (Check(endSquare3)) {
                legalMoves.add(new Move(startSquare, endSquare3));

            }
            if (super.getRank() >= 0 && super.getRank() <= 7 && super.getFile() - 1 >= 0 && super.getFile() - 1 <= 7) {
                Square endSquare4 = board[super.getRank()][super.getFile() - 1];
                if (Check(endSquare4)) {
                    legalMoves.add(new Move(startSquare, endSquare4));
                }
            }

            if (super.getRank() + 1 >= 0 && super.getRank() + 1 <= 7 && super.getFile() + 1 >= 0
                    && super.getFile() + 1 <= 7) {
                Square endSquare5 = board[super.getRank() + 1][super.getFile() + 1];
                if (Check(endSquare5)) {
                    legalMoves.add(new Move(startSquare, endSquare5));
                }
            }
            if (super.getRank() - 1 >= 0 && super.getRank() - 1 <= 7 && super.getFile() - 1 >= 0
                    && super.getFile() - 1 <= 7) {
                Square endSquare6 = board[super.getRank() - 1][super.getFile() - 1];
                if (Check(endSquare6)) {
                    legalMoves.add(new Move(startSquare, endSquare6));
                }
            }

            if (super.getRank() + 1 >= 0 && super.getRank() + 1 <= 7 && super.getFile() - 1 >= 0
                    && super.getFile() - 1 <= 7) {
                Square endSquare7 = board[super.getRank() + 1][super.getFile() - 1];
                if (Check(endSquare7)) {
                    legalMoves.add(new Move(startSquare, endSquare7));
                }
            }
            if (super.getRank() - 1 >= 0 && super.getRank() - 1 <= 7 && super.getFile() + 1 >= 0
                    && super.getFile() + 1 <= 7) {
                Square endSquare8 = board[super.getRank() - 1][super.getFile() + 1];
                if (Check(endSquare8)) {
                    legalMoves.add(new Move(startSquare, endSquare8));
                }

            }
        }

        return legalMoves;

    }

    @Override
    public String getImgUrl() {
        String color = this.getColor().substring(0, 1).toUpperCase();
        return color + "K";
    }

}
