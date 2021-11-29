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

    // Fields to indicicate castling
    // These fields only indicate whether the respective pieces have moved or not.
    // They don't check for other conditions required.
    private final boolean whiteKingSideCastleAllowed;
    private final boolean whiteQueenSideCastleAllowed;
    private final boolean blackKingSideCastleAllowed;
    private final boolean blackQueenSideCastleAllowed;

    // Field to set enPassantSquare
    private final Square enPassantSquare;

    // receives Builder object
    // and shallow copies it into a BoardController object
    private BoardController(Builder builder) {
        this.board = builder.board;
        this.whitePlayer = builder.whitePlayer;
        this.blackPlayer = builder.blackPlayer;
        this.currentPlayer = builder.currentPlayer;
        this.whiteKingSideCastleAllowed = builder.whiteKingSideCastleAllowed;
        this.whiteQueenSideCastleAllowed = builder.whiteQueenSideCastleAllowed;
        this.blackKingSideCastleAllowed = builder.blackKingSideCastleAllowed;
        this.blackQueenSideCastleAllowed = builder.blackQueenSideCastleAllowed;
        this.enPassantSquare = builder.enPassantSquare;
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
        String castling = fields[2];
        String enPassant = fields[3];
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

        // For castling
        setCastlingFieldsFEN(builder, castling);

        // for en-passant
        // variable enPassant is of the form: e4, h8 etc.
        if (enPassant.equals("-")) {
            builder.setEnPassantSquare(null);
        } else {
            int enPassantFile = enPassant.charAt(0) - 'a';
            int enPassantRank = Integer.parseInt(castling.substring(1));
            Square enPassantSquare = Square.createSquare(enPassantRank, enPassantFile, null);
            builder.setEnPassantSquare(enPassantSquare);
        }

        return builder.build();
    }

    private static void setCastlingFieldsFEN(Builder builder, String castlingFEN) {
        builder.setAllCastlingFieldsToFalse();
        for (char c : castlingFEN.toCharArray()) {
            switch (c) {
                case 'k':
                    builder.setKingSideCastling("BLACK");
                    break;
                case 'K':
                    builder.setKingSideCastling("WHITE");
                    break;
                case 'q':
                    builder.setQueenSideCastling("BLACK");
                    break;
                case 'Q':
                    builder.setQueenSideCastling("WHITE");
            }
        }
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

        // Fields to indicicate castling
        // These fields only indicate whether the respective pieces have moved or not.
        // They don't check for other conditions required.
        private boolean whiteKingSideCastleAllowed;
        private boolean whiteQueenSideCastleAllowed;
        private boolean blackKingSideCastleAllowed;
        private boolean blackQueenSideCastleAllowed;

        // Field to set enPassantSquare
        private Square enPassantSquare;

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
            this.whiteKingSideCastleAllowed = prevBoard.whiteKingSideCastleAllowed;
            this.whiteQueenSideCastleAllowed = prevBoard.whiteQueenSideCastleAllowed;
            this.blackKingSideCastleAllowed = prevBoard.blackKingSideCastleAllowed;
            this.blackQueenSideCastleAllowed = prevBoard.blackQueenSideCastleAllowed;
            this.enPassantSquare = prevBoard.enPassantSquare;
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
            String name = oldPeice.toString();

            Square endSquare = move.getEndSquare();
            int endRank = endSquare.getRank();
            int endFile = endSquare.getFile();

            Piece newPeice = Piece.createPiece(endRank, endFile, color, getPieceName(name.charAt(0)));

            this.removePiece(oldPeice);
            this.setPiece(newPeice);

            setCastlingFields(move);
            setEnPassantFields(move);

            return this;
        }

        // Sets all castling fields to false
        private void setAllCastlingFieldsToFalse() {
            whiteKingSideCastleAllowed = false;
            whiteQueenSideCastleAllowed = false;
            blackKingSideCastleAllowed = false;
            blackQueenSideCastleAllowed = false;
        }

        // This method sets the respective castling fields and also changes the board
        // state to accomadate special moves
        private void setCastlingFields(Move move) {
            Square startSquare = move.getStartSquare();
            Square endSquare = move.getEndSquare();
            Piece piece = startSquare.getPiece();

            // If piece is a king
            if (piece.toString().equalsIgnoreCase("K")) {
                // If white
                if (piece.getColor().equalsIgnoreCase("WHITE")) {
                    whiteKingSideCastleAllowed = false;
                    whiteQueenSideCastleAllowed = false;
                }
                // If black
                else {
                    blackKingSideCastleAllowed = false;
                    blackQueenSideCastleAllowed = false;
                }

                // Checking if the king has castled
                if (move.hasMovedTwoSteps()) {
                    int rookRank = piece.getRank();
                    int rookFile = (startSquare.getFile() + endSquare.getFile()) / 2;
                    Piece newPiece = Piece.createPiece(rookRank, rookFile, piece.getColor(), "ROOK");
                    Piece oldPeice;
                    if (endSquare.getFile() == 6)
                        oldPeice = Piece.createPiece(endSquare.getRank(), 7, piece.getColor(), "ROOK");
                    else if (endSquare.getFile() == 2)
                        oldPeice = Piece.createPiece(endSquare.getRank(), 0, piece.getColor(), "ROOK");
                    else
                        throw new Error("Invalid destinationFile: " + endSquare.getFile());

                    // Update board state for castled rook
                    this.removePiece(oldPeice);
                    this.setPiece(newPiece);
                }
            }
            // if piece is a rook
            else if (piece.toString().equals("ROOK")) {

                // if white
                if (piece.getColor().equalsIgnoreCase("WHITE")) {
                    if (piece.getFile() == '0') {
                        this.whiteQueenSideCastleAllowed = false;
                    }
                    if (piece.getFile() == '9') {
                        this.whiteKingSideCastleAllowed = false;
                    }
                }

                // if black
                else if (piece.getColor().equalsIgnoreCase("BLACK")) {
                    if (piece.getFile() == '0') {
                        this.blackQueenSideCastleAllowed = false;
                    }
                    if (piece.getFile() == '9') {
                        this.blackKingSideCastleAllowed = false;
                    }
                } else
                    throw new Error("Invalid color: " + piece.getColor());
            }
        }

        // This method sets the respective EnPassant fields and also changes the board
        // state to accomadate special moves
        private void setEnPassantFields(Move move) {
            Square startSquare = move.getStartSquare();
            Square endSquare = move.getEndSquare();
            Piece piece = startSquare.getPiece();

            // If piece is a pawn
            if (piece.toString().equalsIgnoreCase("PAWN")) {
                // If pawn has moved 2 steps
                if (move.hasMovedTwoSteps()) {
                    int enPassantFile = piece.getFile();
                    int enPassantRank = (startSquare.getRank() + endSquare.getRank()) / 2;
                    this.enPassantSquare = Square.createSquare(enPassantRank, enPassantFile, null);
                    return; // This line prevents enPassantSquare from becoming null again at the end
                }

                // Remove piece if capture by en-passant
                if (enPassantSquare != null && endSquare.equals(enPassantSquare)) {
                    if (enPassantSquare.getRank() == 5) {
                        Piece capturedPiece = board[4][enPassantSquare.getFile()].getPiece();
                        this.removePiece(capturedPiece);
                    } else if (enPassantSquare.getRank() == 3) {
                        Piece capturedPiece = board[2][enPassantSquare.getFile()].getPiece();
                        this.removePiece(capturedPiece);
                    } else {
                        throw new Error("Invalid enPassant rank: " + enPassantSquare.getRank());
                    }
                }
            }
            enPassantSquare = null;
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

        // Allow king side castling
        private Builder setKingSideCastling(String color) {
            if (color.equalsIgnoreCase("WHITE"))
                this.whiteKingSideCastleAllowed = true;
            else if (color.equalsIgnoreCase("BLACK"))
                this.blackKingSideCastleAllowed = true;
            else
                throw new Error("Invalid Color: " + color);
            return this;
        }

        // Allow queen side castling
        private Builder setQueenSideCastling(String color) {
            if (color.equalsIgnoreCase("WHITE"))
                this.whiteQueenSideCastleAllowed = true;
            else if (color.equalsIgnoreCase("BLACK"))
                this.blackQueenSideCastleAllowed = true;
            else
                throw new Error("Invalid Color: " + color);
            return this;
        }

        // Set enPassant square
        private Builder setEnPassantSquare(Square square) {
            this.enPassantSquare = square;
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
