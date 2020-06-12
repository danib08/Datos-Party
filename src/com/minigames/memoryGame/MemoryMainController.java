package com.minigames.memoryGame;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MemoryMainController {

    /**
     * This method is called when the Start Game button is clicked, and shows the minigame scene
     * @param event Receives a click on the button
     * @throws IOException
     */
    public void changeToGame(ActionEvent event) throws IOException{
        Parent mentalParent = FXMLLoader.load(getClass().getResource("MemoryGame.fxml"));
        Scene mentalScene = new Scene(mentalParent);

        // This gets the stage information.
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(mentalScene);
        window.show();
    }
}
