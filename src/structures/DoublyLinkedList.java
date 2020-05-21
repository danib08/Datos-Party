package structures;

public class DoublyLinkedList extends List {
    DoubleSquare head;
    DoubleSquare tail;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public void append(int data) {
        DoubleSquare doubleSquare = new DoubleSquare(data);
        if (this.head == null){
            this.head = this.tail = doubleSquare;
        }
        else {
            this.tail.setNext(doubleSquare);
            doubleSquare.setPrev(this.tail);
            this.tail = doubleSquare;
        }
        this.length++;
    }

    public void printList() {
        StringBuilder list = new StringBuilder("[");
        DoubleSquare tmp = this.head;
        while (tmp != null) {
            list.append(tmp.convertToString());
            if (tmp != this.tail) {
                list.append(", ");
            }
            tmp = tmp.getNext();
        }
        list.append("]");
        System.out.println(list);
    }

    public void printPrev() {
        StringBuilder list = new StringBuilder("[");
        DoubleSquare tmp = this.head.getNext();
        while (tmp != null) {
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
