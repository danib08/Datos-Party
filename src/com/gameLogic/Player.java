package com.gameLogic;

import com.structures.*;
import java.util.Random;
import java.util.Scanner;

/** Represents a player.
 */
public class Player {
    private String name;
    private Square position;
    private Events eventHandler;
    private Star star;
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
     * @param eventHandler The class that will handle all of the events
     * @param star A buyable star
     */
    public Player(String name, Square position, Events eventHandler, Star star) {
        this.name = name;
        this.position = position;
        this.eventHandler = eventHandler;
        this.star = star;
        this.coins = 0;
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
        this.coins += coins;
    }

    /**
     * Moves the player's position the number of squares rolled by the dice
     *
     * @param playerArray Array of players, is used to check if two players share a square.
     */
    public void move(Player[] playerArray) {
        int movement = this.roll();
        while (movement > 1) {
            System.out.println("Remaining movement: " + movement);

            if (this.star.getPosition() == this.position) {
                this.buyStar();
            }

            if (this.position.getPathLink() != null && !pathChanged) {
                this.changePath();
            }

            else if (this.backwards) {
                this.position = position.getPrev();
                this.pathChanged = false;
            }

            else {
                this.position = position.getNext();
                this.pathChanged = false;
            }
            movement--;
        }

        System.out.println("Remaining movement: " + movement);
        Square prevSquare = this.position;

        if (this.position.getPathLink() != null) {
            this.changePath();
        } else if (this.backwards){
            this.position = this.position.getPrev();
            this.pathChanged = false;
        }
        else {
            this.position = this.position.getNext();
            this.pathChanged = false;
        }
        movement--;
        System.out.println("Remaining movement: " + movement);

        if (this.star.getPosition() == this.position) {
            this.buyStar();
        }

        //System.out.println("PREV: " + tmp.getData());
        //System.out.println("CURR: " + this.position.getData());

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
                this.updateCoins(+5);
                break;
            case 3:
                System.out.println("Red Square");
                this.updateCoins(-5);
                break;
            case 4:
                System.out.println("Yellow Square");
                this.eventHandler.eventSelecter(this);
                this.eventHandler.checkLength();
                break;
        }
    }

    /**
     * This method executes the logic used when a player encounters a bifurcation
     */
    private void changePath() {
        if (this.onMain) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Want to change direction: Y/N");
            String resp = scan.nextLine();
            if (resp.equals("Y")) {
                this.position = this.position.getPathLink();
                if (this.position.getData() == 3) {
                    this.backwards = true;
                }
                this.onMain = false;
                this.pathChanged = true;
            }
            else {
                this.position = this.position.getNext();
            }
        }
        else {
            this.onMain = true;
            this.backwards = false;
            this.position = this.position.getPathLink();
            this.pathChanged = true;
        }
    }

    /**
     * Rolls two six-died dice adds them and returns the total.
     *
     * @return An integer containing the total of the roll.
     */
    private int roll() {
        Random random = new Random();
        int result = random.nextInt(6) + 1;
        result += random.nextInt(6) + 1;
        return result;
    }

    /**
     * Checks if player has the money to buy a star, then asks if he wants to buy it
     * if the player says yes, a star is added and the star position gets changed.
     */
    private void buyStar() {
        Scanner scan = new Scanner(System.in);
        if (this.coins >= 15) {
            System.out.println("Do you want to buy a Star: YES/NO");
            String response = scan.nextLine();
            if (response.equals("YES")) {
                this.updateCoins(-15);
                this.stars++;
                this.star.positionStar();
            }
        }
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

    public void setBackwards(boolean backwards) {
        this.backwards = backwards;
    }
}

