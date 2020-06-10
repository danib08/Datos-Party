package com.partyInterface;

import com.minigames.bombGame.BombController;
import com.minigames.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class GameBoardController {

    /**
     * This opens a new window so the player can make a choice to change directions
     * @return The choice of the player as a boolean
     * @throws IOException if a file described in the loaders cannot be found/read/loaded.
     */
    public boolean pathSel() throws IOException {
        Stage pathWindow = new Stage();
        Parent pathParent = FXMLLoader.load(getClass().getResource("PathSelection.fxml"));
        Scene pathScene = new Scene(pathParent);

        pathWindow.initModality(Modality.APPLICATION_MODAL);
        pathWindow.setTitle("Select Path");
        pathWindow.setResizable(false);

        pathWindow.setOnCloseRequest(Event::consume);

        pathWindow.setScene(pathScene);
        pathWindow.showAndWait();

        boolean response = PathSelController.isResponse();

        System.out.println(response);

        return response;
    }

    public boolean starBuy() throws IOException{
        int coins = 15;

        Stage starWindow = new Stage();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("StarInterface.fxml"));
        Parent starParent = loader.load();
        Scene starScene = new Scene(starParent);

        // Accessing the Interface controller
        StarController controller = loader.getController();
        controller.initData(coins);

        starWindow.initModality(Modality.APPLICATION_MODAL);
        starWindow.setTitle("Buying a Star");
        starWindow.setResizable(false);

        starWindow.setOnCloseRequest(Event::consume);

        starWindow.setScene(starScene);
        starWindow.showAndWait();

        boolean response = StarController.isResponse();

        System.out.println(response);

        return response;
    }

    /**
     * this opens a new window when the bomb minigame is executed.
     * @throws IOException if the file to load cannot be accessed.
     */

    public void startBombGame() throws IOException{
        Stage bombWindow = new Stage();

        FXMLLoader bombLoader = new FXMLLoader();
        bombLoader.setLocation(getClass().getResource("minigames/bombGame.fxml"));
        Parent bombParent = bombLoader.load();
        Scene bombScene = new Scene(bombParent);

        BombController bombController = bombLoader.getController();

        //this modality is meant to transform the minigame window into the only interaction-allowed one for the user.
        //thus the players can only exit the window by playing the minigame.
        bombWindow.initModality(Modality.APPLICATION_MODAL);
        bombWindow.setTitle("Detonators Minigame");

        bombWindow.setOnCloseRequest(Event :: consume);

        bombWindow.setScene(bombScene);

    }
}
