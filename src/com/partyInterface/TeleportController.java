package com.partyInterface;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 * This class controls the Teleport.fxml
 */
public class TeleportController {

    private boolean answer;

    /**
     * This method is executed when the button Yes is clicked
     * It changes the answer boolean to true
     */
    public void left(ActionEvent event) {
        this.answer = true;
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.close();
    }

    /**
     * This method is executed when the button No is clicked
     * It changes the answer boolean to false
     */
    public void right(ActionEvent event) {
        this.answer = false;
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.close();
    }

    /**
     * Getter for the answer attribute
     * @return the answer attribute
     */
    public boolean getAnswer() {
        return this.answer;
    }
}

