public class Plug {
    private Plug LinkedTo = null;
    private final Character Letter;
    private int LinkerToIndex;

    public Plug(Character letter) {
        this.Letter = letter;
    }

    public Character getLetter() {
        return this.Letter;
    }

    public Plug getLinkedTo() {
        if(this.LinkedTo == null){
            return this;
        }else{
            return this.LinkedTo;
        }

    }

    public int getLinkerToIndex(){ return this.LinkerToIndex;}

    public void setLinkedTo(Plug linkedTo) {
        this.LinkedTo = linkedTo;
    }

    public void setLinkerToIndex(int linkerToIndex) {
        this.LinkerToIndex = linkerToIndex;
    }

    public Character translate() {
        if (!(this.LinkedTo.equals(null))) {
            return this.LinkedTo.getLetter();
        } else {
            return this.Letter;
        }
    }
}
