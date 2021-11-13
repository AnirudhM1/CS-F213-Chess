package models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import models.Piece;
import models.Square;

public class King extends Piece {
	
	

	protected King(int rank, int file, String color) {
		super(rank, file, color);
	}
	
	

	@Override
	public Collection<Move> getAllMoves(Square[][] board) {
		Collection<Move> legalMoves = new ArrayList<>();
        
        legalMoves.add(new Move(super.getRank()+1, super.getFile(), this));
        legalMoves.add(new Move(super.getRank()-1, super.getFile(), this));

        legalMoves.add(new Move(super.getRank(), super.getFile()+1, this));
        legalMoves.add(new Move(super.getRank(), super.getFile()-1, this));

        legalMoves.add(new Move(super.getRank()+1, super.getFile()+1, this));
        legalMoves.add(new Move(super.getRank()-1, super.getFile()-1, this));

        legalMoves.add(new Move(super.getRank()+1, super.getFile()-1, this));
        legalMoves.add(new Move(super.getRank()-1, super.getFile()+1, this));
        
        return legalMoves;
		
	}

	
	

  
    
}
