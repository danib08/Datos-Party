package com.gameLogic;

import com.structures.*;

/** Represents a player.
 */
public class Player {
    private final String name;
    private Square position;
    private final Star star;
    private int coins;
    private int stars;
    private int placement;
    private boolean backwards;
    private boolean onMain;
    private boolean pathChanged;

    /**
     * Creates a player
     * @param name The player's name in-game.
     * @param position The player's position (Square object)
     * @param star A buyable star
     */
    public Player(String name, Square position, Star star) {
        this.name = name;
        this.position = position;
        this.star = star;
        this.coins = 5;
        this.stars = 0;
        this.placement = 1;
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
     * Moves the player's position the number of squares rolled by the dice
     *
     * @param playerArray Array of players, is used to check if two players share a square.
     */
    public void move(int roll, Player[] playerArray) {
        while (roll > 1) {
            System.out.println("Remaining movement: " + roll);

            if (this.star.getPosition() == this.position) {
                this.buyStar();
            }

            if (this.position.getPathLink() != null && !pathChanged) {
                //this.changePath();
            }

            else if (this.backwards) {
                this.position = position.getPrev();
                this.pathChanged = false;
            }

            else {
                this.position = position.getNext();
                this.pathChanged = false;
            }
            roll--;
        }

        System.out.println("Remaining movement: " + roll);
        Square prevSquare = this.position;

        if (this.position.getPathLink() != null) {
            //this.changePath();
        } else if (this.backwards){
            this.position = this.position.getPrev();
            this.pathChanged = false;
        }
        else {
            this.position = this.position.getNext();
            this.pathChanged = false;
        }
        roll--;
        System.out.println("Remaining movement: " + roll);

        if (this.star.getPosition() == this.position) {
            this.buyStar();
        }

        for (Player player : playerArray) {
            if (player.getPosition() == this.position && !player.name.equals(this.name)) {
                System.out.println("Start Duel");
                duel(prevSquare, player);
            }
        }

        switch (this.position.getData()) {
            case 1:
                System.out.println("Blue Square");
                break;
            case 2:
                System.out.println("Green Square");
                this.updateCoins(3);
                break;
            case 3:
                System.out.println("Red Square");
                this.updateCoins(-3);
                break;
            case 4:
                System.out.println("Yellow Square");
                break;
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
     * This method starts a duel
     *
     * @param prevSquare This is the Square that is positioned before the current Square of both players
     * @param p2         The Player that will be the opponent
     */
    private void duel(Square prevSquare, Player p2) {
        /*
        Instantiate the minigame class
        Player winner = minigame.play(p1, p2);
        Player loser;
        if (winner == p1) {
            loser = p2;
        }
        else {
            loser = p1;
        }
        loser.setPosition(prevSquare);
         */
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
     * Gets the player's placement on the rankings.
     *
     * @return An integer representing the player's current placement on the ranking.
     */
    public int getPlacement() {
        return this.placement;
    }

    /**
     * Sets the player's placement on the rankings.
     *
     * @param placement An integer to represent the player's placement on the ranking.
     */
    public void setPlacement(int placement) {
        this.placement = placement;
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

