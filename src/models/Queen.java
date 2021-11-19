package models;

import java.util.List;
import java.util.ArrayList;



public class Queen extends Piece{
	protected Queen(int rank, int file, String color) {
		super(rank, file, color);
	}
	
	public boolean Check(Square End) {
		if(End.getRank()>=0 && End.getFile()>=0 && End.getRank()<=7 && End.getFile()<=7) {
			if(End.isOccupied()) {
				if(this.getColor().equals(End.getPiece().getColor())) {
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
	public List<Move> getAllMoves(Square[][] board){
		List<Move> legalMoves = new ArrayList<>();
		Square startSquare = createSquare(super.getRank(), super.getFile(),this);
        Square endSquare = board[super.getRank()][super.getFile()];

		for(int i = 0 ; i<7; i++)
		{
			endSquare =  board[super.getRank()+i][super.getFile()];
			if(Check(endSquare)){
				legalMoves.add(new Move(startSquare, endSquare));
			}
			else break;
		}
		for(int i = 0 ; i<7; i++)
		{
			endSquare =  board[super.getRank()-i][super.getFile()];
			if(Check(endSquare)){
				legalMoves.add(new Move(startSquare, endSquare));
			}
			else break;
		}
		for(int i = 0 ; i<7; i++)
		{
			endSquare =  board[super.getRank()][super.getFile()-i];
			if(Check(endSquare)){
				legalMoves.add(new Move(startSquare, endSquare));
			}
			else break;
		}
		for(int i = 0 ; i<7; i++)
		{
			endSquare =  board[super.getRank()][super.getFile()+i];
			if(Check(endSquare)){
				legalMoves.add(new Move(startSquare, endSquare));
			}
			else break;
		}
		for(int i = 0 ; i<7; i++)
		{
			endSquare =  board[super.getRank()+i][super.getFile()+i];
			if(Check(endSquare)){
				legalMoves.add(new Move(startSquare, endSquare));
			}
			else break;
		}
		for(int i = 0 ; i<7; i++)
		{
			endSquare =  board[super.getRank()+i][super.getFile()-i];
			if(Check(endSquare)){
				legalMoves.add(new Move(startSquare, endSquare));
			}
			else break;
		}
		for(int i = 0 ; i<7; i++)
		{
			endSquare =  board[super.getRank()-i][super.getFile()+i];
			if(Check(endSquare)){
				legalMoves.add(new Move(startSquare, endSquare));
			}
			else break;
		}
		for(int i = 0 ; i<7; i++)
		{
			endSquare =  board[super.getRank()-i][super.getFile()-i];
			if(Check(endSquare)){
				legalMoves.add(new Move(startSquare, endSquare));
			}
			else break;
		}
		return legalMoves;
	}


    
}
