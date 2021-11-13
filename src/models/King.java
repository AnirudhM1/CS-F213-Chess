package models;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
	
	

	protected King(int rank, int file, String color) {
		super(rank, file, color);
	}
	
	

	@Override
	public List<Move> getAllMoves(Square[][] board) {
		List<Move> legalMoves = new ArrayList<>();
		Square startSquare = Square.createSquare(super.getRank(), super.getFile(),this);
		
		Square endSquare1 = Square.createSquare(super.getRank()+1, super.getFile(),this);
        legalMoves.add(new Move(startSquare, endSquare1));
        Square endSquare2 = Square.createSquare(super.getRank()-1, super.getFile(),this);
        legalMoves.add(new Move(startSquare, endSquare2));
        
        Square endSquare3 = Square.createSquare(super.getRank(), super.getFile()+1,this);
        legalMoves.add(new Move(startSquare, endSquare3));
        Square endSquare4 = Square.createSquare(super.getRank(), super.getFile()-1,this);
        legalMoves.add(new Move(startSquare, endSquare4));
        
        Square endSquare5 = Square.createSquare(super.getRank()+1, super.getFile()+1,this);
        legalMoves.add(new Move(startSquare, endSquare5));
        Square endSquare6 = Square.createSquare(super.getRank()-1, super.getFile()-1,this);
        legalMoves.add(new Move(startSquare, endSquare6));
        
        Square endSquare7 = Square.createSquare(super.getRank()+1, super.getFile()-1,this);
        legalMoves.add(new Move(startSquare, endSquare7));
        Square endSquare8 = Square.createSquare(super.getRank()-1, super.getFile()+1,this);
        legalMoves.add(new Move(startSquare, endSquare8));

        
        return legalMoves;
		
	}
    
}
