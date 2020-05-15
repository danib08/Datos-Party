package com.structures;

public class CircularDoublyLinkedList extends List {
    DoubleSquare head;

    public void append(int data) {
        DoubleSquare doubleSquare = new DoubleSquare(data);
        if (this.head == null) {
            this.head = doubleSquare;
        }
        else {
            DoubleSquare tmp = this.head;
            while (tmp.getNext() != this.head) {
                tmp = tmp.getNext();
            }
            doubleSquare.setPrev(tmp);
            tmp.setNext(doubleSquare);
        }
        doubleSquare.setNext(this.head);
        this.head.setPrev(doubleSquare);
        this.length++;
    }

    public void insert(int data, int i) {
        if (i == this.length) {
            this.append(data);
        }
        else if (i <= length && i >= 0) {
            DoubleSquare doubleSquare = new DoubleSquare(data);
            if (i == 0) {
                DoubleSquare tmp = this.head;
                while (tmp.getNext() != this.head) {
                    tmp = tmp.getNext();
                }
                doubleSquare.setNext(this.head);
                doubleSquare.setPrev(tmp);
                doubleSquare.getNext().setPrev(doubleSquare);
                tmp.setNext(doubleSquare);
                this.head = doubleSquare;
            }
            else {
                int count = 0;
                DoubleSquare tmp = this.head;
                while (count < i - 1) {
                    tmp = tmp.getNext();
                    count++;
                }
                doubleSquare.setNext(tmp.getNext());
                tmp.getNext().setPrev(doubleSquare);
                tmp.setNext(doubleSquare);
                doubleSquare.setPrev(tmp);
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
                DoubleSquare tmp = this.head;
                while (tmp.getNext() != this.head) {
                    tmp = tmp.getNext();
                }
                this.head = this.head.getNext();
                this.head.setPrev(tmp);
                tmp.setNext(this.head);
            }
            else {
                int count = 0;
                DoubleSquare tmp = this.head;
                while (count < i - 1) {
                    tmp = tmp.getNext();
                    count++;
                }
                tmp.setNext(tmp.getNext().getNext());
                tmp.getNext().setPrev(tmp);
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
                DoubleSquare tmp = this.head;
                while (tmp.getNext() != this.head) {
                    tmp = tmp.getNext();
                }
                value = this.head.getData();
                this.head = this.head.getNext();
                this.head.setPrev(tmp);
                tmp.setNext(this.head);
            }
            else {
                int count = 0;
                DoubleSquare tmp = this.head;
                while (count < i - 1) {
                    tmp = tmp.getNext();
                    count++;
                }
                value = tmp.getNext().getData();
                tmp.setNext(tmp.getNext().getNext());
                tmp.getNext().setPrev(tmp);
            }
            this.length--;
        }
        else {
            value = -1;
            System.out.println("Invalid index");
        }
        return value;
    }

    public void printList() {
        StringBuilder list = new StringBuilder("[");
        DoubleSquare tmp = this.head;
        while (tmp.getNext() != this.head) {
            list.append(tmp.convertToString());
            list.append(", ");
            tmp = tmp.getNext();
        }
        list.append(tmp.convertToString());
        list.append("]");
        System.out.println(list);
    }

    public void printPrev() {
        StringBuilder list = new StringBuilder("[");
        list.append(this.head.getPrev().convertToString());
        list.append(", ");
        DoubleSquare tmp = this.head.getNext();
        while (tmp != this.head) {
            list.append(tmp.getPrev().convertToString());
            if (tmp.getNext() != this.head) {
                list.append(", ");
            }
            tmp = tmp.getNext();
        }
        list.append("]");
        System.out.println(list);
    }
}
