package com.gameLogic;

import com.structures.List;
import com.structures.Square;
import java.util.Random;

/**
 * This class represents an in-game Star. The Singleton pattern is applied here.
 */
public final class Star {
    // The Star instance is private to make sure it can't be accessed globally
    private static Star star;
    private Square position;
    private final List[] listArray;
    private boolean alreadyPositioned;

    /**
     * Constructor is private to prevent additional instances
     * @param listArray an array with all of the paths in the game
     */
    private Star(List[] listArray) {
        this.listArray = listArray;
        this.alreadyPositioned = false;
    }

    /**
     * Checks if a Star object is created. If it is, it does nothing. If not, it creates it.
     * The static keyword lets the method be called without a Star instance already created.
     * The synchronized keyword makes the Singleton pattern safe in multi-threaded environments.
     * @return the unique Star instance
     */
    public static synchronized Star getStar(List[] listArray) {
        if (star == null) {
            star = new Star(listArray);
        }
        return star;
    }

    /**
     * Positions the star on a random path from the listArray, and on a random Square from the selected listArray.
     * If it's the star's the first time being positioned, the first 10 Squares from the mainBoard are excluded from the
     * possible locations.
     */
    public void positionStar() {
        if (this.alreadyPositioned = false) {
            this.alreadyPositioned = true;
        }

        Random random = new Random();
        int arrayIndex = random.nextInt(this.listArray.length - 1);
        List path = this.listArray[arrayIndex];
        int pathIndex;

        if (!this.alreadyPositioned && path.getLength() == 36) {
            pathIndex = random.nextInt(26) + 10;
        }
        else {
            pathIndex = random.nextInt(path.getLength());
        }
        this.position = path.getElement(pathIndex);
    }

    /**
     * Getter for the position attribute
     * @return a Square object that represents the cell in which the Star is positioned
     */
    public Square getPosition() {
        return this.position;
    }
}
