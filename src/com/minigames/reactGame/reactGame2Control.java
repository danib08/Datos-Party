package com.minigames.reactGame;

import com.gameLogic.Player;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class reactGame2Control implements Initializable {
    Player[] players;
    @FXML Label label1;
    @FXML Label label2;
    @FXML Label label3;
    @FXML Label label4;
    @FXML Label labelMain;

    @FXML TextField textField1;
    @FXML TextField textField2;
    @FXML TextField textField3;
    @FXML TextField textField4;

    private IntegerProperty propertyPlayer1 = new SimpleIntegerProperty(0);
    private IntegerProperty propertyPlayer2 = new SimpleIntegerProperty(0);
    private IntegerProperty propertyPlayer3 = new SimpleIntegerProperty(0);
    private IntegerProperty propertyPLayer4 = new SimpleIntegerProperty(0);

    private boolean pressedA;
    private boolean pressedT;
    private boolean pressedN;
    private boolean pressedP;

    int aPoints;
    int tPoints;
    int nPoints;
    int pPoints;

    private boolean expectingPress;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textField1.setText("Player 1: ");
        textField2.setText("Player 2: ");
        textField3.setText("Player 3: ");
        textField4.setText("Player 4: ");

        this.label1.textProperty().bind(this.propertyPlayer1.asString());
        this.label2.textProperty().bind(this.propertyPlayer2.asString());
        this.label3.textProperty().bind(this.propertyPlayer3.asString());
        this.label4.textProperty().bind(this.propertyPLayer4.asString());


        labelMain.setStyle("-fx-background-color: gray");
        this.pressedA = false;
        this.pressedN = false;
        this.pressedT = false;
        this.pressedP = false;
        expectingPress = false;

    }

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

    public void initData(Player[] players){
        this.players = players;
    }


}
