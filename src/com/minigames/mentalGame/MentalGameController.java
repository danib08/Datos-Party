package com.minigames.mentalGame;

import com.gameLogic.Player;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class MentalGameController implements Initializable {

    // This classes are used to update data in the interface.
    IntegerProperty milisecondsProperty = new SimpleIntegerProperty(this, "Miliseconds", 0);
    IntegerProperty secondsProperty = new SimpleIntegerProperty(this, "Seconds", 0);

    @FXML
    private Label secondsLabel;
    @FXML
    private Label milisecondsLabel;
    @FXML
    private Label timeSetLabel;
    @FXML
    private Label secondsFirstZero;
    @FXML
    private Button timerButton;
    @FXML
    private Button finishButton;
    @FXML
    private Text textTime;

    private long startTime;
    private boolean pressA;
    private boolean pressT;
    private boolean pressN;
    private boolean pressP;
    private long time1;
    private long time2;
    private long time3;
    private long time4;
    private Player[] playerArr;
    private int secondsToGo;

    /**
     * Gets data from another interface
     * @param playerArr the player array created in the game interface
     */
    public void initData(Player[] playerArr){
        this.playerArr = playerArr;
    }

    @Override
    /**
     * The player
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.pressA = false;
        this.pressN = false;
        this.pressT = false;
        this.pressP = false;
        this.time1 = 12;
        this.time2 = 12;
        this.time3 = 12;
        this.time4 = 12;

        this.milisecondsLabel.textProperty().bind((this.milisecondsProperty).asString());
        this.secondsLabel.textProperty().bind((this.secondsProperty).asString());

        Random random = new Random(System.currentTimeMillis());
        this.secondsToGo = random.nextInt(4) + 6;
        this.timeSetLabel.setText("Stop time at: " + this.secondsToGo + " seconds!");
        this.secondsToGo *= 100;
    }

    public void keyPressed(KeyEvent event){
        if (event.getCode().equals(KeyCode.A) && !this.pressA){
            this.time1 = System.currentTimeMillis()-this.startTime;
            System.out.println(this.time1);
            this.pressA = true;
        }
        else if (event.getCode().equals(KeyCode.T) && !this.pressT){
            this.time2 = System.currentTimeMillis()-this.startTime;
            System.out.println(this.time2);
            this.pressT = true;
        }
        else if (event.getCode().equals(KeyCode.N) && !this.pressN){
            this.time3 = System.currentTimeMillis()-this.startTime;
            System.out.println(this.time3);
            this.pressN = true;
        } else if (event.getCode().equals(KeyCode.P) && !this.pressP) {
            this.time4 = System.currentTimeMillis() - this.startTime;
            System.out.println(this.time4);
            this.pressP = true;
        }
    }

    public void startGameButton(ActionEvent event){
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

    public void finishedGameWindow(ActionEvent event){

        long[] res = {time1, time2, time3, time4};

        //Player[] winners = this.sortLowestIndex(this.playerArr, res);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        Parent mentalParent = null;
        try {
            mentalParent = FXMLLoader.load(getClass().getResource("MentalMain.fxml"));
            Scene mentalScene = new Scene(mentalParent);
            window.setScene(mentalScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //TODO: Test this
    public Player[] sortLowestIndex(Player[] playerArr,long[] res){
        Player[] winners = new Player[playerArr.length];
        for (int i = 0; i < playerArr.length; i++) {
            long tmp = res[0];
            int j = 0;
            for (long time : res) {
                if (Math.abs(time - secondsToGo * 1000) < Math.abs(tmp - secondsToGo * 1000)) {
                    tmp = time;
                }
                j++;
            }
            winners[i] = playerArr[j];
            res[j] = 100;
        }
        return winners;
    }
}