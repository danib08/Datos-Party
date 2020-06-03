package com.gameLogic;

import com.structures.*;
import java.util.Random;
import java.util.Scanner;
// JavaDoc Link: https://www.dummies.com/programming/java/how-to-use-javadoc-to-document-your-classes/
/** Represents a player.
 */
public class Player {
    private int coins;
    private int stars;
    private Square position;
    private int placement;
    private String name;
    private List path;
    private Events eventHandler;
    private Star star;

    /** Creates a player with a specified name
     * @param name The player's name in game.
     * @param path The player's initial path on the board.
     */
    public Player(String name, Square position, List path, Events eventHandler, Star star){
        this.coins = 0;
        this.stars = 0;
        this.position = position;
        this.placement = 1;
        this.name = name;
        this.path = path;
        this.eventHandler = eventHandler;
        this.star = star;
    }

    /** Adds or substracts a certain amount of coins to the player.
     * @param coins The amount of coins to be added or substracted.
     */
    public void updateCoins(int coins){
        this.coins += coins;
    }

    /** Moves the player's position the number of squares rolled by the dice
     * @param playerArray Array of players, is used to check if two players share a square.
     */
    public void move(Player[] playerArray){
        int movement = this.roll();
        while (movement > 1){
            System.out.println("Remaining movement: " + movement);
            // if (this.position.isConnected) {}

            //if (this.star.getPosition() = this.position {buyStar()}

            this.position = position.getNext();
            movement--;
        }

        System.out.println("Remaining movement: " + movement);
        Square tmp = this.position;
        this.position = position.getNext();
        movement--;
        System.out.println("Remaining movement: " + movement);
        System.out.println("PREV: " + tmp.getData());
        System.out.println("CURR: " + this.position.getData());

        for (Player player : playerArray) {
            if (player.getPosition() == this.position && player.name.equals(this.name)) {
                System.out.println("Start Duel");
                // TODO: call Duel(prevSquare) event
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
                break;
        }
    }

    // TODO: void changePath

    /** Rolls two six-died dice adds them and returns the total.
     * @return An integer containing the total of the roll.
     */
    private int roll(){
        Random random = new Random();
        int result = random.nextInt(6) + 1;
        result += random.nextInt(6) + 1;
        return result;
    }

    /** Checks if player has the money to buy a star, then asks if he wants to buy it
     *  if the player says yes, a star is added and the star position gets changed.
     */
    private void buyStar() {
        Scanner scan = new Scanner(System.in);
        if (this.coins >= 15){
            System.out.println("Do you want to buy a Star: YES/NO");
            String response = scan.nextLine();
            if (response.equals("YES")) {
                this.stars ++;
                this.star.positionStar();
            }
        }
    }

    /** Gets the player's current amount of coins
     * @return An integer representing the player's current amount of coins.
     */
    public int getCoins(){
        return this.coins;
    }

    /** Gets the player's current amount of stars.
     * @return An integer representing the player's current amount of coins.
     */
    public int getStars(){
        return this.stars;
    }

    /**
     * updates the player's current ammount of stars.
     * @param stars an integer value to increment or decrement the player's star ammount.
     */
    public void updateStars(int stars){
        this.stars += stars;
    }

    /** Gets the player's current position on the board.
     * @return A Square representing the player's position on the board.
     */
    public Square getPosition(){
        return this.position;
    }

    /** Sets the player's position on the board.
     * @param position A Square containing the player's position on the board.
     */
    public void setPosition(Square position){
        this.position = position;
    }

    /** Gets the player's placement on the rankings.
     * @return An integer representing the player's current placement on the ranking.
     */
    public int getPlacement(){
        return this.placement;
    }

    /** Sets the player's placement on the rankings.
     * @param placement An integer to represent the player's placement on the ranking.
     */
    public void setPlacement(int placement){
        this.placement = placement;
    }

    /** Gets the player's name.
     * @return A string representing the player's name.
     */
    public String getName(){
        return this.name;
    }

    /** Gets the player's current path.
     * @return A List representing the player's current path.
     */
    public List getPath(){
        return this.path;
    }

    /** Sets the player's path.
     * @param path A List containing the player's path.
     */
    public void setPath(List path){
        this.path = path;
    }
}
