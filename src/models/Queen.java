package models;

import java.util.List;
import java.util.ArrayList;



public class Queen extends Piece{
	protected Queen(int rank, int file, String color) {
		super(rank, file, color);
	}

	public boolean Check(Square End) {
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
	public List<Move> getAllMoves(Square[][] board){
		List<Move> legalMoves = new ArrayList<>();
		Square startSquare = board[super.getRank()][super.getFile()];
        Square endSquare = board[super.getRank()][super.getFile()];

		for(int i = 0 ; i<7; i++)
		{
			int x = super.getRank() + i;
			int y = super.getFile() ;
			if(x >=0 && x<= 7 && y>=0 && y<=7) {


				endSquare = board[x][y];
				if (Check(endSquare)) {
					legalMoves.add(new Move(startSquare, endSquare));
				} else break;
			}
		}
		for(int i = 0 ; i<7; i++)
		{
			int x = super.getRank() - i;
			int y = super.getFile() ;
			if(x >=0 && x<= 7 && y>=0 && y<=7) {


				endSquare = board[x][y];
				if (Check(endSquare)) {
					legalMoves.add(new Move(startSquare, endSquare));
				} else break;
			}
		}
		for(int i = 0 ; i<7; i++)
		{
			int x = super.getRank() ;
			int y = super.getFile() -i ;
			if(x >=0 && x<= 7 && y>=0 && y<=7) {


				endSquare = board[x][y];
				if (Check(endSquare)) {
					legalMoves.add(new Move(startSquare, endSquare));
				} else break;
			}
		}

		for(int i = 0 ; i<7; i++)
		{
			int x = super.getRank() ;
			int y = super.getFile() + i ;
			if(x >=0 && x<= 7 && y>=0 && y<=7) {


				endSquare = board[x][y];
				if (Check(endSquare)) {
					legalMoves.add(new Move(startSquare, endSquare));
				} else break;
			}
		}
		for(int i = 0 ; i<7; i++)
		{
			int x = super.getRank() + i;
			int y = super.getFile() +i;
			if(x >=0 && x<= 7 && y>=0 && y<=7) {


				endSquare = board[x][y];
				if (Check(endSquare)) {
					legalMoves.add(new Move(startSquare, endSquare));
				} else break;
			}
		}
		for(int i = 0 ; i<7; i++)
		{
			int x = super.getRank() + i;
			int y = super.getFile() -i ;
			if(x >=0 && x<= 7 && y>=0 && y<=7) {


				endSquare = board[x][y];
				if (Check(endSquare)) {
					legalMoves.add(new Move(startSquare, endSquare));
				} else break;
			}
		}
		for(int i = 0 ; i<7; i++)
		{
			int x = super.getRank() - i;
			int y = super.getFile() +i;
			if(x >=0 && x<= 7 && y>=0 && y<=7) {


				endSquare = board[x][y];
				if (Check(endSquare)) {
					legalMoves.add(new Move(startSquare, endSquare));
				} else break;
			}
		}
		for(int i = 0 ; i<7; i++)
		{
			int x = super.getRank() - i;
			int y = super.getFile()-i ;
			if(x >=0 && x<= 7 && y>=0 && y<=7) {


				endSquare = board[x][y];
				if (Check(endSquare)) {
					legalMoves.add(new Move(startSquare, endSquare));
				} else break;
			}
		}

		return legalMoves;
	}


    
}
