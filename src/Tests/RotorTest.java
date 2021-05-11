package Tests;

import code.EnigmaParts;
import code.Rotor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RotorTest {
    Rotor testRotor;
    int startingOffset = 1;
    String testSettings = "DMTWSILRUYQNKFEJCAZBPGXOHV";

    @BeforeEach
    void setUp() {
        this.testRotor = new Rotor(startingOffset, testSettings);

    }

    @Test
    void translateCharacterForward() {
        char output = this.testRotor.translateCharacterForward('A', true);
        assertEquals('m', output);
    }

    @Test
    void translateCharacterBackwards() {
        char output = this.testRotor.translateCharacterBackwards('m', false);
        assertEquals('a',output);

    }

    @Test
    void getRotorSettings() {
        ArrayList<Character> localSettings = this.testRotor.getRotorSettings();
        ArrayList<Character> localTestSettings = EnigmaParts.translateStringToArrayList(testSettings);

        assertEquals(localTestSettings, localSettings);
    }

    @Test
    void getCurrentOffset() {
        int actual = this.testRotor.getCurrentOffset();
        assertEquals(startingOffset, actual);
    }

    @Test
    void reset() {
        this.testRotor.rotate();
        assertEquals(startingOffset+1, this.testRotor.getCurrentOffset());
        this.testRotor.reset();
        assertEquals(startingOffset, this.testRotor.getCurrentOffset());
    }

}