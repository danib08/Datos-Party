package com.minigames.bombGame;

import com.gameLogic.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class BombGame2 {
    @FXML ImageView player1bomb;
    @FXML ImageView player2bomb;
    @FXML ImageView player3bomb;
    @FXML ImageView player4bomb;
    Player[] players;



    public void showDetonation() throws FileNotFoundException {

        FileInputStream input = new FileInputStream("src/com/images/explosion.png");
        Image explosion = new Image(input);
        player1bomb.setImage(explosion);
        player2bomb.setImage(explosion);
        player3bomb.setImage(explosion);
        player4bomb.setImage(explosion);
    }
}
