package models;

import controllers.BoardController;
import models.Move;

import java.util.*;

public class Player{
    
    public Square[][] board;

    public boolean inCheck;
    
    private String color;
    private final ArrayList<Move> allMoves = new ArrayList<Move>();
    private final ArrayList<Piece> allPieces = new ArrayList<Piece>();

    public Player(Square[][] board){
        
        this.board = board;
    
    }

    public boolean isInCheck(){
        
        return inCheck;
        
    }

    public ArrayList<Move> getAllMoves(ArrayList<Move> allMoves){
        
        return this.allMoves;

    }

    public ArrayList<Piece> getAllPieces(ArrayList<Piece> allPieces){
        
        return this.allPieces;

    }

    public String getColor(String color){
        
        return this.color;

    }

    private void addCastleMove(){

    }

    private void addEnPassentMove(){

    }

}
