package structures;

public class CircularDoublyLinkedList extends List {
    DoubleSquare head;
    DoubleSquare tail;

    public void append(int data) {
        DoubleSquare doubleSquare = new DoubleSquare(data);
        if (this.head == null) {
            this.head = this.tail = doubleSquare;
        }
        else {
            this.tail.setNext(doubleSquare);
            doubleSquare.setPrev(this.tail);
            this.tail = doubleSquare;
        }
        this.tail.setNext(this.head);
        this.head.setPrev(this.tail);
        this.length++;
    }

    public void printList() {
        StringBuilder list = new StringBuilder("[");
        DoubleSquare tmp = this.head;
        while (tmp != this.tail) {
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
            if (tmp != this.tail) {
                list.append(", ");
            }
            tmp = tmp.getNext();
        }
        list.append("]");
        System.out.println(list);
    }
}