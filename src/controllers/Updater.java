package controllers;

import models.Move;
import models.Square;
import network.Client;
import views.Gui;

public class Updater {
    private BoardController board;
    private Gui gui;

    private boolean inSocketMode;

    public Updater() {
        init();
    }

    private void init() {
        gui = new Gui(this);
        board = null;
        inSocketMode = false;
    }

    public void connect(String host, int port) {
        inSocketMode = true;
        Client.connect(host, port, this);
    }

    public void start() {
        board = BoardController.initialize();
        gui.setState(this, board.getBoard());
        gui.run();
    }

    public void checkAndUpdate(Move move) {
        gui.resetClicks();
        if (board.isMoveLegal(move)) {
            board = board.executeMove(move);
            gui.setState(this, board.getBoard());
            gui.addMove(move);

            // Send to move to other device
            if (inSocketMode)
                Client.write(move.toString());
        }
    }

    public void checkAndUpdate(String move) {
        String start = move.substring(0, 2);
        String end = move.substring(2);

        int startRank = start.charAt(1) - '1';
        int startFile = start.charAt(0) - 'a';
        int endRank = end.charAt(1) - '1';
        int endFile = end.charAt(0) - 'a';

        Square startSquare = board.getBoard()[startRank][startFile];
        Square endSquare = board.getBoard()[endRank][endFile];

        Move parsedMove = new Move(startSquare, endSquare);
        checkAndUpdate(parsedMove);
    }

    // These functions are to display connection messages for server client model
    public void connectionWaiting() {
        gui.startConnectionWaiting();
    }

    public void endConnectionWaiting() {
        gui.endConnectionWaiting();
    }

    // // This function is only used for debugging purposes.
    // // This is to be deleted when the initialize function is completed in BC
    // private static Square[][] defaultBoard() {
    // Square[][] board = new Square[8][8];
    // for (int i = 0; i < 8; i++) {
    // for (int j = 0; j < 8; j++) {
    // board[i][j] = Square.createSquare(i, j, null);
    // }
    // }
    // return board;
    // }
}
