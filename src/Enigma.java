import java.awt.*;

public class Enigma {
    private static UI enigmaUI;
    public Enigma() {
        SwitchBoardWiring board = SwitchBoard.getInstance();
//        System.out.println(board.getPlugBoard());
        board.setUpTranslation('a','z');

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

    public static void setEnigmaUI(UI enigmaUI) {
        Enigma.enigmaUI = enigmaUI;
    }
}
