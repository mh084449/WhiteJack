package blackjack;

public class Player {

    private final String name;
    private int score = 0;
    private Card [] card = new Card[11];
    private Boolean lost = false;
    private Boolean blackjack = false;

    public Player(String name, Card card, Card card1) {
        this.name = name;
        this.card[0] = card;
        this.card[1] = card1;
        this.score = card.getValue() + card1.getValue();
    }

    public Card[] getCard() {
        return card;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setLost(Boolean lost) {
        this.lost = lost;
    }

    public void setBlackjack(Boolean blackjack) {
        this.blackjack = blackjack;
    }
}
