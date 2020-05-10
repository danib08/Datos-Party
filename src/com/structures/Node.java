package com.structures;

public class Node {
    int data;
    Node next;

    public Node(int data) {
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
