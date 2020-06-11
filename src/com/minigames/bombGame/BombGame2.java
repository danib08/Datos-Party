package com.minigames.bombGame;

import com.gameLogic.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class BombGame2 implements Initializable{
    @FXML ImageView player1bomb;
    @FXML ImageView player2bomb;
    @FXML ImageView player3bomb;
    @FXML ImageView player4bomb;
    @FXML Button createData;
    @FXML Button stepButton;
    @FXML TextField explodedText;
    Player[] players;
    Player[] toReward;
    int i;
    int pAmount = 3;
    int call = 1;

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stepButton.setVisible(false);
        explodedText.setVisible(false);
        if (pAmount <=3){
            player4bomb.setVisible(false);
            if (pAmount == 2){
                player3bomb.setVisible(false);
            }
        }
    }


    public void startGame(ActionEvent event) {
        //this.pAmount = this.players.length;
        createData.setVisible(false);
        stepButton.setVisible(true);
        explodedText.setVisible(true);
        Detonator[] detonators = new Detonator[pAmount];
        Detonator detonator1 = new Detonator(players[0]);
        Detonator detonator2 = new Detonator(players[1]);
        detonators[0] = detonator1;
        detonators[1] = detonator2;

        if (pAmount >=3){
            Detonator detonator3 = new Detonator(players[2]);
            detonators[2] = detonator3;
            if (pAmount  == 4){
                Detonator detonator4 = new Detonator(players[3]);
                detonators[3] = detonator4;
            }
        }
        this.toReward = new Player[pAmount];
        Random toBlowUp;
        toBlowUp = new Random();
        this.i = pAmount-1;
        while (i > -1){
            int x = toBlowUp.nextInt(pAmount);
            if (x == pAmount-1){
                x = pAmount;
            }
            if (!detonators[x].detonated()){
                detonators[x].setExploded(true);
                toReward[i] = detonators[x].getSelector();
            }
            i--;
        }
        this.reward(toReward);

    }

    public void updateData(ActionEvent event) throws FileNotFoundException{
        FileInputStream image = new FileInputStream("src/images/explosion.png");
        Image xplode = new Image(image);
        switch (call){
            case 1:
                player1bomb.setImage(xplode);
                explodedText.setText("mamaste: " + toReward[pAmount-1].getName());
                break;
            case 2:
                player2bomb.setImage(xplode);
                explodedText.setText("mamaste: " + toReward[pAmount-2].getName());
                break;
            case 3:
                player3bomb.setImage(xplode);
                explodedText.setText("mamaste: " + toReward[pAmount-3].getName());
                break;
            case 4:
                player4bomb.setImage(xplode);
                explodedText.setText("mamaste: " + toReward[pAmount-4].getName());
                break;
        }
        call++;
    }
    public void reward(Player[] players){
        int coins = 12;
        int len = players.length;
        int take = coins / len;
        for (Player player : players){
            player.updateCoins(coins);
            coins -= take;
        }
    }
    public void initData(Player[] players){
        this.players = players;
    }

}
