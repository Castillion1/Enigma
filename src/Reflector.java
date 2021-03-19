import java.util.ArrayList;

public class Reflector extends EnigmaParts {
    private final ArrayList<Character> reflectorSettings;
    public Reflector(String reflections){
        this.reflectorSettings = translateStringtoArrayList(reflections);
    }

    public Character translateCharacter(Character currentCharacter){
        return this.reflectorSettings.get(alphabet.indexOf(currentCharacter));
    }
}
