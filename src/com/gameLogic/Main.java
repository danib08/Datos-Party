package com.gameLogic;

import com.structures.*;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
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

        for (Integer event : eventList) {
            eventStack.prepend(event);
        }

        Scanner scan = new Scanner(System.in);
        System.out.println("Choose number of players: 2, 3 o 4");
        int numberPlayer = scan.nextInt();

        Player[] playerArray = new Player[numberPlayer];
        for (int i = 0; i < numberPlayer; i++){
            System.out.println("Enter the name of the player #" + (i+1) +" : ");
            if (i == 0){
                scan.nextLine();
            }
            String name = scan.nextLine();
            playerArray[i] = new Player(name, mainBoard.getHead(), mainBoard);
        }
        int roundsPlayed = 0;
        System.out.println("Enter number of rounds: ");
        int roundsTotal = scan.nextInt();
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
            roundsPlayed++;
        }
    }
}

