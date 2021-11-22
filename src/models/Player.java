package models;

import controllers.BoardController;
import models.Move;

import java.util.*;

public class Player{
    
    private final Square[][] board;

    private final boolean inCheck =  false;
    
    private final String color;
    private final ArrayList<Move> allMoves = new ArrayList<Move>();
    private final ArrayList<Piece> allPieces = new ArrayList<Piece>();
    
    public boolean kingMoved=false;
    public boolean rook1Moved=false;
    public boolean rook2Moved=false;

    public Player(Square[][] board, String color){
        
        this.board = board;
        this.color = color;
    
    }
    
    public void calculateAllMoves(ArrayList<Piece> allPieces){
        
        allMoves.clear();
        
        for(Piece piece : allPieces){
            
            allMoves.addAll(piece.getAllMoves(board));
        
        }
        
    }
    public boolean isInCheck(){
        
        return this.inCheck;
        
    }

    public ArrayList<Move> getAllMoves(){
        
        return this.allMoves;

    }

    public ArrayList<Piece> getAllPiecesArrayList(){
        
        return this.allPieces;

    }

    public String getColor(){
        
        return this.color;

    }
    
    //Function to check whether piece is under attack at that square
    public boolean underAttack(Square square) {
    	return false;
    }
    
    

    private void addCastleMove(Piece King,Piece Rook){
    	//rook1 is for long castle and rook2 is for short castle
    	
    	//short castle for white king
    	if ((King.getColor()=="White") && Rook.getRank()==7) {
    		
    		if(kingMoved==false && rook2Moved==false) {
        		
        		Square startSquareK = Square.createSquare(4,0,King);
        		Square startSquareR2= Square.createSquare(7,0,Rook);
        	    if (underAttack(board[4][0])==false && underAttack(board[5][0])==false && (board[5][0]).isOccupied()==false  && underAttack(board[6][0])==false && (board[6][0]).isOccupied()==false) {
        			Square endSquareK = board[6][0];
        			Square endSquareR2 = board[5][0];
        			allMoves.add(new Move(startSquareK, endSquareK));//add king move
        			allMoves.add(new Move(startSquareR2, endSquareR2));//add rook move
        		}
        		
        	}
    		
    		
    	}
    	
    	//short castle for Black king
    	if ((King.getColor()=="Black") && Rook.getRank()==7) {
    		
    		if(kingMoved==false && rook2Moved==false) {
        		
        		Square startSquareK = Square.createSquare(4,7,King);
        		Square startSquareR2= Square.createSquare(7,7,Rook);
        	    if (underAttack(board[4][7])==false && underAttack(board[5][7])==false && (board[5][7]).isOccupied()==false  && underAttack(board[6][7])==false && (board[6][7]).isOccupied()==false) {
        			Square endSquareK = board[6][7];
        			Square endSquareR2 = board[5][7];
        			allMoves.add(new Move(startSquareK, endSquareK));//add king move
        			allMoves.add(new Move(startSquareR2, endSquareR2));//add rook move
        		}
        		
        	}
    		
    		
    	}
    	
    	//long castle for White king
    	if ((King.getColor()=="White") && Rook.getRank()==0) {
    		
    		if(kingMoved==false && rook1Moved==false) {
        		
        		Square startSquareK = Square.createSquare(4,0,King);
        		Square startSquareR1= Square.createSquare(0,0,Rook);
        	    if (underAttack(board[4][0])==false && underAttack(board[3][0])==false && (board[3][0]).isOccupied()==false  && underAttack(board[2][0])==false && (board[2][0]).isOccupied()==false) {
        			Square endSquareK = board[2][0];
        			Square endSquareR1= board[3][0];
        			allMoves.add(new Move(startSquareK, endSquareK));//add king move
        			allMoves.add(new Move(startSquareR1, endSquareR1));//add rook move
        		}
        		
        	}
    		
    		
    	}
    	
    	//long castle for Black king
    	if ((King.getColor()=="Black") && Rook.getRank()==0) {
    		
    		if(kingMoved==false && rook1Moved==false) {
        		
        		Square startSquareK = Square.createSquare(4,7,King);
        		Square startSquareR1= Square.createSquare(0,7,Rook);
        	    if (underAttack(board[4][7])==false && underAttack(board[3][7])==false && (board[3][7]).isOccupied()==false  && underAttack(board[2][7])==false && (board[2][7]).isOccupied()==false) {
        			Square endSquareK = board[2][7];
        			Square endSquareR1= board[3][7];
        			allMoves.add(new Move(startSquareK, endSquareK));//add king move
        			allMoves.add(new Move(startSquareR1, endSquareR1));//add rook move
        		}
        		
        	}
    		
    		
    	}
    	
    	
    	
    	
    	

    }

    private void addEnPassentMove(){

    }

}