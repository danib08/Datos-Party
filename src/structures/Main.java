package structures;

/**
 * This class will be the one in charge to create all of the lists
 */
public class Main {

    public static void main(String[] args) {
        CircularSinglyLinkedList mainBoard = new CircularSinglyLinkedList();
        SinglyLinkedList pathA = new SinglyLinkedList();
        SinglyLinkedList pathB = new SinglyLinkedList();
        DoublyLinkedList pathC = new DoublyLinkedList();
        CircularDoublyLinkedList pathD = new CircularDoublyLinkedList();

        mainBoard.append(1);
        mainBoard.append(2);
        mainBoard.append(3);
        mainBoard.append(1);
        mainBoard.append(3);
        mainBoard.append(4);
        mainBoard.append(2);
        mainBoard.append(1);
        mainBoard.append(2);
        mainBoard.append(3);
        mainBoard.append(4);
        mainBoard.append(2);
        mainBoard.append(1);
        mainBoard.append(2);
        mainBoard.append(3);
        mainBoard.append(1);
        mainBoard.append(2);
        mainBoard.append(4);
        mainBoard.append(3);
        mainBoard.append(2);
        mainBoard.append(3);
        mainBoard.append(1);
        mainBoard.append(2);
        mainBoard.append(4);
        mainBoard.append(2);
        mainBoard.append(1);
        mainBoard.append(3);
        mainBoard.append(4);
        mainBoard.append(2);
        mainBoard.append(3);
        mainBoard.append(4);
        mainBoard.append(1);
        mainBoard.append(2);
        mainBoard.append(2);
        mainBoard.append(3);
        mainBoard.append(4);

        pathA.append(2);
        pathA.append(1);
        pathA.append(3);
        pathA.append(4);
        pathA.append(2);

        pathB.append(4);
        pathB.append(4);
        pathB.append(4);
        pathB.append(4);

        pathC.append(1);
        pathC.append(2);
        pathC.append(4);
        pathC.append(3);
        pathC.append(1);
        pathC.append(2);
        pathC.append(2);
        pathC.append(4);
        pathC.append(1);
        pathC.append(3);

        pathD.append(4);
        pathD.append(4);
        pathD.append(4);
        pathD.append(4);
    }
}
