package com.minigames.reactGame;

import com.gameLogic.Player;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.Temporal;
import java.util.Random;
import java.util.ResourceBundle;

public class reactGame2Control implements Initializable{
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

    private IntegerProperty propertyPlayer1 = new SimpleIntegerProperty(0);
    private IntegerProperty propertyPlayer2 = new SimpleIntegerProperty(0);
    private IntegerProperty propertyPlayer3 = new SimpleIntegerProperty(0);
    private IntegerProperty propertyPLayer4 = new SimpleIntegerProperty(0);

    //private boolean pressedA;
    //private boolean pressedT;
    //private boolean pressedN;
    //private boolean pressedP;
    private boolean expectingClick;
    //private boolean expectingPress;
    private boolean isClicked;

    Player[] players;
    long [] pTimes;

    long start;
    long finish;

    int pAmount = 3;
    //int aPoints;
    //int tPoints;
    //int nPoints;
    //int pPoints;
    int i = 0;
    Random timestart;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textField1.setText("Player 1: ");
        textField2.setText("Player 2: ");
        textField3.setText("Player 3: ");
        textField4.setText("Player 4: ");

        //this.label1.textProperty().bind(this.propertyPlayer1.asString());
        //this.label2.textProperty().bind(this.propertyPlayer2.asString());
        //this.label3.textProperty().bind(this.propertyPlayer3.asString());
        //this.label4.textProperty().bind(this.propertyPLayer4.asString());

        this.labelMain.setDisable(true);

        if (pAmount>= 3){
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
    /*
    public void listenPress(KeyEvent keyPress){
        if(keyPress.getCode().equals(KeyCode.A) && !this.pressedA && expectingPress){
            this.pressedA = true;
            this.aPoints += 1;
            this.propertyPlayer1.setValue(aPoints);
        }
        else if(keyPress.getCode().equals(KeyCode.T) && !this.pressedT && expectingPress){
            this.pressedT = true;
            this.tPoints += 1;
            this.propertyPlayer1.setValue(tPoints);
        }
        else if(keyPress.getCode().equals(KeyCode.N) && !this.pressedN && expectingPress){
            this.pressedN = true;
            this.nPoints += 1;
            this.propertyPlayer1.setValue(nPoints);
        }
        else if(keyPress.getCode().equals(KeyCode.P) && !this.pressedP && expectingPress){
            this.pressedP = true;
            this.pPoints += 1;
            this.propertyPlayer1.setValue(pPoints);
        }
    }

     */
    /*
    public void listenRelease(KeyEvent keyRelease){
        if(keyRelease.getCode().equals(KeyCode.A) && this.pressedA && expectingPress) {
            this.pressedA = false;
        }
        else if(keyRelease.getCode().equals(KeyCode.T) && this.pressedT && expectingPress) {
            this.pressedT = false;
        }
        else if(keyRelease.getCode().equals(KeyCode.N) && this.pressedN && expectingPress) {
            this.pressedN = false;
        }
        else if(keyRelease.getCode().equals(KeyCode.P) && this.pressedP && expectingPress) {
            this.pressedP = false;
        }
    }

     */

    public void initData(Player[] players){
        this.players = players;
        //textField1.setText("Player 1: ");
        //textField2.setText("Player 2: ");
        //textField3.setText("Player 3: ");
        //textField4.setText("Player 4: ");

        //this.label1.textProperty().bind(this.propertyPlayer1.asString());
        //this.label2.textProperty().bind(this.propertyPlayer2.asString());
        //this.label3.textProperty().bind(this.propertyPlayer3.asString());
        //this.label4.textProperty().bind(this.propertyPLayer4.asString());
        //timestart = new Random();
        //pTimes = new long[this.players.length];
        //labelMain.setStyle("-fx-background-color: gray");
    }

    public void startRound() throws InterruptedException{
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Random timestart = new Random();
                if (i == pAmount - 1) {
                    int t = timestart.nextInt(4) + 1;
                    Thread.sleep(1000 * t);
                    labelMain.setDisable(false);
                    expectingClick = true;
                    isClicked = false;
                    Platform.runLater(()->{
                        labelMain.setText("click now!");
                        labelMain.setStyle("-fx-background-color: #00ff00");
                        startButton.setText("finish");
                    });
                    start = System.currentTimeMillis();
                } else if (i == pAmount) {
                    startButton.setDisable(true);
                    Player[] toReward = new Player[pAmount];
                    //TODO finish playerArray logic to reward the players.
                    //for (int j = 0; j < pAmount; j++) {
                    //toReward[j] = players[getSmallest(pTimes)];
                    //reward(toReward);
                    //}
                } else {
                    int t = timestart.nextInt(4) + 1;
                    labelMain.setDisable(true);
                    Thread.sleep(1000*t);
                    labelMain.setDisable(false);
                    expectingClick = true;
                    isClicked = false;
                    start = System.currentTimeMillis();
                    Platform.runLater(()->{
                        labelMain.setText("test");
                        labelMain.setStyle("-fx-background-color: #00ff00");
                    });
                }
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }

    public void listenClick(MouseEvent click){
        System.out.println("clicked");
        if (expectingClick && !isClicked){
            expectingClick = false;
            isClicked = true;
        }
        long finish = System.currentTimeMillis();
        long time = finish-start;
        labelMain.setStyle("-fx-background-color: gray");
        labelMain.setDisable(true);
        pTimes[i] = time;
        switch (i) {
            case 0:
                label1.setText("time: " + time);
                break;
            case 1:
                label2.setText("time: " + time);
                break;
            case 2:
                label3.setText("time: " + time);
                break;
            case 3:
                label4.setText("time: " + time);
                break;
        }
        this.i++;
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
    public int getSmallest(long[] pTimes){
        int index = 0;
        int result = 0;
        long value = pTimes[0];
        while (index < pAmount-1){
            long nextVal = pTimes[index+1];
            if (nextVal < value){
                value = nextVal;
                result = index;
            }
            index++;
        }
        pTimes[result] = 500000;
        return result;
    }
}
