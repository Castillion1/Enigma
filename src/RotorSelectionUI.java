import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class RotorSelectionUI extends UIPages {
    private static boolean hasSelectedRotors = false;
    private JTabbedPane tabbedPane;
    private JFrame screen;
    private JComponent IntroductionPanel;
    private JComponent NumberOfRotorComponent;
    private JComponent SelectionOfSettings;
    private JComponent switchBoardTab;
    private JComponent encryptTab;
    private JComponent reflectorTab;
    private TextArea switchBoardInput;
    private TextArea encryptInput;
    private TextArea encryptOutput;
    private JButton encrypt;
    private ArrayList<FileInput> rotors = new ArrayList<>();
    private ArrayList<ScreenComboBox> rotorSettings = new ArrayList<>();
    private ArrayList<ScreenComboBox> rotorOffsets = new ArrayList<>();
    private JComboBox reflectorJComboBox;
    private RotorSelectionScreen rotorSelection;


    public RotorSelectionUI(){
        super();
    }

    public void make(){
        this.screen = new JFrame("Enigma Machine Simulator");//Title for the whole thing
        this.screen.setMinimumSize(new Dimension(750,500));
        this.tabbedPane = makeRotorSelectionScreen("Rotor Selection", 1, 1);
        this.IntroductionPanel = makeTextPanel("<html><p>This is an Engima machine simulator, it does allow you to" +
                " reconstruct the initial machine using 3 rotors but it also allows you to use as many rotors as you like" +
                " and pair rotors from different machines up</p></html>", 1 ,1);//TODO improve introduction text

        makeSelectionOfSettingsStarter();
        makeSwitchBoardTab();
        makeEncryptTab();
        makeReflectorTab();
        if(this.rotorSelection.getComboBox()!=null){
            this.rotorSelection.getComboBox().setSelectedIndex(2);//2 selects the '3' option the default for the enigma machine
        }

        this.tabbedPane.addTab("Introduction", this.IntroductionPanel);
        this.tabbedPane.addTab("How many rotors would you like to use?", this.NumberOfRotorComponent);
        this.tabbedPane.addTab("Please select the settings", this.SelectionOfSettings);
        this.tabbedPane.addTab("Switch board set-up", this.switchBoardTab);
        this.tabbedPane.addTab("Choose a reflector", this.reflectorTab);
        this.tabbedPane.addTab("Encrypt a message",this.encryptTab);
        this.tabbedPane.addChangeListener(e ->
                System.out.println("Tab ="+ this.tabbedPane.getSelectedIndex() + " is selected")
        );

        this.screen.add(this.tabbedPane);
        this.screen.setVisible(true);// needs to be the last thing in this class, otherwise causing visual glitches

    }
    private void makeEncryptTab(){
        this.encryptTab = new JPanel(false);
        this.encryptTab.setLayout(new GridLayout(5,1));
        JLabel EncryptMessage = new JLabel("<html><p>Please enter a message </p></html>");
        EncryptMessage.setVerticalAlignment(SwingConstants.BOTTOM);
        EncryptMessage.setHorizontalAlignment(SwingConstants.LEFT);
        this.encryptTab.add(EncryptMessage);
        this.encryptInput = new TextArea();
        this.encryptTab.add(this.encryptInput);
        JLabel OutputLabel =  new JLabel("Output: ");
        OutputLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        OutputLabel.setHorizontalAlignment(SwingConstants.LEFT);
        this.encryptTab.add(OutputLabel);
        this.encryptOutput = new TextArea();
        this.encryptTab.add(this.encryptOutput);
        this.encrypt = new JButton("Encrypt");
        this.encrypt.addActionListener(e ->{
            ArrayList<Integer> localOffsets = new ArrayList<>();
            for(int i=0; i<rotorOffsets.size();i++){
                localOffsets.add((Integer) rotorOffsets.get(i).getComboBox().getSelectedItem());
                System.out.println("rotor offsets? ="+rotorOffsets.get(i).getComboBox().getSelectedItem());
            }
            ArrayList<String> localSettings = new ArrayList<>();
            for(int k=0; k<rotorSettings.size();k++){
                String localCompare = (String) rotorSettings.get(k).getComboBox().getSelectedItem();
                localCompare = localCompare.replaceAll("\\s+", "");
                String[] localCompareArray =localCompare.split(",");
                String localSetting = localCompareArray[2];
                localSettings.add(localSetting);

            }

            ArrayList<Integer> notchPostions = new ArrayList<>();//TODO??? do i care about this
            for(int l =0; l<26; l++){
                notchPostions.add(0);
            }
            String tmp = (String) this.reflectorJComboBox.getSelectedItem();
            tmp =tmp.replaceAll("\\s+", "");
            String[] splitPart = tmp.split(",");
            Reflector localReflector = new Reflector(splitPart[1]);
            System.out.println(SwitchBoard.getInstance().getSettings());
            Enigma machine = new Enigma(localOffsets, localSettings,notchPostions,localReflector ,SwitchBoard.getInstance());
            this.encryptOutput.setText(machine.translate(this.encryptInput.getText(),true,false));
        });
        this.encryptTab.add(this.encrypt);

    }
    private void makeReflectorTab(){
        this.reflectorTab = new JPanel(false);
        this.reflectorTab.setLayout(new GridLayout(3,1));
        JLabel descriptionLabel = new JLabel("<html><p>Please select your rotor</p></html>");
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.reflectorTab.add(descriptionLabel);
        ArrayList<String> reflectorSettings = readFile(false);
        this.reflectorJComboBox= new JComboBox(reflectorSettings.toArray());

        this.reflectorTab.add(reflectorJComboBox);
        this.reflectorTab.add(new JLabel());
    }


    private void makeSwitchBoardTab(){
        this.switchBoardTab = new JPanel(false);
        this.switchBoardTab.setLayout(new GridLayout(3,1));
        this.switchBoardTab.add(new JLabel("<html><p>Please enter your switchboard settings, this can be done by simply entering" +
                ", for example AB CD will then link A to B and so on, vice versa.</p></html>"));
        this.switchBoardInput = new TextArea();
        JButton confirmButton = new JButton("Confirm rotor settings");
        confirmButton.addActionListener(e -> {
            if(checkSwitchBoardInput(switchBoardInput.getText())){
                setUpSwitchBoard(switchBoardInput.getText());
            }
        });

        this.switchBoardTab.add(switchBoardInput);
        this.switchBoardTab.add(confirmButton);

    }

    private void setUpSwitchBoard(String input){
        input = input.toLowerCase();
        String[] inputArray = input.split(" ");
        TranslationContext switchBoard = SwitchBoard.getInstance();
        for(int i =0; i<inputArray.length; i++){
            String[] splitChars = inputArray[i].split("");
            Character firstChar = splitChars[0].charAt(0);
            Character secondChar = splitChars[1].charAt(0);
            System.out.println();
            switchBoard.setUpTranslation(firstChar, secondChar, true);
        }

    }

    private boolean checkSwitchBoardInput(String input){
        input = input.replaceAll("\\s+", "");
        input = input.toLowerCase();
        char[] inputChar = input.toCharArray();
        Arrays.sort(inputChar);
        String sorted = new String(inputChar);
        String[] sortedArray = sorted.split("");
        ArrayList<String> sortedArrayList = new ArrayList<String>(Arrays.asList(sortedArray));//sorted into the right order
        for(int i =0; i < sortedArrayList.size()-1; i++){
            if(sortedArrayList.get(i).equals(sortedArrayList.get(i+1))){
                return false;
            }
        }
        return true;

    }

    private void makeSelectionOfSettingsStarter(){
        ArrayList<String> rotorSettings = readFile(true);

        for(int i = 0; i<rotorSettings.size()-1; i++){
            rotors.add(new FileInput(rotorSettings.get(i)));
        }

        this.rotorSelection = new RotorSelectionScreen("Please select how many rotors you would like to use");
        this.NumberOfRotorComponent = rotorSelection.makeRotorSettingsSelectionPanel(3 ,1, makeUpTo(rotorSettings.size()));

        rotorSelection.getComboBox().addActionListener(e -> {
            if(this.SelectionOfSettings!=null){
                this.screen.remove(this.SelectionOfSettings);
            }
            this.SelectionOfSettings = makeSelectionOfSettings(rotorSelection,"Please select the settings");
            if(this.tabbedPane.getTabCount()>0){
                this.tabbedPane.remove(2);
                this.tabbedPane.insertTab("Please select the settings",null, SelectionOfSettings,null, 2);

//                this.screen.pack();
            }

        });
    }

    private JComponent makeSelectionOfSettings(RotorSelectionScreen rotorSelection, String text) {
        int W = 4;
        int H = Integer.parseInt(rotorSelection.getComboBox().getItemAt(rotorSelection.getComboBox().getSelectedIndex()).toString());
        this.rotorSettings.clear();
        this.rotorOffsets.clear();
        ArrayList<String> options = getOptions();
        JPanel panel = new JPanel(false);
        Integer[] listOfOffsets = new Integer[26];
        for(int i = 0; i< listOfOffsets.length; i++){
            listOfOffsets[i] = i;
        }
        JLabel DescriptionLabel = new JLabel(text);
        panel.add(DescriptionLabel);
        DescriptionLabel.setHorizontalAlignment(JLabel.CENTER);

        panel.setLayout(new GridLayout(H+1,W,10,10));

        panel.add(DescriptionLabel);
        String fillerLabelText = ""; //helps debug //TODO remove later
        for(int j = 0; j<W-1; j++){
            panel.add(new JLabel(fillerLabelText));//filler label
        }

        for(int i =0; i<H; i ++){
            int j = i +1;
            panel.add(new JLabel("Rotor: " + j));
            JComboBox currentSetting = new JComboBox(options.toArray());
            int localSearch = 0;

            this.rotorSettings.add(new ScreenComboBox(currentSetting,i));
            panel.add(currentSetting);

            JLabel offsetLabel = new JLabel("Rotor Offset: ");
            offsetLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panel.add(offsetLabel);

            JComboBox currentOffset = new JComboBox(listOfOffsets);
            this.rotorOffsets.add(new ScreenComboBox(currentOffset, i));
            panel.add(currentOffset);

        }
        return panel;
    }

    private ArrayList<String> getOptions(){
        ArrayList<String> output = new ArrayList<>();
        for(int i =0; i<this.rotors.size();i++){
            FileInput tmp  = this.rotors.get(i);
            output.add(tmp.getName()+" ," + tmp.getUsedFor() + " ," + tmp.getSetting());
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

    private ArrayList<String> readFile(boolean rotor){
        String fullPathMaker = new File(".").getAbsolutePath();
        ArrayList<String> output = new ArrayList<>();
        String name;
        if(rotor){
            name  = fullPathMaker+"\\src\\RotorSetting.txt";
        }else{
            name = fullPathMaker+"\\src\\ReflectorSettings.txt";
        }

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
