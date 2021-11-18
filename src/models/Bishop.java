package models;

import java.util.*;

public class Bishop extends Piece {

    protected Bishop(int rank, int file, String color) {
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
            }
            else {
                return true;
            }
        } else {
            return false;
      }
    

    @Override
    public ArrayList<Move> getAllMoves(Square[][] board) {
        ArrayList<Move> legalMoves = new ArrayList<>();
        Square startSquare = Square.createSquare(super.getRank(), super.getFile(), this);

        for(int i=1;i<=7;i++){
            endSquare = board[super.getRank()+i][super.getFile()+i];
            if(Check(endSquare)){
                    legalMoves.add(new Move(startSquare, endSquare));
                }
            }

        for(int i=1;i<=7;i++){
            endSquare = board[super.getRank()-i][super.getFile()-i];
            if(Check(endSquare)){
                    legalMoves.add(new Move(startSquare, endSquare));
                }
            }

        for(int i=1;i<=7;i++){
            endSquare = board[super.getRank()-i][super.getFile()+i];
            if(Check(endSquare)){
                    legalMoves.add(new Move(startSquare, endSquare));
                }
            }
        
        for(int i=1;i<=7;i++){
            endSquare = board[super.getRank()+i][super.getFile()-i];
            if(Check(endSquare)){
                    legalMoves.add(new Move(startSquare, endSquare));
                }
            }

        
        return legalMoves;
    }
}    
    


