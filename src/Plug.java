public class Plug {
    private Plug LinkedTo;
    private final Character Letter;

    public Plug(Character letter) {
        this.Letter = letter;
    }

    public Character getLetter() {
        return this.Letter;
    }

    public Plug getLinkedTo() {
        return this.LinkedTo;
    }

    public void setLinkedTo(Plug linkedTo) {
        this.LinkedTo = linkedTo;
    }


    public Character translate() {
        if (!(this.LinkedTo.equals(null))) {
            return this.LinkedTo.getLetter();
        } else {
            return this.Letter;
        }
    }
}
