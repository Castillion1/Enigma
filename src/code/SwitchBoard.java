package code;

public class SwitchBoard extends TranslationContext {
    //This is to ensure it is a singleton.
    //As there should only be 1 SwitchBoard
    private static TranslationContext instance = null;
    public static TranslationContext getInstance() {
        if (instance == null) {
            instance = new TranslationContext();
            instance.make();

        }

        return instance;
    }

}
