public class Start {
    private static Enigma machine;
    public static void main(String[] args) {
        machine = new Enigma();

    }

    public static Enigma getMachine(){
        return machine;
    }
}
