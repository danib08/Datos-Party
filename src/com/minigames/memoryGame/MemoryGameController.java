package com.minigames.memoryGame;

import com.gameLogic.Player;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class MemoryGameController implements Initializable {

    // This attributes are used to update data in the interface.
    StringProperty playerNameProperty = new SimpleStringProperty("Player1");

    @FXML private Button butt11;
    @FXML private Button butt12;
    @FXML private Button butt13;
    @FXML private Button butt14;
    @FXML private Button butt21;
    @FXML private Button butt22;
    @FXML private Button butt23;
    @FXML private Button butt24;
    @FXML private Button butt31;
    @FXML private Button butt32;
    @FXML private Button butt33;
    @FXML private Button butt34;


    @FXML
    private Label player1Label;
    @FXML
    private Label player2Label;
    @FXML
    private Label player3Label;
    @FXML
    private Label player4Label;
    @FXML
    private Button nextButton;
    @FXML
    private Button finishButton;
    @FXML
    private Label currentPlayer;
    @FXML
    private ImageView image11;
    @FXML
    private ImageView image12;
    @FXML
    private ImageView image13;
    @FXML
    private ImageView image14;
    @FXML
    private ImageView image21;
    @FXML
    private ImageView image22;
    @FXML
    private ImageView image23;
    @FXML
    private ImageView image24;
    @FXML
    private ImageView image31;
    @FXML
    private ImageView image32;
    @FXML
    private ImageView image33;
    @FXML
    private ImageView image34;
    @FXML
    private ImageView playerImg3;
    @FXML
    private ImageView playerImg4;

    Image dinoImg;
    Image girlImg;
    Image dogImg;
    Image boyImg;
    Image cardImg;

    ImageView imageRow1;
    ImageView imageRow2;
    ImageView imageRow3;
    Button buttonRow1;
    Button buttonRow2;
    Button buttonRow3;

    Player[] playerArr;
    Player[] winnerArr;
    int winnerInd;
    private int row;
    private int playerInt;
    ArrayList<Integer> row1 = new ArrayList<>();
    ArrayList<Integer> row2 = new ArrayList<>();
    ArrayList<Integer> row3 = new ArrayList<>();

    boolean play1done;
    boolean play2done;
    boolean play3done = true;
    boolean play4done = true;

    /**
     * Gets data from another interface
     * @param playerArr the player array created in the game interface
     */
    public void initData(Player[] playerArr){
        this.playerArr = playerArr;
    }

    /**
     * Initializer to set variables to their initial values
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.row1.add(1);
        this.row1.add(2);
        this.row1.add(3);
        this.row1.add(4);
        this.row2.add(1);
        this.row2.add(2);
        this.row2.add(3);
        this.row2.add(4);
        this.row3.add(1);
        this.row3.add(2);
        this.row3.add(3);
        this.row3.add(4);
        this.play1done = false;
        this.play2done = false;
        this.winnerInd = 0;
        this.row = 1;
        this.playerInt = 1;
        Collections.shuffle(row1);
        Collections.shuffle(row2);
        Collections.shuffle(row3);
        System.out.println(row1 + "\n" + row2 + "\n" + row3);
        try {
            FileInputStream dinoFIS = new FileInputStream("src/com/images/dino.png");
            FileInputStream girlFIS = new FileInputStream("src/com/images/girl.png");
            FileInputStream dogFIS = new FileInputStream("src/com/images/dog.png");
            FileInputStream boyFIS = new FileInputStream("src/com/images/boy.png");
            FileInputStream cardFIS = new FileInputStream("src/com/images/card.jpg");
            this.dinoImg = new Image(dinoFIS);
            this.girlImg = new Image(girlFIS);
            this.dogImg = new Image(dogFIS);
            this.boyImg = new Image(boyFIS);
            this.cardImg = new Image(cardFIS);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int row = 1;
        int playAmo = 4;
        //playAmo = playerArr.length;
        winnerArr = new Player[playAmo];

        this.currentPlayer.textProperty().bind(this.playerNameProperty);

        //this.player1Label.setText(playerArr[0].getName());
        //this.currentPlayer.setText(playerArr[0].getName());
        //this.player2Label.setText(playerArr[1].getName());

        if (playAmo >= 3){
            //this.player3Label.setText(playerArr[2].getName());
            this.player3Label.setVisible(true);
            this.playerImg3.setVisible(true);
            this.play3done = false;
            if (playAmo == 4){
                //this.player4Label.setText(playerArr[3].getName());
                this.player4Label.setVisible(true);
                this.playerImg4.setVisible(true);
                this.play4done = false;
            }
        }
    }

    /**
     * Checks what button was pressed and changes some values depending on the button.
     * @param event JavaFX class called automatically when a button is pressed.
     */
    public void pressButton(ActionEvent event){
        Button but = ((Button) event.getSource());
        String id = ((Node) event.getSource()).getId();
        int index = 0;
        switch(this.row) {
            case 1:
                switch (id){
                    case "butt11":
                        index = 0;
                        showImage(index, this.row1, this.image11);
                        this.imageRow1 = image11;
                        break;
                    case "butt12":
                        index = 1;
                        showImage(index, this.row1, this.image12);
                        this.imageRow1 = image12;
                        break;
                    case "butt13":
                        index = 2;
                        showImage(index, this.row1, this.image13);
                        this.imageRow1 = image13;
                        break;
                    case "butt14":
                        index = 3;
                        showImage(index, this.row1, this.image14);
                        this.imageRow1 = image14;
                        break;
                }
                System.out.println("Row 1: " + this.row1 + "\nIndex: " + index + "\nPlayer: " + playerInt);
                this.buttonRow1 = but;
                this.butt11.setDisable(true);
                this.butt12.setDisable(true);
                this.butt13.setDisable(true);
                this.butt14.setDisable(true);
                if (row1.get(index) == this.playerInt){
                    this.butt21.setDisable(false);
                    this.butt22.setDisable(false);
                    this.butt23.setDisable(false);
                    this.butt24.setDisable(false);
                    this.row = 2;
                }
                else {
                    this.nextButton.setDisable(false);
                    this.nextButton.setVisible(true);
                }
                break;
            case 2:
                switch (id){
                    case "butt21":
                        index = 0;
                        showImage(index, this.row2, this.image21);
                        this.imageRow2 = image21;
                        break;
                    case "butt22":
                        index = 1;
                        showImage(index, this.row2, this.image22);
                        this.imageRow2 = image22;
                        break;
                    case "butt23":
                        index = 2;
                        showImage(index, this.row2, this.image23);
                        this.imageRow2 = image23;
                        break;
                    case "butt24":
                        index = 3;
                        showImage(index, this.row2, this.image24);
                        this.imageRow2 = image24;
                        break;
                }
                System.out.println("Row 2: " + this.row2 + "\nIndex: " + index + "\nPlayer: " + playerInt);
                this.buttonRow2 = but;
                this.butt21.setDisable(true);
                this.butt22.setDisable(true);
                this.butt23.setDisable(true);
                this.butt24.setDisable(true);
                if (row2.get(index) == this.playerInt){
                    this.butt31.setDisable(false);
                    this.butt32.setDisable(false);
                    this.butt33.setDisable(false);
                    this.butt34.setDisable(false);
                    this.row = 3;
                }
                else {
                    this.nextButton.setDisable(false);
                    this.nextButton.setVisible(true);
                }
                break;
            case 3:
                switch (id){
                    case "butt31":
                        index = 0;
                        showImage(index, this.row3, this.image31);
                        this.imageRow3 = image31;
                        break;
                    case "butt32":
                        index = 1;
                        showImage(index, this.row3, this.image32);
                        this.imageRow3 = image32;
                        break;
                    case "butt33":
                        index = 2;
                        showImage(index, this.row3, this.image33);
                        this.imageRow3 = image33;
                        break;
                    case "butt34":
                        index = 3;
                        showImage(index, this.row3, this.image34);
                        this.imageRow3 = image34;
                        break;
                }
                System.out.println("Row 3: " + this.row3 + "\nIndex: " + index + "\nPlayer: " + playerInt);
                this.buttonRow3 = but;
                this.butt31.setDisable(true);
                this.butt32.setDisable(true);
                this.butt33.setDisable(true);
                this.butt34.setDisable(true);
                boolean done = false;
                if (row3.get(index) == this.playerInt){
                    done = true;
                    System.out.println("Aqui");
                    this.buttonRow1.setVisible(false);
                    this.buttonRow2.setVisible(false);
                    this.buttonRow3.setVisible(false);
                    switch (this.playerInt){
                        case 1:
                            this.play1done = true;
                            //this.winnerArr[winnerInd] = playerArr[0];
                            break;
                        case 2:
                            this.play2done = true;
                            //this.winnerArr[winnerInd] = playerArr[0];
                            break;
                        case 3:
                            this.play3done = true;
                            //this.winnerArr[winnerInd] = playerArr[0];
                            break;
                        case 4:
                            this.play4done = true;
                            //this.winnerArr[winnerInd] = playerArr[0];
                            break;
                    }
                }
                if (play1done && play2done && play3done && play4done) {
                    this.finishButton.setDisable(false);
                    this.finishButton.setVisible(true);
                }
                else {
                    this.nextButton.setDisable(false);
                    this.nextButton.setVisible(true);
                    if (done){
                        this.row = 4;
                    }
                    else {
                        this.row = 3;
                    }
                }
                break;
        }
    }

    /**
     * Shows the image behind the card
     * @param index The column of the card
     * @param row The row of the card
     * @param imgView The image to be shown
     */
    private void showImage(int index, ArrayList<Integer> row, ImageView imgView){
        int intImage = row.get(index);
        System.out.println("Imagen: " + intImage);
        switch (intImage){
            case 1:
                imgView.setImage(dinoImg);
                break;
            case 2:
                imgView.setImage(girlImg);
                break;
            case 3:
                imgView.setImage(dogImg);
                break;
            case 4:
                imgView.setImage(boyImg);
                break;
        }
    }

    /**
     * Changes to the next Player and skips players who already finished.
     */
    public void nextPlayer(){
        this.playerInt = this.playerInt + 1;
        this.nextButton.setVisible(false);
        this.playerInt = this.playerInt % 4;
        if (playerInt == 1 && play1done){
            playerInt++;
        }
        if (playerInt == 2 && play2done){
            playerInt++;
        }
        if (playerInt == 3 && play3done){
            playerInt++;
        }
        if (playerInt == 0 && play4done){
            playerInt++;
        }
        if (playerInt == 0){
            this.playerInt = 4;
        }
        this.playerNameProperty.setValue("Player " + (playerInt));
        this.butt11.setDisable(false);
        this.butt12.setDisable(false);
        this.butt13.setDisable(false);
        this.butt14.setDisable(false);
        switch (this.row){
            case 1:
                this.imageRow1.setImage(cardImg);
                break;
            case 2:
                this.imageRow1.setImage(cardImg);
                this.imageRow2.setImage(cardImg);
                break;
            case 3:
                this.imageRow1.setImage(cardImg);
                this.imageRow2.setImage(cardImg);
                this.imageRow3.setImage(cardImg);
                break;
            case 4:
                break;
        }
        this.row = 1;
    }

    /**
     * Changes the scene and rewards the players.
     * @param event JavaFX class called automatically when a button is pressed.
     */
    public void finishedGameWindow(ActionEvent event){

        //Player[] winners = this.sortLowestIndex(this.playerArr, res);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        Parent mentalParent = null;
        try {
            mentalParent = FXMLLoader.load(getClass().getResource("MemoryMain.fxml"));
            Scene mentalScene = new Scene(mentalParent);
            window.setScene(mentalScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}