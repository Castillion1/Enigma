import java.util.ArrayList;

public class Enigma {

    private static boolean isInitialized = false;
    //    private static RotorSelectionUI rotorSelectionUI = new RotorSelectionUI();
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
        System.out.println(this.settings);
        setUpRotors(offsets, settings, notchPositions);

    }

    public void resetRotors() {
        for (int i = 0; i < this.rotors.size(); i++) {
            this.rotors.get(i).reset();
        }
    }

    public void setUpRotors(ArrayList<Integer> offsets, ArrayList<String> Settings, ArrayList<Integer> notchPositions) {
        if (offsets.size() != Settings.size()) {
            throw new IllegalArgumentException("There is a problem with the offsets and settings");
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
        boolean debug = true;
        char local;
        rotateRotors();
        if (debug) {
            System.out.println("char to Translate =" + charToTranslate);
        }
        local = this.board.translateForward(charToTranslate, 0, true);

        if (debug) {
            System.out.println("After board =" + charToTranslate);
            System.out.println("rotor offset = " + this.rotors.get(0).getCurrentOffset());
        }

        if (charToTranslate == ' ') {
            return charToTranslate;
        }


        for (int i = 0; i < this.rotors.size(); i++) {
            if (debug) {
                System.out.println("local before normal = " + local);
            }
            local = this.rotors.get(i).translateCharacterForward(local, encrypt);

            if (debug) {
                System.out.println("local after = " + local);
            }
        }
        if (debug) {
            System.out.println("Before reflector local = " + local);
        }
        local = this.reflector.translateCharacter(local);
        if (debug) {
            System.out.println("local after = " + local);
        }
        for (int j = this.rotors.size() - 1; j >= 0; j--) {
            if (debug) {
                System.out.println("local = " + local);
            }
            local = this.rotors.get(j).translateCharacterBackwards(local, encrypt);
            if (debug) {
                System.out.println("local after = " + local);
            }
        }
        local = this.board.translateBackwards(local, 0, true);
        System.out.println();
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
