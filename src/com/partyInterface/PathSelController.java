package com.partyInterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;



public class PathSelController  {
    protected  boolean response;
    @FXML protected Button yesButton;
    @FXML protected Button noButton;

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

    public boolean isResponse() {
        return response;
    }
}