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
        this.time1 = 12;
        this.time2 = 12;
        this.time3 = 12;
        this.time4 = 12;
        this.startGame = false;

        this.milisecondsLabel.textProperty().bind((this.milisecondsProperty).asString());
        this.secondsLabel.textProperty().bind((this.secondsProperty).asString());

        Random random = new Random(System.currentTimeMillis());
        this.secondsToGo = random.nextInt(4) + 6;
        this.timeSetLabel.setText("Stop time at: " + this.secondsToGo + " seconds!");
        this.secondsToGo *= 100;
    }

    /**
     * Handles the key pressed event, gets a time when a player press their assigned key.
     * @param event JavaFX class called automatically when a key is pressed.
     */
    public void keyPressed(KeyEvent event){
        if (event.getCode().equals(KeyCode.A) && !this.pressA && this.startGame){
            this.time1 = System.currentTimeMillis()-this.startTime;
            this.pressA = true;
        }
        else if (event.getCode().equals(KeyCode.T) && !this.pressT  && this.startGame){
            this.time2 = System.currentTimeMillis()-this.startTime;
            this.pressT = true;
        }
        else if (event.getCode().equals(KeyCode.N) && !this.pressN  && this.startGame){
            this.time3 = System.currentTimeMillis()-this.startTime;
            this.pressN = true;
        } else if (event.getCode().equals(KeyCode.P) && !this.pressP  && this.startGame) {
            this.time4 = System.currentTimeMillis() - this.startTime;
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
        FXMLLoader rewardLoader = new FXMLLoader();
        rewardLoader.setLocation(getClass().getResource("/com/minigames/reward.fxml"));
        Parent rewardParent = rewardLoader.load();
        Scene rewardScene = new Scene(rewardParent);
        Reward controller = rewardLoader.getController();
        //controller.initData(winnerArr);
        Stage window = (Stage) ((Node)buttonClick.getSource()).getScene().getWindow();
        //takes the obtained Stage and changes its Scene to the new fxml file.
        window.setScene(rewardScene);
        window.show();
    }
}