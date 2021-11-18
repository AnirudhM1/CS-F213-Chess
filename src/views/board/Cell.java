package views.board;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import models.Square;

public class Cell extends JPanel implements MouseInputListener {

    private Square square;

    public Cell(BoardPanel panel, Square square) {
        this.square = square;

        setCell();
    }

    private void setCell() {
        int rank = square.getRank();
        int file = square.getFile();

        Color color = ((rank + file) % 2 == 0) ? BoardPanel.BLACK_COLOR : BoardPanel.WHITE_COLOR;
        setBackground(color);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Select/deselect square OR create move

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Select square in draggable mode

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Release square and create move

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Highlight the square

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // remove highlight

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public int hashCode() {
        return (8 * square.getRank() + square.getFile());
    }

    @Override
    public String toString() {
        int r = square.getRank();
        char f = (char) ('a' + square.getFile());

        StringBuilder builder = new StringBuilder();
        builder.append(f);
        builder.append(r);

        return builder.toString();
    }

}
