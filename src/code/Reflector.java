package code;

import java.util.ArrayList;

public class Reflector extends TranslationContext {
    private final ArrayList<Character> reflectorSettings;
    private TranslationContext localContext = new TranslationContext();

    public Reflector(String reflections) {
        this.reflectorSettings = translateStringToArrayList(reflections);
        localContext.make(reflectorSettings);
        setUpTranslations();
    }

    public Character translateCharacter(Character currentCharacter) {
        return this.localContext.translateForward(currentCharacter, 0, true);
    }

    private void setUpTranslations() {
        for (int i = 0; i < alphabet.size(); i++) {
            this.localContext.setUpTranslation(this.reflectorSettings.get(i), this.reflectorSettings.get(this.alphabet.indexOf(this.reflectorSettings.get(i))), true);
        }

    }
}
