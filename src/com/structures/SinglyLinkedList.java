package com.structures;

/**
 * A list formed by Square objects
 */
public class SinglyLinkedList extends List {
    private Square tail;

    /**
     * Creates a SinglyLinkedList
     */
    public SinglyLinkedList() {
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
        this.length++;
    }

    public void printList() {
        StringBuilder list = new StringBuilder("[");
        Square tmp = this.head;
        while (tmp != null) {
            list.append(tmp.convertToString());
            if (tmp != this.tail) {
                list.append(", ");
            }
            tmp = tmp.getNext();
        }
        list.append("]");
        System.out.println(list);
    }

    /**
     * This method will add a new element to the top of the list
     * @param data The value that the new element will contain
     */
    public void prepend(int data) {
        Square square = new Square(data);
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
