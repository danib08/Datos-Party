package com.minigames.pressGame;

import com.gameLogic.Player;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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

public class AmountGameController implements Initializable {

    IntegerProperty milisecondsProperty = new SimpleIntegerProperty(this, "Miliseconds", 0);
    IntegerProperty secondsProperty = new SimpleIntegerProperty(this, "Seconds", 0);

    @FXML
    private Label secondsLabel;
    @FXML
    private Label milisecondsLabel;
    @FXML
    private Label secondsFirstZero;
    @FXML
    private Label player1Label;
    @FXML
    private Label player2Label;
    @FXML
    private Label player3Label;
    @FXML
    private Label player4Label;
    @FXML
    private Label presses1Label;
    @FXML
    private Label presses2Label;
    @FXML
    private Label presses3Label;
    @FXML
    private Label presses4Label;
    @FXML
    private Button timerButton;
    @FXML
    private Button finishButton;

    private boolean pressA;
    private boolean pressT;
    private boolean pressN;
    private boolean pressP;
    private IntegerProperty presses1 = new SimpleIntegerProperty(0);
    private IntegerProperty presses2 = new SimpleIntegerProperty(0);
    private IntegerProperty presses3 = new SimpleIntegerProperty(0);
    private IntegerProperty presses4 = new SimpleIntegerProperty(0);
    private Player[] playerArr;

    private boolean startGame;

    public void initData(Player[] playerArr){
        this.playerArr = playerArr;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.startGame = false;
        int playAmo = 4;
        //int playAmo = playerArr.length;
        this.pressA = false;
        this.pressN = false;
        this.pressT = false;
        this.pressP = false;

        //this.player1Label.setText(playerArr[0].getName());
        //this.player2Label.setText(playerArr[1].getName());

        this.presses1Label.textProperty().bind((this.presses1).asString());
        this.presses2Label.textProperty().bind((this.presses2).asString());

        this.milisecondsLabel.textProperty().bind((this.milisecondsProperty).asString());
        this.secondsLabel.textProperty().bind((this.secondsProperty).asString());

        if (playAmo >= 3){
            //this.player3Label.setText(playerArr[2].getName());
            this.presses3Label.textProperty().bind((this.presses3).asString());
            this.player3Label.setVisible(true);
            this.presses3Label.setVisible(true);
            if (playAmo == 4){
                //this.player4Label.setText(playerArr[3].getName());
                this.presses4Label.textProperty().bind((this.presses4).asString());
                this.player4Label.setVisible(true);
                this.presses4Label.setVisible(true);
            }
        }

    }

    public void keyPressed(KeyEvent event){
        if (event.getCode().equals(KeyCode.A) && !this.pressA && startGame){
            this.presses1.setValue(this.presses1.getValue() + 1);
            System.out.println("Player 1: " + this.presses1.getValue());
            this.pressA = true;
        }
        else if (event.getCode().equals(KeyCode.T) && !this.pressT && startGame){
            this.presses2.setValue(this.presses2.getValue() + 1);
            System.out.println("Player 2: " + this.presses2.getValue());
            this.pressT = true;
        }
        else if (event.getCode().equals(KeyCode.N) && !this.pressN && startGame){
            this.presses3.setValue(this.presses3.getValue() + 1);
            System.out.println("Player 3: " + this.presses3.getValue());
            this.pressN = true;
        } else if (event.getCode().equals(KeyCode.P) && !this.pressP && startGame) {
            this.presses4.setValue(this.presses4.getValue() + 1);
            System.out.println("Player 4: " + this.presses4.getValue());
            this.pressP = true;
        }
    }

    public void keyReleased(KeyEvent event){
        if (event.getCode().equals(KeyCode.A)){
            this.pressA = false;
        }
        else if (event.getCode().equals(KeyCode.T)){
            this.pressT = false;
        }
        else if (event.getCode().equals(KeyCode.N)){
            this.pressN = false;
        } else if (event.getCode().equals(KeyCode.P)) {
            this.pressP = false;
        }
    }

    public void startGameButton(ActionEvent event){
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

    public void finishedGameWindow(ActionEvent event){

        //Player[] winners = this.sortLowestIndex(this.playerArr, res);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        Parent amountParent = null;
        try {
            amountParent = FXMLLoader.load(getClass().getResource("AmountMain.fxml"));
            Scene mentalScene = new Scene(amountParent);
            window.setScene(mentalScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
