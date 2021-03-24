import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Rotor extends  EnigmaParts {
    private ArrayList<Character> rotorSettings = new ArrayList<>();
    private int currentOffset = 0;

    public Rotor(int startingOffset, String rotorSettings){
        this.currentOffset = startingOffset;
        this.rotorSettings = translateStringToArrayList(rotorSettings);
        System.out.println(this.rotorSettings);
    }
    /*
    In theory the method behind this will be as follows
    The Rotor order is the important part, the current value that is shown
     is done via the currentOffset
     To translate one character it needs to provide the value, that it shall translate
     A user gives it a value that needs to be translated.
     The code will then find out what value that is in the static array and then will use that number to find
     its translation. once this is done the currentOffset will increment by 1 to simulate the turning of a rotor

     */

    public Character translateCharacter(Character currentCharacter){
        int a = alphabet.indexOf(currentCharacter) + this.currentOffset;
        System.out.println("a = "+ a + "currentCharacter = "+ currentCharacter);
        return this.rotorSettings.get(a);
    }

    public ArrayList<Character> getRotorSettings(){
        return this.rotorSettings;
    }

    //Rotate the rotor
    public boolean rotate(){
        if(this.currentOffset == 25){
            this.currentOffset = 0;
            return true;
        }
        this.currentOffset ++;
        return false;
    }



}
