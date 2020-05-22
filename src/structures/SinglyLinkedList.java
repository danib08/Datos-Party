package structures;

/**
 * A list formed by Square objects
 */
public class SinglyLinkedList extends List {
    private Square head;
    private Square tail;

    /**
     * Creates a SinglyLinkedList
     */
    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public void append(int data) {
        Square square = new Square(data);
        if (this.head == null) {
            this.head = this.tail = square;
        }
        else {
            this.tail.setNext(square);
            this.tail = square;
        }
        this.length++;
    }

    public void printList() {
        StringBuilder list = new StringBuilder("[");
        Square tmp = this.head;
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
}
