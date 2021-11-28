package views.board;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import models.Square;

public class Cell extends JPanel implements MouseInputListener {

    private Square square;
    private BoardPanel panel;
    private Color defaultColor;
    private static final Color highlightedColor = new Color(220, 240, 239);
    private boolean highlighted;

    public Cell(BoardPanel panel, Square square) {
        this.square = square;
        this.panel = panel;

        setCell();
    }

    private void setCell() {
        int rank = square.getRank();
        int file = square.getFile();

        defaultColor = ((rank + file) % 2 == 0) ? BoardPanel.BLACK_COLOR : BoardPanel.WHITE_COLOR;
        setBackground(defaultColor);
        highlighted = false;

        addImage();

        addMouseListener(this);

    }

    private void addImage() {
        if (this.square.isOccupied()) {
            String complete_url = "assets/" + square.getPiece().getImgUrl() + ".png";
            ImageIcon icon = new ImageIcon(complete_url);
            JLabel image_label = new JLabel(icon);
            add(image_label);
        }
    }

    public void toggleHighlight() {
        if (highlighted) {
            setBackground(defaultColor);
            highlighted = false;
        } else {
            setBackground(highlightedColor);
            highlighted = true;
        }
    }

    public Square getSquare() {
        return this.square;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Select/deselect square OR create move
        panel.clickedCell(this);
        // toggleHighlight();
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
        setBorder(BorderFactory.createLineBorder(Color.RED));
        // Highlight the square
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setBorder(null);
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

    @Override
    public boolean equals(Object cellObj) {
        if (!(cellObj instanceof Cell))
            return false;
        Cell cell = (Cell) cellObj;
        return this.square.equals(cell.square);
    }

}
