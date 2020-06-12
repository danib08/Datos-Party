package com.minigames.potatoGame;

import com.gameLogic.Player;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class PotatoGameController {

    protected Player[] players;

    @FXML
    protected Label p1Name;
    @FXML
    protected Label p2Name;
    @FXML
    protected Label p3Name;
    @FXML
    protected Label p4Name;
    @FXML
    protected Label p1Sta;
    @FXML
    protected Label p2Sta;
    @FXML
    protected Label p3Sta;
    @FXML
    protected Label p4Sta;
    @FXML
    protected Label potatoExplodedLabel;
    @FXML
    protected ImageView p1Img;
    @FXML
    protected ImageView p2Img;
    @FXML
    protected ImageView p3Img;
    @FXML
    protected ImageView p4Img;
    @FXML
    protected Button finishButt;
    @FXML
    protected Button starButt;
    @FXML
    protected Label onBombLabel;

    protected String currentPlayer;
    protected int playersAlive;
    protected boolean p1alive;
    protected boolean p2alive;
    protected boolean p3alive;
    protected boolean p4alive;

    protected boolean gameStarted;
    protected boolean potatoExploded;

    /**
     * Gets data from another interface
     * @param players the player array created in the game interface
     */
    public void initData(Player[] players){
        this.players = players;
        this.currentPlayer = "Player 1";
        this.p1alive = true;
        this.p2alive = true;
        this.p3alive = false;
        this.p4alive = false;
        this.playersAlive = players.length;

//        this.p1Name.setText(players[0].getName());
//        this.p2Name.setText(players[1].getName());

        if (players.length >= 3) {
//            this.p3Name.setText(players[2].getName());
            this.p3Name.setVisible(true);
            this.p3Sta.setVisible(true);
            this.p3alive = true;
            this.p3Img.setVisible(true);
            if (players.length == 4) {
//                this.p4Name.setText(players[3].getName());
                this.p4Name.setVisible(true);
                this.p4alive = true;
                this.p4Sta.setVisible(true);
                this.p4Img.setVisible(true);
            }
        }
    }

    /**
     * Handles the key pressed event, gets a time when a player press their assigned key.
     * @param event JavaFX class called automatically when a key is pressed.
     */
    public void keyPressed(KeyEvent event){
        int currPlay;
        if (event.getCode().equals(KeyCode.A) && this.p1alive && gameStarted && currentPlayer.equals("Player 1")){
            currPlay = 1;
            this.alivePlayer(currPlay);
        }
        else if (event.getCode().equals(KeyCode.T) && this.p2alive && gameStarted && currentPlayer.equals("Player 2")){
            currPlay = 2;
            this.alivePlayer(currPlay);
        }
        else if (event.getCode().equals(KeyCode.N) && this.p3alive && gameStarted && currentPlayer.equals("Player 3")){
            currPlay = 3;
            this.alivePlayer(currPlay);
        } else if (event.getCode().equals(KeyCode.P) && this.p4alive && gameStarted && currentPlayer.equals("Player 4")) {
            currPlay = 4;
            this.alivePlayer(currPlay);
        }
    }

    public void alivePlayer(int currPlay){
        switch (currPlay){
            case 1:
                if (!p2alive){
                    if (!p3alive){
                        this.currentPlayer = "Player 4";
                    }
                    else {
                        this.currentPlayer = "Player 3";
                    }
                }
                else {
                    this.currentPlayer = "Player 2";
                }
                break;
            case 2:
                if (!p3alive){
                    if (!p4alive){
                        this.currentPlayer = "Player 1";
                    }
                    else {
                        this.currentPlayer = "Player 4";
                    }
                }
                else {
                    this.currentPlayer = "Player 3";
                }
                break;
            case 3:
                if (!p4alive){
                    if (!p1alive){
                        this.currentPlayer = "Player 2";
                    }
                    else {
                        this.currentPlayer = "Player 1";
                    }
                }
                else {
                    this.currentPlayer = "Player 4";
                }
                break;
            case 4:
                if (!p1alive){
                    if (!p2alive){
                        this.currentPlayer = "Player 3";
                    }
                    else {
                        this.currentPlayer = "Player 2";
                    }
                }
                else {
                    this.currentPlayer = "Player 1";
                }
                break;
        }
        this.onBombLabel.setText(this.currentPlayer);
    }


    /**
     * Starts the game logic
     * @param event JavaFX class called automatically when a button is pressed.
     */
    public void startGameButton(ActionEvent event){
        this.potatoExplodedLabel.setText("Round Started!");
        int currPlay = 4;
        alivePlayer(currPlay);
        if (this.playersAlive == 1){
            this.finishButt.setVisible(true);
            this.finishButt.setDisable(false);
            this.starButt.setVisible(false);
            this.potatoExplodedLabel.setVisible(false);
            return;
        }
        this.gameStarted = true;
        this.starButt.setLayoutX(2000);
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() {
                Random rand = new Random();
                int time = rand.nextInt(3) + 5;
                time *= 100;
                for (int i = 0; i < time; i ++) {
                    try {
                        Thread.sleep(10);
                    }
                    catch (InterruptedException ignored) {
                    }
                }
                Platform.runLater(() -> {
                    potatoExplodedLabel.setText("The bomb exploded");
                });
                gameStarted = false;
                starButt.setLayoutX(667);
                bombExplosion();
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
        this.playersAlive--;
    }

    /**
     * Changes the scene and rewards the players.
     * @param event JavaFX class called automatically when a button is pressed.
     */
    public void finishedGameWindow(ActionEvent event){

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        Parent amountParent = null;
        try {
            amountParent = FXMLLoader.load(getClass().getResource("PotatoMain.fxml"));
            Scene mentalScene = new Scene(amountParent);
            window.setScene(mentalScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void bombExplosion(){
        switch (this.currentPlayer){
            case "Player 1":
                this.p1alive = false;
                Platform.runLater(() -> {
                    this.p1Sta.setText("Dead");
                    this.p1Img.setVisible(false);
                });
                break;
            case "Player 2":
                this.p2alive = false;
                Platform.runLater(() -> {
                    this.p2Sta.setText("Dead");
                    this.p2Img.setVisible(false);
                });
                break;
            case "Player 3":
                this.p3alive = false;
                Platform.runLater(() -> {
                    this.p3Sta.setText("Dead");
                    this.p3Img.setVisible(false);
                });
                break;
            case "Player 4":
                this.p4alive = false;
                Platform.runLater(() -> {
                    this.p4Sta.setText("Dead");
                    this.p4Img.setVisible(false);
                });
                break;
        }
    }
}
