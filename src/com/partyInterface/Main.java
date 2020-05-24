package com.partyInterface;

import com.structures.CircularSinglyLinkedList;

public class Main {
    public int min = 1;
    public int max = 6;
    public int i = 0;


    public int Main(String[] args) {
        CircularSinglyLinkedList board = new CircularSinglyLinkedList();

        while (i < 15){
            board.append((int) Math.random() * (max - min + 1) + min);
            i++;
        }
        board.printList();
        return board.getLength();
    }

}

