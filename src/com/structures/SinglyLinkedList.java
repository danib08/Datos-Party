package com.structures;

public class SinglyLinkedList {
    private Node head;
    private int length = 0;

    public int getLength() {
        return this.length;
    }

    public void append(int data) {
        Node node = new Node(data);
        if (this.head == null) {
            this.head = node;
        }
        else {
            Node tmp = this.head;
            while (tmp.getNext() != null) {
                tmp = tmp.getNext();
            }
            tmp.setNext(node);
        }
        this.length++;
    }

    public void insert(int data, int i) {
        if (i == this.length) {
            this.append(data);
        }
        else if (i < length && i >= 0) {
            Node node = new Node(data);
            if (i == 0) {
                node.setNext(this.head);
                this.head = node;
            }
            else {
                int count = 0;
                Node tmp = this.head;
                while (count < i - 1) {
                    tmp = tmp.getNext();
                    count++;
                }
                node.setNext(tmp.getNext());
                tmp.setNext(node);
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
                Node tmp = this.head;
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
                Node tmp = this.head;
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
        Node tmp = this.head;
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
