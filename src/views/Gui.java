package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.*;

public class Gui {
    private JFrame mainframe;

    private JPanel outerBoardPanel;
    private JPanel boardPanel;
    private JPanel optionPanel;
    private JPanel extraPanel;

    public Gui() {
        setMainFrame();
        setPanels();
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

    public static void main(String[] args) {
        Gui gui = new Gui();
        gui.run();
    }
}