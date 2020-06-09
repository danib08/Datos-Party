package com.minigames.bombGame;

import com.gameLogic.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ResourceBundle;

public class BombGame2 implements Initializable {
    @FXML ImageView player1bomb;
    @FXML ImageView player2bomb;
    @FXML ImageView player3bomb;
    @FXML ImageView player4bomb;
    Player[] players;
    Image explosion = new Image("explosion.png");

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int pAmount = players.length;
        //TODO bring the player array to the game window

    }

    public void showDetonation(){
        //TODO fix the url to the images to test the button.
        player1bomb.setImage(explosion);
        player2bomb.setImage(explosion);
        player3bomb.setImage(explosion);
        player4bomb.setImage(explosion);
    }
}
