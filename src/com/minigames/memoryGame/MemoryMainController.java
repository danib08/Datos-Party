package com.minigames.memoryGame;

import com.gameLogic.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class controlls the MemoryMain.fxml
 */
public class MemoryMainController {

    protected Player[] players;

    /**
     * Sets the initial data for the controller.
     * @param players Player array
     */
    public void initData(Player[] players){
        this.players = players;
    }

    /**
     * This method is called when the Start Game button is clicked, and shows the minigame scene
     * @param event Receives a click on the button
     * @throws IOException
     */
    public void changeToGame(ActionEvent event) throws IOException {
        FXMLLoader memoryLoader = new FXMLLoader();
        memoryLoader.setLocation(getClass().getResource("MemoryGame.fxml"));

        Parent minigameParent = memoryLoader.load();
        Scene memoryScene = new Scene(minigameParent);

        MemoryGameController controller = memoryLoader.getController();
        controller.initData(players);

        // This gets the stage information.
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(memoryScene);
        window.show();
    }
}
