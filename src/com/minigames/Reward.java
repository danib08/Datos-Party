package com.minigames;

import com.gameLogic.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


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

    /**
     * method that is invoked when the reward scene is created, to set the necessary values to the
     * inner fields so it works properly.
     * @param players the player array carried over by the main scene from the game Stage, that contains
     *                the initial player instances.
     */
    public void initData(Player[] players){
        this.players = players;
        this.pAmount = players.length;
        fxTextFields = new TextField[pAmount];
        minigamePrize = new int[pAmount];

        name1 = players[0].getName();
        name2 = players[1].getName();

        if (pAmount >= 3){
            name3 = players[2].getName();
            fxTextFields[2] = prizeGrid3;
            if (pAmount == 4){
                name4 = players[3].getName();
                fxTextFields[3] = prizeGrid4;
            }
        }



        playerGrid1.setText(name1);
        playerGrid2.setText(name2);

        fxTextFields[0] = prizeGrid1;
        fxTextFields[1] = prizeGrid2;
        fxTextFields[2] = prizeGrid3;
        fxTextFields[3] = prizeGrid4;

        if (pAmount <= 3){
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
    /**
     * this method is responsible for updating the player attributes with their "prize earnings" for each minigame
     * they play.
     * @param toReward a Player-type array modified to bring the initial instances ordered by performance in the
     *                 minigame.
     */
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

    public void exfil(ActionEvent event){
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.close();
    }

}
