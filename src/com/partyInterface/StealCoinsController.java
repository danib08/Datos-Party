package com.partyInterface;

import com.gameLogic.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StealCoinsController {

    @FXML protected Button button1;
    @FXML protected Button button2;
    @FXML protected Button button3;
    @FXML protected Button button4;

    protected Button[] buttons;
    protected int playerTargetIndex;

    /**
     * Initializes the button array and enables certain buttons
     * @param currentPlayer the player that activated the event
     * @param playerArray the player array of the game
     */
    public void initData(int currentPlayer, Player[] playerArray){
        buttons = new Button[playerArray.length];
        buttons[0] = button1;
        buttons[1] = button2;

        if (playerArray.length >= 3) {
            buttons[2] = button3;
            if (playerArray.length == 4) {
                buttons[3] = button4;
            }
        }

        for (int i = 0; i < buttons.length; i++) {
            if (i != currentPlayer && playerArray[i].getCoins() != 0) {
                buttons[i].setDisable(false);
            }
        }
    }

    /**
     * Changes the playerTargetIndex attribute's value to 0
     * @param event Receives a click on the button1
     */
    public void press1(ActionEvent event){
        this.playerTargetIndex = 0;
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.close();
    }

    /**
     * Changes the playerTargetIndex attribute's value to 1
     * @param event Receives a click on the button2
     */
    public void press2(ActionEvent event){
        this.playerTargetIndex = 1;
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.close();
    }

    /**
     * Changes the playerTargetIndex attribute's value to 2
     * @param event Receives a click on the button3
     */
    public void press3(ActionEvent event){
        this.playerTargetIndex = 2;
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.close();
    }

    /**
     * Changes the playerTargetIndex attribute's value to 3
     * @param event Receives a click on the button4
     */
    public void press4(ActionEvent event){
        this.playerTargetIndex = 3;
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.close();
    }

    /**
     * Acces the playerTargetIndex
     * @return an integer
     */
    public int getPlayerTargetIndex() {
        return this.playerTargetIndex;
    }
}
