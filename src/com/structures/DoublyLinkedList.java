package com.structures;

public class DoublyLinkedList {
    DoubleNode head;
    int length = 0;

    public int getLength() {
        return this.length;
    }

    public void append(int data) {
        DoubleNode node = new DoubleNode(data);
        if (this.head == null){
            this.head = node;
        }
        else {
            DoubleNode tmp = this.head;
            while (tmp.next != null) {
                tmp = tmp.next;
            }
            node.prev = tmp;
            tmp.next = node;
        }
        this.length++;
    }

    public void insert(int data, int i) {
        if (i == this.length) {
            this.append(data);
        }
        else if (i < length && i >= 0) {
            DoubleNode node = new DoubleNode(data);
            if (i == 0) {
                node.next = this.head;
                this.head.prev = node;
                this.head = node;
            }
            else {
                int count = 0;
                DoubleNode tmp = this.head;
                while (count < i - 1) {
                    tmp = tmp.next;
                    count++;
                }
                node.next = tmp.next;
                tmp.next.prev = node;
                tmp.next = node;
                node.prev = tmp;
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
                this.head.prev = null;
            }
            else {
                int count = 0;
                DoubleNode tmp = this.head;
                while (count < i - 1) {
                    tmp = tmp.next;
                    count++;
                }
                tmp.next = tmp.next.next;
                if (i != this.length - 1) {
                    tmp.next.prev = tmp;
                }
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
                this.head.prev = null;
            }
            else {
                int count = 0;
                DoubleNode tmp = this.head;
                while (count < i - 1) {
                    tmp = tmp.next;
                    count++;
                }
                value = tmp.next.getData();
                tmp.next = tmp.next.next;
                if (i != this.length - 1) {
                    tmp.next.prev = tmp;
                }
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
        DoubleNode tmp = this.head;
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

    public void printPrev() {
        StringBuilder list = new StringBuilder("[");
        DoubleNode tmp = this.head.next;
        while (tmp != null) {
            list.append(tmp.prev.convertToString());
            if (tmp.next != null) {
                list.append(", ");
            }
            tmp = tmp.next;
        }
        list.append("]");
        System.out.println(list);
    }
}
