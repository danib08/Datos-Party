package com.structures;

public class CircularSimpleList {
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
            while (tmp.next != this.head) {
                tmp = tmp.next;
            }
            tmp.next = node;
        }
        node.next = this.head;
        this.length++;
    }

    public void insert(int data, int i) {
        if (i == this.length) {
            this.append(data);
        }
        else if (i <= length && i >= 0) {
            Node node = new Node(data);
            if (i == 0) {
                Node tmp = this.head;
                while (tmp.next != this.head) {
                    tmp = tmp.next;
                }
                node.next = this.head;
                tmp.next = node;
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
                Node tmp = this.head;
                while (tmp.next != this.head) {
                    tmp = tmp.next;
                }
                this.head = this.head.next;
                tmp.next = this.head;
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
                Node tmp = this.head;
                while (tmp.next != this.head) {
                    tmp = tmp.next;
                }
                value = this.head.getData();
                this.head = this.head.next;
                tmp.next = this.head;
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
            value = -1;
        }
        return value;
    }

    public void printList() {
        StringBuilder list = new StringBuilder("[");
        Node tmp = this.head;
        while (tmp.next != this.head) {
            list.append(tmp.convertToString());
            list.append(", ");
            tmp = tmp.next;
        }
        list.append(tmp.convertToString());
        list.append("]");
        System.out.println(list);
    }
}

