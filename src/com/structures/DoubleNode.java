package com.structures;

public class DoubleNode {
    int data;
    DoubleNode next;
    DoubleNode prev;

    public DoubleNode(int data) {
        this.data = data;
    }

    public int getData() {
        return this.data;
    }

    public String convertToString() {
        return Integer.toString(this.data);
    }

    public void setData(int newData) {
        this.data = newData;
    }
}
