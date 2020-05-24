package com.structures;

import java.util.ArrayList;
import java.util.Collections;

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
    }
}
