package com.partyInterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * This class controls the StarInterface.fxml
 */
public class StarController {
    private boolean response;

    @FXML Label buyText;
    @FXML Button noStarB;
    @FXML Button yesStarB;

    /**
     * Checks if the player has enough coins to buy the star
     * @param coins The amount of coins the player has
     */
    public void initData(int coins){
        if (coins >= 15){
            buyText.setText("Do you want to buy it?");
            buyText.setLayoutX(100);
            yesStarB.setDisable(false);
        }
        else {
            buyText.setText("You don't have enough coins.");
            buyText.setLayoutX(75);
            yesStarB.setDisable(true);
        }
    }

    /**
     * Getter for the response attribute
     * @return a boolean
     */
    public boolean getResponse() {
        return this.response;
    }

    /**
     * Changes the attribute response to false and closes the window
     * @param event A click on the noStarB
     */
    public void pressNo(ActionEvent event){
        response = false;
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.close();
    }

    /**
     * Changes the attribute response to true and closes the window
     * @param event A click on the yesStarB
     */
    public void pressYes(ActionEvent event){
        response = true;
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.close();
    }
}
