package views;

import javax.swing.JFrame;

public class Gui {
    private JFrame mainframe;

    public Gui() {
        setMainFrame();
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
    }
}