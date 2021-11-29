package models;

import java.util.List;
import java.util.ArrayList;

public class Queen extends Piece {
    protected Queen(int rank, int file, String color) {
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

    public List<Move> getAllMoves(Square[][] board) {
        List<Move> legalMoves = new ArrayList<>();
        Square startSquare = board[super.getRank()][super.getFile()];
        Square endSquare = board[super.getRank()][super.getFile()];

        for (int i = 1; i < 7; i++) {
            int x = super.getRank() + i;
            int y = super.getFile();
            if (x >= 0 && x <= 7 && y >= 0 && y <= 7) {

                endSquare = board[x][y];
                if (Check(endSquare)) {
                    legalMoves.add(new Move(startSquare, endSquare));

                    // Check() guarantees that the occupying peice is an enemy peice
                    if (endSquare.isOccupied())
                        break;
                } else
                    break;
            }
        }
        for (int i = 1; i < 7; i++) {
            int x = super.getRank() - i;
            int y = super.getFile();
            if (x >= 0 && x <= 7 && y >= 0 && y <= 7) {

                endSquare = board[x][y];
                if (Check(endSquare)) {
                    legalMoves.add(new Move(startSquare, endSquare));

                    // Check() guarantees that the occupying peice is an enemy peice
                    if (endSquare.isOccupied())
                        break;
                } else
                    break;
            }
        }
        for (int i = 1; i < 7; i++) {
            int x = super.getRank();
            int y = super.getFile() - i;
            if (x >= 0 && x <= 7 && y >= 0 && y <= 7) {

                endSquare = board[x][y];
                if (Check(endSquare)) {
                    legalMoves.add(new Move(startSquare, endSquare));

                    // Check() guarantees that the occupying peice is an enemy peice
                    if (endSquare.isOccupied())
                        break;
                } else
                    break;
            }
        }

        for (int i = 1; i < 7; i++) {
            int x = super.getRank();
            int y = super.getFile() + i;
            if (x >= 0 && x <= 7 && y >= 0 && y <= 7) {

                endSquare = board[x][y];
                if (Check(endSquare)) {
                    legalMoves.add(new Move(startSquare, endSquare));

                    // Check() guarantees that the occupying peice is an enemy peice
                    if (endSquare.isOccupied())
                        break;
                } else
                    break;
            }
        }
        for (int i = 1; i < 7; i++) {
            int x = super.getRank() + i;
            int y = super.getFile() + i;
            if (x >= 0 && x <= 7 && y >= 0 && y <= 7) {

                endSquare = board[x][y];
                if (Check(endSquare)) {
                    legalMoves.add(new Move(startSquare, endSquare));

                    // Check() guarantees that the occupying peice is an enemy peice
                    if (endSquare.isOccupied())
                        break;
                } else
                    break;
            }
        }
        for (int i = 1; i < 7; i++) {
            int x = super.getRank() + i;
            int y = super.getFile() - i;
            if (x >= 0 && x <= 7 && y >= 0 && y <= 7) {

                endSquare = board[x][y];
                if (Check(endSquare)) {
                    legalMoves.add(new Move(startSquare, endSquare));

                    // Check() guarantees that the occupying peice is an enemy peice
                    if (endSquare.isOccupied())
                        break;
                } else
                    break;
            }
        }
        for (int i = 1; i < 7; i++) {
            int x = super.getRank() - i;
            int y = super.getFile() + i;
            if (x >= 0 && x <= 7 && y >= 0 && y <= 7) {

                endSquare = board[x][y];
                if (Check(endSquare)) {
                    legalMoves.add(new Move(startSquare, endSquare));

                    // Check() guarantees that the occupying peice is an enemy peice
                    if (endSquare.isOccupied())
                        break;
                } else
                    break;
            }
        }
        for (int i = 1; i < 7; i++) {
            int x = super.getRank() - i;
            int y = super.getFile() - i;
            if (x >= 0 && x <= 7 && y >= 0 && y <= 7) {

                endSquare = board[x][y];
                if (Check(endSquare)) {
                    legalMoves.add(new Move(startSquare, endSquare));

                    // Check() guarantees that the occupying peice is an enemy peice
                    if (endSquare.isOccupied())
                        break;
                } else
                    break;
            }
        }

        return legalMoves;
    }

    @Override
    public String toString() {
        String piece_name = "q";
        // piece_name is capital if color is white
        if (getColor().equalsIgnoreCase("WHITE"))
            piece_name = piece_name.toUpperCase();
        return piece_name;
    }

    public String getImgUrl() {
        String color = this.getColor().substring(0, 1).toUpperCase();
        return color + "Q";
    }

}
