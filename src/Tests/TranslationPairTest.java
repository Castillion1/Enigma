package Tests;

import code.TranslationPair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TranslationPairTest {
    TranslationPair pair;

    @BeforeEach
    void setUp() {
    this.pair = new TranslationPair('t');

    }

    @Test
    void getLetter() {
        char output = this.pair.getLetter();
        assertEquals('t', output);
    }

    @Test
    void getLinkedTo() {
        assertEquals(this.pair, this.pair.getLinkedTo());//Before linked so is linked to itself. I.E not plugged in

    }

    @Test
    void setLinkedTo() {
        TranslationPair local = new TranslationPair('f');
        this.pair.setLinkedTo(local);
        assertEquals(local, this.pair.getLinkedTo());//Before linked so is linked to itself. I.E not plugged in
    }
}