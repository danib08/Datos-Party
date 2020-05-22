package com.structures;

public class DoubleSquare {
    private int data;
    private DoubleSquare next;
    private DoubleSquare prev;

    public DoubleSquare(int data) {
        this.data = data;
        this.prev = null;
    }

    public int getData() {
        return this.data;
    }

    public void setData(int newData) {
        this.data = newData;
    }

    public DoubleSquare getNext() {
        return this.next;
    }

    public void setNext(DoubleSquare square) {
        this.next = square;
    }

    public String convertToString() {
        return Integer.toString(this.data);
    }

    public DoubleSquare getPrev() {
        return this.prev;
    }

    public void setPrev(DoubleSquare node) {
        this.prev = node;
    }
}