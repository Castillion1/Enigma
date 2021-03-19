import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Enigma {
    private static UI enigmaUI;
    private Rotor firstRotor;
    private Rotor secondRotor;
    private Rotor thirdRotor;
    private boolean isInitialized = false;
    public Enigma() {
        SwitchBoardWiring board = SwitchBoard.getInstance();
        board.setUpTranslation('a','z');
        board.setUpTranslation('f', 'e');
        System.out.println(board.translate('a'));
        System.out.println(board.translate('c'));
        System.out.println(board.translate('f'));
        System.out.println(board.translate('o'));
//        UI tmp = new UI();
//        tmp.makeUI();
        workOnce();
    }

    public void workOnce(){

//        enigmaUI.setVisible(true);
        //I am going from right to left, so the right most rotor is the first Rotor
        Rotor firstRotor = new Rotor(0, "JGDQOXUSCAMIFRVTPNEWKBLZYH");
        Rotor secondRotor = new Rotor(0, "NTZPSFBOKMWRCJDIVLAEYUXHGQ");
        Rotor thirdRotor = new Rotor(0,"JVIUBHTCDYAKEQZPOSGXNRMWFL");
        Reflector reflector = new Reflector("QYHOGNECVPUZTFDJAXWMKISRBL");
        Character firstValue = firstRotor.translateCharacter('a');//this must be sent through as lower case
        System.out.println("firstValue = " +firstValue);
        Character secondValue = secondRotor.translateCharacter(firstValue);
        System.out.println("secondValue = "+secondValue);
        Character thirdValue = thirdRotor.translateCharacter(secondValue);
        System.out.println("thirdValue = "+thirdValue);
        Character reflectedValue = reflector.translateCharacter(thirdValue);
        System.out.println("reflectedValue = "+reflectedValue);
        Character thirdValueRef = thirdRotor.translateCharacter(reflectedValue);
        System.out.println("thirdValueRef = "+thirdValueRef);
        Character secondValueRef = secondRotor.translateCharacter(thirdValueRef);
        System.out.println("secondValueRef = "+secondValueRef);
        Character firstValueRef = firstRotor.translateCharacter(secondValueRef);
        System.out.println("firstValueRef = "+firstValueRef);
//        enigmaUI.setTheThirdTestLabel(firstValueRef);

    }

    public void setUpRotors(ArrayList offsets,ArrayList Settings ){
        if(offsets.size() != Settings.size()){
            
        }
    }
    //this function is going to be written to take a string and print out the result, until the UI is done
    public void translate(String ToTranslate){
        if(isInitialized==false){

        }
    }

    public static void setEnigmaUI(UI enigmaUI) {
        Enigma.enigmaUI = enigmaUI;
    }
}
