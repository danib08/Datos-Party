package com.structures;

/**
 * A list formed by Square objects, in which the last element is linked to the first
 */
public class CircularSinglyLinkedList extends List {

    /**
     * Creates a CircularSinglyLinkedList
     */
    public CircularSinglyLinkedList() {
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
        this.tail.setNext(this.head);
        this.length++;
    }
}
