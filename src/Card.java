import java.util.List;
import java.util.Objects;

public class Card {
    private String suit;
    private String value;
    private int points;

    /**
     * Constructor for Card class
     * @param suit
     * @param value
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
    public int getPoints() {return points; }

    /**
     * Compares suit and value of two cards
     * @param otherCard Card being compared
     * @return true, if the two cards have the same suit and value
     */
    public boolean equals(Card otherCard) {
        return (this.suit.equals(otherCard.suit))&&(this.value.equals(otherCard.value));
    }

    /**
     * Compares two Cards to see if they have the same suit
     * @param otherCard Card being compared
     * @return if the two cards have the same suit
     */
    public boolean sameSuit(Card otherCard){
        return this.suit.equals(otherCard.suit);
    }

    /**
     * Compares two Cards to see if they have the same value
     * @param otherCard
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
