package com.structures;

/**
 * A list formed by Square objects, in which the last element is linked to the first
 */
public class CircularSinglyLinkedList extends List {
    private Square head;
    private Square tail;

    /**
     * Creates a CircularSinglyLinkedList
     */
    public CircularSinglyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public void append(int data) {
        Square square = new Square(data);
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

    public void printList() {
        StringBuilder list = new StringBuilder("[");
        Square tmp = this.head;
        while (tmp != this.tail) {
            list.append(tmp.convertToString());
            list.append(", ");
            tmp = tmp.getNext();
        }
        list.append(tmp.convertToString());
        list.append("]");
        System.out.println(list);
    }

    public Square getHead() {
        return head;
    }
}
