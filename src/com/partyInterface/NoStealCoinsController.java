package com.partyInterface;


import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class NoStealCoinsController {

    @FXML
    protected Label targetText;

    public void initData(String stealer){
        this.targetText.setText(stealer);
    }
}

