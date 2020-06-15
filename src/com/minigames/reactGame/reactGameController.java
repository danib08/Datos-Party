package com.minigames.reactGame;

import com.gameLogic.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class controlls the reactGame.fxml
 */
public class reactGameController {
    Player[] players;

    @FXML
    Button startReactGame;

    /**
     * when the button bound to this method is clicked, it creates the necessary items for another scene
     * to be loaded.
     * @param buttonClick event related to clicking the button bound to this method.
     * @throws IOException if the fxml file that it attempts to load fails, is corrupted or not found.
     */
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


    /**
     * method used by the second controller to carry the player instances over the next scene
     * @param arr Player-type array that carries the instanced players when the game logic starts.
     */
    public void initData(Player[] arr){
        this.players= arr;
    }
}
