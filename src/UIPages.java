import javax.swing.*;
import java.awt.*;

public class UIPages extends JFrame {
    private int WholePageWidth = 1000;
    private int WholePageHeight = 1000;
    private int ButtonWidth = 150;
    private int ButtonHeight = 150;
    private int hGap = 10;
    private int vGap = 10;

    public UIPages(String title){
        super(title);
    }

    public JFrame makeScreen (String title, int rows, int cols){
        JFrame screen = new JFrame(title);
        screen.setMinimumSize(new Dimension(WholePageWidth, WholePageHeight));
        screen.setBounds(500,500, WholePageWidth, WholePageHeight);
        GridLayout layout = new GridLayout(rows,cols,hGap,vGap);
        screen.setLayout(layout);
        return screen;

    }

    public JFrame makeRotorSelectionScreen(String title, int rows, int cols){//Make the rotor selection screen
        JFrame screen = new JFrame(title);
        screen.setMinimumSize(new Dimension(250, 250));
        screen.setBounds(500,500, 250, 500);
        CardLayout layout = new CardLayout(hGap,vGap);
        screen.setLayout(layout);
        return screen;

    }

}
