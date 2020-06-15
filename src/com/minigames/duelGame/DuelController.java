package com.minigames.duelGame;

import com.gameLogic.Player;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * This class controlls the Duel.fxml
 */
public class DuelController{

    // This attributes are used to update data in the interface.
    protected IntegerProperty milisecondsProperty = new SimpleIntegerProperty(this, "Miliseconds", 0);
    protected IntegerProperty secondsProperty = new SimpleIntegerProperty(this, "Seconds", 0);
    protected IntegerProperty presses1 = new SimpleIntegerProperty(0);
    protected IntegerProperty presses2 = new SimpleIntegerProperty(0);

    @FXML protected Label secondsLabel;
    @FXML protected Label milisecondsLabel;
    @FXML protected Label secondsFirstZero;
    @FXML protected Label player1Label;
    @FXML protected Label player2Label;
    @FXML protected Label presses1Label;
    @FXML protected Label presses2Label;
    @FXML protected Button timerButton;
    @FXML protected Button finishButton;

    protected boolean pressA;
    protected boolean pressT;
    protected Player[] playerArr;
    protected boolean startGame;

    protected Player winner;

    /**
     * Gets data from another interface
     * @param playerArr the player array created in the game interface
     */
    public void initData(Player[] playerArr){
        this.playerArr = playerArr;
        this.startGame = false;
        this.pressA = false;
        this.pressT = false;

        this.player1Label.setText(playerArr[0].getName());
        this.player2Label.setText(playerArr[1].getName());

        this.presses1Label.textProperty().bind((this.presses1).asString());
        this.presses2Label.textProperty().bind((this.presses2).asString());

        this.milisecondsLabel.textProperty().bind((this.milisecondsProperty).asString());
        this.secondsLabel.textProperty().bind((this.secondsProperty).asString());
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
    public void finishedGameWindow(ActionEvent event){

        int times1 = presses1.getValue();
        int times2 = presses2.getValue();

        if (times1 > times2){
            this.winner = playerArr[0];
        }
        else {
            this.winner = playerArr[1];
        }
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.close();
    }

    /**
     * Getter for the winner attribute
     * @return Player Object
     */
    public Player getWinner() {
        return winner;
    }
}
