package com.partyInterface;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class WinCoinsController {

    @FXML private Label changeText;

    /**
     * Sets the changeText label value
     * @param obtain the value to be assigned
     */
    public void initData(boolean obtain){
        if (obtain){
            changeText.setText("win");
        }
        else {
            changeText.setText("lose");
        }
    }
}
