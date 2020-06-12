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
    private SinglyLinkedList stack;
    private ArrayList<Integer> eventList;

    /**
     * Creates a new Events class
     * @param stack A stack containing all of the events, in a randomized order
     * @param eventList An ArrayList that contains all of the events (used to refill the stack)
     */
    public Events(SinglyLinkedList stack, ArrayList<Integer> eventList) {
        this.stack = stack;
        this.eventList = eventList;
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
