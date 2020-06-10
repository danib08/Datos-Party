package com.partyInterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.IOException;

public class MenuController {

    @FXML
    Button infoButton;
    @FXML
    Button basicsButton;

    /**
     * This method is called when the Info button is clicked, and shows the new Info scene
     * @param event Receives a click on the button
     * @throws IOException
     */
    public void changeToInfo(ActionEvent event) throws IOException {
        Parent infoParent = FXMLLoader.load(getClass().getResource("Info.fxml"));
        Scene infoScene = new Scene(infoParent);

        // This gets the stage information.
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(infoScene);
        window.show();
    }

    /**
     * This method is called when the Basics button is clicked, and shows the new Basics scene
     * @param event Receives a click on the button
     * @throws IOException
     */
    public void changeToBasic(ActionEvent event) throws IOException{
        Parent basicParent = FXMLLoader.load(getClass().getResource("Basics.fxml"));
        Scene basicsScene = new Scene(basicParent);

        // This gets the stage information.
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(basicsScene);
        window.show();
    }
}