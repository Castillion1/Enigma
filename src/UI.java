import javax.swing.*;
import java.awt.*;

public class UI extends JFrame {

    private JLabel SecondRotor;
    private JFrame screen;
    private JButton translate;
    private JTextField input;
    private int WholePageWidth = 1000;
    private int WholePageHeight = 1000;
    private int ButtonWidth = 150;
    private int ButtonHeight = 150;
    private TextArea InputTextField;
    private JToggleButton encryptOrDecrypt;

    public UI() {
        super("Test Title");
    }

    public void makeUI(){
        this.screen = new JFrame("Enigma");
        this.screen.setMinimumSize(new Dimension(WholePageWidth, WholePageHeight));
        this.screen.setBounds(500,500, WholePageWidth, WholePageHeight);
        GridLayout Layout = new GridLayout(3,5,100,100);

        this.screen.setLayout(Layout);
        this.translate = new JButton("Translate");
        EnigmaListener listener = new EnigmaListener();
        this.translate.addActionListener(listener);
        this.translate.setLayout(null);
        this.screen.add(this.translate);

//        this.TempButton = new JButton("Translate");

//        this.screen.add(this.TempButton);
        this.InputTextField  = new TextArea("Sample text for translation",1,1, 1);

        this.screen.add(InputTextField);

        this.encryptOrDecrypt = new JToggleButton("Encrypt");
        this.screen.add(this.encryptOrDecrypt);
        this.screen.setVisible(true);

    }

    public TextArea getInputTextField(){
        return this.InputTextField;
    }

    public boolean getEncryptOrDecrypt(){
        return this.encryptOrDecrypt.isSelected();
    }

    public void setTheThirdTestLabel(int value) {
        this.SecondRotor.setText(String.valueOf(value));
    }

    public void setTheThirdTestLabel(Character value) {
        this.SecondRotor.setText(Character.toString(value));
    }


}
