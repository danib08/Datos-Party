package com.structures;

public class CircularDoubleList {
    DoubleNode head;
    int length = 0;

    public int getLength() {
        return this.length;
    }

    public void append(int data) {
        DoubleNode node = new DoubleNode(data);
        if (this.head == null) {
            this.head = node;
        }
        else {
            DoubleNode tmp = this.head;
            while (tmp.next != this.head) {
                tmp = tmp.next;
            }
            node.prev = tmp;
            tmp.next = node;
        }
        node.next = this.head;
        this.head.prev = node;
        this.length++;
    }

    public void insert(int data, int i) {
        if (i == this.length) {
            this.append(data);
        }
        else if (i <= length && i >= 0) {
            DoubleNode node = new DoubleNode(data);
            if (i == 0) {
                DoubleNode tmp = this.head;
                while (tmp.next != this.head) {
                    tmp = tmp.next;
                }
                node.next = this.head;
                node.prev = tmp;
                node.next.prev = node;
                tmp.next = node;
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
                DoubleNode tmp = this.head;
                while (tmp.next != this.head) {
                    tmp = tmp.next;
                }
                this.head = this.head.next;
                this.head.prev = tmp;
                tmp.next = this.head;
            }
            else {
                int count = 0;
                DoubleNode tmp = this.head;
                while (count < i - 1) {
                    tmp = tmp.next;
                    count++;
                }
                tmp.next = tmp.next.next;
                tmp.next.prev = tmp;
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
                DoubleNode tmp = this.head;
                while (tmp.next != this.head) {
                    tmp = tmp.next;
                }
                value = this.head.getData();
                this.head = this.head.next;
                this.head.prev = tmp;
                tmp.next = this.head;
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
                tmp.next.prev = tmp;
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
        while (tmp.next != this.head) {
            list.append(tmp.convertToString());
            list.append(", ");
            tmp = tmp.next;
        }
        list.append(tmp.convertToString());
        list.append("]");
        System.out.println(list);
    }

    public void printPrev() {
        StringBuilder list = new StringBuilder("[");
        list.append(this.head.prev.convertToString());
        list.append(", ");
        DoubleNode tmp = this.head.next;
        while (tmp != this.head) {
            list.append(tmp.prev.convertToString());
            if (tmp.next != this.head) {
                list.append(", ");
            }
            tmp = tmp.next;
        }
        list.append("]");
        System.out.println(list);
    }

    public static void main(String[] args) {
        CircularDoubleList list = new CircularDoubleList();
        list.append(1);
        list.append(2);
        list.append(4);
        list.insert(0, 0);
        list.insert(3, 3);
        list.delete(4);
        list.printList();
        list.printPrev();
    }
}
