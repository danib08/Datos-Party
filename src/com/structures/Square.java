package com.structures;

/**
 * Node for the simply linked lists that acts as a square for the game board
 */
public class Square {
    protected int data;
    protected Square next;
    protected Square pathLink;
    protected int row;
    protected int col;

    /**
     * Creates a Square object
     * @param data This is the value that the Square will contain
     */
    public Square(int data, int row, int col) {
        this.data = data;
        this.next = null;
        this.pathLink = null;
        this.row = row;
        this.col = col;
    }

    /**
     * This method is used to access the data attribute
     * @return An integer which is the data contained in the Square
     */
    public int getData() {
        return this.data;
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
     * This method purpose is to make Square and DoubleSquare classes compatible
     * @param square Square to be added by DoubleSquare classes
     */
    public void setPrev(Square square){
    }

    /**
     * Converts the data attribute from an integer to a String
     * @return A String that represents the data value
     */
    public String convertToString() {
        return Integer.toString(this.data);
    }

    /**
     * This method is used to access the pathLink attribute
     * @return A pointer to the pathLink Square
     */
    public Square getPathLink() {
        return pathLink;
    }

    /**
     * This method is used to change the pathLink attribute
     * @param pathLink The pointer to be asigned.
     */
    public void setPathLink(Square pathLink) {
        this.pathLink = pathLink;
    }

    /**
     * This method is used to access the row attribute
     * @return A pointer to the pathLink Square
     */
    public int getRow() {
        return this.row;
    }

    /**
     * This method is used to access the column attribute
     * @return A pointer to the pathLink Square
     */
    public int getCol() {
        return this.col;
    }
}

