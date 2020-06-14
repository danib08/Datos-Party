package com.minigames.potatoGame;

import com.gameLogic.Player;
import com.minigames.mentalGame.MentalGameController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class PotatoMainController {

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
        FXMLLoader potatoLoader = new FXMLLoader();
        potatoLoader.setLocation(getClass().getResource("PotatoGame.fxml"));

        Parent minigameParent = potatoLoader.load();
        Scene potatoScene = new Scene(minigameParent);

        PotatoGameController controller = potatoLoader.getController();
        controller.initData(new Player[4]);

        // This gets the stage information.
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(potatoScene);
        window.show();
    }
}
