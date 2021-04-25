import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class RotorSelectionUI extends UIPages {
    private static boolean hasSelectedRotors = false;
    private JTabbedPane tabbedPane;
    private JFrame screen;
    private JComponent IntroductionPanel;
    private JComponent NumberOfRotorComponent;
    private JComponent SelectionOfSettings;
    private ArrayList<RotorInput> Rotors = new ArrayList<>();

    public RotorSelectionUI(){
        super();
    }

    public void make (){
        this.screen = new JFrame("Enigma Machine Simulator");//Title for the whole thing
        this.screen.setMinimumSize(new Dimension(500,500));
        this.tabbedPane = makeRotorSelectionScreen("Rotor Selection", 1, 1);
        this.IntroductionPanel = makeTextPanel("This is a test", 1 ,1);//TODO add some introduction text
        ArrayList<String> rotorSettings = readRotorSettings();


        for(int i = 0; i<rotorSettings.size()-1; i++){
            Rotors.add(new RotorInput(rotorSettings.get(i)));
        }
        RotorSelectionScreen rotorSelection = new RotorSelectionScreen("Please select how many rotors you would like to use");
        this.NumberOfRotorComponent = rotorSelection.makeRotorSettingsSelectionPanel(3 ,1, makeUpTo(rotorSettings.size()));

        rotorSelection.getComboBox().addActionListener(e -> {
            if(this.SelectionOfSettings!=null){
                this.screen.remove(this.SelectionOfSettings);
            }
            this.SelectionOfSettings = makeSelectionOfSettings(rotorSelection,"Please select the settings for each rotor");
            if(this.tabbedPane.getTabCount()>0){
                this.tabbedPane.remove(2);
                this.tabbedPane.addTab("Please select the settings for your rotors", SelectionOfSettings);
                this.screen.pack();
            }

        });

        if(rotorSelection.getComboBox()!=null){
            rotorSelection.getComboBox().setSelectedIndex(2);//2 selects the '3' option the default for the enigma machine
        }

        this.tabbedPane.addTab("Introduction", IntroductionPanel);
        this.tabbedPane.addTab("How many rotors would you like to use?", NumberOfRotorComponent);
        this.tabbedPane.addTab("Please select the settings for your rotors", SelectionOfSettings);


        this.tabbedPane.addChangeListener(e ->
                System.out.println("Tab ="+ tabbedPane.getSelectedIndex() + " is selected")
        );

        this.screen.add(this.tabbedPane);
        this.screen.setVisible(true);// needs to be the last thing in this class, otherwise causing visual glitches

    }

    private JComponent makeSelectionOfSettings(RotorSelectionScreen rotorSelection, String text) {
        int W = 1;
        int H = Integer.parseInt(rotorSelection.getComboBox().getItemAt(rotorSelection.getComboBox().getSelectedIndex()).toString());
        ArrayList<String> options = getOptions();
        JPanel panel = new JPanel(false);

        JLabel DescriptionLabel = new JLabel(text);
        panel.add(DescriptionLabel);
        DescriptionLabel.setHorizontalAlignment(JLabel.CENTER);

        panel.setLayout(new GridLayout(H+1,W,10,10));

        panel.add(DescriptionLabel);
        String fillerLabelText = "H";
        panel.add(new JLabel(fillerLabelText));//filler label

        for(int i =0; i<H; i ++){
            int j = i +1;
//            panel.add(new JLabel(fillerLabelText));//filler label
            panel.add(new JLabel("Rotor " + j));
            panel.add(new JComboBox<>(options.toArray()));

        }
        return panel;
    }

    private ArrayList<String> getOptions(){
        ArrayList<String> output = new ArrayList<>();
        for(int i =0; i<this.Rotors.size();i++){
            RotorInput tmp  = this.Rotors.get(i);
            output.add(tmp.getName()+" ," + tmp.getDate() + " ," + tmp.getUsedFor());
        }
        return output;
    }

    private JComponent makeTextPanel(String text, int H, int W){
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(H, W));
        panel.add(filler);
        return panel;

    }

    private ArrayList<String> makeUpTo(int limit){
        ArrayList<String> number = new ArrayList<>();
        for(int i = 1; i <limit; i++){
                number.add(Integer.toString(i));
        }
        return number;
    }



    private ArrayList<String> readRotorSettings(){
        String fullPathMaker = new File(".").getAbsolutePath();
        ArrayList<String> output = new ArrayList<>();
        String name  = fullPathMaker+"\\src\\RotorSetting.txt";
        BufferedReader reader = null;
        String line;
        try{
            File file = new File(name);
            FileReader fileReader = new FileReader(file);
            reader = new BufferedReader(fileReader);
            while((line = reader.readLine()) != null){
                output.add(line);
            }

        }catch(FileNotFoundException e){
            e.printStackTrace();
            System.out.println("There is no file with the name = " + name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    return output;
    }


}
