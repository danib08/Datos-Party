package com.structures;

public class DoubleNode {
    private int data;
    private DoubleNode next;
    private DoubleNode prev;

    DoubleNode(int data) {
        this.data = data;
    }

    int getData() {
        return this.data;
    }

    void setData(int newData) {
        this.data = newData;
    }

    DoubleNode getNext() {
        return this.next;
    }

    void setNext(DoubleNode node) {
        this.next = node;
    }

    DoubleNode getPrev() {
        return this.prev;
    }

    void setPrev(DoubleNode node) {
        this.prev = node;
    }

    String convertToString() {
        return Integer.toString(this.data);
    }

}
