package com.structures;

public abstract class List {
    protected int length;

    List() {
        this.length = 0;
    }

    public int getLength() {
        return this.length;
    }

    public abstract void append(int data);

    public abstract void insert(int data, int i);

    public abstract void delete(int i);

    public abstract int pop(int i);

    public abstract void printList();
}


