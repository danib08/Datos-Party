package com.minigames.bombGame;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;


public class BombController{
    @FXML Button helpButton;
    @FXML Button startButton;
    @FXML TextArea helpText;

    public void showHelp(ActionEvent buttonClick){
        if (!helpText.isVisible()){
            helpText.setVisible(true);
        }
        else{
            helpText.setVisible(false);
        }
    }

}
