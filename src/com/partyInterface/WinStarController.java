package com.partyInterface;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class WinStarController {

    @FXML protected Label amountText;

    /**
     * Sets the amountText label value
     * @param stars the value to be assigned
     */
    public void initData(int stars){
        this.amountText.setText(Integer.toString(stars));
    }
}
