package code;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RotorSelectionScreen extends UIPages {
    private String text;
    private JComboBox comboBox;
    private JPanel panel;

    public RotorSelectionScreen(String text) {
        this.text = text;
    }

    public JComponent makeRotorSettingsSelectionPanel(int H, int W, ArrayList<String> choices) {
        this.panel = new JPanel(false);
        this.comboBox = new JComboBox(choices.toArray());
        JLabel label = new JLabel(this.text);
        label.setVerticalAlignment(SwingConstants.BOTTOM);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        this.panel.setLayout(new GridLayout(H, W, 100, 100));
        this.panel.add(label);
        this.panel.add(this.comboBox);
        return this.panel;
    }

    public JComboBox getComboBox() {
        return this.comboBox;
    }
}
