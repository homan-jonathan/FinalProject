import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Cribbage {
    private LinkedList<Card> playerHand;
    private LinkedList<Card> compHand;
    private int playerScore;
    private int compScore;
    private final int finalScore;
    private Set<Card> comparison;
    private Deck deck;

    /**
     * Constructor for the Cribbage class, with predetermined final score
     * @param finalScore
     */
    public Cribbage(int finalScore){
        playerScore = 0;
        compScore = 0;
        this.finalScore = finalScore;
    }

    /**
     * Runs a game of Cribbage until the winning conditions are met
     */
    public void gameLoop(){
        while (playerScore<finalScore&&compScore<playerScore) {
            deck = new Deck();
            playerHand = new LinkedList<>(deck.dealHand(4));
            compHand = new LinkedList<>(deck.dealHand(4));
            playerScore+=countPoints(playerHand);
            compScore+=countPoints(compHand);





        }


        if(playerScore>=compScore) {
            System.out.println("Player Wins");
        } else {
            System.out.println("Computer Wins");
        }


    }

    /**
     * count the points that are in a players hand
     * @param hand
     * @return
     */
    public int countPoints(LinkedList<Card> hand){
        int points = 0;
        points+=countPairs(hand);
        return 1;
    }

    private int countPairs(LinkedList<Card> hand) {



        return -1;
    }
}
