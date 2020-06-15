package com.partyInterface;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * This class contains the DonateCoins.fxml
 */
public class DonateCoinsController {

    protected int coinAmount;

    @FXML protected Label coinAmountText;

    /**
     * Sets the value for the coinAmountText label
     * @param coinAmount The value to be set
     */
    public void initData(int coinAmount){
        this.coinAmount = coinAmount;
        this.coinAmountText.setText(Integer.toString(this.coinAmount));
    }
}
