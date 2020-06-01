package com.gameLogic;
import com.gameLogic.Player;
import com.structures.Square;

public class Events {

    public void winStars(Player player){
        player.updateStars(1);
        System.out.println("player won a star!");
    }

    public void loseStars(Player player){
        int star = player.getStars();
        if (star> 0){
            player.updateStars(-1);
            System.out.println("player lost a star!");
        }
        else{
            System.out.println("player cannot lose any stars.");
        }
    }

    public void stealStar(Player stealer, Player target){
        int stealableStar = target.getStars();
        if (stealableStar > 0){
            stealer.updateStars(1);
            target.updateStars(-1);
            System.out.println("stealer got a star from the target");
        }
        else{
            System.out.println("target has no stealable stars.");
        }
    }

    public void donateCoins(Player donor, Player target){
        int coins = donor.getCoins();
        if (coins >= 5){
            donor.updateCoins(-5);
            target.updateCoins(5);
            System.out.println("donor gave 5 coins to target");
        }
        else{
            System.out.println("donor has insufficient coin balance");
        }
    }

    public void stealCoins(Player stealer, Player target){
        int coins = target.getCoins();
        if (coins >= 5){
            stealer.updateCoins(5);
            target.updateCoins(-5);
            System.out.println("stealer stole coins from target!");
        }
    }

    public void teleport(Player player, Square position){
        player.setPosition(position);
        System.out.println("player teleported to new square!");
    }

    public void swap(Player player1, Player player2){
        Square p1Pos= player1.getPosition();
        Square p2Pos = player2.getPosition();
        player1.setPosition(p2Pos);
        player2.setPosition(p1Pos);
        System.out.println("player1 n player2 swapped positions!");
    }
    /*
    public boolean duel(Player p1, Player p2, Player p3, Player p4){
        Square pos1= p1.getPosition();
        Square pos2= p2.getPosition();
        Square pos3= p3.getPosition();
        Square pos4= p4.getPosition();
        return checkForDuel(p1,p2,p3,p4);
    }

    private boolean checkForDuel(Square p1,Square p2,Square p3,Square p4){
        //TODO check players position as they move
        return false;
    }
    */
}
