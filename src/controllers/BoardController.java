package controllers;

import models.Move;
import models.Piece;
import models.Square;
import models.Player;

public final class BoardController {

    private final Square[][] board;
    private final Player whitePlayer, blackPlayer;
    private final String currentPlayer;

    // receives Builder object
    // and shallow copies it into a BoardController object
    private BoardController(Builder builder) {
        this.board = builder.board;
        this.whitePlayer = builder.whitePlayer;
        this.blackPlayer = builder.blackPlayer;
        this.currentPlayer = builder.currentPlayer;
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
            this.whitePlayer = new Player(this.board, "WHITE");
            this.blackPlayer = new Player(this.board, "BLACK");
            this.currentPlayer = "WHITE";
        }

        // receives BoardController object
        // and shallow copies it into a Builder object
        private Builder(BoardController prevBoard) {
            this.board = prevBoard.board;
            this.whitePlayer = prevBoard.whitePlayer;
            this.blackPlayer = prevBoard.blackPlayer;
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
            return new BoardController(this);
        }
    }
}
