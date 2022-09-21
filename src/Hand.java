public class Hand {

    private final Card[] CARDS; // the cards the hand has
    private int count; // the number of cards this hand has
    private final Deck DECK; // the deck we are playing the game with

    public Hand(Deck deck, boolean isDealer){
        this.DECK = deck;

        // initialize the array
        CARDS = new Card[7];
        CARDS[0] = deck.deal();
        CARDS[1] = deck.deal();
        count = 2;

        if(isDealer){
            CARDS[0].setFaceUp(false);
        }

    }

    public int getTotal(){
        int sum = 0;
        int aces = 0;

        // raw total
        for(int i = 0; i < count; i++){
            sum += CARDS[i].getValue();

            if(CARDS[i].getFace() == Card.Face.ACE){
                ++aces;
            }
        }

        // save me plox
        while(sum > 21 && aces > 0){
            sum -= 10;
            --aces;
        }

        // actual total
        return sum;
    }

    public void hit(){
        CARDS[count++] = DECK.deal();
    }

    public Card getCard(int pos){
        return CARDS[pos];
    }

    public int getCount(){
        return count;
    }

}