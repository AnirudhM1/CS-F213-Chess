package models;

import java.util.ArrayList;
import java.util.List;

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
        int rank_moved = Math.abs(endSquare.getRank() - startSquare.getRank());
        int file_moved = Math.abs(endSquare.getFile() - startSquare.getFile());

        return (rank_moved == 2) || (file_moved == 2);
    }

    // Function to return the name of the move in algebraic notation
    public String getName(List<Move> allMoves) {
        Piece piece = startSquare.getPiece();
        List<Piece> sameTypeOfPieces = new ArrayList<>();
        for (Move move : allMoves) {
            // Skip move if it is the same as the current instance
            if (move.equals(this))
                continue;
            // Same destination
            if (move.endSquare.equals(this.endSquare)) {
                Piece movingPiece = move.startSquare.getPiece();
                if (movingPiece.toString().equals(this.toString())) {
                    sameTypeOfPieces.add(move.startSquare.getPiece());
                }
            }
        }

        if (sameTypeOfPieces.size() == 0) {
            String pieceName = piece.toString().toUpperCase();
            if (pieceName.equals("P"))
                return addPostFix("");

            return addPostFix(pieceName);
        }

        int rank = startSquare.getRank();
        int file = startSquare.getFile();

        // These fields check if rank/file is unique amongst the pieces
        boolean rankUnique = true;
        boolean fileUnique = true;

        for (Piece similarPiece : sameTypeOfPieces) {
            if (similarPiece.getRank() == this.startSquare.getRank())
                rankUnique = false;
            if (similarPiece.getFile() == this.startSquare.getFile())
                fileUnique = false;
        }

        if (fileUnique) {
            String piece_file = String.valueOf((char) ('a' + file));
            return addPostFix(piece.toString().toUpperCase() + piece_file);
        }

        if (rankUnique) {
            String piece_rank = String.valueOf(rank + 1);
            return addPostFix(piece.toString().toUpperCase() + piece_rank);
        }

        String piece_file = String.valueOf((char) ('a' + file));
        String piece_rank = String.valueOf(rank + 1);

        return addPostFix(piece.toString().toUpperCase() + piece_file + piece_rank);
    }

    private String addPostFix(String pre) {
        String post = endSquare.toString();
        boolean isAttacking = endSquare.isOccupied();

        if (isAttacking)
            pre = pre + "x";
        return pre + post;
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
