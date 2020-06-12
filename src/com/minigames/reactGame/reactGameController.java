package com.minigames.reactGame;

import com.gameLogic.Player;
import com.minigames.bombGame.BombGame2;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class reactGameController {
    Player[] players;

    @FXML
    Button startReactGame;

    public void startReactGame(ActionEvent buttonClick) throws IOException {
        FXMLLoader reactLoader = new FXMLLoader();
        reactLoader.setLocation(getClass().getResource("reactGame2.fxml"));

        Parent minigameParent = reactLoader.load();
        Scene reactScene = new Scene(minigameParent);
        reactGame2Control controller = reactLoader.getController();
        controller.initData(players);

        // This gets the stage information to set the new scene.
        Stage window = (Stage) ((Node)buttonClick.getSource()).getScene().getWindow();
        //takes the obtained Stage and changes its Scene to the new fxml file.
        window.setScene(reactScene);
        window.show();
    }



    public void initData(Player[] arr){
        this.players= arr;
    }
}
