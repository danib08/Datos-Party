package com.partyInterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;



public class PathSelController  {
    private static boolean response;
    @FXML
    Button yesButton;
    @FXML
    Button noButton;

    public void pressNo(ActionEvent event){
        response = false;
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.close();
    }

    public void pressYes(ActionEvent event){
        response = true;
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.close();
    }

    public static boolean isResponse() {
        return response;
    }
}