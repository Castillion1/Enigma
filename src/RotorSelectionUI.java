import javax.smartcardio.Card;
import javax.swing.*;
import javax.swing.text.TextAction;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RotorSelectionUI extends UIPages {
    private static boolean hasSelectedRotors = false;
    private JFrame screen;
    private JComboBox<Integer> NumberOfRotors;
    private JComboBox<String> name;
    private JLabel DescriptionOfRotorSelection;

    public RotorSelectionUI(){
        super("Please select how many rotors you would like");
    }

    public void make (){
        this.screen = makeRotorSelectionScreen("Rotor Selection", 2, 1);

        this.screen.addWindowListener(new WindowAdapter() {
            /**
             * Invoked when a window is in the process of being closed.
             * The close operation can be overridden at this point.
             *
             * @param e
             */
            @Override
            public void windowClosing(WindowEvent e) {
                Enigma.makeMainUI();
                super.windowClosing(e);
            }

        });

        this.DescriptionOfRotorSelection = new JLabel("Please select how many rotors you would like");
        this.DescriptionOfRotorSelection.setHorizontalAlignment(SwingConstants.LEFT);
        this.DescriptionOfRotorSelection.setVerticalAlignment(SwingConstants.BOTTOM);

//        String[] choices = {"EKMFLGDQVZNTOWYHXUSPAIBRCJ", "AJDKSIRUXBLHWTMCQGZNPYFVOE", "BDFHJLCPRTXVZNYEIWGAKMUSQO" };
        Integer[] choicesOfNumber = {1,2,3,4,5,6,7,8,9,10};
        this.NumberOfRotors = new JComboBox<>(choicesOfNumber);
        this.screen.add(this.NumberOfRotors);
        this.screen.add(this.DescriptionOfRotorSelection);
        this.screen.pack();
        this.screen.setVisible(true);// needs to be the last thing in this class, otherwise causing visual glitches

    }


}
