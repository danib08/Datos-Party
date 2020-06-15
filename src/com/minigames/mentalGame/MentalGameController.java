package com.minigames.mentalGame;

import com.gameLogic.Player;
import com.minigames.Reward;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

import java.util.Random;

/**
 * This class controlls the MentalGame.fxml
 */
public class MentalGameController{

    // This attributes are used to update data in the interface.
    protected IntegerProperty milisecondsProperty = new SimpleIntegerProperty(this, "Miliseconds", 0);
    protected IntegerProperty secondsProperty = new SimpleIntegerProperty(this, "Seconds", 0);

    @FXML protected Label secondsLabel;
    @FXML protected Label milisecondsLabel;
    @FXML protected Label timeSetLabel;
    @FXML protected Label secondsFirstZero;
    @FXML protected Button timerButton;
    @FXML protected Button finishButton;
    @FXML protected Text textTime;
    @FXML protected Label time1Label;
    @FXML protected Label time2Label;
    @FXML protected Label time3Label;
    @FXML protected Label time4Label;
    @FXML protected Label p1Name;
    @FXML protected Label p2Name;
    @FXML protected Label p3Name;
    @FXML protected Label p4Name;

    protected long startTime;
    protected boolean pressA;
    protected boolean pressT;
    protected boolean pressN;
    protected boolean pressP;
    protected long time1;
    protected long time2;
    protected long time3;
    protected long time4;
    protected Player[] playerArr;
    protected int secondsToGo;
    protected boolean startGame;

    /**
     * Gets data from another interface
     * @param playerArr the player array created in the game interface
     */
    public void initData(Player[] playerArr){
        this.playerArr = playerArr;
        this.pressA = false;
        this.pressN = false;
        this.pressT = false;
        this.pressP = false;
        this.time1 = 12000;
        this.time2 = 12000;
        this.time3 = 12000;
        this.time4 = 12000;
        this.startGame = false;

        this.milisecondsLabel.textProperty().bind((this.milisecondsProperty).asString());
        this.secondsLabel.textProperty().bind((this.secondsProperty).asString());

        Random random = new Random(System.currentTimeMillis());
        this.secondsToGo = random.nextInt(4) + 6;
        this.timeSetLabel.setText("Stop time at: " + this.secondsToGo + " seconds!");
        this.secondsToGo *= 100;

        this.p1Name.setText(playerArr[0].getName());
        this.p2Name.setText(playerArr[1].getName());

        if (playerArr.length >= 3){
            this.p3Name.setText(playerArr[2].getName());
            this.p3Name.setVisible(true);
            this.time3Label.setVisible(true);
            if (playerArr.length == 4){
                this.p4Name.setText(playerArr[3].getName());
                this.p4Name.setVisible(true);
                this.time4Label.setVisible(true);
            }
        }
    }

    /**
     * Handles the key pressed event, gets a time when a player press their assigned key.
     * @param event JavaFX class called automatically when a key is pressed.
     */
    public void keyPressed(KeyEvent event){
        if (event.getCode().equals(KeyCode.A) && !this.pressA && this.startGame){
            this.time1 = Math.abs((System.currentTimeMillis()-this.startTime));
            this.pressA = true;
        }
        else if (event.getCode().equals(KeyCode.T) && !this.pressT  && this.startGame){
            this.time2 = Math.abs((System.currentTimeMillis()-this.startTime));
            this.pressT = true;
        }
        else if (event.getCode().equals(KeyCode.N) && !this.pressN  && this.startGame){
            this.time3 = Math.abs((System.currentTimeMillis()-this.startTime));
            this.pressN = true;
        } else if (event.getCode().equals(KeyCode.P) && !this.pressP  && this.startGame) {
            this.time4 = Math.abs((System.currentTimeMillis()-this.startTime));
            this.pressP = true;
        }
    }

    /**
     * Starts the game logic
     * @param event JavaFX class called automatically when a button is pressed.
     */
    public void startGameButton(ActionEvent event){
        this.startGame = true;
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() {
                timerButton.setLayoutX(2000);
                for (int i = 0; i < 1200; i ++) {
                    int newSeconds = i / 100;
                    int newMilis = i % 100;
                    if (i > 300){
                        milisecondsLabel.setVisible(false);
                        secondsLabel.setVisible(false);
                        secondsFirstZero.setVisible(false);
                        textTime.setVisible(false);
                    }
                    try {
                        Thread.sleep(10);
                    }
                    catch (InterruptedException ignored) {
                    }
                    // Update the GUI on the JavaFX Application Thread
                    Platform.runLater(() -> {
                        milisecondsProperty.setValue(newMilis);
                        secondsProperty.setValue(newSeconds);
                    });
                }
                startGame = false;
                finishButton.setDisable(false);
                finishButton.setVisible(true);
                Platform.runLater(() -> {
                    time1Label.setText(time1/1000 + " : " + time1 % 1000);
                    time2Label.setText(time2/1000 + " : " + time2 % 1000);
                    time3Label.setText(time3/1000 + " : " + time3 % 1000);
                    time4Label.setText(time4/1000 + " : " + time4 % 1000);
                });
                return null;
            }
        };
        this.startTime = System.currentTimeMillis();
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * Changes the scene and rewards the players.
     * @param buttonClick JavaFX class called automatically when a button is pressed.
     */
    public void finishedGameWindow(ActionEvent buttonClick) throws IOException{

        Player[] winnerArr = new Player[playerArr.length];

        long[] times = {time1, time2, time3, time4};

        int index;
        for (int i = 0; i < playerArr.length; i++) {
            index = this.smallestIndex(times);
            winnerArr[i] = playerArr[index];
        }

        FXMLLoader rewardLoader = new FXMLLoader();
        rewardLoader.setLocation(getClass().getResource("/com/minigames/reward.fxml"));
        Parent rewardParent = rewardLoader.load();
        Scene rewardScene = new Scene(rewardParent);
        Reward controller = rewardLoader.getController();
        controller.initData(winnerArr);
        Stage window = (Stage) ((Node)buttonClick.getSource()).getScene().getWindow();
        //takes the obtained Stage and changes its Scene to the new fxml file.
        window.setScene(rewardScene);
        window.show();
    }

    /**
     * Gets the index of the smallest time
     * @param times LongArray of times
     * @return index of the smallest time
     */
    public int smallestIndex(long[] times){
        int smallest = 0;
        long tmp = Math.abs(times[0] - secondsToGo*10);
        for (int i = 1; i < times.length; i++) {
            if (Math.abs(times[i] - secondsToGo*10) < tmp){
                tmp = Math.abs(times[i] - secondsToGo*10);
                smallest = i;
            }
        }
        times[smallest] = 130000;
        return smallest;
    }
}