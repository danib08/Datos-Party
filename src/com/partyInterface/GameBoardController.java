package com.partyInterface;

import com.gameLogic.Events;
import com.gameLogic.Player;
import com.gameLogic.Star;
import com.minigames.bombGame.BombController;
import com.structures.*;
import com.structures.List;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
    Events eventHandler = new Events(this.playerArray, this.pathArray, this.eventStack, this.eventList);

    int numberOfPlayers;
    int numberOfRounds;
    int roundsPlayed = 0;

    String playerName1;
    String playerName2;
    String playerName3;
    String playerName4;

    int lastPlayed = 0;
    int chooseMinigame;

    /**
     * This methods adds all of the Squares to each Path and creates the Event Stack
     * The method is run as soon as this scene is displayed
     * @param url The location used to resolve relative paths for the root object (null if the location is not known)
     * @param resourceBundle The resources used to localize the root object (null if the root object was not localized)
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.mainBoard.append(1);
        this.mainBoard.append(2);
        this.mainBoard.append(3);
        this.mainBoard.append(1);
        this.mainBoard.append(3);
        this.mainBoard.append(4);
        this.mainBoard.append(2);
        this.mainBoard.append(1);
        this.mainBoard.append(2);
        this.mainBoard.append(3);
        this.mainBoard.append(4);
        this.mainBoard.append(2);
        this.mainBoard.append(1);
        this.mainBoard.append(2);
        this.mainBoard.append(3);
        this.mainBoard.append(1);
        this.mainBoard.append(2);
        this.mainBoard.append(4);
        this.mainBoard.append(3);
        this.mainBoard.append(2);
        this.mainBoard.append(3);
        this.mainBoard.append(1);
        this.mainBoard.append(2);
        this.mainBoard.append(4);
        this.mainBoard.append(2);
        this.mainBoard.append(1);
        this.mainBoard.append(3);
        this.mainBoard.append(4);
        this.mainBoard.append(2);
        this.mainBoard.append(3);
        this.mainBoard.append(4);
        this.mainBoard.append(1);
        this.mainBoard.append(2);
        this.mainBoard.append(2);
        this.mainBoard.append(3);
        this.mainBoard.append(4);

        this.pathA.append(2);
        this.pathA.append(1);
        this.pathA.append(3);
        this.pathA.append(4);
        this.pathA.append(2);

        this.pathB.append(4);
        this.pathB.append(4);
        this.pathB.append(4);
        this.pathB.append(4);

        this.pathC.append(1);
        this.pathC.append(2);
        this.pathC.append(4);
        this.pathC.append(3);
        this.pathC.append(1);
        this.pathC.append(2);
        this.pathC.append(4);
        this.pathC.append(2);
        this.pathC.append(1);
        this.pathC.append(3);

        this.pathD.append(4);
        this.pathD.append(4);
        this.pathD.append(4);
        this.pathD.append(4);

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
            this.eventStack.prepend(event);
        }
    }

    public void initData(int numPlayers, int numRounds, String name1, String name2, String name3, String name4) {
        this.numberOfPlayers = numPlayers;
        this.numberOfRounds = numRounds;
        this.playerName1 = name1;
        this.playerName2 = name2;
        this.playerName3 = name3;
        this.playerName4 = name4;
        this.playerArray = new Player[this.numberOfPlayers];

        this.playerArray[0] = new Player(name1, this.mainBoard.getHead(), this.eventHandler, this.star);
        this.playerArray[1] = new Player(name2, this.mainBoard.getHead(), this.eventHandler, this.star);
        if (!name3.equals("")) {
            this.playerArray[2] = new Player(name3, this.mainBoard.getHead(), this.eventHandler, this.star);

            if (!name4.equals("")) {
                this.playerArray[3] = new Player(name4, this.mainBoard.getHead(), this.eventHandler, this.star);
            }
        }
        this.mainGame();
    }

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

        boolean response = StarController.isResponse();

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

    public void mainGame() {
        Scanner scanner = new Scanner(System.in);
        while (this.roundsPlayed < this.numberOfRounds ){
            for (Player player : playerArray){

                // Player round logic goes here
                System.out.println(player.getName());
                System.out.println("Press enter to move");
                if (player == playerArray[0]){
                    scanner.nextLine();
                }
                scanner.nextLine();

                // Rolls the die and moves the player accordingly
                player.move(playerArray);
            }

            // Selects a random integer that will be the next minigame to be played.
            Random random = new Random();
            chooseMinigame = random.nextInt(6) + 1;

            // Checks that the chosen minigame isn't the same that was last played.
            while (lastPlayed == chooseMinigame){
                System.out.println("Selecting another minigame");
                chooseMinigame = random.nextInt(6) + 1;
            }

            lastPlayed = chooseMinigame;

            // This is a switch to select the next minigame to be played.
            switch (chooseMinigame){
                case 1:
                    System.out.println("Play 1st Minigame");
                    break;
                case 2:
                    System.out.println("Play 2nd Minigame");
                    break;
                case 3:
                    System.out.println("Play 3rd Minigame");
                    break;
                case 4:
                    System.out.println("Play 4th Minigame");
                    break;
                case 5:
                    System.out.println("Play 5th Minigame");
                    break;
                case 6:
                    System.out.println("Play 6th Minigame");
                    break;
            }
            roundsPlayed++;
        }
    }
}
