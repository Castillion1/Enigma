import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.ArrayList;

public class SwitchBoardWiring extends EnigmaParts {
    private final ArrayList<Plug> plugBoard = new ArrayList<Plug>();

    public void make() {
        for (int i = 0; i < alphabet.size(); i++) {
            this.plugBoard.add(new Plug(alphabet.get(i)));
        }
    }

    public ArrayList<Plug> getPlugBoard() {
        return this.plugBoard;
    }

    public char translate(Character ToTranslate){
        int localIndexStore = 0;
        for(int i =0; i < alphabet.size();i++){
            if(alphabet.get(i).equals(ToTranslate)){
                localIndexStore = i;
            }
        }
        return this.plugBoard.get(localIndexStore).getLinkedTo().getLetter();

    }

    public void setUpTranslation(Character One, Character Two) {
        //This function takes two Characters and links the correct plugs together.
        Plug localOne = null;
        int localOneInt = 0;
        Plug localTwo = null;
        int localTwoInt = 0;
        for (int i = 0; i < this.plugBoard.size(); i++) {
            if (plugBoard.get(i).getLetter().equals(One)) {
                localOne = plugBoard.get(i);
                localOneInt = i;
            } else if (plugBoard.get(i).getLetter().equals(Two)) {
                localTwo = plugBoard.get(i);
                localTwoInt = i;
            }
        }

        if ((localOne == null) || (localTwo == null)) {
            throw new IllegalStateException("The letters provided is not part of the alphabet");
        }
        localOne.setLinkedTo(localTwo);
        localOne.setLinkerToIndex(localOneInt);
        localTwo.setLinkedTo(localOne);
        localTwo.setLinkerToIndex(localTwoInt);

    }
}
