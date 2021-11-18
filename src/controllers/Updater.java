package controllers;

import models.Square;
import views.Gui;

public class Updater {
    private BoardController board;
    private Gui gui;

    public Updater() {
        init();
    }

    private void init() {
        gui = new Gui(this);
        board = null;
    }

    public void start() {
        board = null; // Default board
        gui.setState(this, defaultBoard()); // We are using defaultBoard only for debugging purposes
        gui.run();
    }

    // This function is only used for debugging purposes.
    // This is to be deleted when the initialize function is completed in BC
    private static Square[][] defaultBoard() {
        Square[][] board = new Square[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = Square.createSquare(i, j, null);
            }
        }
        return board;
    }
}
