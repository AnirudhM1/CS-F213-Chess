package models;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    private boolean wasMoved;

    protected Pawn(int rank, int file, String color) {
        super(rank, file, color);

        if ((rank == 2 && color.equalsIgnoreCase("White")) || (rank == 7 && color.equalsIgnoreCase("Black"))) {
            wasMoved = false;
        }

        else {
            wasMoved = true;
        }

    }

    public boolean Check(Square End) {

        if (End.getRank() >= 0 && End.getFile() >= 0 && End.getRank() <= 7 && End.getFile() <= 7) {
            return !End.isOccupied();
        }

        return End.isOccupied();
    }

    public boolean checkCapture(Square End) {

        if (End.getRank() >= 0 && End.getFile() >= 0 && End.getRank() <= 7 && End.getFile() <= 7) {

            if (End.isOccupied()) {

                if (this.getColor().equals(End.getPiece().getColor())) {
                    return false;
                }

                else {
                    return true;
                }

            }

            return false;
        }

        else {
            return false;
        }

    }

    @Override
    public List<Move> getAllMoves(Square[][] board) {

        List<Move> legalMoves = new ArrayList<>();
        Square startSquare = Square.createSquare(super.getRank(), super.getFile(), this);

        if (super.getColor().equalsIgnoreCase("White")) {

            if (!wasMoved) {

                if (!board[super.getRank() + 2][super.getFile()].isOccupied()) {
                    Square endSquare1 = board[super.getRank() + 2][super.getFile()];

                    if (Check(endSquare1)) {
                        legalMoves.add(new Move(startSquare, endSquare1));
                    }

                }

            }

            if (!board[super.getRank() + 1][super.getFile()].isOccupied()) {
                Square endSquare2 = board[super.getRank() + 1][super.getFile()];

                if (Check(endSquare2)) {
                    legalMoves.add(new Move(startSquare, endSquare2));
                }

            }

            if ((super.getFile() + 1 < 8) && (super.getRank() + 1 < 8)) {

                if (board[super.getRank() + 1][super.getFile() + 1].isOccupied()) {
                    Square endSquare3 = board[super.getRank() + 1][super.getFile() + 1];

                    if (checkCapture(endSquare3)) {
                        legalMoves.add(new Move(startSquare, endSquare3));
                    }

                }

            }

            if ((super.getFile() - 1 >= 0) && (super.getRank() + 1 < 8)) {

                if (board[super.getRank() + 1][super.getFile() - 1].isOccupied()) {
                    Square endSquare4 = board[super.getRank() + 1][super.getFile() - 1];

                    if (checkCapture(endSquare4)) {
                        legalMoves.add(new Move(startSquare, endSquare4));
                    }

                }

            }

        }

        if (super.getColor().equalsIgnoreCase("Black")) {

            if (!wasMoved) {

                if (!board[super.getRank() - 2][super.getFile()].isOccupied()) {
                    Square endSquare5 = board[super.getRank() - 2][super.getFile()];

                    if (Check(endSquare5)) {
                        legalMoves.add(new Move(startSquare, endSquare5));
                    }

                }

            }

            if (!board[super.getRank() - 1][super.getFile()].isOccupied()) {
                Square endSquare6 = board[super.getRank() - 1][super.getFile()];

                if (Check(endSquare6)) {
                    legalMoves.add(new Move(startSquare, endSquare6));
                }

            }

            if ((super.getFile() + 1 < 8) && (super.getRank() - 1 >= 0)) {

                if (board[super.getRank() - 1][super.getFile() + 1].isOccupied()) {
                    Square endSquare7 = board[super.getRank() - 1][super.getFile() + 1];

                    if (checkCapture(endSquare7)) {
                        legalMoves.add(new Move(startSquare, endSquare7));
                    }

                }

            }

            if ((super.getFile() - 1 >= 0) && (super.getRank() - 1 >= 0)) {

                if (board[super.getRank() - 1][super.getFile() - 1].isOccupied()) {
                    Square endSquare8 = board[super.getRank() - 1][super.getFile() - 1];

                    if (checkCapture(endSquare8)) {
                        legalMoves.add(new Move(startSquare, endSquare8));
                    }

                }

            }

        }

        return legalMoves;
    }

    @Override
    public String toString() {
        String piece_name = "p";
        // piece_name is capital if color is white
        if (getColor().equalsIgnoreCase("WHITE"))
            piece_name = piece_name.toUpperCase();
        return piece_name;
    }
}
