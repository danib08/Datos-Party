package com.structures;

/**
 * A list formed by Square objects
 */
public class SinglyLinkedList extends List {

    /**
     * Creates a SinglyLinkedList
     */
    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public void append(int data, int row, int column) {
        Square square = new Square(data, row, column);
        if (this.head == null) {
            this.head = this.tail = square;
        }
        else {
            this.tail.setNext(square);
            this.tail = square;
        }
        this.length++;
    }

    /**
     * This method will add a new element to the top of the list
     * @param data The value that the new element will contain
     */
    public void prepend(int data, int row, int column) {
        Square square = new Square(data, row, column);
        if (this.head == null) {
            this.tail = square;
        }
        square.setNext(this.head);
        this.head = square;
        this.length++;
    }

    /**
     * Deletes the first element of the list and returns the value contained in it
     * @return The value that the recently popped element contained
     */
    public int popHead() {
        int headData = this.head.getData();
        this.head = this.head.getNext();
        this.length--;
        return headData;
    }
}
