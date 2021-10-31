package controllers;

import models.Square;

public final class BoardController {

    private final Square[][] board;
    // private final Player whitePlayer, blackPlayer;
    private final String currentPlayer;

    // receives Builder object
    // and shallow copies it into a BoardController object
    private BoardController(Builder builder) {
        this.board = builder.board;
        // this.whitePlayer = builder.whitePlayer;
        // this.blackPlayer = builder.blackPlayer;
        this.currentPlayer = builder.currentPlayer;
    }

    // getters
    public Square[][] getBoard() {
        return this.board;
    }

    // public Player getWhitePlayer() {
    // return this.whitePlayer;
    // }

    // public Player getBlackPlayer() {
    // return this.blackPlayer;
    // }

    public String getCurrentPlayer() {
        return this.currentPlayer;
    }

    // mutable Builder objects
    // used to build immutable BoardController objects
    public static class Builder {
        private Square[][] board;
        // private Player whitePlayer, blackPlayer;
        private String currentPlayer;

        // receives BoardController object
        // and shallow copies it into a Builder object
        public Builder(BoardController prevBoard) {
            this.board = prevBoard.board;
            // this.whitePlayer = prevBoard.whitePlayer;
            // this.blackPlayer = prevBoard.blackPlayer;
            this.currentPlayer = prevBoard.currentPlayer;
        }

        // to update position of a peice on board
        public Builder move() {
            // update Square[][]
            return this;
        }

        // to change the current playing player.
        // throws error if player is invalid.
        public Builder player() throws Error {
            if (this.currentPlayer.equals("WHITE")) {
                this.currentPlayer = "BLACK";
            } else if (this.currentPlayer.equals("BLACK")) {
                this.currentPlayer = "BLACK";
            } else {
                throw new Error("Invalid currentPlayer: \"" + this.currentPlayer + "\"");
            }

            return this;
        }

        // initialise the board on startup
        public Builder initialize() {
            this.board = new Square[8][8];
            // this.whitePlayer =
            // this.blackPlayer =
            return this;
        }

        // return immutable BoardController object
        // by making a shallow copy of the Builder object
        public BoardController build() {
            return new BoardController(this);
        }
    }
}
