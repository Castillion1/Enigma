package code;

import java.util.ArrayList;

public class Rotor extends TranslationContext {
    private ArrayList<Character> rotorSettings;
    private ArrayList<TranslationPair> translations = new ArrayList<>();
    private TranslationContext localContext = new TranslationContext();
    private int currentOffset = 0;
    private final int startingOffset;

    public Rotor(int startingOffset, String rotorSettings) {
        this.startingOffset = startingOffset;
        this.currentOffset = startingOffset;
        this.rotorSettings = translateStringToArrayList(rotorSettings);
        localContext.make(this.rotorSettings);
        setUpTranslations(false);
    }

    //Setup the context's translations
    private void setUpTranslations(boolean twoWay) {
        for (int i = 0; i < alphabet.size(); i++) {
            this.localContext.setUpTranslation(alphabet.get(i), this.rotorSettings.get(i), twoWay);
        }
    }
    //translate before the reflector
    public char translateCharacterForward(char currentCharacter, boolean encrypt) {
        return this.localContext.translateForward(currentCharacter, this.currentOffset, encrypt);
    }
    //translate after the reflector
    public char translateCharacterBackwards(char currentCharacter, boolean encrypt) {
        return this.localContext.translateBackwards(currentCharacter, this.currentOffset, encrypt);
    }

    public ArrayList<Character> getRotorSettings() {
        return this.rotorSettings;
    }

    public int getCurrentOffset() {
        return this.currentOffset;
    }
    //change offset back to startingOffset
    public void reset() {
        this.currentOffset = this.startingOffset;
    }

    //Rotate the rotor
    public boolean rotate() {
        if (this.currentOffset == (alphabet.size() - 1)) {
            this.currentOffset = 0;
            return true;
        }
        this.currentOffset++;
        return false;
    }


}
