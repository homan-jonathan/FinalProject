import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    static final String[] suits = {"hearts", "diamonds", "clubs", "spades"};
    static final String[] faceValues = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    ArrayList<Card> cards = new ArrayList<>();
    int cardPosition;

    public Deck(){
        for (int i = 0; i< 52; i++){
            cards.add(new Card(faceValues[i%13], suits[i/13]));
        }
        cardPosition=0;
        shuffle();
    }

    /**
     * Deals a card to a player
     * @return Card that is dealt to a player
     */
    public void swap(int numA, int numB) {
        Card tempCard = cards.get(numA);
        cards.set(numA,cards.get(numB));
        cards.set(numB, tempCard);
    }

    /**
     * runs the swap method 52 times to shuffle the deck with a random other Card in the deck
     */
    public void shuffle() {
        Random r = new Random();
        //Shuffles deck by swapping each position in the deck with a random Card
        for(int i = 0; i < cards.size(); i++) {
            swap(i, r.nextInt(cards.size()));
        }
    }

    /**
     * Deals a hand to a player
     * @param amount
     * @return List of a players hand that is dealt
     */
    public List<Card> dealHand(int amount){
        return null;
    }

    public Card dealCard() {
        return null;
    }


}
