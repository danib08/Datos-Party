package com.minigames;

import com.gameLogic.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import javax.xml.soap.Text;

public class Reward {
    @FXML GridPane minigameGrid;

    @FXML TextField playerGrid1;
    @FXML TextField playerGrid2;
    @FXML TextField playerGrid3;
    @FXML TextField playerGrid4;

    @FXML TextField prizeGrid1;
    @FXML TextField prizeGrid2;
    @FXML TextField prizeGrid3;
    @FXML TextField prizeGrid4;

    @FXML Button closeStageButton;

    protected Player[] players;
    protected int[] minigamePrize;
    private TextField[] fxTextFields;

    protected int pAmount;

    String name1;
    String name2;
    String name3;
    String name4;

    public void initData(Player[] players){
        this.players = players;
        this.pAmount = players.length;
        name1 = players[0].getName();
        name2 = players[1].getName();
        name3 = players[2].getName();
        name4 = players[3].getName();

        minigamePrize = new int[pAmount];
        fxTextFields = new TextField[pAmount];

        playerGrid1.setText(name1);
        playerGrid2.setText(name2);

        if (pAmount >=3){
            playerGrid4.setVisible(false);
            prizeGrid4.setVisible(false);

            if (pAmount == 2){
                playerGrid3.setVisible(false);
                prizeGrid3.setVisible(false);
            }
        }
        playerGrid3.setText(name3);
        playerGrid4.setText(name4);

        this.reward(players);
        for (int i = 0; i < pAmount;i++){
            fxTextFields[i].setText(String.valueOf(this.minigamePrize[i]));
        }

    }
    public void reward(Player[] toReward){
        int coins = 12;
        int len = players.length;
        int take = coins / len;

        int prevCoins;
        int currCoins;
        for (int i = 0; i < pAmount; i++) {
            prevCoins = toReward[i].getCoins();
            toReward[i].updateCoins(take);
            currCoins = toReward[i].getCoins();
            minigamePrize[i] = currCoins - prevCoins;
        }
    }

    public void exfil(){
        
    }

}
