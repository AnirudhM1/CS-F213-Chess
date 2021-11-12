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

    public Player(Square[][] board, String color){
        
        this.board = board;
        this.color = color;
    
    }

    public boolean isInCheck(){
        
        return this.inCheck;
        
    }

    public ArrayList<Move> getAllMoves(){
        
        return this.allMoves;

    }

    public ArrayList<Piece> getAllPieces(){
        
        return this.allPieces;

    }

    public String getColor(){
        
        return this.color;

    }

    private void addCastleMove(){

    }

    private void addEnPassentMove(){

    }

}
