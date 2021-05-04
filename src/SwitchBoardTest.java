import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwitchBoardTest {

    @Test
    void getInstance() {
        SwitchBoard.getInstance();
        SwitchBoard.getInstance().setUpTranslation('a','b', true);
        assertEquals(SwitchBoard.getInstance().translateForward('a', 0, true), 'b');
    }
}