package views;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.Updater;
import models.Move;
import models.Square;
import network.Client;
import views.board.BoardPanel;

// import javax.swing.*;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.*;

public class Gui {
    private JFrame mainframe;
    private JFrame connectionFrame;

    private JPanel outerBoardPanel;
    private BoardPanel boardPanel;
    private JPanel optionPanel;
    private JPanel extraPanel;

    // private Updater updater;

    public Gui() {
        setMainFrame();
        setPanels();
    }

    public Gui(Updater updater) {
        // this.updater = updater;

        setMainFrame();
        setPanels();

        // For debugging
        Client.connect("10.60.19.56", 5000, updater);
    }

    public void run() {
        mainframe.setVisible(true);
    }

    private void setMainFrame() {
        mainframe = new JFrame();
        mainframe.setTitle("Chess");
        mainframe.setSize(1000, 750);
        mainframe.setLocationRelativeTo(null);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setLayout(new GridBagLayout());
    }

    private void setPanels() {
        GridBagConstraints c = new GridBagConstraints();

        optionPanel = new JPanel();
        // optionPanel.setLayout();
        optionPanel.setBackground(Color.BLACK);

        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = 0;
        c.gridheight = 1;
        c.weightx = 0.7;
        c.weighty = 0.2;
        c.fill = GridBagConstraints.BOTH;

        mainframe.add(optionPanel, c);

        c = new GridBagConstraints();

        outerBoardPanel = new JPanel();
        outerBoardPanel.setLayout(new GridBagLayout());
        outerBoardPanel.setBackground(Color.RED);

        c.gridx = 0;
        c.gridwidth = 1;
        c.gridy = 1;
        c.gridheight = 1;
        c.weightx = 0.7;
        c.weighty = 0.9;
        c.fill = GridBagConstraints.BOTH;

        // setBoardPanel();

        mainframe.add(outerBoardPanel, c);

        c = new GridBagConstraints();

        extraPanel = new JPanel();
        // extraPanel.setLayout();
        extraPanel.setBackground(Color.BLUE);

        c.gridx = 1;
        c.gridwidth = 1;
        c.gridy = 1;
        c.gridheight = 2;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 0.9;

        mainframe.add(extraPanel, c);
    }

    // Updater class calls this method to set the state of the gui
    public void setState(Updater updater, Square[][] board) {
        // Remove existing boardPanel if there is one and replace is with a new one
        outerBoardPanel.removeAll();
        boardPanel = new BoardPanel(outerBoardPanel, updater, board);
        outerBoardPanel.add(boardPanel);
        outerBoardPanel.repaint();
        outerBoardPanel.revalidate();
    }

    // This method resets all the highlighted squares, etc. in the board panel class
    public void resetClicks() {
        boardPanel.resetClicks();
    }

    public void addMove(Move move) {
        // TODO Add move to the move list
    }

    public void startConnectionWaiting() {
        connectionFrame = new JFrame();
        connectionFrame.setLocationRelativeTo(null);
        connectionFrame.setAlwaysOnTop(true);
        connectionFrame.setSize(200, 200);
        JLabel connectionLabel = new JLabel();
        connectionLabel.setText("AWAITING CONNECTION");
        connectionFrame.getContentPane().add(connectionLabel);
        connectionFrame.setVisible(true);
    }

    public void endConnectionWaiting() {
        connectionFrame.setVisible(false);
    }

}