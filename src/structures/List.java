package structures;

public abstract class List {
    protected int length;

    List() {
        this.length = 0;
    }

    public int getLength() {
        return this.length;
    }

    public abstract void append(int data);

    public abstract void printList();
}
