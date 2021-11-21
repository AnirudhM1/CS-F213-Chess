package controllers;

import java.util.ArrayList;

import models.Move;
import models.Piece;
import models.Square;
import models.Player;

public final class BoardController {

    private final Square[][] board;
    private final Player whitePlayer, blackPlayer;
    private final String currentPlayer;

    private final ArrayList<Move> currentLegalMoves = new ArrayList<Move>();
    private boolean haveLegalMovesGenerated;

    // receives Builder object
    // and shallow copies it into a BoardController object
    private BoardController(Builder builder) {
        this.board = builder.board;
        this.whitePlayer = builder.whitePlayer;
        this.blackPlayer = builder.blackPlayer;
        this.currentPlayer = builder.currentPlayer;
        haveLegalMovesGenerated = false;
    }

    public ArrayList<Move> getCurrentLegalMoves() {
        if (haveLegalMovesGenerated)
            return currentLegalMoves;

        Player playingPlayer = this.currentPlayer.equals("WHITE") ? this.whitePlayer : this.blackPlayer;
        ArrayList<Move> playingMoves = playingPlayer.getAllMoves();

        for (Move playingMove : playingMoves) {
            BoardController simulatedBoard = this.executeMove(playingMove);

            Player otherPlayer = simulatedBoard.currentPlayer.equals("WHITE") ? simulatedBoard.whitePlayer
                    : simulatedBoard.blackPlayer;
            ArrayList<Move> otherMoves = otherPlayer.getAllMoves();

            boolean isPlayingMoveLegal = true;
            for (Move otherMove : otherMoves)
                if (otherMove.attacksKing()) {
                    isPlayingMoveLegal = false;
                    break;
                }

            if (isPlayingMoveLegal)
                currentLegalMoves.add(playingMove);
        }

        haveLegalMovesGenerated = true;
        return currentLegalMoves;
    }

    // getters
    public Square[][] getBoard() {
        return this.board;
    }

    public Player getWhitePlayer() {
        return this.whitePlayer;
    }

    public Player getBlackPlayer() {
        return this.blackPlayer;
    }

    public String getCurrentPlayer() {
        return this.currentPlayer;
    }

    // to create/initialize a fresh instance of BoardController
    public static BoardController initialize() {
        Builder builder = new Builder();
        return builder.build();
    }

    public BoardController executeMove(Move move) {
        Builder builder = new Builder(this);
        return builder.movePeice(move).togglePlayer().build();
    }

    // mutable Builder objects
    // used to build immutable BoardController objects
    private static class Builder {
        private Square[][] board;
        private Player whitePlayer, blackPlayer;
        private String currentPlayer;

        // to create a fresh instance of Builder
        private Builder() {
            this.board = new Square[8][8];
            this.currentPlayer = "WHITE";
        }

        // receives BoardController object
        // and deep copies it into a Builder object
        private Builder(BoardController prevBoard) {
            Square[][] boardClone = new Square[8][8];
            for (int i = 0; i < 8; i++)
                for (int j = 0; j < 8; j++)
                    boardClone[i][j] = prevBoard.board[i][j];

            this.board = boardClone;
            this.currentPlayer = prevBoard.currentPlayer;
        }

        // updates position of a peice on board, using a Move object
        private Builder movePeice(Move move) {
            Square startSquare = move.getStartSquare();
            int startRank = startSquare.getRank();
            int startFile = startSquare.getFile();

            Square endSquare = move.getEndSquare();
            int endRank = endSquare.getRank();
            int endFile = endSquare.getFile();

            Piece peiceToMove = startSquare.getPiece();

            this.board[startRank][startFile] = new Square.EmptySquare(startRank, startFile);
            this.board[endRank][endFile] = new Square.OccupiedSquare(endRank, endFile, peiceToMove);

            return this;
        }

        // to change the current playing player.
        // throws error if player is invalid.
        private Builder togglePlayer() throws Error {
            if (this.currentPlayer.equals("WHITE")) {
                this.currentPlayer = "BLACK";
            } else if (this.currentPlayer.equals("BLACK")) {
                this.currentPlayer = "WHITE";
            } else {
                throw new Error("Invalid currentPlayer: \"" + this.currentPlayer + "\"");
            }

            return this;
        }

        // return immutable BoardController object
        // by making a shallow copy of the Builder object
        private BoardController build() {
            // make sure whitePlayer and blackPlayer have all board updates
            this.whitePlayer = new Player(this.board, "WHITE");
            this.blackPlayer = new Player(this.board, "BLACK");

            return new BoardController(this);
        }
    }
}
