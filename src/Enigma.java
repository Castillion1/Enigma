import java.util.ArrayList;

public class Enigma {
    private static UI enigmaUI;
    //    private Reflector reflector = new Reflector("QYHOGNECVPUZTFDJAXWMKISRBL");
//    private Reflector reflector = new Reflector("LEYJVCNIXWPBQMDRTAKZGFUHOS");
    private Reflector reflector = new Reflector("YRUHQSLDPXNGOKMIEBFZCWVJAT");
    //                                                   "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private ArrayList<Rotor> rotors;
    private boolean isInitialized = false;
    private int initialCapacity = 3;

    public Enigma() {
//        SwitchBoardWiring board = SwitchBoard.getInstance();
//        board.setUpTranslation('a','z');
//        board.setUpTranslation('f', 'e');

        ArrayList<Integer> offsets = new ArrayList<>(initialCapacity);
        ArrayList<String> settings = new ArrayList<>(initialCapacity);

        offsets.add(0);
        offsets.add(0);
        offsets.add(0);
//        offsets.add(0);
//        offsets.add(0);
        //stepping in is correct. going back needs some more work to figure out how it got there
        //where to go from a translated value.
        //The paths to complete the translation work in 1 direction but when trying to go the other way
        //they hit faults and this leads to the message not decoding
        //for example, rotor 1, has a D, in the C position
        //might need to chang te whole idea, or might need to figure out how to get a chac

//                     "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
//        settings.add("EKMFLGDQVZNTOWYHXUSPAIBRCJ");//Rotor 0
//                     "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
//        settings.add("AJDKSIRUXBLHWTMCQGZNPYFVOE");//Rotor 1
//                     "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
//        settings.add("BDFHJLCPRTXVZNYEIWGAKMUSQO");//Rotor 2


//                   "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        settings.add("EKMFLGDQVZNTOWYHXUSPAIBRCJ");//Rotor 2
//                   "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        settings.add("AJDKSIRUXBLHWTMCQGZNPYFVOE");//Rotor 1
//                   "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        settings.add("BDFHJLCPRTXVZNYEIWGAKMUSQO");//Rotor 0
//                   "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

//                   "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
//                    YRUHQSLDPXNGOKMIEBFZCWVJAT   //Reflector
//                   "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        setUpRotors(offsets, settings);

//        translate("my name is callum this is a very long bit of text to try and test the code blahhhhhhhhhh general bit of jibberish", true);
        translate("bp mgww rh wnmcel pzpg hh y xhgy kxyt nps ig fkyj up xhk elv slgl gpm oljy qanieptylehes cvkjrzk fnv rz ijffluytb", false);

//        UI tmp = new UI();
//        tmp.makeUI();
    }
    private void resetRotors(){
        for(int i = 0; i < this.rotors.size();i++){
            this.rotors.get(i).reset();
        }
    }

    public static void setEnigmaUI(UI enigmaUI) {
        Enigma.enigmaUI = enigmaUI;
    }

    public void setUpRotors(ArrayList<Integer> offsets, ArrayList<String> Settings) {
        if (offsets.size() != Settings.size()) {
            throw new IllegalArgumentException("There is a problem with the offsets and settings");
        } else {
            this.rotors = new ArrayList<>(Settings.size());
            for (int i = 0; i < offsets.size(); i++) {
                this.rotors.add(i, new Rotor(offsets.get(i), Settings.get(i)));
            }
            this.isInitialized = true; // the rotors have been setup now
        }
    }

    //this function is going to be written to take a string and print out the result, until the UI is done
    public void translate(String ToTranslate, boolean encrypt) {

        if (this.isInitialized == true) {
            char[] characters = null;
            ArrayList<Character> inputCharacters = EnigmaParts.translateStringToArrayList(ToTranslate);
            StringBuilder output = new StringBuilder("");
            for (int j = 0; j < inputCharacters.size(); j++) {
                output.append(letterTranslate(inputCharacters.get(j), encrypt));
                System.out.println();
            }
            System.out.println(output);
        }
    }

    private char letterTranslate(char charToTranslate, boolean encrypt) {
        boolean debug = true;
        rotateRotors();
        if(debug){
            System.out.println("rotor offset = "+this.rotors.get(0).getCurrentOffset());
        }

        if (charToTranslate == ' ') {
            return charToTranslate;
        }

        char local = 0;
        for (int i = 0; i < this.rotors.size(); i++) {
            if (i == 0) {
                if(debug){
                    System.out.println("Char into = " + charToTranslate);
                }
                local = this.rotors.get(i).translateCharacterForward(charToTranslate, encrypt);
            } else {
                if(debug){
                    System.out.println("local before normal = " + local);
                }
                local = this.rotors.get(i).translateCharacterForward(local,encrypt);
            }
            if(debug){
                System.out.println("local after = " + local);
            }
        }
        if(debug){
            System.out.println("Before reflector local = " + local);
        }
        local = this.reflector.translateCharacter(local);
        if(debug){
            System.out.println("local after = " + local);
        }
        for (int j = this.rotors.size()-1; j >= 0; j--) {
            if(debug){
                System.out.println("local = " + local);
            }
            local = this.rotors.get(j).translateCharacterBackwards(local,encrypt);
            if(debug){
                System.out.println("local after = " + local);
            }
        }
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
