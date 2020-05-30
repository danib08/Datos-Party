package com.gameLogic;

import com.structures.List;
import com.structures.Square;
import java.util.Random;

/**
 * This class represents an in-game Star
 * The Singleton pattern is applied here
 */
final class Star {
    private static Star star;
    private List path;
    private Square position;

    private Star() { }

    /**
     * Checks if a Star object is created. If it is, it does nothing. If not, it creates it.
     * @return the unique Star instance
     */
    public static synchronized Star getStar() {
        if (star == null) {
            star = new Star();
            System.out.println("Star instantiated");
        }
        else {
            System.out.println("Star already created");
        }
        return star;
    }

    public void positionStar(List[] listArray) {
        //TODO: make sure the circular closed path isn't in the array
        Random random = new Random();
        int index = random.nextInt(listArray.length);
        this.path = listArray[index];

        int pathIndex = random.nextInt(this.path.getLength());

    }

    /**
     * Getter for the path attribute
     * @return a List object that represents the path in which the Star is positioned
     */
    public List getPath() {
        return this.path;
    }

    /**
     * Getter for the position attribute
     * @return a Square object that represents the cell in which the Star is positioned
     */
    public Square getPosition() {
        return this.position;
    }
}
