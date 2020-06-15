package com.minigames.bombGame;

import com.gameLogic.Player;
import com.minigames.Reward;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

/**
 * This class controlls the bombGame2.fxml
 */
public class BombGame2{
    @FXML ImageView player1bomb;
    @FXML ImageView player2bomb;
    @FXML ImageView player3bomb;
    @FXML ImageView player4bomb;

    @FXML Button createData;
    @FXML Button stepButton;
    @FXML Button finishButton;

    @FXML TextField explodedText;
    Player[] players;
    Player[] toReward;
    int i;
    int pAmount;
    int call = 1;




    public void startGame(){
        this.pAmount = this.players.length;
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
        while (i >= 0){
            int x = toBlowUp.nextInt(pAmount);

            if (!detonators[x].detonated()){
                detonators[x].setExploded(true);
                toReward[i] = detonators[x].getSelector();
                i--;
            }
        }

    }

    public void updateData(ActionEvent event) throws FileNotFoundException{
        FileInputStream image = new FileInputStream("src/com/images/explosion.png");
        Image xplode = new Image(image);
            switch (call) {
                case 1:
                    player1bomb.setImage(xplode);
                    explodedText.setText("detonated first: " + toReward[pAmount-1].getName());
                    break;

                case 2:
                    player2bomb.setImage(xplode);
                    explodedText.setText("detonated second: " + toReward[pAmount-2].getName());
                    if (call ==pAmount){
                        stepButton.setDisable(true);
                        stepButton.setVisible(false);
                        finishButton.setVisible(true);
                    }
                    break;

                case 3:
                    player3bomb.setImage(xplode);
                    explodedText.setText("detonated third: " + toReward[pAmount-3].getName());
                    if (call ==pAmount){
                        stepButton.setDisable(true);
                        stepButton.setVisible(false);
                        finishButton.setVisible(true);
                    }
                    break;

                case 4:
                    player4bomb.setImage(xplode);
                    explodedText.setText("detonated last: " + toReward[pAmount-4].getName());
                    if (call ==pAmount){
                        stepButton.setDisable(true);
                        stepButton.setVisible(false);
                        finishButton.setVisible(true);
                    }
                    break;
        }
        call++;
    }

    public void initData(Player[] players) {
        this.players = players;
        this.pAmount = players.length;
        stepButton.setVisible(false);
        finishButton.setVisible(false);
        explodedText.setVisible(false);
        if (pAmount <=3){
            player4bomb.setVisible(false);
            if (pAmount == 2){
                player3bomb.setVisible(false);
            }
        }
    }
    public void goReward(ActionEvent buttonClick) throws IOException {
        FXMLLoader rewardLoader = new FXMLLoader();
        rewardLoader.setLocation(getClass().getResource("/com/minigames/reward.fxml"));

        Parent rewardParent = rewardLoader.load();
        Scene rewardScene = new Scene(rewardParent);
        Reward controller = rewardLoader.getController();
        controller.initData(toReward);
        Stage window = (Stage) ((Node)buttonClick.getSource()).getScene().getWindow();
        //takes the obtained Stage and changes its Scene to the new fxml file.
        window.setScene(rewardScene);
        window.show();
    }
}
