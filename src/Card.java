import java.util.List;

public class Card {
    private final String suit;
    private final String value;
    private final int points;

    /**
     * Constructor for Card class
     * @param suit (Heart, Diamond, Spade, Club) Suit for Card
     * @param value (A,2,3,4,5,6,7,8,9,10,J,Q,K) Card String Val
     * @param points (A is 1, 2-9, 10,J,Q,K are 10) integer point val for Card
     */
    public Card(String value, String suit, int points){
        this.suit = suit;
        this.value = value;
        this.points = points;
    }

    /**
     * Copy Constructor for Card class
     * @param other Card to be copied
     */
    public Card(Card other) {
        this.suit = other.suit;
        this.value = other.value;
        this.points=other.points;
    }

    /**
     * Getter Function for the suit of a card (Hearts, Diamonds, Clubs, Spades)
     * @return suit of this Card
     */
    public String getSuit() {
        return suit;
    }

    /**
     * Getter Function for number value of a card (Ace, 2-10, Jack-King)
     * @return value of this Card
     */
    public String getValue() {
        return value;
    }

    /**
     * Getter functions for points value of card (1 for Ace, 2-9, 10 for 10,J,Q,K
     * @return integer points value of Card
     */
    public int getPoints() {
        return Math.min(points, 10);
    }

    /**
     * Getter for returning integer value representing each unique value
     * Used for sorting cards in order
     * @return integer points val of Card (A is 1, 2-10, J is 11, Q is 12, K is 13)
     */
    public int getPointsReal() {
        return points;
    }
    /**
     * Compares two Cards to see if they have the same value
     * @param otherCard Card to compare with
     * @return if the two cards have the same value
     */
    public boolean sameValue(Card otherCard){
        return this.value.equals(otherCard.value);
    }

    /**
     * toString method for Card
     * @return String representation of a card, with its value and suit
     */
    @Override
    public String toString() {
        return (value + " of " + suit);
    }


    /**
     * Prints out a hand of Cards spaced out
     * @param hand List of Cards to be printed
     * @return All cards seperated by commas in a line
     */
    public static String printCards(List<Card> hand) {
        StringBuilder sw = new StringBuilder();
        for (Card c : hand) {
            sw.append(c);
            sw.append("  ,  ");
        }
        return sw.toString();
    }
}
