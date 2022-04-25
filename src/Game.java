public class Game {

    /**
     * Main method that calls gameLoop to run a game of Cribbage
     * @param args
     */
    public static void main(String[] args){
        Cribbage cribbage = new Cribbage(120);
        cribbage.gameLoop();
    }
}
