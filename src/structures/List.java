package structures;

/**
 * Abstract class that serves as a mold for the many type of lists in this package
 */
public abstract class List {
    protected int length;

    /**
     * Creates a List abstract class
     */
    List() {
        this.length = 0;
    }

    /**
     * This method is used to access the length attribute
     * @return An integer that represents the length of the list
     */
    public int getLength() {
        return this.length;
    }

    /**
     * This abstract method will add an element to the back of the list
     * @param data The value that the new element will contain
     */
    public abstract void append(int data);

    /**
     * Shows the list in the console in an organized way
     */
    public abstract void printList();
}
