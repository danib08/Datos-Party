package com.gameLogic;
import com.structures.List;
import com.structures.SinglyLinkedList;
import com.structures.Square;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

/**
 * This class will be in charge of the events in the game
 */
public class Events {
    private Player[] playerArray;
    private List[] listArray;
    private SinglyLinkedList stack;
    private ArrayList<Integer> eventList;

    /**
     * Creates a new Events class
     * @param playerArray An array containing all of the players in the game
     * @param listArray An array containing all of the paths
     * @param stack A stack containing all of the events, in a randomized order
     * @param eventList An ArrayList that contains all of the events (used to refill the stack)
     */
    public Events(Player[] playerArray, List[] listArray, SinglyLinkedList stack, ArrayList<Integer> eventList) {
        this.playerArray = playerArray;
        this.listArray = listArray;
        this.stack = stack;
        this.eventList = eventList;
    }

    /**
     * Grabs the first element in the stack and executes the corresponding event
     * @param playerUnleasher The Player that activated the event
     */
    public void eventSelecter(Player playerUnleasher) {
        int event = this.stack.popHead();

        Random random = new Random();
        int index = random.nextInt(playerArray.length);
        Player playerTarget = playerArray[index];

        while (playerTarget.getName().equals(playerUnleasher.getName())) {
            index = random.nextInt(playerArray.length);
            playerTarget = playerArray[index];
        }

        switch (event) {
            case 1:
                this.eventDuel(playerUnleasher, playerTarget);
                break;
            case 2:
                this.stealCoins(playerUnleasher, playerTarget);
                break;
            case 3:
                this.donateCoins(playerUnleasher, playerTarget);
                break;
            case 4:
                this.loseStar(playerUnleasher, playerTarget);
                break;
            case 5:
                this.winStars(playerUnleasher, 2);
                break;
            case 6:
                this.winStars(playerUnleasher, 5);
                break;
            case 7:
                this.stealStar(playerUnleasher, playerTarget);
                break;
            case 8:
                this.teleport(playerUnleasher, this.listArray);
                break;
            case 9:
                this.swap(playerUnleasher, playerTarget);
                break;
        }
    }

    /**
     * Add stars to the specified Player
     * @param player The Player that will win stars
     * @param stars The amount of stars won
     */
    private void winStars(Player player, int stars) {
        player.updateStars(stars);
        System.out.println("Player won stars!");
    }

    /**
     * Removes a star from the player that activated the event and gives it to another player
     * @param donor The Player that will lose the star
     * @param target The Player that will win a star
     */
    private void loseStar(Player donor, Player target) {
        if (donor.getStars() > 0) {
            donor.updateStars(-1);
            target.updateStars(+1);
        }
        else {
            System.out.println("You have no stars to lose!");
        }
    }

    /**
     * The player that activated the event steals a star from another one
     * @param stealer The Player that will steal a star
     * @param target The Player that will be robbed
     */
    private void stealStar(Player stealer, Player target) {
        if (target.getStars() > 0){
            stealer.updateStars(1);
            target.updateStars(-1);
        }
        else{
            System.out.println("You cannot steal any stars from that player!");
        }
    }

    /**
     * The player that activated the event loses coins, which are given to another player
     * @param donor The Player that loses coins
     * @param target The Player that wins coins
     */
    private void donateCoins(Player donor, Player target){
        if (donor.getCoins() > 0) {
            Random random = new Random();
            int coins = random.nextInt(donor.getCoins() / 2) + 1;

            donor.updateCoins(-coins);
            target.updateCoins(coins);
        }
        else {
            System.out.println("You have no coins to lose!");
        }
    }

    /**
     * The player that activated the event steals coin from another one
     * @param stealer The Player that steals coins
     * @param target The Player that loses coins
     */
    private void stealCoins(Player stealer, Player target) {
        if (target.getCoins() > 0) {
            Random random = new Random();
            int coins = random.nextInt(target.getCoins() / 2) + 1;

            target.updateCoins(-coins);
            stealer.updateCoins(coins);
        }
        else {
            System.out.println("You cannot steal any coins from that player!");
        }
    }

    /**
     * Teleports the player to a random position
     * @param player The Player that will be teleported
     * @param pathArr An array containing all of the paths
     */
    private void teleport(Player player, List[] pathArr){
        Random index = new Random();
        int pathInd = index.nextInt(pathArr.length);
        List destination = pathArr[pathInd];

        int pos = index.nextInt(destination.getLength());
        Square newPos = destination.getElement(pos);
        player.setPosition(newPos);
        System.out.println("Player teleported!");
        if (pathInd == 3 || pathInd == 4){
            Scanner scan = new Scanner(System.in);
            System.out.println("Left or Right: ");
            String resp = scan.nextLine();
            if (resp.equals("R")){
                player.setBackwards(true);
            }
        }
    }

    /**
     * Swaps the positions of two players
     * @param player1 One of the players that will be swapped
     * @param player2 The other player that will be swapped
     */
    private void swap(Player player1, Player player2){
        Square p1Pos= player1.getPosition();
        Square p2Pos = player2.getPosition();
        player1.setPosition(p2Pos);
        player2.setPosition(p1Pos);
        System.out.println("player1 n player2 swapped positions!");
    }

    /**
     * Checks if the stack is empty, if it its it calls the stackRefill method.
     */
    public void checkLength(){
        if (this.stack.getLength() == 0){
            this.stackRefill();
        }
    }

    /**
     * Refills the stack with a new randomized order of events.
     */
    public void stackRefill(){
        Collections.shuffle(this.eventList);

        for (Integer event : this.eventList) {
            this.stack.prepend(event, 0 , 0);
        }
    }

    /**
     * Starts a duel between two players for the prize of coins.
     * @param p1 the player that triggers the event.
     * @param p2 the player selected randomly to duel the other player.
     */
    public void eventDuel(Player p1, Player p2){
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
        winner.updateCoins(10);
            if (loser.getCoins() >= 10) {
                loser.updateCoins(-10);
            }
            else {
                loser.updateCoins(-loser.getCoins());
            }
        */
    }
}
