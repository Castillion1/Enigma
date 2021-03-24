import java.util.ArrayList;
import java.util.Arrays;

public class EnigmaParts {
    protected static final ArrayList<Character> alphabet = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f','g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));

    /*
    This function takes a string and turns it into an ArrayList<Characters> to be able to make the constructor cleaner
    Allowing for a simple position and String of the rotor order
    */
    public static ArrayList<Character> translateStringToArrayList(String values){
        String lowerCaseValues = values.toLowerCase();
        Character[] translationValues = new Character[values.length()];
        for(int i =0; i<lowerCaseValues.length(); i++){
            translationValues[i] = lowerCaseValues.charAt(i);
        }
        return new ArrayList<>(Arrays.asList(translationValues));
    }

}
