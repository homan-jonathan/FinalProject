import java.util.*;

public class Cribbage {
    private int playerScore;
    private int compScore;
    private final int finalScore;
    private Set<Card> comparison;

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
        Scanner scnr = new Scanner(System.in);
        Deck deck;
        LinkedList<Card> playerHand;
        LinkedList<Card> compHand;
        while (playerScore<finalScore&&compScore<finalScore) {
            deck = new Deck();
            playerHand = new LinkedList<>(deck.dealHand(5));
            compHand = new LinkedList<>(deck.dealHand(5));

            for (Card c: playerHand) {
                System.out.println(c);
            }
            System.out.println("Choose card to discard: (Enter 0-4 to pick first to last card to remove)");
            System.out.println("Removed: " + playerHand.remove(scnr.nextInt()));


            //Second phase of game, need to implement giving each hand more than four cards
            //and have them choose one card to remove from the deck
            Card sharedCard = deck.dealCard();
            playerHand.add(sharedCard);
            compHand.add(sharedCard);

            int playerPoints = countPoints(playerHand);
            int compPoints = countPoints(compHand);
            playerScore+=playerPoints;
            compScore+=compPoints;

            for (Card c: playerHand) {
                System.out.print(c + "   ,   ");
            }
            System.out.println("Points gained from hand: " + playerPoints);

            for (Card c: compHand) {
                System.out.print(c + "   ,   ");
            }
            System.out.println("Points computer gained: " + compPoints);

            System.out.println("Current Score: " + playerScore + " to " +  compScore);
            System.out.println("Press any key and enter to continue to next round");
            scnr.next();

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
            String compareVal = cards.get(i).getValue();
            for (int j = i+1; j<cards.size(); j++) {
                String cardVal = cards.get(j).getValue();
                if(compareVal.equals(cardVal)) {
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
