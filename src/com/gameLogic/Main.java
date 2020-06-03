package com.gameLogic;

import com.structures.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

/**
 * This class will be the one in charge to create all of the lists
 */
public class Main {

    public static void main(String[] args) {
        CircularSinglyLinkedList mainBoard = new CircularSinglyLinkedList();
        SinglyLinkedList pathA = new SinglyLinkedList();
        SinglyLinkedList pathB = new SinglyLinkedList();
        DoublyLinkedList pathC = new DoublyLinkedList();
        CircularDoublyLinkedList pathD = new CircularDoublyLinkedList();
        List[] pathArray = {mainBoard, pathA, pathB, pathC, pathD};

        Star star = Star.getStar(pathArray);

        SinglyLinkedList eventStack = new SinglyLinkedList();
        ArrayList<Integer> eventList = new ArrayList<>();

        mainBoard.append(1);
        mainBoard.append(2);
        mainBoard.append(3);
        mainBoard.append(1);
        mainBoard.append(3);
        mainBoard.append(4);
        mainBoard.append(2);
        mainBoard.append(1);
        mainBoard.append(2);
        mainBoard.append(3);
        mainBoard.append(4);
        mainBoard.append(2);
        mainBoard.append(1);
        mainBoard.append(2);
        mainBoard.append(3);
        mainBoard.append(1);
        mainBoard.append(2);
        mainBoard.append(4);
        mainBoard.append(3);
        mainBoard.append(2);
        mainBoard.append(3);
        mainBoard.append(1);
        mainBoard.append(2);
        mainBoard.append(4);
        mainBoard.append(2);
        mainBoard.append(1);
        mainBoard.append(3);
        mainBoard.append(4);
        mainBoard.append(2);
        mainBoard.append(3);
        mainBoard.append(4);
        mainBoard.append(1);
        mainBoard.append(2);
        mainBoard.append(2);
        mainBoard.append(3);
        mainBoard.append(4);

        pathA.append(2);
        pathA.append(1);
        pathA.append(3);
        pathA.append(4);
        pathA.append(2);

        pathB.append(4);
        pathB.append(4);
        pathB.append(4);
        pathB.append(4);

        pathC.append(1);
        pathC.append(2);
        pathC.append(4);
        pathC.append(3);
        pathC.append(1);
        pathC.append(2);
        pathC.append(4);
        pathC.append(2);
        pathC.append(1);
        pathC.append(3);

        pathD.append(4);
        pathD.append(4);
        pathD.append(4);
        pathD.append(4);

        eventList.add(1);
        eventList.add(1);
        eventList.add(1);
        eventList.add(1);
        eventList.add(1);
        eventList.add(1);
        eventList.add(1);
        eventList.add(1);
        eventList.add(1);
        eventList.add(1);
        eventList.add(2);
        eventList.add(2);
        eventList.add(2);
        eventList.add(2);
        eventList.add(2);
        eventList.add(2);
        eventList.add(2);
        eventList.add(2);
        eventList.add(2);
        eventList.add(2);
        eventList.add(3);
        eventList.add(3);
        eventList.add(3);
        eventList.add(3);
        eventList.add(3);
        eventList.add(3);
        eventList.add(3);
        eventList.add(3);
        eventList.add(3);
        eventList.add(3);
        eventList.add(4);
        eventList.add(4);
        eventList.add(4);
        eventList.add(5);
        eventList.add(5);
        eventList.add(5);
        eventList.add(6);
        eventList.add(7);
        eventList.add(7);
        eventList.add(7);
        eventList.add(8);
        eventList.add(8);
        eventList.add(8);
        eventList.add(8);
        eventList.add(8);
        eventList.add(8);
        eventList.add(8);
        eventList.add(8);
        eventList.add(8);
        eventList.add(8);
        eventList.add(9);
        eventList.add(9);
        eventList.add(9);
        eventList.add(9);
        eventList.add(9);
        Collections.shuffle(eventList);

        // Adds the events to the stack.
        for (Integer event : eventList) {
            eventStack.prepend(event);
        }

        // The user can choose the number of players.
        Scanner scan = new Scanner(System.in);
        System.out.println("Choose number of players: 2, 3 o 4");
        int numberPlayer = scan.nextInt();
        Player[] playerArray = new Player[numberPlayer];

        // Creates the Event class to select events.
        Events eventHandler = new Events(playerArray, pathArray, eventStack, eventList);

        // The user can input the name of the players
        for (int i = 0; i < numberPlayer; i++){
            System.out.println("Enter the name of the player #" + (i+1) +" : ");
            if (i == 0){
                scan.nextLine();
            }
            String name = scan.nextLine();
            playerArray[i] = new Player(name, mainBoard.getHead(), mainBoard, eventHandler, star);
        }

        // Selects the number of rounds to be played
        int roundsPlayed = 0;
        System.out.println("Enter number of rounds: ");
        int roundsTotal = scan.nextInt();

        // Variables used to select the minigames
        int lastPlayed = 0;
        int chooseMinigame;

        // Starts the game logic
        while (roundsPlayed < roundsTotal ){
            for (Player player : playerArray){
                // Player round logic goes here
                System.out.println(player.getName());
                System.out.println("Press enter to move");
                if (player == playerArray[0]){
                    scan.nextLine();
                }
                scan.nextLine();

                // Rolls the die and moves the player accordingly
                player.move(playerArray);
            }

            // Selects an integer that will be the next minigame to be played.
            Random random = new Random();
            chooseMinigame = random.nextInt(6) + 1;

            // Checks that the chosen minigame isn't the same that was last played.
            while (lastPlayed == chooseMinigame){
                System.out.println("Selecting another minigame");
                chooseMinigame = random.nextInt(6) + 1;
            }

            lastPlayed = chooseMinigame;

            // This is a switch to select the next minigame to be played.
            switch (chooseMinigame){
                case 1:
                    System.out.println("Play 1st Minigame");
                    break;
                case 2:
                    System.out.println("Play 2nd Minigame");
                    break;
                case 3:
                    System.out.println("Play 3rd Minigame");
                    break;
                case 4:
                    System.out.println("Play 4th Minigame");
                    break;
                case 5:
                    System.out.println("Play 5th Minigame");
                    break;
                case 6:
                    System.out.println("Play 6th Minigame");
                    break;
            }
            roundsPlayed++;
        }
    }
}

