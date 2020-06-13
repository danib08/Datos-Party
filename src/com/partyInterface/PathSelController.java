package com.partyInterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * This class controls the PathSelection.fxml
 */
public class PathSelController  {
    protected boolean response;
    @FXML protected Button yesButton;
    @FXML protected Button noButton;

    /**
     * Changes the attribute response to false and closes the window
     * @param event A click on the noButton
     */
    public void pressNo(ActionEvent event){
        response = false;
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.close();
    }

    /**
     * Changes the attribute response to true and closes the window
     * @param event A click on the yesButton
     */
    public void pressYes(ActionEvent event){
        response = true;
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.close();
    }

    /**
     * Getter for the response attribute
     * @return a boolean
     */
    public boolean getResponse() {
        return response;
    }
}