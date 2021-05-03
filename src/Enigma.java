import java.util.ArrayList;

public class Enigma {

    private static boolean isInitialized = false;
    private Reflector reflector;
    private ArrayList<Rotor> rotors;
    private TranslationContext board;
    private ArrayList<Integer> offsets;
    private ArrayList<String> settings;

    public Enigma(ArrayList<Integer> offsets, ArrayList<String> settings, ArrayList<Integer> notchPositions, Reflector reflector, TranslationContext board) {

        this.offsets = offsets;
        this.settings = settings;
        this.reflector = reflector;
        this.board = board;
        setUpRotors(offsets, settings, notchPositions);

    }

    public void resetRotors() {
        for (int i = 0; i < this.rotors.size(); i++) {
            this.rotors.get(i).reset();
        }
    }

    public void setUpRotors(ArrayList<Integer> offsets, ArrayList<String> Settings, ArrayList<Integer> notchPositions) {
        if (offsets.size() != Settings.size()) {
            throw new IllegalArgumentException(Text.missMatchWithOffsetsAndSettings);
        } else {
            this.rotors = new ArrayList<>(Settings.size());
            for (int i = 0; i < offsets.size(); i++) {
                this.rotors.add(i, new Rotor(offsets.get(i), Settings.get(i), notchPositions.get(i)));
            }
            isInitialized = true; // the rotors have been setup now
        }
    }

    public String translate(String ToTranslate, boolean encrypt, boolean outputString) {

        if (isInitialized == true) {
            ArrayList<Character> inputCharacters = EnigmaParts.translateStringToArrayList(ToTranslate);
            StringBuilder output = new StringBuilder("");
            for (int j = 0; j < inputCharacters.size(); j++) {
                output.append(letterTranslate(inputCharacters.get(j), encrypt));
            }


            return output.toString();
        }
        return "";//Nothing should be returned.
    }

    private char letterTranslate(char charToTranslate, boolean encrypt) {
        char local;
        rotateRotors();

        local = this.board.translateForward(charToTranslate, 0, true);
        if (charToTranslate == ' ') {
            return charToTranslate;
        }

        for (int i = 0; i < this.rotors.size(); i++) {

            local = this.rotors.get(i).translateCharacterForward(local, encrypt);
        }

        local = this.reflector.translateCharacter(local);

        for (int j = this.rotors.size() - 1; j >= 0; j--) {

            local = this.rotors.get(j).translateCharacterBackwards(local, encrypt);

        }
        local = this.board.translateBackwards(local, 0, true);

        return local;
    }

    private void rotateRotors() {
        for (int i = 0; i < this.rotors.size(); i++) {
            if (!(this.rotors.get(i).rotate())) {
                return;
            }
        }

    }
}
