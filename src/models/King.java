package models;

import java.util.ArrayList;
import java.util.List;


public class King extends Piece {
	
	

	protected King(int rank, int file, String color) {
		super(rank, file, color);
	}
	
	public boolean Check(Square End) {
		if(End.getRank()>=0 && End.getFile()>=0 && End.getRank()<=7 && End.getFile()<=7) {
			if(End.isOccupied()) {
				if(this.getColor()==End.getPiece().getColor()) {
					return false;
				}
				else {
					return true;
				}
				
			}
			else {
				return true;
			}
		}
		else {
			return false;
		}
	}
		
	@Override
	public List<Move> getAllMoves(Square[][] board) {
		
		List<Move> legalMoves = new ArrayList<>();
		Square startSquare = Square.createSquare(super.getRank(), super.getFile(),this);
		
		Square endSquare1 = Square.createSquare(super.getRank()+1, super.getFile(),this);
		if(Check(endSquare1)) {
			legalMoves.add(new Move(startSquare, endSquare1));		
		}
		Square endSquare2 = Square.createSquare(super.getRank()-1, super.getFile(),this);
		if(Check(endSquare1)) {
			legalMoves.add(new Move(startSquare, endSquare2));		
		}
		
		Square endSquare3 = Square.createSquare(super.getRank(), super.getFile()+1,this);
		if(Check(endSquare3)) {
			legalMoves.add(new Move(startSquare, endSquare3));	
		}
		Square endSquare4 = Square.createSquare(super.getRank(), super.getFile()-1,this);
        if(Check(endSquare4)) {
			legalMoves.add(new Move(startSquare, endSquare4));	
		}
        
        Square endSquare5 = Square.createSquare(super.getRank()+1, super.getFile()+1,this);
        if(Check(endSquare5)) {
			legalMoves.add(new Move(startSquare, endSquare5));	
		}
        Square endSquare6 = Square.createSquare(super.getRank()-1, super.getFile()-1,this);
        if(Check(endSquare6)) {
			legalMoves.add(new Move(startSquare, endSquare6));	
		}
        
        Square endSquare7 = Square.createSquare(super.getRank()+1, super.getFile()-1,this);
        if(Check(endSquare7)) {
			legalMoves.add(new Move(startSquare, endSquare7));	
		}
        Square endSquare8 = Square.createSquare(super.getRank()-1, super.getFile()+1,this);
        if(Check(endSquare8)) {
			legalMoves.add(new Move(startSquare, endSquare8));	
		}

        
        return legalMoves;
		
	}
    
}
