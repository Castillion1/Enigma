import javax.swing.*;

public class ScreenComboBox <E> extends JComponent  {
    private JComboBox<E> comboBox;
    private int index;
    public ScreenComboBox(JComboBox<E> comboBox, int number ){
        this.comboBox = comboBox;
        this.index = number;
    }

    public JComboBox<E> getComboBox() {
        return comboBox;
    }

    public int getIndex() {
        return index;
    }
}
