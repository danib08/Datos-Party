package com.minigames.bombGame;
import com.gameLogic.Player;

import java.util.Random;

public class BombDetonator{
    int pAmount;
    Player [] players;
    /**
     *  this method is the only constructor this class consists of.
     * @param players the main player array used in the game logic.
     */
    public BombDetonator(Player[] players) {
        this.players = players;
    }


}
