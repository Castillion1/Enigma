import java.util.ArrayList;

public class Rotor extends  TranslationContext {
    private ArrayList<Character> rotorSettings = new ArrayList<>();
    private ArrayList<TranslationPair> translations= new ArrayList<>();
    private TranslationContext localContext = new TranslationContext();
    private int currentOffset = 0;
    private int startingOffset;
    private int notchPosition = 0;

    public Rotor(int startingOffset, String rotorSettings, int notchPosition){
        this.startingOffset = startingOffset;
        this.currentOffset = startingOffset;
        this.rotorSettings = translateStringToArrayList(rotorSettings);
        this.notchPosition = notchPosition;
        localContext.make(this.rotorSettings);
        setUpTranslations(false);
//        testTranslations();
    }
    /*
    In theory the method behind this will be as follows
    The Rotor order is the important part, the current value that is shown
     is done via the currentOffset
     To translate one character it needs to provide the value, that it shall translate
     A user gives it a value that needs to be translated.
     The code will then find out what value that is in the static array and then will use that number to find
     its translation. once this is done the currentOffset will increment by 1 to simulate the turning of a rotor
     This is going to be actually done via translation pairs. To link each letter to another.

     */
    private void testTranslations(){
        for(int i =0; i < alphabet.size(); i++){
            Character local = localContext.translateForward(alphabet.get(i),this.currentOffset, true);
        }
    }
    private void setUpTranslations(boolean twoWay){
        for(int i =0; i < alphabet.size(); i++){
            this.localContext.setUpTranslation(alphabet.get(i),this.rotorSettings.get(i), twoWay);
        }
    }

    public char translateCharacterForward(char currentCharacter, boolean encrypt){
        return this.localContext.translateForward(currentCharacter, this.currentOffset, encrypt);
    }

    public char translateCharacterBackwards(char currentCharacter, boolean encrypt){
        return this.localContext.translateBackwards(currentCharacter, this.currentOffset, encrypt);
    }

    public ArrayList<Character> getRotorSettings(){
        return this.rotorSettings;
    }

    public int getCurrentOffset(){
        return this.currentOffset;
    }
    public void reset(){
        this.currentOffset = this.startingOffset;
    }
    //Rotate the rotor
    public boolean rotate(){
        if(this.currentOffset == (alphabet.size()-1)){
            this.currentOffset = 0;
            return true;
        }
        this.currentOffset ++;
        return false;
    }



}
