package com.partyInterface;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * This class controls the Swap.fxml
 */
public class SwapController {

    @FXML protected Label targetText;

    /**
     * This method sets the value for targetText
     * @param target the value to be set
     */
    public void initData(String target) {
        this.targetText.setText(target);
    }

}
