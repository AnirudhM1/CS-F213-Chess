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
        calculateAllPieces();
        calculateAllMoves();
    }
    
    private void calculateAllMoves() {

        allMoves.clear();

        for (Piece piece : allPieces) {

            allMoves.addAll(piece.getAllMoves(board));

        }

    }

    private void calculateAllPieces() {
        allPieces.clear();

        for (int rank = 0; rank < 8; rank++) {
            for (int file = 0; file < 8; file++) {
                Piece piece = board[rank][file].getPiece();

                if (piece != null && piece.getColor().equals(this.color))
                    allPieces.add(piece);
            }
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

    private void addCastleMove(){

    }

    private void addEnPassentMove(){

    }

}
