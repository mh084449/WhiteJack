package blackjack;

import java.util.Scanner;

public class BlackJack {

    static Game game = new Game();

    public static void main(String[] args) {

        GUI gui = new GUI();

        Scanner input = new Scanner(System.in);
        String name;
        int score;
        Player player;
        int num;

        for(int i = 0; i < 4; ++i){
            num = i+1;
            if(i == 3){
                System.out.println("Enter Player " + num + " (Dealer) Name:");
            }
            else {
                System.out.println("Enter Player " + num + " Name:");
            }
            name = input.next();
            game.spawnPlayer(name, i);
        }

        System.out.println();

        gui.runGUI( game.getCard(), game.getPlayer()[0].getCard(), game.getPlayer()[1].getCard(),
                game.getPlayer()[2].getCard(), game.getPlayer()[3].getCard() );



        Card tmpCard;

        for(int i = 0; i < 3; ++i){

            player = game.getPlayer()[i];
            score = player.getScore();

            System.out.println(player.getName() + "'s Turn");
            System.out.println(player.getName() + "'s Score: " + score);

            do{
                System.out.println("1) Hit");
                System.out.println("2) Stand");
                System.out.println("--------------------");
                System.out.println();

                num = input.nextInt();
            }
            while(!(num == 1 || num == 2));

            while(num != 2){
                score = player.getScore();
                tmpCard = game.drawCard();
                score += tmpCard.getValue();

                gui.updatePlayerHand(tmpCard, i);

                System.out.println();
                System.out.println(player.getName() + "'s New Score: " + score);

                if(score == 21){
                    player.setBlackjack(true);
                    System.out.println();
                    System.out.println(player.getName() + " Scored BlackJack!");
                    System.out.println();
                }
                else if(score > 21){
                    System.out.println();
                    System.out.println(player.getName() + " is Busted!");
                    System.out.println();
                    player.setLost(true);
                    score = 0;
                }

                player.setScore(score);

                if(player.getBlackjack()){
                    break;
                }
                if(player.getLost()){
                    break;
                }


                do {
                    System.out.println();
                    System.out.println("1) Hit");
                    System.out.println("2) Stand");
                    System.out.println("--------------------");

                    num = input.nextInt();
                }
                while(!(num == 1 || num == 2));
            }

            if(num == 2){
                System.out.println();
            }
        }

        game.updateScore();

        player = game.getPlayer()[3];
        int highScore = game.getHighScore();
        int cnt = 0;
        int index = -1;

        score = player.getScore();
        System.out.println("Highest Score: " + highScore);
        System.out.println();
        System.out.println("Dealer's Turn");
        System.out.println("Dealer Score: " + score);
        System.out.println();

        while(!(player.getScore() > highScore)){

            tmpCard = game.drawCard();
            score += tmpCard.getValue();
            player.setScore(score);

            gui.updateDealerHand(tmpCard, game.getCard());

            System.out.println("Dealer is Drawing...");
            System.out.println("Dealer New Score: " + score);

            if(score == 21){
                player.setBlackjack(true);
                System.out.println();
                System.out.println(player.getName() + " Scored BlackJack!");
                System.out.println();
                if(highScore == 21){
                    System.out.println("GAME STATE: A PUSH!");
                    return;
                }
            }
            else if(score > 21){
                System.out.println();
                System.out.println(player.getName() + " (Dealer) is Busted!");
                System.out.println();
                player.setLost(true);
                for(int i = 0; i < 3; ++i){
                     score = game.getPlayer()[i].getScore();
                     if(score == highScore){
                         cnt++;
                         index = i;
                     }
                }
                if(cnt == 1){
                    System.out.println(game.getPlayer()[index].getName() + " Won!!!");
                }
                else{
                    System.out.println("GAME STATE: A PUSH!");
                }
                return;
            }

            score = player.getScore();

        }

        System.out.println("GAME STATE: " + player.getName() + " (Dealer) Won!!!");
        return;
    }
}
