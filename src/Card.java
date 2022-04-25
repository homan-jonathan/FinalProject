import java.util.Objects;

public class Card {
    private String suit;
    private String value;

    /**
     * Constructor for Card class
     * @param suit
     * @param value
     */
    public Card(String suit, String value){
        this.suit = suit;
        this.value = value;
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
     * Compares suit and value of two cards
     * @param otherCard Card being compared
     * @return true, if the two cards have the same suit and value
     */
    public boolean equals(Card otherCard) {
        return (this.suit.equals(otherCard.suit))&&(this.value.equals(otherCard.value));
    }

    /**
     * Compares two Cards to see if they have the same suit
     * @param otherCard
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
}
