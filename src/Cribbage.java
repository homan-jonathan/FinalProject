import java.util.*;

/**
 * @Author Garrett Martin and Jonathan Homan
 * Holds gameLoop method allowing for games of Cribbage to be played along with complementary to run the game
 * Differences to cribbage in pegging round and minor point calculation differences however game still plays the same
 */
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
        LinkedList<Card> crib;
        boolean playerCrib = true;
        while (playerScore<finalScore&&compScore<finalScore) {
            deck = new Deck();
            playerHand = new LinkedList<>(deck.dealHand(6));
            compHand = new LinkedList<>(deck.dealHand(6));
            crib = new LinkedList<>();


            if (playerCrib) {
                System.out.println("The discards will go in your crib");
            } else {
                System.out.println("The discards will go in the opponent crib");
            }
            // Allows Player to choose which 2 cards to discard, which then make up the crib
            // along with the discards from the AI
            System.out.println(Card.printCards(playerHand));
            System.out.println("Choose card to discard: (Enter 1-6 to pick first to last card to remove)");
            Card temp = playerHand.remove(scnr.nextInt()-1);
            System.out.println("Removed: " + temp);
            crib.add(temp);

            System.out.println(Card.printCards(playerHand));
            System.out.println("Choose card to discard: (Enter 1-5 to pick first to last card to remove)");
            temp = playerHand.remove(scnr.nextInt()-1);
            System.out.println("Removed: " + temp);
            crib.add(temp);

            //Removes first 2 cards from AI hand, no deterministic decision
            crib.add(compHand.remove(0));
            crib.add(compHand.remove(0));


            //First phase of game, playing cards to get points
            int playerPoints = playerScore;
            int compPoints = compScore;
            peggingRound(new LinkedList<>(playerHand),new LinkedList<>(compHand));
            System.out.println("You gained: " + (playerScore-playerPoints) + " points");
            System.out.println("Computer gained: " + (compScore-compPoints) + " points");
            System.out.println("Enter any key and enter to continue to second phase of round");
            scnr.next();

            //Second phase of game counting points in each hand with the shared card
            Card sharedCard = deck.dealCard();
            playerHand.add(sharedCard);
            compHand.add(sharedCard);
            crib.add(sharedCard);

            playerPoints = countPoints(playerHand);
            compPoints = countPoints(compHand);
            int cribPoints = countPoints(crib);
            playerScore+=playerPoints;
            compScore+=compPoints;

            System.out.println(Card.printCards(playerHand));
            System.out.println("Points gained from hand: " + playerPoints + "\n");

            System.out.println(Card.printCards(compHand));
            System.out.println("Points computer gained: " + compPoints+ "\n");

            // Determines whose crib it is and gives them the points from it
            System.out.println(Card.printCards(crib));
            if (playerCrib) {
                System.out.println("Points gained from your crib: " + cribPoints+"\n");
                playerScore+=cribPoints;
                playerCrib = false;
            } else {
                System.out.println("Points computer gained from crib: " + cribPoints+"\n");
                compScore+=cribPoints;
                playerCrib = true;
            }

            System.out.println("Current Score: " + playerScore + " to " +  compScore);
            System.out.println("Press any key and enter to continue to next round");
            scnr.nextLine();

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
    private int countPoints(LinkedList<Card> hand){
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
        int score = 0;
        if(hand.size() == 5) {
            int tempPoints = 0;
            for (Card c : hand) {
                tempPoints += c.getPoints();
            }
            if(tempPoints == 15){
                score += 2;
            }
        }
        Card last = hand.removeLast();
        int lastPoints = last.getPoints();



        for(int i=0; i<hand.size(); i++){
            int i_score = hand.get(i).getPoints();
            if(lastPoints + i_score == 15)
                score += 2;

            for(int j=i+1; j<hand.size(); j++){
                int j_score = hand.get(j).getPoints();
                if(i_score + j_score + lastPoints == 15)
                    score += 2;

                for(int k=j+1; k<hand.size(); k++){
                    int k_score = hand.get(k).getPoints();
                    if(i_score + j_score + k_score + lastPoints == 15)
                        score += 2;
                }
            }
        }
        if(hand.size() > 1) {
            return score + count15(hand);
        }
        return score;
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

        for (int i : runCounter) {
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
