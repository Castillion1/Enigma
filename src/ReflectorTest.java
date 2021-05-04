import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReflectorTest {
    Reflector testReflector;
    String settings = "EJMZALYXVBWFCRQUONTSPIKHGD";

    @BeforeEach
    void setUp() {
        this.testReflector = new Reflector(this.settings);
    }

    @Test
    void translateCharacter() {
        char output = this.testReflector.translateCharacter('m');
        assertEquals('c', output);
        output = this.testReflector.translateCharacter('z');
        assertEquals('d', output);
    }
}