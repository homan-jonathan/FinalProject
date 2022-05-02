import java.util.*;

public class Cribbage {
    private int playerScore;
    private int compScore;
    private final int finalScore;

    /**
     * Constructor for the Cribbage class, with predetermined final score
     * @param finalScore Number of points to reach where game will end
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

            System.out.println(Card.printCards(playerHand));
            System.out.println("Choose card to discard: (Enter 1-5 to pick first to last card to remove)");
            System.out.println("Removed: " + playerHand.remove(scnr.nextInt()-1));
            compHand.remove(0);


            //First phase of game, playing cards to get points
            int playerPoints = playerScore;
            int compPoints = compScore;
            peggingRound(new LinkedList<>(playerHand),new LinkedList<>(compHand));
            System.out.println("You gained: " + (playerScore-playerPoints) + " points");
            System.out.println("Computer gained: " + (compScore-compPoints) + " points");
            System.out.println("Enter any key to continue to second phase of round");
            scnr.next();

            //Second phase of game counting points in each hand with the shared card
            Card sharedCard = deck.dealCard();
            playerHand.add(sharedCard);
            compHand.add(sharedCard);

            playerPoints = countPoints(playerHand);
            compPoints = countPoints(compHand);
            playerScore+=playerPoints;
            compScore+=compPoints;

            System.out.println(Card.printCards(playerHand));
            System.out.println("Points gained from hand: " + playerPoints);

            System.out.println(Card.printCards(compHand));
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
        points+=countFlush(new LinkedList<>(hand));
        points+=countPairs(new LinkedList<>(hand));
        points+=count15(new LinkedList<>(hand));
        points+=countRuns(new LinkedList<>(hand));


        return points;
    }

    /**
     * counts the points from pairs
     * @param hand hand to count points
     * @return number of points counted
     * Does NOT account for three of a kind
     */
    private int countPairs(LinkedList<Card> hand) {
        HashSet<String> cardVals = new HashSet<>();
        int points = 0;
        cardVals.add(hand.remove(0).getValue());
        for (Card c : hand) {
            if (cardVals.contains(c.getValue())) {
                points+=2;
            }
            cardVals.add(c.getValue());
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

    /**
     * Counts how many ways the hand can make 15
     * 2 points for each time 15 is made
     * @param hand Hand of Cards to count
     * @return number of points gained
     */
    private int count15(LinkedList<Card> hand) {
        int points = 0;
        return points;
    }


    /**
     * Counts the number of runs made by the hand
     * each run gains points equal to how many Cards are in the run
     * i.e (2,8,9,9,10) 2 runs of 3 so 6 points
     * (6,7,7,7,8) 3 runs of 3 so 9 points
     * Problematic hands (6,7,7,8,8) should be 4 runs of 3 so 12 points
     * BUT- Evaluates to 3 runs of 3 so 9 points
     * @param hand Hand of Cards to count
     * @return number of points gained
     */
    private int countRuns(LinkedList<Card> hand) {
        TreeSet<Integer> runCounter = new TreeSet<>();
        int points = 0;
        int numRemoved = 0;
        for (Card c: hand) {
            runCounter.add(c.getPointsReal());
        }

        //Removes any ints from set that do not have nearby integers
        for (int i: new TreeSet<>(runCounter)) {
            if (!(runCounter.contains(i+1)||runCounter.contains(i-1))) {
                runCounter.remove(i);
                numRemoved++;
            }
        }

        for (int i: runCounter) {
            //Run of 3
            if (runCounter.contains(i+1)&&runCounter.contains(i+2)) {
                points +=3*(hand.size()-runCounter.size()+1-numRemoved);
            }
            //Run of 4
            if (runCounter.contains(i+1)&&runCounter.contains(i+2)&&runCounter.contains(i+3)) {
                points +=4;
            }
        }
        return points;
    }






    private void peggingRound(LinkedList<Card> playerHand, LinkedList<Card> compHand) {
        Scanner scnr = new Scanner(System.in);
        //False means last player to play was Computer, True means Player
        boolean lastPlayed = false;
        Card playedCard;
        Card topCard = compHand.remove(0);
        int stackValue = topCard.getPoints();

        while (!(playerHand.size()==0&&compHand.size()==0)) {

            if (lastPlayed) {
                playedCard = compHand.remove();
                stackValue += playedCard.getPoints();
                if (stackValue%15==0) {
                    compScore+=2;
                }
                if (playedCard.sameValue(topCard)) {
                    compScore+=2;
                }
                topCard = playedCard;
                lastPlayed = false;
            } else {
                System.out.println("Last Played: " + topCard);
                System.out.println("Stack value is : " + stackValue);
                System.out.println("Choose a card to play: (1 is first Card in Row)");
                System.out.println(Card.printCards(playerHand));
                playedCard = playerHand.remove(scnr.nextInt()-1);
                stackValue += playedCard.getPoints();
                if (stackValue%15==0) {
                    playerScore+=2;
                    System.out.println(stackValue + "! plus 2 points\n");
                }
                if (playedCard.sameValue(topCard)) {
                    playerScore+=2;
                    System.out.println("Pair! plus 2 points\n");
                }
                topCard = playedCard;
                lastPlayed = true;
            }

        }
    }




}
