/**
 * @Author Garrett Martin and Jonathan Homan
 * Class with main method to start game
 */
public class Game {

    /**
     * Main method that calls gameLoop to run a game of Cribbage
     * @param args Args
     */
    public static void main(String[] args){
        Cribbage cribbage = new Cribbage(120);
        cribbage.gameLoop();
    }
}
