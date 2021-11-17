package views.board;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Dimension;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;
import javax.swing.JComponent;

import controllers.Updater;
import models.Square;

public class BoardPanel extends JPanel {

    private static final Color WHITE_COLOR = Color.WHITE;
    private static final Color BLACK_COLOR = Color.GREEN;

    private Square[][] board;
    private Updater updater;
    private JPanel outerBoardPanel;

    private Cell[] cells;
    private Cell selectedCell = null;

    public BoardPanel(JPanel outerBoardPanel, Updater updater, Square[][] board) {
        this.outerBoardPanel = outerBoardPanel;
        this.updater = updater;
        this.board = board;
        this.cells = new Cell[64];

        setPanel();

        setCells();
    }

    private void setPanel() {
        setLayout(new GridLayout(8, 8));
        addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent e) {
                componentResize(outerBoardPanel, BoardPanel.this);
            }

        });
    }

    private static void componentResize(JComponent outer, JComponent inner) {

        double width = outer.getWidth() * 0.8;
        double height = outer.getHeight() * 0.9;
        double size = Math.min(width, height);

        Dimension dim = new Dimension();
        dim.setSize(size, size);

        inner.setPreferredSize(dim);

        outer.revalidate();

    }

    private void setCells() {
        for (int rank = 0; rank < 8; rank++) {
            for (int file = 0; file < 8; file++) {
                Square sq = board[rank][file];
                Cell cell = new Cell(this, sq);
                add(cell);
            }
        }
    }

}
