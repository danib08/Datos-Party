package com.structures;

public class Square {
    private int data;
    private Square next;

    public Square(int data) {
        this.data = data;
        this.next = null;
    }

    public int getData() {
        return this.data;
    }

    public void setData(int newData) {
        this.data = newData;
    }

    public Square getNext() {
        return this.next;
    }

    public void setNext(Square square) {
        this.next = square;
    }

    public String convertToString() {
        return Integer.toString(this.data);
    }
}
