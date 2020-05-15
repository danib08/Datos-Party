package com.structures;

public class SinglyLinkedList extends List {
    private Square head;

    public SinglyLinkedList() {
        this.head = null;
    }

    public void append(int data) {
        Square square = new Square(data);
        if (this.head == null) {
            this.head = square;
        }
        else {
            Square tmp = this.head;
            while (tmp.getNext() != null) {
                tmp = tmp.getNext();
            }
            tmp.setNext(square);
        }
        this.length++;
    }

    public void insert(int data, int i) {
        if (i == this.length) {
            this.append(data);
        }
        else if (i < length && i >= 0) {
            Square square = new Square(data);
            if (i == 0) {
                square.setNext(this.head);
                this.head = square;
            }
            else {
                int count = 0;
                Square tmp = this.head;
                while (count < i - 1) {
                    tmp = tmp.getNext();
                    count++;
                }
                square.setNext(tmp.getNext());
                tmp.setNext(square);
            }
            this.length++;
        }
        else {
            System.out.println("Invalid index");
        }
    }

    public void delete(int i) {
        if (i >= 0 && i < this.length) {
            if (i == 0) {
                this.head = this.head.getNext();
            }
            else {
                int count = 0;
                Square tmp = this.head;
                while (count < i - 1) {
                    tmp = tmp.getNext();
                    count++;
                }
                tmp.setNext(tmp.getNext().getNext());
            }
            this.length--;
        }
        else {
            System.out.println("Invalid index");
        }
    }

    public int pop(int i) {
        int value;
        if (i >= 0 && i < this.length) {
            if (i == 0) {
                value = this.head.getData();
                this.head = this.head.getNext();
            }
            else {
                int count = 0;
                Square tmp = this.head;
                while (count < i - 1) {
                    tmp = tmp.getNext();
                    count++;
                }
                value = tmp.getNext().getData();
                tmp.setNext(tmp.getNext().getNext());
            }
            this.length--;
        }
        else {
            System.out.println("Invalid index");
            value = -1;
        }
        return value;
    }

    public void printList() {
        StringBuilder list = new StringBuilder("[");
        Square tmp = this.head;
        while (tmp != null) {
            list.append(tmp.convertToString());
            if (tmp.getNext() != null) {
                list.append(", ");
            }
            tmp = tmp.getNext();
        }
        list.append("]");
        System.out.println(list);
    }
}
