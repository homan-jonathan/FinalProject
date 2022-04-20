import java.util.List;
import java.util.Set;

public class Cribbage {
    private List<Card> playerHand;
    private List<Card> compHand;
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

    }

    /**
     * count the points that are in a players hand
     * @param hand
     * @return
     */
    public int countPoints(List<Card> hand){
        int points = 0;
        return -1;
    }
}
