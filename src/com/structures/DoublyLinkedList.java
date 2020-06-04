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

    public void append(int data) {
        DoubleSquare doubleSquare = new DoubleSquare(data);
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

    @Override
    public Square getTail() {
        return this.tail;
    }

    /**
     * Shows all of the elements that are the previous of another, as a list
     * in the console in an organized way
     */
    public void printPrev() {
        StringBuilder list = new StringBuilder("[");
        Square tmp = this.head.getNext();
        while (tmp != null) {
            list.append(tmp.getPrev().convertToString());
            if (tmp != this.tail) {
                list.append(", ");
            }
            tmp = tmp.getNext();
        }
        list.append("]");
        System.out.println(list);
    }
}
