package com.partyInterface;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class WinCoinsController {

    @FXML
    Label changeText;

    public void initData(boolean obtain){
        if (obtain){
            changeText.setText("win");
        }
        else {
            changeText.setText("lose");
        }
    }
}
