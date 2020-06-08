package com.minigames.bombGame;
import com.gameLogic.Player;


public class Detonator {
    final private Player selector;
    private boolean exploded;

    /**
     * public constructor for detonator objects.
     * @param choseThis the player that the detonator object is meant to be tied to.
     */
    public Detonator(Player choseThis){
        this.selector = choseThis;
        this.exploded = false;
    }

    /**
     * a method to access the explosion state for the specific instance.
     * @return boolean value for explosion state.
     */
    public boolean detonated(){
        return this.exploded;
    }

    /**
     * method to set the explosion value for the specific instance.
     * @param value a boolean value to determine the explosion state.
     */
    public void setExploded(boolean value){
        this.exploded = value;
    }

    /**
     * method to access the private attribute related to the player reference that the detonator object has.
     * @return a Player-type object consisting of its 'owner', according to the game context.
     */
    public Player getSelector(){
        return this.selector;
    }
}
