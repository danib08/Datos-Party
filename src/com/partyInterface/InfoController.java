package com.partyInterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class InfoController {


    /**
     * This method is called when the Return to Menu button is clicked, and shows the Menu scene
     * @param event Receives a click on the button
     * @throws IOException if a file described in the loaders cannot be found/read/loaded.
     */
    public void changeToMenu(ActionEvent event) throws IOException{
        Parent menuParent = FXMLLoader.load(getClass().getResource("MenuUI.fxml"));
        Scene menuScene = new Scene(menuParent);

        // This gets the stage information.
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(menuScene);
        window.show();
    }
}
