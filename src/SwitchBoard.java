import java.util.ArrayList;

public class SwitchBoard extends TranslationContext {
    //This is a wrapper around the SwitchBoardWiring class, to ensure it is a singleton.
    private static TranslationContext instance = null;
    private ArrayList<TranslationPair> plugBoard = new ArrayList<TranslationPair>();

    public static TranslationContext getInstance() {
        if (instance == null) {
            instance = new TranslationContext();
            instance.make();
        }

        return instance;
    }

    public char translate(Character input){
        return instance.translateForward(input, 0 ,true);
    }


}
