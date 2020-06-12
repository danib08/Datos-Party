package com.partyInterface;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StealCoinsController {

    int coinAmount;
    String target;

    @FXML
    protected Label targetText;
    @FXML
    protected Label coinAmountText;

    public void initData(int coinAmount, String target){
        this.coinAmount = coinAmount;
        this.target = target;
        this.coinAmountText.setText(Integer.toString(this.coinAmount));
        this.targetText.setText(this.target);
    }
}
