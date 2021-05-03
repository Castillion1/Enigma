public class TranslationPair {
    private final Character Letter;
    private TranslationPair LinkedTo = null;

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

    public void setLinkedTo(TranslationPair linkedTo) {
        this.LinkedTo = linkedTo;
    }

}
