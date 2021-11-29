package blackjack;

import java.util.Random;
import static java.lang.Math.max;

public class Game {

    private Player [] player = new Player[4];
    private Card [] card = new Card[52];
    private int highScore = 0;

    public Game() {
        generateDeck();
    }

    public Card[] getCard() {
        return card;
    }

    public Player[] getPlayer() {
        return player;
    }

    public int getHighScore() {
        return highScore;
    }

    private void generateDeck(){
        int suit = 0;
        int rank = 0;
        for(int i = 0; i < 52; ++i){
            card[i] = new Card(suit, rank);
            rank++;
            if(rank == 13){
                suit++;
                rank = 0;
            }
        }
    }

    public Card drawCard(){
        Random rand = new Random();
        int randomChoice = rand.nextInt(52);

        while (card[randomChoice] == null){
            randomChoice = rand.nextInt(52);
        }

        Card tmpCard = new Card(card[randomChoice]);
        card[randomChoice] = null;

        return tmpCard;
    }

    public void spawnPlayer(String name, int index){

        player[index] = new Player(name, drawCard(), drawCard());

    }

    public void updateScore(int score){

        highScore = max(score, highScore);
    }

}
