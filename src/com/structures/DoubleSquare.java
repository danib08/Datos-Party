package com.structures;

/**
 * Node for the doubly linked lists that acts as a square for the game board
 */
public class DoubleSquare extends Square {
    private DoubleSquare prev;

    /**
     * Creates a DoubleSquare object
     * @param data This is the value that the DoubleSquare will contain
     */
    public DoubleSquare(int data, int row, int column) {
        super(data, row, column);
        this.prev = null;
    }

    /**
     * This method is used to access the prev attribute
     * @return A pointer to the previous Square
     */
    @Override
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
