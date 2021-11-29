package blackjack;

import static java.lang.Math.min;

public class Card {

    private final int suit;
    private final int rank;
    private final int value;

    public Card(int suit, int rank) {
        this.suit = suit;
        this.rank = rank;
        this.value = setValue(rank);
    }

    public Card(Card card){
        this.suit = card.suit;
        this.rank = card.rank;
        this.value = card.value;
    }

    public int getSuit() {
        return suit;
    }

    public int getRank() {
        return rank;
    }

    public int getValue() {
        return value;
    }

    public int setValue(int rank) {
        return min(rank+1, 10);
    }

}
