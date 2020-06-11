package com.partyInterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class StarController {
    private boolean response;

    @FXML Label buyText;
    @FXML Button noStarB;
    @FXML Button yesStarB;

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

    public boolean isResponse() {
        return this.response;
    }

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
}
