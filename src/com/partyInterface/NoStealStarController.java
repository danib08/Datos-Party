package com.partyInterface;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class NoStealStarController {

    @FXML protected Label targetText;

    /**
     * Sets the value of the targetText label
     * @param target the value to be set
     */
    public void initData(String target) {
        this.targetText.setText(target);
    }
}
