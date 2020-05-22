package com.structures;

/**
 * Node for the doubly linked lists that acts as a square for the game board
 */
public class DoubleSquare {
    private int data;
    private DoubleSquare next;
    private DoubleSquare prev;

    /**
     * Creates a DoubleSquare object
     * @param data This is the value that the DoubleSquare will contain
     */
    public DoubleSquare(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    /**
     * This method is used to access the data attribute
     * @return An integer which is the data contained in the Square
     */
    public int getData() {
        return this.data;
    }

    /**
     * This method is used to change the value of the data attribute
     * @param newData The new value for the data attribute
     */
    public void setData(int newData) {
        this.data = newData;
    }

    /**
     * This method is used to access the next attribute
     * @return A pointer to the next Square
     */
    public DoubleSquare getNext() {
        return this.next;
    }

    /**
     * This method is used to change the value of the next attribute
     * @param square The new next pointer that will be assigned
     */
    public void setNext(DoubleSquare square) {
        this.next = square;
    }

    /**
     * This method is used to access the prev attribute
     * @return A pointer to the previous Square
     */
    public DoubleSquare getPrev() {
        return this.prev;
    }

    /**
     * This method is used to change the value of the prev attribute
     * @param node The new prev pointer that will be assigned
     */
    public void setPrev(DoubleSquare node) {
        this.prev = node;
    }

    /**
     * Converts the data attribute from an integer to a String
     * @return A String that represents the data value
     */
    public String convertToString() {
        return Integer.toString(this.data);
    }
}
