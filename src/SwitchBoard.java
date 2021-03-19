import java.lang.reflect.Array;
import java.util.ArrayList;

public class SwitchBoard {
    //This is a wrapper around the SwitchBoardWiring class, to ensure it is a singleton.
    private static SwitchBoardWiring instance = null;
    private ArrayList<Plug> plugBoard = new ArrayList<Plug>();

    public static SwitchBoardWiring getInstance() {
        if (instance == null) {
            instance = new SwitchBoardWiring();
            instance.make();
        }

        return instance;
    }


}
