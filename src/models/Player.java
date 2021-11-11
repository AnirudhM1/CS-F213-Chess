package models;

import controllers.BoardController;
import models.Move;

import java.util.*;

public class Player{
    
    public Square[][] board;

    private String color;
    private ArrayList<Move> allMoves = new ArrayList<Move>();
    private ArrayList<Piece> allPieces = new ArrayList<Piece>();

    public Player(Square[][] board, String currentPlayer){
        this.board = board;
        this.currentPlayer = color;
    }

    public boolean isInCheck(){
        
        
    }

    public ArrayList<Move>(ArrayList<Move> allMoves){
        
        return ArrayList<Move> allMoves;

    }

    public ArrayList<Piece>(ArrayList<Move> allPieces){
        
        return ArrayList<Piece> allPieces;

    }

    public String getColor(String color){
        
        return color;

    }

    private void addCastleMove(){

    }

    private void addEnPassentMove(){

    }

}