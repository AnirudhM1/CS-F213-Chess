package models;

import controllers.BoardController;
import models.Move;

import java.util.*;

public class Player{
    
    public Square[][] board;

    private String color;
    private ArrayList<Move> allMoves = new ArrayList<Move>();
    private ArrayList<Piece> allPieces = new ArrayList<Piece>();

    public Player(Square[][] board){
        
        this.board = board;
    
    }

    public boolean isInCheck(){
        
        
    }

    public ArrayList<Move> getAllMoves(ArrayList<Move> allMoves){
        
        return allMoves;

    }

    public ArrayList<Piece> getAllPieces(ArrayList<Piece> allPieces){
        
        return allPieces;

    }

    public String getColor(String color){
        
        return color;

    }

    private void addCastleMove(){

    }

    private void addEnPassentMove(){

    }

}
