import com.sun.istack.internal.NotNull;

import java.util.ArrayList;

public class TranslationContext extends EnigmaParts {
    private final ArrayList<TranslationPair> context = new ArrayList<>();
    private ArrayList<Character> settings = new ArrayList<>();

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

    public ArrayList<TranslationPair> getContext() {
        return this.context;
    }

    public ArrayList<Character> getSettings(){
        return this.settings;
    }

    public char translateForward(Character ToTranslate, int offset, boolean encrypt){

        int localIndexStore = 0;
        for(int i =0; i < alphabet.size();i++){
            if(alphabet.get(i).equals(ToTranslate)){
                localIndexStore = i;
            }
        }
        localIndexStore = localIndexChange(localIndexStore, offset, encrypt);
        return this.context.get(localIndexStore).getLinkedTo().getLetter();

    }
    private int localIndexChange(int localIndex,int offset, boolean encrypt ){
        if(encrypt){
            if(localIndex+offset>25){//this method of doing the offset is wrong
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

    public char translateBackwards(Character ToTranslate, int offset, boolean encrypt){
        int localIndexStore = 0;
//        System.out.println();
        //there might be a nicer way by changing this loop. making it take into account the offset straight away?
        for(int i =0; i < alphabet.size();i++){
            if(this.context.get(i).getLinkedTo().getLetter().equals(ToTranslate)){
                localIndexStore = i;
            }
        }
        localIndexStore = localIndexChange(localIndexStore, offset, encrypt);
        return this.context.get(localIndexStore).getLetter();
    }

    public void setUpTranslation(@NotNull Character One,@NotNull Character Two, boolean twoWay) {
        if((One.equals(Two))&&(Two.equals(One))){
            return;
        }
        //This function takes two Characters and links the correct plugs together.
        TranslationPair localOne = null;
        int localOneInt = 0;
        TranslationPair localTwo = null;
        int localTwoInt = 0;
        for (int i = 0; i < alphabet.size(); i++) {
            if (context.get(i).getLetter().equals(One)) {
                localOne = context.get(i);
                localOneInt = i;
            } else if (context.get(i).getLetter().equals(Two)) {
                localTwo = context.get(i);
                localTwoInt = i;
            }
        }

        if ((localOne == null) || (localTwo == null)) {
            throw new IllegalStateException("The letters provided is not part of the alphabet");
        }

        localOne.setLinkedTo(localTwo);
        localOne.setLinkerToIndex(localOneInt);
        if(twoWay){
            localTwo.setLinkedTo(localOne);
            localTwo.setLinkerToIndex(localTwoInt);
        }


    }
}
