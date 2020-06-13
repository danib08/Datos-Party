package com.structures;

/**
 * A list formed by DoubleSquare objects
 */
public class DoublyLinkedList extends List {
    private DoubleSquare tail;

    /**
     * Creates a DoublyLinkedList
     */
    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public void append(int data, int row, int column) {
        DoubleSquare doubleSquare = new DoubleSquare(data, row, column);
        if (this.head == null) {
            this.head = this.tail = doubleSquare;
        }
        else {
            this.tail.setNext(doubleSquare);
            doubleSquare.setPrev(this.tail);
            this.tail = doubleSquare;
        }
        this.length++;
    }

    /**
     * Getter for the tail attribute
     * @return a Double Square object
     */
    @Override
    public DoubleSquare getTail() {
        return this.tail;
    }
}
