package views.board;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Dimension;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;
import javax.swing.JComponent;

import controllers.Updater;
import models.Move;
import models.Square;

public class BoardPanel extends JPanel {

    public static final Color WHITE_COLOR = Color.WHITE;
    public static final Color BLACK_COLOR = new Color(165, 42, 42);

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

    public void clickedCell(Cell cell) {
        if (selectedCell == null) {
            selectedCell = cell;
            cell.toggleHighlight();
        } else if (selectedCell.equals(cell)) {
            selectedCell = null;
            cell.toggleHighlight();
        } else {
            selectedCell.toggleHighlight();
            generateMove(cell);
            selectedCell = null;
        }
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

    private void setCells() {
        for (int rank = 7; rank >= 0; rank--) {
            for (int file = 0; file < 8; file++) {
                Square sq = board[rank][file];
                Cell cell = new Cell(this, sq);
                add(cell);
                cells[cell.hashCode()] = cell;
            }
        }
    }

    // This function is called by the Gui class to reset all clicks, etc
    public void resetClicks() {
        // TODO implement this function
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

    private void generateMove(Cell cell) {
        Move move = new Move(selectedCell.getSquare(), cell.getSquare());
        updater.checkAndUpdate(move);
    }

}
