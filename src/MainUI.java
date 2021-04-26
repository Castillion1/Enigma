//import javax.swing.*;
//import javax.xml.soap.Text;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.lang.reflect.Array;
//import java.util.ArrayList;
//
//public class MainUI extends UIPages {
//
//    private JFrame screen;
//    private JButton translate;
//    private JButton Reset;
//    private int WholePageWidth = 1000;
//    private int WholePageHeight = 1000;
//    private int ButtonWidth = 500;
//    private int ButtonHeight = 150;
//
//    private TextArea InputTextField;
//    private TextArea OutputField;
//    private JToggleButton OutputToConsole;
//    private JToggleButton encryptOrDecrypt;
//
//    public MainUI() {
//        super();
//    }
//
//    public void makeMainUI(){
//        this.screen = makeScreen("Enigma", 5, 1);
//        this.translate = new JButton("Translate");
//
//        this.translate.addActionListener(e -> {
//            String inputText = this.InputTextField.getText();
//            String outputText = Start.getMachine().translate(inputText, this.encryptOrDecrypt.isSelected(), this.OutputToConsole.isSelected());
//            this.OutputField.setText(outputText);
//        });
//
//        this.InputTextField  = new TextArea("Sample text for translation",1,1, 1);
//
//        this.OutputField = new TextArea("Sample Output", 1,1,1);
//
//        this.OutputToConsole = new JToggleButton("Should the machine output to the console?");
//
//        this.encryptOrDecrypt = new JToggleButton("Encrypt");
//
//        this.Reset = new JButton("Reset");
//
//        this.Reset.addActionListener(e -> {
//            Start.getMachine().resetRotors();
//        });
//
//        this.screen.add(this.translate);
//        this.screen.add(this.OutputToConsole);
//        this.screen.add(this.Reset);
//        this.screen.add(this.encryptOrDecrypt);
//        this.screen.add(this.InputTextField);
//        this.screen.add(this.OutputField);
//
//
//        this.screen.setVisible(true);
//
//    }
//
//    public JButton getReset(){
//        return this.Reset;
//    }
//    public TextArea getOutputField(){
//        return this.OutputField;
//    }
//    public TextArea getInputTextField(){
//        return this.InputTextField;
//    }
//    public JToggleButton getOutputToConsole(){
//        return this.OutputToConsole;
//    }
//    public JToggleButton getEncryptOrDecrypt(){
//        return this.encryptOrDecrypt;
//    }
//
//}
