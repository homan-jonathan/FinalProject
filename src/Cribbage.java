import java.util.*;

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
        while (playerScore<finalScore&&compScore<finalScore) {
            deck = new Deck();
            playerHand = new LinkedList<>(deck.dealHand(4));
            compHand = new LinkedList<>(deck.dealHand(4));
            playerScore+=countPoints(playerHand);
            compScore+=countPoints(compHand);





        }


        if(playerScore>=compScore) {
            System.out.println("Player Wins: " + playerScore + " to " + compScore);
        } else {
            System.out.println("Computer Wins: " + compScore + " to " + playerScore);
        }


    }

    /**
     * count the points that are in a players hand
     * @param hand hand to count points
     * @return number of points counted
     */
    public int countPoints(LinkedList<Card> hand){
        int points = 0;
        points+=countFlush(hand);
        points+=countPairs(hand);


        return points;
    }

    /**
     * counts the points from pairs
     * @param hand hand to count points
     * @return number of points counted
     */
    private int countPairs(LinkedList<Card> hand) {
        ArrayList<Card> cards = new ArrayList<>(hand);
        int points = 0;
        points+=countFlush(hand);
        for (int i = 0; i<cards.size()-1;i++) {
            Card compareCard = cards.get(i);
            for (int j = i+1; j<cards.size(); j++) {
                if(compareCard.sameValue(cards.get(j))) {
                    points+=2;
                }
            }
        }

        return points;
    }

    /**
     * Checks if flush and adds point if flush
     * @param hand hand of Cards to check
     * @return points if flush exists
     */
    private int countFlush(LinkedList<Card> hand) {
        HashSet<String> suitSet = new HashSet<>();
        for (Card c: hand) {
            suitSet.add(c.getSuit());
        }
        if (suitSet.size()==1) {
            return hand.size();
        } else {
            return 0;
        }
    }



}
