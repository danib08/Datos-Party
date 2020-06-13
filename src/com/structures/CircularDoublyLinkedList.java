package com.structures;

/**
 * A list formed by DoubleSquare objects, in which the last element is linked to the first
 */
public class CircularDoublyLinkedList extends List {
    DoubleSquare head;
    DoubleSquare tail;

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

    /**
     * Shows all of the elements that are the previous of another, as a list
     * in the console in an organized way
     */
    public void printPrev() {
        StringBuilder list = new StringBuilder("[");
        list.append(this.head.getPrev().convertToString());
        list.append(", ");
        Square tmp = this.head.getNext();
        while (tmp != this.head) {
            list.append(tmp.getPrev().convertToString());
            if (tmp != this.tail) {
                list.append(", ");
            }
            tmp = tmp.getNext();
        }
        list.append("]");
        System.out.println(list);
    }

    /**
     * This method returns the first element in the list
     * @return the head attribute
     */
    @Override
    public Square getHead() {
        return this.head;
    }

    @Override
    public Square getElement(int index) {
        Square tmp = this.head;
        for (int i = 0; i < index; i++) {
            tmp = tmp.getNext();
        }
        return tmp;
    }

    public static void main(String[] args) {
        CircularDoublyLinkedList d = new CircularDoublyLinkedList();
        d.append(0,0, 0);
        d.append(1,0, 0);
        d.append(2,0, 0);
        System.out.println(d.getElement(0));
    }
}
