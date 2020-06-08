package com.minigames.bombGame;
import com.gameLogic.Player;


public class Detonator {
    final private Player selector;
    private boolean exploded;

    public Detonator(Player choseThis){
        this.selector = choseThis;
        this.exploded = false;
    }

    public boolean detonated(){
        return this.exploded;
    }

    public void setExploded(boolean value){
        this.exploded = value;
    }


    public Player getSelector(){
        return this.selector;
    }
}
