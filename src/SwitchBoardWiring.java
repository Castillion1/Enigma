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

    public void setUpTranslation(Character One, Character Two) {
        //This function takes two Characters and links the correct plugs together.
        Plug localOne = null;
        Plug localTwo = null;
        for (int i = 0; i < this.plugBoard.size(); i++) {
            if (plugBoard.get(i).getLetter().equals(One)) {
                localOne = plugBoard.get(i);
            } else if (plugBoard.get(i).getLetter().equals(Two)) {
                localTwo = plugBoard.get(i);
            }
        }

        if ((localOne == null) || (localTwo == null)) {
            throw new IllegalStateException("The letters provided is not part of the alphabet");
        }

        localOne.setLinkedTo(localTwo);
        localTwo.setLinkedTo(localOne);

    }
}
