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

    // ! do not access currentLegalMoves directly.
    // ! use getCurrentLegalMoves() instead.
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

    // check if a given move is legal or not
    public boolean isMoveLegal(Move moveToCheck) {
        for (Move legalMove : getCurrentLegalMoves())
            if (legalMove.equals(moveToCheck))
                return true;

        return false;
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

    // to create/initialize an instance of BoardController, with the Pieces at their
    // starting positions
    public static BoardController initialize() {
        final String initFEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
        return createBoardFromFEN(initFEN);
    }

    public BoardController executeMove(Move move) {
        Builder builder = new Builder(this);
        return builder.movePeice(move).togglePlayer().build();
    }

    // To create an instance of BoardController from FEN String
    // TODO check for errors in FEN String
    public static BoardController createBoardFromFEN(String FEN) {
        String[] fields = FEN.split(" ");
        String piece_placement = fields[0];
        String active_color = fields[1];
        // String castling = fields[2];
        // String enPassant = fields[3];
        // String half_moves = fields[4];
        // String full_moves = fields[5];

        Builder builder = new Builder();

        // For piece placement
        int rank = 7;
        for (String row : piece_placement.split("/")) {
            int file = 0;
            for (char c : row.toCharArray()) {
                if (Character.isAlphabetic(c)) {
                    String color = (Character.isUpperCase(c)) ? "WHITE" : "BLACK";
                    Piece piece = Piece.createPiece(rank, file, color, getPieceName(c));
                    file++;
                    builder.setPiece(piece);
                } else if (Character.isDigit(c))
                    file += Integer.parseInt(String.valueOf(c));
                else {
                    // TODO Error handling
                }
            }
            rank--;
        }

        // For active color
        String currentPlayerColor = (active_color.equals("w")) ? "WHITE" : "BLACK";
        builder.setCurrentPlayer(currentPlayerColor);

        return builder.build();
    }

    private static String getPieceName(char c) {
        c = Character.toUpperCase(c);
        switch (c) {
            case 'P':
                return "PAWN";
            case 'R':
                return "ROOK";
            case 'Q':
                return "QUEEN";
            case 'K':
                return "KING";
            case 'B':
                return "BISHOP";
            case 'N':
                return "KNIGHT";
            default:
                throw new Error("Invalid piece character: " + c);
        }
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
            for (int rank = 0; rank < 8; rank++)
                for (int file = 0; file < 8; file++)
                    this.board[rank][file] = Square.createSquare(rank, file, null);

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

        // Adds the given piece to the board
        private Builder setPiece(Piece piece) {
            if (piece == null)
                throw new Error("BoardController.Builder.setPiece(Piece piece) cannot be passed null as a value");

            int rank = piece.getRank();
            int file = piece.getFile();
            this.board[rank][file] = Square.createSquare(rank, file, piece);
            return this;
        }

        // Removes the given piece from the board
        private Builder removePiece(Piece piece) {
            if (piece == null)
                throw new Error("BoardController.Builder.removePiece(Piece piece) cannot be passed null as a value");

            int rank = piece.getRank();
            int file = piece.getFile();
            this.board[rank][file] = Square.createSquare(rank, file, null);
            return this;
        }

        // Sets the current player of the board
        private Builder setCurrentPlayer(String color) {
            if (color.equalsIgnoreCase("WHITE"))
                this.currentPlayer = "WHITE";
            else if (color.equalsIgnoreCase("BLACK"))
                this.currentPlayer = "BLACK";
            else
                throw new Error("Invalid player color: " + color);
            return this;
        }

        // updates position of a peice on board, using a Move object
        private Builder movePeice(Move move) {
            Piece oldPeice = move.getStartSquare().getPiece();
            String color = oldPeice.getColor();
            String name = oldPeice.getClass().getName();

            Square endSquare = move.getEndSquare();
            int endRank = endSquare.getRank();
            int endFile = endSquare.getFile();

            Piece newPeice = Piece.createPiece(endRank, endFile, color, name);

            this.removePiece(oldPeice);
            this.setPiece(newPeice);

            return this;
        }

        // to change the current playing player.
        // throws error if player is invalid.
        private Builder togglePlayer() throws Error {
            if (this.currentPlayer.equals("WHITE"))
                this.setCurrentPlayer("BLACK");
            else if (this.currentPlayer.equals("BLACK"))
                this.setCurrentPlayer("WHITE");
            else
                throw new Error("Invalid currentPlayer: \"" + this.currentPlayer + "\"");

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
