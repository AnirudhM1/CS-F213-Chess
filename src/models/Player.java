package models;

import controllers.BoardController;
import models.Move;

import java.util.*;

public class Player{
    
    public final Square[][] board;

    public final boolean inCheck =  false;
    
    private final String color = BoardController.currentPlayer;
    private final ArrayList<Move> allMoves = new ArrayList<Move>();
    private final ArrayList<Piece> allPieces = new ArrayList<Piece>();

    public Player(Square[][] board){
        
        this.board = board;
    
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
