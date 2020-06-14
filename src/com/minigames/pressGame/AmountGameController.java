package com.minigames.pressGame;

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
import javafx.stage.Stage;

import java.io.IOException;

public class AmountGameController{

    // This attributes are used to update data in the interface.
    protected IntegerProperty milisecondsProperty = new SimpleIntegerProperty(this, "Miliseconds", 0);
    protected IntegerProperty secondsProperty = new SimpleIntegerProperty(this, "Seconds", 0);
    protected IntegerProperty presses1 = new SimpleIntegerProperty(0);
    protected IntegerProperty presses2 = new SimpleIntegerProperty(0);
    protected IntegerProperty presses3 = new SimpleIntegerProperty(0);
    protected IntegerProperty presses4 = new SimpleIntegerProperty(0);

    @FXML protected Label secondsLabel;
    @FXML protected Label milisecondsLabel;
    @FXML protected Label secondsFirstZero;
    @FXML protected Label player1Label;
    @FXML protected Label player2Label;
    @FXML protected Label player3Label;
    @FXML protected Label player4Label;
    @FXML protected Label presses1Label;
    @FXML protected Label presses2Label;
    @FXML protected Label presses3Label;
    @FXML protected Label presses4Label;
    @FXML protected Button timerButton;
    @FXML protected Button finishButton;

    protected boolean pressA;
    protected boolean pressT;
    protected boolean pressN;
    protected boolean pressP;
    protected Player[] playerArr;
    protected boolean startGame;

    /**
     * Gets data from another interface
     * @param playerArr the player array created in the game interface
     */
    public void initData(Player[] playerArr){
        this.playerArr = playerArr;
        this.startGame = false;
        int playAmo = playerArr.length;
        this.pressA = false;
        this.pressN = false;
        this.pressT = false;
        this.pressP = false;

        this.player1Label.setText(playerArr[0].getName());
        this.player2Label.setText(playerArr[1].getName());

        this.presses1Label.textProperty().bind((this.presses1).asString());
        this.presses2Label.textProperty().bind((this.presses2).asString());

        this.milisecondsLabel.textProperty().bind((this.milisecondsProperty).asString());
        this.secondsLabel.textProperty().bind((this.secondsProperty).asString());

        if (playAmo >= 3){
            this.player3Label.setText(playerArr[2].getName());
            this.presses3Label.textProperty().bind((this.presses3).asString());
            this.player3Label.setVisible(true);
            this.presses3Label.setVisible(true);
            if (playAmo == 4){
                this.player4Label.setText(playerArr[3].getName());
                this.presses4Label.textProperty().bind((this.presses4).asString());
                this.player4Label.setVisible(true);
                this.presses4Label.setVisible(true);
            }
        }
    }

    /**
     * Handles the key pressed event
     * @param event JavaFX class called automatically when a key is pressed.
     */
    public void keyPressed(KeyEvent event){
        if (event.getCode().equals(KeyCode.A) && !this.pressA && startGame){
            this.presses1.setValue(this.presses1.getValue() + 1);
            this.pressA = true;
        }
        else if (event.getCode().equals(KeyCode.T) && !this.pressT && startGame){
            this.presses2.setValue(this.presses2.getValue() + 1);
            this.pressT = true;
        }
        else if (event.getCode().equals(KeyCode.N) && !this.pressN && startGame){
            this.presses3.setValue(this.presses3.getValue() + 1);
            this.pressN = true;
        } else if (event.getCode().equals(KeyCode.P) && !this.pressP && startGame) {
            this.presses4.setValue(this.presses4.getValue() + 1);
            this.pressP = true;
        }
    }

    /**
     * Handles the key released event
     * @param event JavaFX class called automatically when a key is released.
     */
    public void keyReleased(KeyEvent event){
        if (event.getCode().equals(KeyCode.A)){
            this.pressA = false;
        }
        else if (event.getCode().equals(KeyCode.T)){
            this.pressT = false;
        }
        else if (event.getCode().equals(KeyCode.N)){
            this.pressN = false;
        }
        else if (event.getCode().equals(KeyCode.P)) {
            this.pressP = false;
        }
    }

    /**
     * Starts the game logic
     */
    public void startGameButton(){
        this.secondsFirstZero.setText("0");
        timerButton.setLayoutX(2000);
        this.startGame = true;
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() {
                for (int i = 1000; i > 0; i --) {
                    int newSeconds = i / 100;
                    int newMilis = i % 100;
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
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * Changes the scene and rewards the players.
     * @param event JavaFX class called automatically when a button is pressed.
     */
    public void finishedGameWindow(ActionEvent event) throws IOException {
        Player[] winnerArr = new Player[playerArr.length];

        int times1 = presses1.getValue();
        int times2 = presses2.getValue();
        int times3 = presses3.getValue();
        int times4 = presses4.getValue();

        int[] timesPressed = {times1, times2, times3, times4};

        int index;
        for (int i = 0; i < playerArr.length; i++) {
            index = this.biggestIndex(timesPressed);
            winnerArr[i] = playerArr[index];
        }

        FXMLLoader rewardLoader = new FXMLLoader();
        rewardLoader.setLocation(getClass().getResource("/com/minigames/reward.fxml"));
        Parent rewardParent = rewardLoader.load();
        Scene rewardScene = new Scene(rewardParent);
        Reward controller = rewardLoader.getController();
        controller.initData(winnerArr);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        //takes the obtained Stage and changes its Scene to the new fxml file.
        window.setScene(rewardScene);
        window.show();
    }

    private int biggestIndex(int[] timesPressed) {
        int biggest = 0;
        int tmp = timesPressed[0];

        for (int i = 1; i < timesPressed.length; i++) {
            if (timesPressed[i] > tmp) {
                biggest = i;
                tmp = timesPressed[i];
            }
        }
        timesPressed[biggest] = -1;
        return biggest;
    }
}
