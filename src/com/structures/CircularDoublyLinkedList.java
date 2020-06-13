package com.structures;

/**
 * A list formed by DoubleSquare objects, in which the last element is linked to the first
 */
public class CircularDoublyLinkedList extends List {
    private DoubleSquare head;
    private DoubleSquare tail;

    public void append(int data, int row, int column) {
        DoubleSquare doubleSquare = new DoubleSquare(data, row, column);
        if (this.head == null) {
            this.head = this.tail = doubleSquare;
        } else {
            this.tail.setNext(doubleSquare);
            doubleSquare.setPrev(this.tail);
            this.tail = doubleSquare;
        }
        this.tail.setNext(this.head);
        this.head.setPrev(this.tail);
        this.length++;
    }

    /**
     * This method returns the first element in the list
     * @return the head attribute
     */
    @Override
    public Square getHead() {
        return this.head;
    }

    /**
     * Iterates the list until the entered index, and returns the Square in that index
     * @param index The desired index
     * @return a Square element in the desired index
     */
    @Override
    public Square getElement(int index) {
        Square tmp = this.head;
        for (int i = 0; i < index; i++) {
            tmp = tmp.getNext();
        }
        return tmp;
    }
}
