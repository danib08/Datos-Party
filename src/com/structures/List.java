package com.structures;

/**
 * Abstract class that serves as a mold for the many type of lists in this package
 */
public abstract class List {
    protected Square head;
    protected Square tail;
    protected int length;

    /**
     * Creates an abstract class List instance
     */
    List() {
        this.length = 0;
    }

    /**
     * This method is used to access the length attribute
     * @return An integer that represents the length of the list
     */
    public int getLength() {
        return this.length;
    }

    /**
     * This method returns the first element in the list
     * @return the head attribute
     */
    public Square getHead() {
        return this.head;
    }

    /**
     * Iterates the list until the entered index, and returns the Square in that index
     * @param index The desired index
     * @return a Square element in the desired index
     */
    public Square getElement(int index) {
        Square tmp = this.head;
        for (int i = 0; i < index; i++) {
            tmp = tmp.getNext();
        }
        return tmp;
    }

    /**
     * This abstract method will add an element to the back of the list
     * @param data The value that the new element will contain
     * @param row The row in which the Square will be positioned on the gridPane of the GUI
     * @param column The column in which the Square will be positioned on the gridPane of the GUI
     */
    public abstract void append(int data, int row, int column);

    /**
     * This method is used to access the tail attribute
     * @return A pointer to the tail Square
     */
    public Square getTail() {
        return this.tail;
    }
}
