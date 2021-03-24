import java.util.ArrayList;

public class Enigma {
    private static UI enigmaUI;
    private Reflector reflector = new Reflector("QYHOGNECVPUZTFDJAXWMKISRBL");
    private ArrayList<Rotor> rotors;
    private boolean isInitialized = false;
    public Enigma() {
//        SwitchBoardWiring board = SwitchBoard.getInstance();
//        board.setUpTranslation('a','z');
//        board.setUpTranslation('f', 'e');
//        System.out.println(board.translate('a'));
//        System.out.println(board.translate('c'));
//        System.out.println(board.translate('f'));
//        System.out.println(board.translate('o'));
        ArrayList<Integer> offsets = new ArrayList<>(5);
        ArrayList<String> settings = new ArrayList<>(5);

        offsets.add(0);
        offsets.add(0);
        offsets.add(0);
        offsets.add(0);
        offsets.add(0);

        settings.add("JGDQOXUSCAMIFRVTPNEWKBLZYH");
        settings.add("NTZPSFBOKMWRCJDIVLAEYUXHGQ");
        settings.add("JVIUBHTCDYAKEQZPOSGXNRMWFL");
        settings.add("EKMFLGDQVZNTOWYHXUSPAIBRCJ");
        settings.add("AJDKSIRUXBLHWTMCQGZNPYFVOE");

        setUpRotors(offsets, settings);

        translate("My name is callum");
//        UI tmp = new UI();
//        tmp.makeUI();
//        workOnce();
    }
    //retired function
    //relied on old 3 rotor design
    //
    public void workOnce(){

//        enigmaUI.setVisible(true);
        //Going from right to left and then back, so the right most rotor is the first Rotor
//
//        Character firstValue = firstRotor.translateCharacter('a');//this must be sent through as lower case
//        System.out.println("firstValue = " +firstValue);
//        Character secondValue = secondRotor.translateCharacter(firstValue);
//        System.out.println("secondValue = "+secondValue);
//        Character thirdValue = thirdRotor.translateCharacter(secondValue);
//        System.out.println("thirdValue = "+thirdValue);
//        Character reflectedValue = reflector.translateCharacter(thirdValue);
//        System.out.println("reflectedValue = "+reflectedValue);
//        Character thirdValueRef = thirdRotor.translateCharacter(reflectedValue);
//        System.out.println("thirdValueRef = "+thirdValueRef);
//        Character secondValueRef = secondRotor.translateCharacter(thirdValueRef);
//        System.out.println("secondValueRef = "+secondValueRef);
//        Character firstValueRef = firstRotor.translateCharacter(secondValueRef);
//        System.out.println("firstValueRef = "+firstValueRef);
//        enigmaUI.setTheThirdTestLabel(firstValueRef);

    }

    public void setUpRotors(ArrayList<Integer> offsets,ArrayList<String> Settings ){
        if(offsets.size() != Settings.size()){
            throw new IllegalArgumentException("There is a problem with the offsets and settings");
        }else{
            this.rotors = new ArrayList<>(Settings.size());
            for(int i =0; i< offsets.size(); i++){
                this.rotors.add(i,new Rotor(offsets.get(i), Settings.get(i)));
            }
            this.isInitialized = true; // the rotors have been setup now
        }
    }
    //this function is going to be written to take a string and print out the result, until the UI is done
    public void translate(String ToTranslate){

        if(this.isInitialized==true){
            char[] characters = null;
            System.out.println(ToTranslate);
            ArrayList<Character> inputCharacters = EnigmaParts.translateStringToArrayList(ToTranslate);
            System.out.println(inputCharacters);
            ArrayList<Character> outputCharacters = new ArrayList<>(inputCharacters.size());
            for(int j=0; j<inputCharacters.size();j++){
                letterTranslate(inputCharacters.get(j));
            }
        }
    }

    private char letterTranslate(char charToTranslate){
        for(int i=0;i<this.rotors.size();i++){
            char local = 0;
            if(i==0){
                local = this.rotors.get(i).translateCharacter(charToTranslate);
            }else{
                local = this.rotors.get(i).translateCharacter(local);
            }

        }
        return 'a';
    }

    public static void setEnigmaUI(UI enigmaUI) {
        Enigma.enigmaUI = enigmaUI;
    }
}
