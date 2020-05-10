package com.structures;

public class SinglyLinkedList {
    Node head;
    int length = 0;

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
            while (tmp.next != null) {
                tmp = tmp.next;
            }
            tmp.next = node;
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
                node.next = this.head;
                this.head = node;
            }
            else {
                int count = 0;
                Node tmp = this.head;
                while (count < i - 1) {
                    tmp = tmp.next;
                    count++;
                }
                node.next = tmp.next;
                tmp.next = node;
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
                this.head = this.head.next;
            }
            else {
                int count = 0;
                Node tmp = this.head;
                while (count < i - 1) {
                    tmp = tmp.next;
                    count++;
                }
                tmp.next = tmp.next.next;
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
                this.head = this.head.next;
            }
            else {
                int count = 0;
                Node tmp = this.head;
                while (count < i - 1) {
                    tmp = tmp.next;
                    count++;
                }
                value = tmp.next.getData();
                tmp.next = tmp.next.next;
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
            if (tmp.next != null) {
                list.append(", ");
            }
            tmp = tmp.next;
        }
        list.append("]");
        System.out.println(list);
    }
}
