import com.sun.istack.internal.NotNull;

import java.util.ArrayList;

public class TranslationContext extends EnigmaParts {
    private final ArrayList<TranslationPair> context = new ArrayList<>();
    private ArrayList<Character> settings = new ArrayList<>();
    //One of these need to be called, before trying to do any work with the translation context
    public void make(ArrayList<Character> settings) {
        this.settings = settings;
        for (int i = 0; i < alphabet.size(); i++) {
            this.context.add(new TranslationPair(alphabet.get(i)));
        }
    }
    public void make() {
        for (int i = 0; i < alphabet.size(); i++) {
            this.context.add(new TranslationPair(alphabet.get(i)));
        }
    }
    //When a message is being translated before the reflector
    public char translateForward(Character ToTranslate, int offset, boolean encrypt){
        int localIndexStore = 0;
        Character newFinding = ToTranslate;
        for(int i =0; i < alphabet.size();i++){
            if(alphabet.get(i).equals(newFinding)){
                localIndexStore = i;
            }
        }

        localIndexStore = localIndexChange(localIndexStore, offset, encrypt); //leaving rotation
        return this.context.get(localIndexStore).getLinkedTo().getLetter();

    }
    //The logic for combining the index and the offset.
    private int localIndexChange(int localIndex,int offset, boolean encrypt ){
        if(encrypt){
            if(localIndex+offset>25){
                localIndex = (localIndex+offset) - alphabet.size();
            }else{
                localIndex = localIndex+offset;
            }
        }else{
            if((localIndex-offset)<0){
                localIndex =alphabet.size() - Math.abs(localIndex-offset);
            }else{
                localIndex = Math.abs(localIndex - offset);
            }
        }
        return localIndex;
    }
    ////When a message is being translated after the reflector
    public char translateBackwards(Character ToTranslate, int offset, boolean encrypt){
        int localIndexStore = 0;
        Character newFinding = ToTranslate;
        for(int i =0; i < alphabet.size();i++){
            if(this.context.get(i).getLinkedTo().getLetter().equals(newFinding)){
                localIndexStore = i;
            }
        }
        localIndexStore = localIndexChange(localIndexStore, offset, encrypt);
        return this.context.get(localIndexStore).getLetter();
    }
    //This function takes two Characters and links the correct plugs together.
    public void setUpTranslation(Character One, Character Two, boolean twoWay) {
        if((One.equals(Two))&&(Two.equals(One))){
            return;
        }
        TranslationPair localOne = null;
        TranslationPair localTwo = null;
        for (int i = 0; i < alphabet.size(); i++) {
            if (context.get(i).getLetter().equals(One)) {
                localOne = context.get(i);
            } else if (context.get(i).getLetter().equals(Two)) {
                localTwo = context.get(i);
            }
        }

        if ((localOne == null) || (localTwo == null)) {
            throw new IllegalStateException(EnigmaText.lettersNotExisting);
        }

        localOne.setLinkedTo(localTwo);
        if(twoWay){
            localTwo.setLinkedTo(localOne);
        }


    }
    //Just sets all the linkedTo, to itself.
    public void emptyTranslations(){
        for(int i =0; i<alphabet.size();i++){
            context.get(i).setLinkedTo(context.get(i));
        }
    }

}
