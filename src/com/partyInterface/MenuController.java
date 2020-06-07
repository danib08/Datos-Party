package com.partyInterface;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
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

    public void changeToInfo(ActionEvent event) throws IOException {
        Parent infoParent = FXMLLoader.load(getClass().getResource("GameInfo.fxml"));
        Scene infoScene = new Scene(infoParent);

        // This gets the stage information.
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(infoScene);
        window.show();
    }

    public void changeToBasic(ActionEvent event) throws IOException{
        Parent basicParent = FXMLLoader.load(getClass().getResource("BasicInfo.fxml"));
        Scene basicScene = new Scene(basicParent);

        // This gets the stage information.
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(basicScene);
        window.show();
    }
}
