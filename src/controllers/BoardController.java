package controllers;

import models.Square;

public final class BoardController {

    public enum PlayerColor {
        BLACK, WHITE
    }

    private final Square[][] board;
    // private final Player whitePlayer, blackPlayer;
    private final PlayerColor currentPlayer;

    private BoardController(Builder builder) {
        this.board = builder.board;
        // this.whitePlayer = builder.whitePlayer;
        // this.blackPlayer = builder.blackPlayer;
        this.currentPlayer = builder.currentPlayer;
    }

    public static class Builder {
        private Square[][] board;
        // private Player whitePlayer, blackPlayer;
        private PlayerColor currentPlayer;

        public Builder() {
            // update Square[][]
        }

        public Builder player() {
            if (this.currentPlayer == PlayerColor.WHITE) {
                this.currentPlayer = PlayerColor.BLACK;
            } else {
                this.currentPlayer = PlayerColor.WHITE;
            }

            return this;
        }

        public Builder initialize() {
            this.board = new Square[8][8];
            // this.whitePlayer =
            // this.blackPlayer =
            return this;
        }

        public BoardController build() {
            return new BoardController(this);
        }
    }
}
