package com.minigames.mentalGame;

import com.gameLogic.Player;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MentalMainController {

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
    public void changeToGame(ActionEvent event) throws IOException{
        FXMLLoader mentalLoader = new FXMLLoader();
        mentalLoader.setLocation(getClass().getResource("MentalGame.fxml"));

        Parent minigameParent = mentalLoader.load();
        Scene mentalScene = new Scene(minigameParent);

        MentalGameController controller = mentalLoader.getController();
        controller.initData(players);

        // This gets the stage information.
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.initModality(Modality.APPLICATION_MODAL);

        window.setOnCloseRequest(Event::consume);

        window.setScene(mentalScene);
        window.showAndWait();
    }
}