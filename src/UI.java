import javax.swing.*;
import java.awt.*;

public class UI extends JFrame {

    private JLabel SecondRotor;
    private JFrame screen;
    private JButton translate;
    private JButton TempButton;
    private JTextField input;
    private int WholePageWidth = 1000;
    private int WholePageHeight = 1000;
    private int ButtonWidth = 150;
    private int ButtonHeight = 150;

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
        this.translate.setLayout(null);
        this.screen.add(this.translate);

        this.TempButton = new JButton("Translate");

        this.screen.add(this.TempButton);


//        this.screen.pack();
        this.screen.setVisible(true);
//        System.out.println("I'm going to try and make a UI");
//        int numberOfRotors = 3;
//        this.ThirdRotor = new JLabel("Rotor Test");
//        JLabel[] labelArray = new JLabel[numberOfRotors - 3];//Why -3?
//        if(numberOfRotors > 3){
//            for (int i = 0; i < (numberOfRotors - 3); i++) {
//                labelArray[i] = new JLabel(Integer.toString(i));
//                TestPanel.add(labelArray[i]);
//            }
//        }
//
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setContentPane(TestPanel);
//        this.pack();
    }

    public void setTheThirdTestLabel(int value) {
        this.SecondRotor.setText(String.valueOf(value));
    }

    public void setTheThirdTestLabel(Character value) {
        this.SecondRotor.setText(Character.toString(value));
    }


}
