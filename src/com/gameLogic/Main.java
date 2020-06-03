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

        //here the different lists are created
        //the mainBoard represents the outer border for the path players will see.
        CircularSinglyLinkedList mainBoard = new CircularSinglyLinkedList();

        //pathA,B and C are the secondary paths that players can opt to cross over to.
        SinglyLinkedList pathA = new SinglyLinkedList();
        SinglyLinkedList pathB = new SinglyLinkedList();
        DoublyLinkedList pathC = new DoublyLinkedList();

        //the path D is the one that can only be accessed through 'teleporting'.
        CircularDoublyLinkedList pathD = new CircularDoublyLinkedList();

        //a List-type array is made with the five paths.
        List[] pathArray = {mainBoard, pathA, pathB, pathC, pathD};

        //the star is created with a specific method, not regularly instantiated
        //because this object is accessed through a Singleton design pattern
        Star star = Star.getStar(pathArray);

        //A stack is implemented using the SinglyLinkedList structure, this is meant to
        //hold the representation of events to be accessed by the game logic.
        SinglyLinkedList eventStack = new SinglyLinkedList();

        //The collections class ArrayList is used to create a randomizable structure that can
        //later be accessed by the main() method to add the random-ordered elements to the event stack.
        ArrayList<Integer> eventList = new ArrayList<>();

        //As the game board follows a strictly defined order, the paths are filled with the respective
        //element that will tell the game logic what type of "Square" or position the players are on.
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

        //the ArrayList object is used as described previously, to add a certain amount of numbers
        //from 1 to 9 that will sequentially be randomized
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

        //shuffle method is used to "randomize" the eventList.
        Collections.shuffle(eventList);
        
        for (Integer event : eventList) {
            eventStack.prepend(event);
        }

        Scanner scan = new Scanner(System.in);
        System.out.println("Choose number of players: 2, 3 o 4");
        int numberPlayer = scan.nextInt();
        Player[] playerArray = new Player[numberPlayer];

        Events eventHandler = new Events(playerArray, pathArray, eventStack, eventList);

        for (int i = 0; i < numberPlayer; i++){
            System.out.println("Enter the name of the player #" + (i+1) +" : ");
            if (i == 0){
                scan.nextLine();
            }
            String name = scan.nextLine();
            playerArray[i] = new Player(name, mainBoard.getHead(), mainBoard, eventHandler, star);
        }

        int roundsPlayed = 0;
        System.out.println("Enter number of rounds: ");
        int roundsTotal = scan.nextInt();

        // For the minigame
        int lastPlayed = 0;
        int chooseMinigame;

        while (roundsPlayed < roundsTotal ){
            for (Player player : playerArray){
                // Player round logic goes here
                System.out.println(player.getName());
                System.out.println("Press enter to move");
                if (player == playerArray[0]){
                    scan.nextLine();
                }
                scan.nextLine();
                player.move(playerArray);
            }

            Random random = new Random();
            chooseMinigame = random.nextInt(6) + 1;

            while (lastPlayed == chooseMinigame){
                System.out.println("Selecting another minigame");
                chooseMinigame = random.nextInt(6) + 1;
            }

            lastPlayed = chooseMinigame;

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

