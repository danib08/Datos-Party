package com.partyInterface;

import com.gameLogic.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * this class controls the finalWindow.fxml file.
 */
public class finalController {
    //name fields
    @FXML TextField p1nameField;
    @FXML TextField p2nameField;
    @FXML TextField p3nameField;
    @FXML TextField p4nameField;

    //star fields
    @FXML TextField p1starsField;
    @FXML TextField p2starsField;
    @FXML TextField p3starsField;
    @FXML TextField p4starsField;

    //coin fields
    @FXML TextField p1coinsField;
    @FXML TextField p2coinsField;
    @FXML TextField p3coinsField;
    @FXML TextField p4coinsField;

    @FXML Button closeButton;

    Player[] players;
    Player[] playerArr;

    int pAmount;

    int[] starArr;

    int[] playerIndexes;

    /**
     * main method for the controller class, called to initialize fields to their respective value.
     * @param playerArr a player array carrying the original Player-type instances that will be used in this class.
     */
    public void initData(Player[] playerArr){
        this.pAmount = playerArr.length;
        this.playerArr =playerArr;
        this.players = new Player[pAmount];
        this.starArr = new int[pAmount];
        for (int i = 0; i < pAmount; i++) {
            starArr[i] = playerArr[i].getStars();
        }
        sortArray(starArr);
        for (int j = 0; j <pAmount; j++) {
            players[j] = playerArr[playerIndexes[j]];
        }

        //setting the name values
        p1nameField.setText(players[0].getName());
        p2nameField.setText(players[1].getName());

        //setting the star and coin values
        p1starsField.setText(Integer.toString(players[0].getStars()));
        p1coinsField.setText(Integer.toString(players[0].getCoins()));

        p2starsField.setText(Integer.toString(players[1].getStars()));
        p2coinsField.setText(Integer.toString(players[1].getCoins()));

        //in case that there are only two players, sets the Textfields for p3 and p4 as hidden.
        if (pAmount == 2){
            p3nameField.setVisible(false);
            p3coinsField.setVisible(false);
            p3starsField.setVisible(false);

            p4nameField.setVisible(false);
            p4starsField.setVisible(false);
            p4coinsField.setVisible(false);
        }

        if (pAmount >= 3){
            p3nameField.setText(players[2].getName());

            p3starsField.setText(Integer.toString(players[2].getStars()));
            p3coinsField.setText(Integer.toString(players[2].getCoins()));

            //as there are actually three players for this case, the respective fields have to be shown.
            p3nameField.setVisible(true);
            p3coinsField.setVisible(true);
            p3starsField.setVisible(true);
            if (pAmount == 4){
                p4nameField.setText(players[3].getName());

                p4starsField.setText(Integer.toString(players[3].getStars()));
                p4coinsField.setText(Integer.toString(players[3].getCoins()));

                //as there are a true max of players (4), the respective fields are shown.
                p4nameField.setVisible(true);
                p4starsField.setVisible(true);
                p4coinsField.setVisible(true);
            }
        }
    }

    /**
     * method bound to a button that closes the window when capturing a button click event.
     * @param buttonClick the caught event used to get resources from the window in which the event happened.
     */
    public void closeWindow(ActionEvent buttonClick){
        //obtains the Stage(window) in which the scene is loaded.
        Stage window = (Stage) ((Node)buttonClick.getSource()).getScene().getWindow();
        window.close();
    }

    /**
     * method invoked by initData() to get a 'key' of sorted indexes in the form of an array.
     * @param stars the star array containing a copy of each player's star amount value.
     */
    private void sortArray(int[] stars) {
        playerIndexes = new int[pAmount];
        for (int i = 0; i < pAmount; i++) {
            playerIndexes[i] = getLargest(stars,i);
        }
    }

    /**
     * method that gets invoked to get the index for the largest values in a int-typed array.
     * @param starArray the integer array containing a copy of each player's star values.
     * @param call carries the integer value for the iteration counter that tells any method calls
     *             on which iteration it currently places itself.
     * @return integer value for the index in which the largest star value is located. If the star amount
     * happens to be the same, it compares with the coin amount.
     */
    private int getLargest(int[] starArray,int call){
        int [] values = {-1,-2,-3,-4};
        int tempStar = starArray[0];
        int index = 0;
        for (int i = 1; i < starArray.length; i++) {
            if (starArray[i] > tempStar) {
                tempStar = starArray[i];
                index = i;
            }
            else if (starArray[i] == tempStar){
                 boolean changedIndex = checkCoins(this.playerArr[index],this.playerArr[i]);
                 if (changedIndex){
                     tempStar = starArray[i];
                     index = i;
                 }
            }
        }
        starArray[index] = values[call];
        return index;
    }

    /**
     * this method checks the amount of coins the two players have, and returns a value depending on the result.
     * @param p1 a first Player instance to be a part of the comparison.
     * @param p2 the second Player instance that gets compared by the method.
     * @return boolean value 'false' if the first player has more coins, 'true' if it's the second one.
     */
    private boolean checkCoins(Player p1, Player p2){
        if (p1.getCoins() > p2.getCoins()){
            return false;
        }
        return true;
    }
}
