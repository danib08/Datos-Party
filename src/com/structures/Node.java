package com.structures;

public class Node {
    private int data;
    private Node next;

    Node(int data) {
        this.data = data;
    }

    int getData() {
        return this.data;
    }

    Node getNext() {
        return this.next;
    }

    void setNext(Node node) {
        this.next = node;
    }

    String convertToString() {
        return Integer.toString(this.data);
    }

    void setData(int newData) {
        this.data = newData;
    }
}
