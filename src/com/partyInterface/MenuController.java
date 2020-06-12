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

    protected @FXML Button infoButton;
    protected @FXML Button basicsButton;
    protected @FXML Button gameButton;

    /**
     * This method is called when the infoButton is clicked and shows the Info scene
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
     * This method is called when the basicsButton is clicked and shows the Basics scene
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


    /**
     * This method is called when the gameButton is clicked, and shows the PreGame scene
     * @param event Receives a click on the button
     * @throws IOException
     */
    public void changeToPreGame(ActionEvent event) throws IOException{
        Parent preGameParent = FXMLLoader.load(getClass().getResource("preGameMenu.fxml"));
        Scene preGameScene = new Scene(preGameParent);

        // This gets the stage information.
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(preGameScene);
        window.show();
    }
}
