package com.minigames.reactGame;

import com.gameLogic.Player;
import com.minigames.Reward;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

/**
 * This class controlls the reactGame.fxml
 */
public class reactGame2Control{
    @FXML Label label1;
    @FXML Label label2;
    @FXML Label label3;
    @FXML Label label4;
    @FXML Label labelMain;

    @FXML TextField textField1;
    @FXML TextField textField2;
    @FXML TextField textField3;
    @FXML TextField textField4;

    @FXML Button startButton;
    @FXML Button finishButton;

    Player[] players;
    Player[] toReward;
    long [] pTimes;

    long start;
    long finish;

    int pAmount;
    int i = 0;
    Random timestart;

    /**
     * Sets the initial data for the controller.
     * @param players Player array
     */
    public void initData(Player[] players){
        this.players = players;
        this.pAmount = players.length;
        textField1.setText(players[0].getName());
        textField2.setText(players[1].getName());
        if (pAmount >=3 ){
            textField3.setText(players[2].getName());
            if (pAmount == 4){
                textField4.setText(players[3].getName());
            }
        }

        this.labelMain.setDisable(true);

        if (pAmount <= 3){
            textField4.setVisible(false);
            label4.setVisible(false);
            if (pAmount == 2){
                textField3.setVisible(false);
                label3.setVisible(false);
            }
        }
        pTimes = new long[pAmount];
        labelMain.setStyle("-fx-background-color: gray");
    }

    /**
     * this method, bound to the start button, starts counting time for the player in question to register their
     * reaction time.
     * @throws InterruptedException if the thread is suddenly interrupted by a higher-priority process.
     */
    public void startRound() throws InterruptedException{
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                timestart = new Random();
                startButton.setDisable(true);
                if (i == pAmount - 1) {
                    int t = timestart.nextInt(4) + 1;
                    Thread.sleep(1000 * t);
                    labelMain.setDisable(false);
                    Platform.runLater(()->{
                        labelMain.setText("click now!");
                        labelMain.setStyle("-fx-background-color: #00ff00");
                        startButton.setVisible(false);
                        finishButton.setVisible(true);
                    });
                    start = System.currentTimeMillis();
                }
                else {
                    int t = timestart.nextInt(4) + 1;
                    Thread.sleep(1000*t);
                    labelMain.setDisable(false);
                    Platform.runLater(()->{
                        labelMain.setText("click now!");
                        labelMain.setStyle("-fx-background-color: #00ff00");
                    });
                    start = System.currentTimeMillis();
                }
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * method bound to the center label that listens for click events.
     *
     */
    public void listenClick(){
        labelMain.setDisable(true);
        startButton.setDisable(false);
        finish = System.currentTimeMillis();
        long time = finish-start;
        labelMain.setStyle("-fx-background-color: gray");
        this.pTimes[i] = time;

        switch (i) {
            case 0:
                label1.setText("time: " + time);
                break;
            case 1:
                label2.setText("time: " + time);
                if (i == pAmount-1){
                    toReward = new Player[pAmount];
                    int index;
                    for (int j = 0; j < pAmount; j++) {
                        index = getSmallest(pTimes);
                        toReward[j] = players[index];
                    }
                    updateReward(toReward);
                }
                break;
            case 2:
                label3.setText("time: " + time);
                if (i == pAmount-1) {
                    toReward = new Player[pAmount];
                    int index;
                    for (int j = 0; j < pAmount; j++) {
                        index = getSmallest(pTimes);
                        toReward[j] = players[index];
                    }
                    updateReward(toReward);
                }
                break;
            case 3:
                label4.setText("time: " + time);
                if (i == pAmount -1) {
                    toReward = new Player[pAmount];
                    int index;
                    for (int j = 0; j < pAmount; j++) {
                        index = getSmallest(pTimes);
                        toReward[j] = players[index];
                    }
                    updateReward(toReward);
                }
                break;
        }
        this.i++;
    }


    /**
     * method to obtain the smallest value in a specific array, change it to a bigger value that on a next
     * iteration, cannot be confused for a small value
     * @param pTimes a long-type array containing the player registered times.
     * @return the index-positional value for the smallest value in the ptimes array.
     */
    public int getSmallest(long[] pTimes){
        long min = pTimes[0];
        int index = 0;
        for (int i = 1; i < pTimes.length ; i++) {
            if (min > pTimes[i]){
                min = pTimes[i];
                index = i;
            }
        }
        this.pTimes[index] = 5000;
        return index;
    }

    /**
     * Updates the reward attribute
     * @param toRewardUpdate the array that is updated.
     */
    public void updateReward(Player[] toRewardUpdate){
        this.toReward = toRewardUpdate;
    }

    /**
     * Opens the reward window
     * @param buttonClick JavaFX class that is called when pressing a button
     * @throws IOException
     */
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
