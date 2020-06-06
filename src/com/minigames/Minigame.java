package com.minigames;
import com.gameLogic.Player;
public abstract class Minigame{
    Player[] players;

    public Minigame(Player[] players){
        this.players = players;
    }

    public abstract void startGame();

    public void reward(Player[] players){
        //TODO add the reward coding for the players
        int coins = 12;
        int len = players.length;
        int take = coins / len;
        for (Player player : players){
            player.updateCoins(coins);
            coins -= take;
        }
    }
}
