package models;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    protected Bishop(int rank, int file, String color) {
        super(rank, file, color);

    }

    // checking if its a legal bishop move
    public boolean isValid(Square[][] board, Square startSquare, Square endSquare) {

        int fromX = startSquare.getRank();
        int toX = endSquare.getRank();
        int fromY = startSquare.getFile();
        int toY = endSquare.getFile();

        if (toX == fromX && toY == fromY)
            return false; // cannot move anything
        if (toX < 0 || toX > 7 || fromX < 0 || fromX > 7 || toY < 0 || toY > 7 || fromY < 0 || fromY > 7)
            return false;

        if (toX - fromX == toY - fromY)
            return true;

        return false;
    }

    // below function is same as above but with int arguments
    public boolean isValidsq(int fromX, int fromY, int toX, int toY) {

        if (toX == fromX && toY == fromY)
            return false; // cannot move to same square
        if (toX < 0 || toX > 7 || fromX < 0 || fromX > 7 || toY < 0 || toY > 7 || fromY < 0 || fromY > 7)
            return false;

        if (toX - fromX == toY - fromY || toX - fromX == fromY - toY)
            return true;

        return false;
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

        // for cases when bishop is not at any of the 4 edges of the board
        int x = startSquare.getRank();
        int y = startSquare.getFile();

        int toX = x - 1;
        int toY = y - 1;
        while (isValidsq(x, y, toX, toY) == true) {
            Square endSquare = board[toX][toY];

            if (Check(endSquare) == true) {
                legalMoves.add(new Move(startSquare, endSquare));
                if (endSquare.isOccupied())
                    break;
            } else
                break;
            toX--;
            toY--;
        }
        int toX1 = x + 1;
        int toY1 = y + 1;
        while (isValidsq(x, y, toX1, toY1) == true) {
            Square endSquare = board[toX1][toY1];
            if (Check(endSquare) == true) {
                legalMoves.add(new Move(startSquare, endSquare));
                if (endSquare.isOccupied())
                    break;
            } else
                break;
            toX1++;
            toY1++;
        }

        int toX2 = x - 1;
        int toY2 = y + 1;
        while (isValidsq(x, y, toX2, toY2) == true) {
            Square endSquare = board[toX2][toY2];
            if (Check(endSquare) == true) {
                legalMoves.add(new Move(startSquare, endSquare));
                if (endSquare.isOccupied())
                    break;
            } else
                break;
            toX2--;
            toY2++;
        }

        int toX3 = x + 1;
        int toY3 = y - 1;
        while (isValidsq(x, y, toX3, toY3) == true) {
            Square endSquare = board[toX3][toY3];
            if (Check(endSquare) == true) {
                legalMoves.add(new Move(startSquare, endSquare));
                if (endSquare.isOccupied())
                    break;
            } else
                break;
            toX3++;
            toY3--;
        }

        return legalMoves;

    }

    @Override
    public String getImgUrl() {
        String color = this.getColor().substring(0, 1).toUpperCase();
        return color + "B";
    }

    @Override
    public String toString() {
        String piece_name = "b";
        // piece_name is capital if color is white
        if (getColor().equalsIgnoreCase("WHITE"))
            piece_name = piece_name.toUpperCase();
        return piece_name;
    }
}