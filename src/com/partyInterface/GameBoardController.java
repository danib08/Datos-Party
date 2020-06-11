package com.partyInterface;

import com.gameLogic.Events;
import com.gameLogic.Player;
import com.gameLogic.Star;
import com.minigames.bombGame.BombController;
import com.structures.*;
import com.structures.List;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class GameBoardController implements Initializable {

    // Here the different lists are created
    // The mainBoard represents the outer border for the path players will see.
    CircularSinglyLinkedList mainBoard = new CircularSinglyLinkedList();

    // PathA, B and C are the secondary paths that players can opt to cross over to.
    SinglyLinkedList pathA = new SinglyLinkedList();
    SinglyLinkedList pathB = new SinglyLinkedList();
    DoublyLinkedList pathC = new DoublyLinkedList();

    // The path D is the one that can only be accessed through 'teleporting'.
    CircularDoublyLinkedList pathD = new CircularDoublyLinkedList();

    // A List-type array is made with the five paths.
    List[] pathArray = {mainBoard, pathA, pathB, pathC, pathD};

    // The star is created with a specific method, not regularly instantiated
    // because this object is accessed through a Singleton design pattern
    Star star = Star.getStar(pathArray);

    // A stack is implemented using the SinglyLinkedList structure, this is meant to
    // hold the representation of events to be accessed by the game logic.
    SinglyLinkedList eventStack = new SinglyLinkedList();

    // The collections class ArrayList is used to create a randomizable structure that can
    // later be accessed by the main() method to add the random-ordered elements to the event stack.
    ArrayList<Integer> eventList = new ArrayList<>();

    // This array will contain all of the players
    Player[] playerArray;

    // The EventHandler is declared
    Events eventHandler = new Events(playerArray, this.pathArray, this.eventStack, this.eventList);

    int numberOfPlayers;
    int currentPlayer = 0;
    int numberOfRounds;
    int roundsPlayed = 0;

    String playerName1;
    String playerName2;
    String playerName3;
    String playerName4;

    int lastPlayed = 0;
    int chooseMinigame;

    Image playerImage1;
    Image playerImage2;
    Image playerImage3;
    Image playerImage4;

    @FXML
    TextField playerID1;
    @FXML
    TextField playerID2;
    @FXML
    TextField playerID3;
    @FXML
    TextField playerID4;
    @FXML
    Label roundsText;
    @FXML
    Label playerText;
    @FXML
    GridPane boardGrid;

    /**
     * This methods adds all of the Squares to each Path and creates the Event Stack
     * The method is run as soon as this scene is displayed
     * @param url The location used to resolve relative paths for the root object (null if the location is not known)
     * @param resourceBundle The resources used to localize the root object (null if the root object was not localized)
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.mainBoard.append(1, 9, 0);
        this.mainBoard.append(2, 9, 1);
        this.mainBoard.append(3, 9, 2);
        this.mainBoard.append(1, 9, 3);
        this.mainBoard.append(3, 9, 4);
        this.mainBoard.append(4, 9, 5);
        this.mainBoard.append(2, 9, 6);
        this.mainBoard.append(1, 9, 7);
        this.mainBoard.append(2, 9, 8);
        this.mainBoard.append(3, 9, 9);
        this.mainBoard.append(4, 8, 9);
        this.mainBoard.append(2, 7, 9);
        this.mainBoard.append(1, 6, 9);
        this.mainBoard.append(2, 5, 9);
        this.mainBoard.append(3, 4, 9);
        this.mainBoard.append(1, 3, 9);
        this.mainBoard.append(2, 2, 9);
        this.mainBoard.append(4, 1, 9);
        this.mainBoard.append(3, 0, 9);
        this.mainBoard.append(2, 0, 8);
        this.mainBoard.append(3, 0, 7);
        this.mainBoard.append(1, 0, 6);
        this.mainBoard.append(2, 0, 5);
        this.mainBoard.append(4, 0, 4);
        this.mainBoard.append(2, 0, 3);
        this.mainBoard.append(1, 0, 2);
        this.mainBoard.append(3, 0, 1);
        this.mainBoard.append(4, 0, 0);
        this.mainBoard.append(2, 1, 0);
        this.mainBoard.append(3, 2, 0);
        this.mainBoard.append(4, 3, 0);
        this.mainBoard.append(1, 4, 0);
        this.mainBoard.append(2, 5, 0);
        this.mainBoard.append(2, 6, 0);
        this.mainBoard.append(3, 7, 0);
        this.mainBoard.append(4, 8, 0);

        this.pathA.append(2, 3, 8);
        this.pathA.append(1, 3, 7);
        this.pathA.append(3, 3, 6);
        this.pathA.append(4, 2, 6);
        this.pathA.append(2, 1, 6);

        this.pathB.append(4, 8, 3);
        this.pathB.append(4, 7, 3);
        this.pathB.append(4, 7, 2);
        this.pathB.append(4, 7, 1);

        this.pathC.append(1, 6, 8);
        this.pathC.append(2, 6, 7);
        this.pathC.append(4, 6, 6);
        this.pathC.append(3, 6, 5);
        this.pathC.append(1, 5, 5);
        this.pathC.append(2, 5, 4);
        this.pathC.append(4, 5, 3);
        this.pathC.append(2, 5, 2);
        this.pathC.append(1, 4, 2);
        this.pathC.append(3, 4, 1);

        this.pathD.append(4, 2, 3);
        this.pathD.append(4, 3, 3);
        this.pathD.append(4, 3, 4);
        this.pathD.append(4, 2, 4);

        // Sets the Squares that have links between paths
        this.mainBoard.getElement(3).setPathLink(pathB.getHead());
        this.mainBoard.getElement(12).setPathLink(pathC.getHead());
        this.mainBoard.getElement(15).setPathLink(pathA.getHead());
        this.mainBoard.getElement(31).setPathLink(pathC.getTail());
        this.pathA.getTail().setPathLink(this.mainBoard.getElement(21));
        this.pathB.getTail().setPathLink(this.mainBoard.getElement(34));
        this.pathC.getHead().setPathLink(this.mainBoard.getElement(12));
        this.pathC.getTail().setPathLink(this.mainBoard.getElement(31));

        // The ArrayList object is used as described previously, to add a certain amount of numbers
        // from 1 to 9 that will sequentially be randomized
        this.eventList.add(1);
        this.eventList.add(1);
        this.eventList.add(1);
        this.eventList.add(1);
        this.eventList.add(1);
        this.eventList.add(1);
        this.eventList.add(1);
        this.eventList.add(1);
        this.eventList.add(1);
        this.eventList.add(1);
        this.eventList.add(2);
        this.eventList.add(2);
        this.eventList.add(2);
        this.eventList.add(2);
        this.eventList.add(2);
        this.eventList.add(2);
        this.eventList.add(2);
        this.eventList.add(2);
        this.eventList.add(2);
        this.eventList.add(3);
        this.eventList.add(3);
        this.eventList.add(3);
        this.eventList.add(3);
        this.eventList.add(3);
        this.eventList.add(3);
        this.eventList.add(3);
        this.eventList.add(3);
        this.eventList.add(3);
        this.eventList.add(3);
        this.eventList.add(4);
        this.eventList.add(4);
        this.eventList.add(4);
        this.eventList.add(5);
        this.eventList.add(5);
        this.eventList.add(5);
        this.eventList.add(6);
        this.eventList.add(7);
        this.eventList.add(7);
        this.eventList.add(7);
        this.eventList.add(8);
        this.eventList.add(8);
        this.eventList.add(8);
        this.eventList.add(8);
        this.eventList.add(8);
        this.eventList.add(8);
        this.eventList.add(8);
        this.eventList.add(8);
        this.eventList.add(8);
        this.eventList.add(8);
        this.eventList.add(9);
        this.eventList.add(9);
        this.eventList.add(9);
        this.eventList.add(9);
        this.eventList.add(9);

        // Shuffle method is used to "randomize" the eventList.
        Collections.shuffle(this.eventList);

        // Adds the events to the stack.
        for (Integer event : this.eventList) {
            this.eventStack.prepend(event, 0,0 );
        }
    }

    public void loadImages() throws FileNotFoundException {
        FileInputStream image1 = new FileInputStream("src/com/images/dino.png");
        playerImage1 = new Image(image1);

        FileInputStream image2 = new FileInputStream("src/com/images/girl.png");
        playerImage2 = new Image(image2);

        if (this.numberOfPlayers >= 3) {
            FileInputStream image3 = new FileInputStream("src/com/images/dog.png");
            playerImage3 = new Image(image3);

            if (this.numberOfPlayers == 4) {
                FileInputStream image4 = new FileInputStream("src/com/images/boy.png");
                playerImage4 = new Image(image4);
            }
        }

        Ima
        this.boardGrid.add(new ImageView(playerImage1), 0, 9);
    }

    public void initData(int numPlayers, int numRounds, String name1, String name2, String name3, String name4) throws FileNotFoundException {
        this.numberOfPlayers = numPlayers;
        this.numberOfRounds = numRounds;
        this.playerName1 = name1;
        this.playerName2 = name2;
        this.playerName3 = name3;
        this.playerName4 = name4;
        this.playerArray = new Player[this.numberOfPlayers];

        this.playerArray[0] = new Player(name1, this.mainBoard.getHead(), this.eventHandler, this.star);
        this.playerID1.setText(this.playerName1);

        this.playerArray[1] = new Player(name2, this.mainBoard.getHead(), this.eventHandler, this.star);
        this.playerID2.setText(this.playerName2);

        if (!name3.equals("")) {
            this.playerArray[2] = new Player(name3, this.mainBoard.getHead(), this.eventHandler, this.star);
            this.playerID3.setText(this.playerName3);

            if (!name4.equals("")) {
                this.playerArray[3] = new Player(name4, this.mainBoard.getHead(), this.eventHandler, this.star);
                this.playerID4.setText(this.playerName4);
            }
        }
        this.loadImages();
        this.playerText.setText(this.playerName1);
    }

    public void move() {
        System.out.println("Move");
        if (this.roundsPlayed < this.numberOfPlayers) {
            int roll = this.playerArray[0].roll();
        }
    }

    /**
     * This opens a new window so the player can make a choice to change directions
     * @return The choice of the player as a boolean
     * @throws IOException if a file described in the loaders cannot be found/read/loaded.
     */
    public boolean pathSel() throws IOException {
        Stage pathWindow = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("PathSelection.fxml"));
        Parent pathParent = loader.load();
        Scene pathScene = new Scene(pathParent);

        PathSelController controller = loader.getController();

        pathWindow.initModality(Modality.APPLICATION_MODAL);
        pathWindow.setTitle("Select Path");
        pathWindow.setResizable(false);

        pathWindow.setOnCloseRequest(Event::consume);

        pathWindow.setScene(pathScene);
        pathWindow.showAndWait();

        boolean response = controller.isResponse();

        System.out.println(response);

        return response;
    }

    /**
     * This opens a new window so the player can make a choice to buy a star
     * @return The choice of the player as a boolean
     * @throws IOException if a file described in the loaders cannot be found/read/loaded.
     */
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

        boolean response = controller.isResponse();

        System.out.println(response);

        return response;
    }

    /**
     * This opens a new window when the bomb minigame is executed
     * @throws IOException if the file to load cannot be accessed
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
