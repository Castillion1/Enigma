import javax.swing.*;
import java.awt.*;

public abstract class UIPages extends JFrame {
    private int WholePageWidth = 1000;
    private int WholePageHeight = 1000;
    private int ButtonWidth = 150;
    private int ButtonHeight = 150;
    protected int hGap = 10;
    protected int vGap = 10;

    public UIPages(){
        super("");//Not used
    }

    public JFrame makeScreen (String title, int rows, int cols){
        JFrame screen = new JFrame(title);
        screen.setMinimumSize(new Dimension(WholePageWidth, WholePageHeight));
        screen.setBounds(500,500, WholePageWidth, WholePageHeight);
        GridLayout layout = new GridLayout(rows,cols,hGap,vGap);
        screen.setLayout(layout);
        return screen;

    }

    public JTabbedPane makeRotorSelectionScreen(String title, int rows, int cols){//Make the rotor selection screen
        JTabbedPane screen = new JTabbedPane();
        screen.setMinimumSize(new Dimension(250, 250));
        screen.setBounds(500,500, 250, 500);
        return screen;

    }

}
