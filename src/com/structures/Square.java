package com.structures;

/**
 * Node for the simply linked lists that acts as a square for the game board
 */
public class Square {
    protected int data;
    protected Square next;

    /**
     * Creates a Square object
     * @param data This is the value that the Square will contain
     */
    public Square(int data) {
        this.data = data;
        this.next = null;
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
    public Square getNext() {
        return this.next;
    }

    /**
     * This method is used to change the value of the next attribute
     * @param square The new next pointer that will be assigned
     */
    public void setNext(Square square) {
        this.next = square;
    }

    /**
     * This method returns null when called
     * Its purpose is to make the Square and DoubleSquare classes compatible
     * @return null
     */
    public Square getPrev() {
        return null;
    }

    /**
     * Converts the data attribute from an integer to a String
     * @return A String that represents the data value
     */
    public String convertToString() {
        return Integer.toString(this.data);
    }
}
