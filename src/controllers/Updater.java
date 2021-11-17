package controllers;

import views.Gui;

public class Updater {
    private BoardController board;
    private Gui gui;

    public Updater() {
        init();
    }

    private void init() {
        board = null; // Default board
        gui = new Gui(this);
    }
}
