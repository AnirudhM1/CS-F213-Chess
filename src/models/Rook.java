package models;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    protected Rook(int rank, int file, String color) {
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
        while (checkNextMove == true) {
            if (endSquare.getRank() + 1 >= 0 && endSquare.getRank() + 1 <= 7 &&
                    endSquare.getFile() >= 0 && endSquare.getFile() <= 7) {

                endSquare = board[endSquare.getRank() + 1][endSquare.getFile()];
            }

            if (Check(endSquare)) {
                legalMoves.add(new Move(startSquare, endSquare));
                if (endSquare.isOccupied()) {
                    checkNextMove = false;
                }

            } else {
                checkNextMove = false;
            }
        }

        endSquare = board[super.getRank()][super.getFile()];
        checkNextMove = true;
        while (checkNextMove == true) {
            if (endSquare.getRank() - 1 >= 0 && endSquare.getRank() - 1 <= 7 &&
                    endSquare.getFile() >= 0 && endSquare.getFile() <= 7) {

                endSquare = board[endSquare.getRank() - 1][endSquare.getFile()];
            }
            if (Check(endSquare)) {
                legalMoves.add(new Move(startSquare, endSquare));
                if (endSquare.isOccupied()) {
                    checkNextMove = false;
                }
            } else {
                checkNextMove = false;
            }
        }

        endSquare = board[super.getRank()][super.getFile()];
        checkNextMove = true;
        while (checkNextMove == true) {
            if (endSquare.getRank() >= 0 && endSquare.getRank() <= 7 &&
                    endSquare.getFile() + 1 >= 0 && endSquare.getFile() + 1 <= 7) {

                endSquare = board[super.getRank()][super.getFile() + 1];
            }

            if (Check(endSquare)) {
                legalMoves.add(new Move(startSquare, endSquare));
                if (endSquare.isOccupied()) {
                    checkNextMove = false;
                }

            } else {
                checkNextMove = false;
            }
        }

        endSquare = board[super.getRank()][super.getFile()];
        checkNextMove = true;
        while (checkNextMove == true) {
            if (endSquare.getRank() >= 0 && endSquare.getRank() <= 7 &&
                    endSquare.getFile() - 1 >= 0 && endSquare.getFile() - 1 <= 7) {

                endSquare = board[super.getRank()][super.getFile() - 1];
            }

            if (Check(endSquare)) {
                legalMoves.add(new Move(startSquare, endSquare));
                if (endSquare.isOccupied()) {
                    checkNextMove = false;
                }

            } else {
                checkNextMove = false;
            }
        }

        return legalMoves;

    }

    @Override
    public String getImgUrl() {
        String color = this.getColor().substring(0, 1).toUpperCase();
        return color + "R";
    }

}