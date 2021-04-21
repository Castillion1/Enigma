public class TranslationPair {
    private TranslationPair LinkedTo = null;
    private final Character Letter;
    private int LinkerToIndex;

    public TranslationPair(Character letter) {
        this.Letter = letter;
    }

    public Character getLetter() {
        return this.Letter;
    }

    public TranslationPair getLinkedTo() {
        if(this.LinkedTo == null){
            return this;
        }else{
            return this.LinkedTo;
        }

    }

    public int getLinkerToIndex(){ return this.LinkerToIndex;}

    public void setLinkedTo(TranslationPair linkedTo) {
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
