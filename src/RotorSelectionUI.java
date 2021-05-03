import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class RotorSelectionUI extends UIPages {
    private JTabbedPane tabbedPane;
    private JFrame screen;
    private JComponent IntroductionPanel;
    private JComponent NumberOfRotorComponent;
    private JComponent SelectionOfSettings;
    private JComponent switchBoardTab;
    private JComponent encryptTab;
    private JComponent reflectorTab;
    private JComponent decryptTab;
    private TextArea switchBoardInput;
    private TextArea encryptInput;
    private TextArea encryptOutput;
    private TextArea decryptInput;
    private TextArea decryptOutput;
    private JButton encrypt;
    private JButton decrypt;
    private ArrayList<FileInput> rotors = new ArrayList<>();
    private ArrayList<ScreenComboBox> rotorSettings = new ArrayList<>();
    private ArrayList<ScreenComboBox> rotorOffsets = new ArrayList<>();
    private JComboBox reflectorJComboBox;
    private RotorSelectionScreen rotorSelection;


    public RotorSelectionUI(){
        super();
    }

    //starts the whole UI off, calling this will make the whole UI.
    public void make(){
        this.screen = new JFrame(Text.title);
        this.screen.setMinimumSize(new Dimension(750,500));
        this.tabbedPane = makeRotorSelectionScreen(Text.rotorSelection, 1, 1);
        this.IntroductionPanel = makeTextPanel(Text.introductionText, 1 ,1);

        makeSelectionOfSettingsStarter();//each tab has it's own function
        makeSwitchBoardTab();
        makeEncryptTab();
        makeReflectorTab();
        makeDecryptTab();

        if(this.rotorSelection.getComboBox()!=null){
            this.rotorSelection.getComboBox().setSelectedIndex(2);//2 selects the '3' option the default for the enigma machine
        }

        this.tabbedPane.addTab(Text.introductionTitle, this.IntroductionPanel);
        this.tabbedPane.addTab(Text.numberOfRotorsTitle, this.NumberOfRotorComponent);
        this.tabbedPane.addTab(Text.selectionOfSettingsTitle, this.SelectionOfSettings);
        this.tabbedPane.addTab(Text.switchBoardTitle, this.switchBoardTab);
        this.tabbedPane.addTab(Text.reflectorTitle, this.reflectorTab);
        this.tabbedPane.addTab(Text.encryptTitle,this.encryptTab);
        this.tabbedPane.addTab(Text.decryptTitle, this.decryptTab);

        this.screen.add(this.tabbedPane);
        this.screen.setVisible(true);// needs to be the last thing in this class, otherwise causing visual glitches

    }
    //make the Decrypt Tab
    private void makeDecryptTab(){
        this.decryptTab = new JPanel(false);
        this.decryptTab.setLayout(new GridLayout(5,1));
        JLabel EncryptMessage = new JLabel(Text.enterAMessage);
        EncryptMessage.setVerticalAlignment(SwingConstants.BOTTOM);
        EncryptMessage.setHorizontalAlignment(SwingConstants.LEFT);
        this.decryptTab.add(EncryptMessage);

        this.decryptInput = new TextArea();
        this.decryptTab.add(this.decryptInput);
        JLabel OutputLabel =  new JLabel(Text.output);
        OutputLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        OutputLabel.setHorizontalAlignment(SwingConstants.LEFT);
        this.decryptTab.add(OutputLabel);

        this.decryptOutput = new TextArea();
        this.decryptTab.add(this.decryptOutput);
        this.decrypt = new JButton(Text.decrypt);
//        this.decryptTab.add(this.decrypt);
        this.decrypt = addListener(this.decrypt, false);
        this.decryptTab.add(this.decrypt);
    }
    //make the Encrypt Tab
    private void makeEncryptTab(){
        this.encryptTab = new JPanel(false);
        this.encryptTab.setLayout(new GridLayout(5,1));
        JLabel EncryptMessage = new JLabel(Text.enterAMessage);
        EncryptMessage.setVerticalAlignment(SwingConstants.BOTTOM);
        EncryptMessage.setHorizontalAlignment(SwingConstants.LEFT);
        this.encryptTab.add(EncryptMessage);

        this.encryptInput = new TextArea();
        this.encryptTab.add(this.encryptInput);
        JLabel OutputLabel =  new JLabel(Text.output);
        OutputLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        OutputLabel.setHorizontalAlignment(SwingConstants.LEFT);
        this.encryptTab.add(OutputLabel);

        this.encryptOutput = new TextArea();
        this.encryptTab.add(this.encryptOutput);
        this.encrypt = new JButton(Text.encrypt);
        this.encrypt = addListener(this.encrypt, true);//needs to encrypt the message so true
        this.encryptTab.add(this.encrypt);

    }
    private JButton addListener(JButton button, boolean toEncrypt){
        button.addActionListener(e ->{
            ArrayList<Integer> localOffsets = new ArrayList<>();
            for(int i=0; i<rotorOffsets.size();i++){
                localOffsets.add((Integer) rotorOffsets.get(i).getComboBox().getSelectedItem());
            }

            ArrayList<String> localSettings = new ArrayList<>();
            for(int k=0; k<rotorSettings.size();k++){
                String localCompare = (String) rotorSettings.get(k).getComboBox().getSelectedItem();
                localCompare = localCompare.replaceAll("\\s+", "");
                String[] localCompareArray =localCompare.split(",");
                String localSetting = localCompareArray[2];
                localSettings.add(localSetting);
            }

            ArrayList<Integer> notchPostions = new ArrayList<>();
            for(int l =0; l<26; l++){
                notchPostions.add(0);
            }

            String tmp = (String) this.reflectorJComboBox.getSelectedItem();
            tmp =tmp.replaceAll("\\s+", "");
            String[] splitPart = tmp.split(",");
            Reflector localReflector = new Reflector(splitPart[1]);
            Enigma machine = new Enigma(localOffsets, localSettings,notchPostions,localReflector ,SwitchBoard.getInstance());
            if(toEncrypt){
                this.encryptOutput.setText(machine.translate(this.encryptInput.getText(),true,false));
            }else{
                this.decryptOutput.setText(machine.translate(this.decryptInput.getText(),false,false));
            }

        });
        return button;
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
        this.switchBoardTab.add(new JLabel(Text.switchBoardText));
        this.switchBoardInput = new TextArea();
        JButton confirmButton = new JButton(Text.confirmRotorSettings);
        confirmButton.addActionListener(e -> {
        if(!(switchBoardInput.getText().equals(""))){
            SwitchBoard.getInstance().emptyTranslations();
            if(checkSwitchBoardInput(switchBoardInput.getText())){
                setUpSwitchBoard(switchBoardInput.getText());
                JFrame frame = new JFrame(Text.settingsHaveBeenConfirmed);
                frame.setMinimumSize(new Dimension(300,200));
                frame.add(new JLabel(Text.settingsHaveBeenConfirmed));
                frame.setVisible(true);
            }else{
                JFrame frame = new JFrame(Text.issueWithSettings);
                frame.setMinimumSize(new Dimension(300,200));
                frame.add(new JLabel(Text.issueWithSettings));
                frame.setVisible(true);
            }
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

        this.rotorSelection = new RotorSelectionScreen(Text.numberOfRotorsText);
        this.NumberOfRotorComponent = rotorSelection.makeRotorSettingsSelectionPanel(3 ,1, makeUpTo(rotorSettings.size()));

        rotorSelection.getComboBox().addActionListener(e -> {
            if(this.SelectionOfSettings!=null){
                this.screen.remove(this.SelectionOfSettings);
            }
            this.SelectionOfSettings = makeSelectionOfSettings(rotorSelection,Text.selectionOfSettings);
            if(this.tabbedPane.getTabCount()>0){
                this.tabbedPane.remove(2);
                this.tabbedPane.insertTab(Text.selectionOfSettings,null, SelectionOfSettings,null, 2);

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
        Integer[] listOfOffsets = new Integer[25];
        for(int i = 0; i< listOfOffsets.length; i++){
            listOfOffsets[i] = i;
        }
        JLabel DescriptionLabel = new JLabel(text);
        panel.add(DescriptionLabel);
        DescriptionLabel.setHorizontalAlignment(JLabel.CENTER);

        panel.setLayout(new GridLayout(H+1,W,10,10));

        panel.add(DescriptionLabel);
        String fillerLabelText = ""; //helps debug //
        for(int j = 0; j<W-1; j++){
            panel.add(new JLabel(fillerLabelText));//filler label
        }

        for(int i =0; i<H; i ++){
            int j = i +1;
            panel.add(new JLabel(Text.rotor + j));
            JComboBox currentSetting = new JComboBox(options.toArray());

            this.rotorSettings.add(new ScreenComboBox(currentSetting,i));
            panel.add(currentSetting);

            JLabel offsetLabel = new JLabel(Text.rotorOffset);
            offsetLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panel.add(offsetLabel);

            JComboBox currentOffset = new JComboBox(listOfOffsets);
            currentOffset.setSelectedIndex(0);
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
            name  = fullPathMaker+Text.pathToRotors;
        }else{
            name = fullPathMaker+Text.pathToReflectors;
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    return output;
    }


}
