package com.gameLogic;

import com.structures.Square;

/** Represents a player.
 */
public class Player {
    private final String name;
    private Square position;
    private int coins;
    private int stars;
    private boolean backwards;
    private boolean onMain;
    private boolean pathChanged;

    /**
     * Creates a player
     * @param name The player's name in-game.
     * @param position The player's position (Square object)
     */
    public Player(String name, Square position) {
        this.name = name;
        this.position = position;
        this.coins = 5;
        this.stars = 0;
        this.backwards = false;
        this.onMain = true;
        this.pathChanged = false;
    }

    /**
     * Adds or substracts a certain amount of coins to the player.
     *
     * @param coins The amount of coins to be added or substracted.
     */
    public void updateCoins(int coins) {
        if (coins < 0 && Math.abs(coins) > this.coins) {
            this.coins = 0;
        }
        else {
            this.coins += coins;
        }
    }

    /**
     * Checks if player has the money to buy a star, then asks if he wants to buy it
     * if the player says yes, a star is added and the star position gets changed.
     */
    public void buyStar() {
        this.updateCoins(-15);
        this.stars++;
    }

    /**
     * Gets the player's current amount of coins
     *
     * @return An integer representing the player's current amount of coins.
     */
    public int getCoins() {
        return this.coins;
    }

    /**
     * Gets the player's current amount of stars.
     *
     * @return An integer representing the player's current amount of coins.
     */
    public int getStars() {
        return this.stars;
    }

    /**
     * Updates the player's current ammount of stars.
     * @param stars an integer value to increment or decrement the player's star ammount.
     */
    public void updateStars(int stars) {
        this.stars += stars;
    }

    /**
     * Gets the player's current position on the board.
     *
     * @return A Square representing the player's position on the board.
     */
    public Square getPosition() {
        return this.position;
    }

    /**
     * Sets the player's position on the board.
     *
     * @param position A Square containing the player's position on the board.
     */
    public void setPosition(Square position) {
        this.position = position;
    }

    /**
     * Gets the player's name.
     *
     * @return A string representing the player's name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the player backwards attribute
     * @return A boolean representing if the player moves backwards or not
     */
    public boolean getBackwards() {
        return this.backwards;
    }

    /**
     * Sets the player's backwards attribute
     * @param backwards the boolean that the backwards parameter will take
     */
    public void setBackwards(boolean backwards) {
        this.backwards = backwards;
    }

    /**
     * Gets the player pathChanged attribute
     * @return A boolean that tells us if the player recently changed paths
     */
    public boolean getPathChanged() {
        return this.pathChanged;
    }

    /**
     * Sets the player's pathChanged attribute
     * @param pathChanged the boolean that the pathChanged parameter will take
     */
    public void setPathChanged(boolean pathChanged) {
        this.pathChanged = pathChanged;
    }

    /**
     * Gets the player onMain attribute
     * @return A boolean that tells us if the player is on the Main Path
     */
    public boolean getOnMain() {
        return this.onMain;
    }

    /**
     * Sets the player's onMain attribute
     * @param onMain the boolean that the onMain parameter will take
     */
    public void setOnMain(boolean onMain) {
        this.onMain = onMain;
    }
}
