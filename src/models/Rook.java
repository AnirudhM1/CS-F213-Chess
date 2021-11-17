package models;

import java.util.ArrayList;
import java.util.List;


public class Rook extends Piece {



	protected Rook(int rank, int file, String color) {
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
		
	@Override
	public List<Move> getAllMoves(Square[][] board) {
		
		List<Move> legalMoves = new ArrayList<>();
		Square startSquare = Square.createSquare(super.getRank(), super.getFile(),this);
		
		Square endSquare = board[super.getRank()+1][ super.getFile()];
		boolean checkNextMove=true;
		while(checkNextMove==true)
		{
			if(Check(endSquare)) {
				legalMoves.add(new Move(startSquare, endSquare));
				if(endSquare.isOccupied()){
					checkNextMove=false;
				}
				endSquare=board[endSquare.getRank()+1][ endSquare.getFile()];
			}
			else{
				checkNextMove=false;
			}
		}

		endSquare = board[super.getRank()-1][ super.getFile()];
		checkNextMove=true;
		while(checkNextMove==true)
		{
			if(Check(endSquare)) {
				legalMoves.add(new Move(startSquare, endSquare));
				if(endSquare.isOccupied()){
					checkNextMove=false;
				}
				endSquare=board[endSquare.getRank()-1][ endSquare.getFile()];
			}
			else{
				checkNextMove=false;
			}
		}

		endSquare = board[super.getRank()][ super.getFile()+1];
		checkNextMove=true;
		while(checkNextMove==true)
		{
			if(Check(endSquare)) {
				legalMoves.add(new Move(startSquare, endSquare));
				if(endSquare.isOccupied()){
					checkNextMove=false;
				}
				endSquare=board[endSquare.getRank()][ endSquare.getFile()+1];
			}
			else{
				checkNextMove=false;
			}
		}

		endSquare = board[super.getRank()][ super.getFile()-1];
		checkNextMove=true;
		while(checkNextMove==true)
		{
			if(Check(endSquare)) {
				legalMoves.add(new Move(startSquare, endSquare));
				if(endSquare.isOccupied()){
					checkNextMove=false;
				}
				endSquare=board[endSquare.getRank()][ endSquare.getFile()-1];
			}
			else{
				checkNextMove=false;
			}
		}
        
        return legalMoves;
		
	}
    
}
