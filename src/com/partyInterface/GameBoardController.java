package com.partyInterface;

import com.gameLogic.Player;
import com.gameLogic.Star;
import com.minigames.bombGame.BombController;
import com.minigames.duelGame.DuelController;
import com.minigames.memoryGame.MemoryMainController;
import com.minigames.mentalGame.MentalMainController;
import com.minigames.potatoGame.PotatoMainController;
import com.minigames.pressGame.AmountMainController;
import com.minigames.reactGame.reactGameController;
import com.structures.*;
import com.structures.List;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    int numberOfPlayers;
    int currentPlayer = 0;
    int numberOfRounds;
    int roundsPlayed = 1;

    String playerName1;
    String playerName2;
    String playerName3;
    String playerName4;

    int lastPlayed = 7;
    int currentMove;

    ImageView starImage;

    ImageView playerImage1;
    ImageView playerImage2;
    ImageView playerImage3;
    ImageView playerImage4;

    ImageView[] imageArray;
    TextField[] coinsArray;
    TextField[] starsArray;

    @FXML TextField playerID1;
    @FXML TextField playerID2;
    @FXML TextField playerID3;
    @FXML TextField playerID4;
    @FXML Label roundsText;
    @FXML Label playerText;
    @FXML GridPane boardGrid;
    @FXML Label rollLabel;
    @FXML Button rollButton;
    @FXML Button moveButton;
    @FXML TextField coins1;
    @FXML TextField coins2;
    @FXML TextField coins3;
    @FXML TextField coins4;
    @FXML TextField stars1;
    @FXML TextField stars2;
    @FXML TextField stars3;
    @FXML TextField stars4;

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

    /**
     * This method initializes some attributes of the GameBoardController class
     * @param numPlayers The number of players in the game
     * @param numRounds The number of rounds to be played
     * @param name1 The name of the first player
     * @param name2 The name of the second player
     * @param name3 The name of the third player (empty if there isn't a third player)
     * @param name4 The name of the fourth player (empty if there isn't a fourth player)
     * @throws FileNotFoundException Signals that an attempt to open the file denoted by a specified pathname has failed
     */
    public void initData(int numPlayers, int numRounds, String name1, String name2, String name3, String name4) throws FileNotFoundException {
        this.numberOfPlayers = numPlayers;
        this.numberOfRounds = numRounds;
        this.playerName1 = name1;
        this.playerName2 = name2;
        this.playerName3 = name3;
        this.playerName4 = name4;
        this.playerArray = new Player[this.numberOfPlayers];
        this.imageArray = new ImageView[this.numberOfPlayers];
        this.coinsArray = new TextField[this.numberOfPlayers];
        this.starsArray = new TextField[this.numberOfPlayers];

        this.playerArray[0] = new Player(name1, this.mainBoard.getHead());
        this.playerID1.setText(this.playerName1);
        this.coinsArray[0] = coins1;
        this.starsArray[0] = stars1;

        this.playerArray[1] = new Player(name2, this.mainBoard.getHead());
        this.playerID2.setText(this.playerName2);
        this.coinsArray[1] = coins2;
        this.starsArray[1] = stars2;

        if (!name3.equals("")) {
            this.playerArray[2] = new Player(name3, this.mainBoard.getHead());
            this.playerID3.setText(this.playerName3);
            this.coins3.setText("5");
            this.stars3.setText("0");
            this.coinsArray[2] = coins3;
            this.starsArray[2] = stars3;

            if (!name4.equals("")) {
                this.playerArray[3] = new Player(name4, this.mainBoard.getHead());
                this.playerID4.setText(this.playerName4);
                this.coins4.setText("5");
                this.stars4.setText("0");
                this.coinsArray[3] = coins4;
                this.starsArray[3] = stars4;
            }
        }
        this.star.positionStar();
        this.playerText.setText(this.playerName1);
        this.roundsText.setText(Integer.toString(this.roundsPlayed));
        this.moveButton.setLayoutY(-100);
        this.loadImages();
    }

    /**
     * Loads and shows all of the images of the players
     * @throws FileNotFoundException Signals that an attempt to open the file denoted by a specified pathname has failed
     */
    public void loadImages() throws FileNotFoundException {
        FileInputStream inputStream1 = new FileInputStream("src/com/images/dino.png");
        Image image1 = new Image(inputStream1);
        this.playerImage1 = new ImageView(image1);
        playerImage1.setFitHeight(75);
        playerImage1.setFitWidth(75);
        imageArray[0] = playerImage1;
        this.boardGrid.add(playerImage1, 0, 9);

        FileInputStream inputStream2 = new FileInputStream("src/com/images/girl.png");
        Image image2 = new Image(inputStream2);
        this.playerImage2 = new ImageView(image2);
        playerImage2.setFitHeight(75);
        playerImage2.setFitWidth(75);
        imageArray[1] = playerImage2;
        this.boardGrid.add(playerImage2, 0, 9);

        if (this.numberOfPlayers >= 3) {
            FileInputStream inputStream3 = new FileInputStream("src/com/images/dog.png");
            Image image3 = new Image(inputStream3);
            this.playerImage3 = new ImageView(image3);
            playerImage3.setFitHeight(75);
            playerImage3.setFitWidth(75);
            imageArray[2] = playerImage3;
            this.boardGrid.add(playerImage3, 0, 9);

            if (this.numberOfPlayers == 4) {
                FileInputStream inputStream4 = new FileInputStream("src/com/images/boy.png");
                Image image4 = new Image(inputStream4);
                this.playerImage4 = new ImageView(image4);
                playerImage4.setFitHeight(75);
                playerImage4.setFitWidth(75);
                imageArray[3] = playerImage4;
                this.boardGrid.add(playerImage4, 0, 9);
            }
        }
        FileInputStream inputStreamStar = new FileInputStream("src/com/images/star.png");
        Image imageStar = new Image(inputStreamStar);
        this.starImage = new ImageView(imageStar);
        starImage.setFitHeight(75);
        starImage.setFitWidth(75);

        int starRow = this.star.getPosition().getRow();
        int starCol = this.star.getPosition().getCol();
        this.boardGrid.add(starImage, starCol, starRow);

    }

    /**
     * This method generates a random roll of two dice
     */
    public void roll() {
        rollButton.setLayoutY(-100);
        Random random = new Random();
        int result = random.nextInt(6) + 1;
        result += random.nextInt(6) + 1;

        this.currentMove = result;
        rollLabel.setText(Integer.toString(result));
        this.moveButton.setLayoutY(36);
    }

    /**
     * This method manages the movement of the players, and the interface changes according
     * to situations that happen if they land on a certain path, buy starts or change paths.
     * It basically works as the main game method
     * @throws IOException if a file described in the loaders cannot be found/read/loaded
     */
    public void move() throws IOException {
        Player player = this.playerArray[currentPlayer];
        Square prevSquare = null;
        if (this.currentMove >= 1) {
            if (this.currentMove == 1) {
                prevSquare = player.getPosition();
            }
            if (player.getPosition().getPathLink() != null && !player.getPathChanged()) {
                this.pathSel(player);
            }
            else if (player.getBackwards()) {
                player.setPosition(player.getPosition().getPrev());
                player.setPathChanged(false);
            }
            else {
                player.setPosition(player.getPosition().getNext());
                player.setPathChanged(false);
            }
            this.currentMove--;
        }

        int row = player.getPosition().getRow();
        int col = player.getPosition().getCol();
        this.boardGrid.getChildren().remove(this.imageArray[currentPlayer]);
        this.boardGrid.add(this.imageArray[currentPlayer], col, row);

        rollLabel.setText(Integer.toString(currentMove));

        if (this.star.getPosition() == player.getPosition()) {
            this.starBuy(player, currentPlayer);
        }

        if (this.currentMove == 0) {
           this.moveButton.setLayoutY(-100);

            for (Player playerTarget : playerArray) {
                if (playerTarget.getPosition() == player.getPosition() && !playerTarget.getName().equals(player.getName())){
                    samePos(player, playerTarget, prevSquare);
                }
            }

           switch (player.getPosition().getData()) {
               case 2:
                    this.changeCoins(true);
                    player.updateCoins(3);
                    this.coinsArray[this.currentPlayer].setText(Integer.toString(player.getCoins()));
                    break;
               case 3:
                    this.changeCoins(false);
                    player.updateCoins(-3);
                    this.coinsArray[this.currentPlayer].setText(Integer.toString(player.getCoins()));
                    break;
               case 4:
                   this.eventSelecter(currentPlayer);
                   this.checkLength();
                   break;
            }
            if (this.currentPlayer == this.numberOfPlayers - 1) {
                if (this.roundsPlayed == this.numberOfRounds) {
                    //TODO Finish game, show reward scene
                    System.out.println("Game finished");
                }
                else {
                    this.currentPlayer = 0;
                    this.roundsPlayed++;
                    Random random = new Random();
                    int i = random.nextInt(6);
                    while (lastPlayed == i){
                        i = random.nextInt(6);
                    }
                    i = 2;
                    switch (i){
                        case 0:
                            this.startBombGame();
                            break;
                        case 1:
                            this.startMemoryGame();
                            break;
                        case 2:
                            this.startMentalGame();
                            break;
                        case 3:
                            this.startPotatoGame();
                            break;
                        case 4:
                            this.startPressGame(this.playerArray);
                            break;
                        case 5:
                            this.startReactionGame();
                            break;
                    }
                    System.out.println("Played minigame");
                    this.roundsText.setText(Integer.toString(this.roundsPlayed));
                    for (int j = 0; j<= this.numberOfPlayers-1; j++) {
                        this.coinsArray[j].setText(Integer.toString(this.playerArray[j].getCoins()));
                    }
                }
            }
            else {
                this.currentPlayer++;
            }
            this.playerText.setText(this.playerArray[currentPlayer].getName());
            this.rollButton.setLayoutY(36);
        }
    }

    /**
     * Opens a new window that shows if the player lost or won coins
     * @throws IOException if a file described in the loaders cannot be found/read/loaded.
     */
    public void changeCoins(boolean obtain) throws IOException {
        Stage coinsWindow = new Stage();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("WinCoins.fxml"));
        Parent coinsParent = loader.load();
        Scene starScene = new Scene(coinsParent);

        // Accessing the Interface controller
        WinCoinsController controller = loader.getController();
        controller.initData(obtain);

        coinsWindow.initModality(Modality.APPLICATION_MODAL);
        coinsWindow.setTitle("Coins");
        coinsWindow.setResizable(false);

        coinsWindow.setScene(starScene);
        coinsWindow.showAndWait();
    }

    /**
     * This method handles when two players are in the same Square
     * @param playerUnleasher the player that activated the event
     * @param triggerPlayer the player that is in the same Square as currentPlayer
     * @param prevSquare the Square behind the current Square
     */
    public void samePos(Player playerUnleasher, Player triggerPlayer, Square prevSquare) throws IOException {
        Player[] duelArray = new Player[2];

        duelArray[0] = playerUnleasher;
        duelArray[1] = triggerPlayer;

        Player winner = startDuelGame(duelArray);
        Player loser;

        if (winner == playerUnleasher){
            loser = triggerPlayer;
        }
        else {
            loser = playerUnleasher;
        }

        int index = 0;
        for (int i = 0; i < numberOfPlayers; i++) {
            if (loser == this.playerArray[i]){
                index = i;
                break;
            }
        }

        loser.setPosition(prevSquare);
        int row = loser.getPosition().getRow();
        int col = loser.getPosition().getCol();
        this.boardGrid.getChildren().remove(this.imageArray[index]);
        this.boardGrid.add(this.imageArray[index], col, row);

    }

    /**
     * Opens a new window so the player can make a choice to change directions
     * @throws IOException if a file described in the loaders cannot be found/read/loaded.
     */
    public void pathSel(Player player) throws IOException {
        if (player.getOnMain()) {
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

            boolean response = controller.getResponse();
            if (response) {
                player.setPosition(player.getPosition().getPathLink());
                if (player.getPosition().getData() == 3) {
                    player.setBackwards(true);
                }
                player.setOnMain(false);
                player.setPathChanged(true);
            }
            else {
                player.setPosition(player.getPosition().getNext());
            }
        }
        else {
            player.setOnMain(true);
            player.setBackwards(false);
            player.setPosition(player.getPosition().getPathLink());
            player.setPathChanged(true);
        }
    }

    /**
     * Opens a new window so the player can make a choice to buy a star
     * @throws IOException if a file described in the loaders cannot be found/read/loaded
     */
    public void starBuy(Player player, int currentPlayer) throws IOException{
        Stage starWindow = new Stage();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("StarInterface.fxml"));
        Parent starParent = loader.load();
        Scene starScene = new Scene(starParent);

        StarController controller = loader.getController();
        controller.initData(player.getCoins());

        starWindow.initModality(Modality.APPLICATION_MODAL);
        starWindow.setTitle("Star!");
        starWindow.setResizable(false);

        starWindow.setOnCloseRequest(Event::consume);

        starWindow.setScene(starScene);
        starWindow.showAndWait();

        boolean response = controller.getResponse();
        if (response) {
            player.buyStar();
            star.positionStar();
            this.starsArray[currentPlayer].setText(Integer.toString(player.getStars()));

            int row = star.getPosition().getRow();
            int col = star.getPosition().getCol();
            this.boardGrid.getChildren().remove(this.starImage);
            this.boardGrid.add(this.starImage, col, row);
        }
    }

    /**
     * Grabs the first element in the stack and executes the corresponding event
     * @param currentPlayer index of the current player
     * @throws IOException if a file described in the loaders cannot be found/read/loaded
     */
    public void eventSelecter(int currentPlayer) throws IOException {
        int event = this.eventStack.popHead();
        switch (event) {
            case 1:
                this.duel(currentPlayer);
                break;
            case 2:
                System.out.println(2);
                this.stealCoins(currentPlayer);
                break;
            case 3:
                System.out.println(3);
                this.donateCoins(currentPlayer);
                break;
            case 4:
                System.out.println(4);
                this.loseStar(currentPlayer);
                break;
            case 5:
                System.out.println(5);
                this.winStar(currentPlayer, 2);
                break;
            case 6:
                System.out.println(6);
                this.winStar(currentPlayer, 5);
                break;
            case 7:
                System.out.println(7);
                this.stealStar(currentPlayer);
                break;
            case 8:
                System.out.println(8);
                this.teleport(currentPlayer);
                break;
            case 9:
                System.out.println(9);
                this.swap(currentPlayer);
                break;
        }
    }

    /**
     * This method activates a duel between two players
     * @param currentPlayer the index of the player that activated the duel
     * @throws IOException if the file to load couldn't be lodead/read
     */
    public void duel(int currentPlayer) throws IOException {
        Player[] duelArray = new Player[2];
        Player playerUnleasher = this.playerArray[currentPlayer];
        Player playerTarget;

        Random random = new Random();
        int targetIndex = random.nextInt(3);
        playerTarget = this.playerArray[targetIndex];

        while (playerTarget.getName().equals(playerUnleasher.getName())) {
            targetIndex = random.nextInt(3);
            playerTarget = this.playerArray[targetIndex];
        }

        duelArray[0] = playerUnleasher;
        duelArray[1] = playerTarget;

        Player winner = this.startDuelGame(duelArray);

        Player loser;

        if (winner == playerUnleasher){
            loser = playerTarget;
        }
        else {
            loser = playerUnleasher;
        }

        winner.updateCoins(5);
        loser.updateCoins(-5);

        this.coinsArray[currentPlayer].setText(Integer.toString(playerUnleasher.getCoins()));
        this.coinsArray[targetIndex].setText(Integer.toString(this.playerArray[targetIndex].getCoins()));
    }

    /**
     * The player that activated the event steals coin from another one
     * @param currentPlayer index of the current player
     * @throws IOException if a file described in the loaders cannot be found/read/loaded
     */
    public void stealCoins(int currentPlayer) throws IOException {
        Player playerUnleasher = this.playerArray[currentPlayer];

        Stage stealCoinsWindow = new Stage();
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("StealCoins.fxml"));
        Parent stealCoinsParent = loader.load();
        Scene stealCoinsScene = new Scene(stealCoinsParent);

        stealCoinsWindow.initModality(Modality.APPLICATION_MODAL);
        stealCoinsWindow.setTitle("Steal Coins Event");
        stealCoinsWindow.setResizable(false);
        stealCoinsWindow.setOnCloseRequest(Event::consume);

        StealCoinsController controller = loader.getController();
        controller.initData(currentPlayer, this.playerArray);

        stealCoinsWindow.setScene(stealCoinsScene);
        stealCoinsWindow.showAndWait();

        int targetIndex = controller.getPlayerTargetIndex();
        Random random = new Random();
        int coins = random.nextInt(this.playerArray[targetIndex].getCoins() / 2) + 1;

        this.playerArray[currentPlayer].updateCoins(coins);
        this.playerArray[targetIndex].updateCoins(-coins);

        this.coinsArray[currentPlayer].setText(Integer.toString(playerUnleasher.getCoins()));
        this.coinsArray[targetIndex].setText(Integer.toString(this.playerArray[targetIndex].getCoins()));
    }

    /**
     * The player that activated the event gives coins to another one
     * @param currentPlayer index of the current player
     * @throws IOException if a file described in the loaders cannot be found/read/loaded
     */
    public void donateCoins(int currentPlayer) throws IOException {
        Player playerUnleasher = this.playerArray[currentPlayer];

        Stage donateCoinsWindow = new Stage();
        FXMLLoader loader = new FXMLLoader();
        String path;

        if (playerUnleasher.getCoins() / 2 >= this.numberOfPlayers - 1) {
            path = "DonateCoins.fxml";
        }
        else {
            path = "NoDonateCoins.fxml";
        }

        loader.setLocation(getClass().getResource(path));
        Parent donateCoinsParent = loader.load();
        Scene donateCoinsScene = new Scene(donateCoinsParent);

        donateCoinsWindow.initModality(Modality.APPLICATION_MODAL);
        donateCoinsWindow.setTitle("Donate Coins Event");
        donateCoinsWindow.setResizable(false);

        if (playerUnleasher.getCoins() / 2 >= (this.numberOfPlayers - 1)) {
            Random random = new Random();
            int coins = random.nextInt(playerUnleasher.getCoins() / 2) + 1;
            while (coins % (this.numberOfPlayers - 1) != 0) {
                coins = random.nextInt(playerUnleasher.getCoins() / 2) + 1;
            }

            DonateCoinsController controller = loader.getController();
            controller.initData(coins);
            int toGive = coins / this.numberOfPlayers;

            playerUnleasher.updateCoins(-coins);
            for (int i = 0; i < this.numberOfPlayers; i++) {
                if (i != currentPlayer) {
                    this.playerArray[i].updateCoins(toGive);
                }
            }
            for (int i = 0; i < this.numberOfPlayers; i++) {
                this.coinsArray[i].setText(Integer.toString(this.playerArray[i].getCoins()));
            }
        }
        donateCoinsWindow.setScene(donateCoinsScene);
        donateCoinsWindow.showAndWait();
    }


    /**
     * The player that activated the event loses a star and gives it to another player
     * @param currentPlayer index of the current player
     * @throws IOException if a file described in the loaders cannot be found/read/loaded
     */
    public void loseStar(int currentPlayer) throws IOException {
        Player playerUnleasher = this.playerArray[currentPlayer];

        Stage loseStarWindow = new Stage();
        FXMLLoader loader = new FXMLLoader();
        String path;

        if (playerUnleasher.getStars() > 0) {
            path = "LoseStar.fxml";
        }
        else {
            path = "NoLoseStar.fxml";
        }

        loader.setLocation(getClass().getResource(path));
        Parent loseStarParent = loader.load();
        Scene loseStarScene = new Scene(loseStarParent);

        loseStarWindow.initModality(Modality.APPLICATION_MODAL);
        loseStarWindow.setTitle("Lose Star Event");
        loseStarWindow.setResizable(false);

        if (playerUnleasher.getStars() > 0) {
            Player playerTarget;
            Random random = new Random();
            int targetIndex = random.nextInt(playerArray.length);
            playerTarget = playerArray[targetIndex];

            while(playerTarget.getName().equals(playerUnleasher.getName())) {
                targetIndex = random.nextInt(playerArray.length);
                playerTarget = playerArray[targetIndex];
            }

           LoseStarController controller = loader.getController();
           controller.initData(playerTarget.getName());

            playerUnleasher.updateStars(-1);
            playerTarget.updateStars(1);

            this.starsArray[currentPlayer].setText(Integer.toString(playerUnleasher.getStars()));
            this.starsArray[targetIndex].setText(Integer.toString(playerTarget.getStars()));
        }
        loseStarWindow.setScene(loseStarScene);
        loseStarWindow.showAndWait();
    }

    /**
     * The player that activated this event wins a certain amount of stars
     * @param currentPlayer index of the current player
     * @param stars stars The amount of stars that will be won
     * @throws IOException if a file described in the loaders cannot be found/read/loaded
     */
    public void winStar(int currentPlayer, int stars) throws IOException {
        Player playerUnleasher = this.playerArray[currentPlayer];

        Stage winStarWindow = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("WinStar.fxml"));
        Parent winStarParent = loader.load();
        Scene winStarScene = new Scene(winStarParent);

        winStarWindow.initModality(Modality.APPLICATION_MODAL);
        winStarWindow.setTitle("Win Star Event");
        winStarWindow.setResizable(false);

        WinStarController controller = loader.getController();
        controller.initData(stars);

        winStarWindow.setScene(winStarScene);
        winStarWindow.showAndWait();

        playerUnleasher.updateStars(stars);
        this.starsArray[currentPlayer].setText(Integer.toString(playerUnleasher.getStars()));
    }

    /**
     * The player that activated this event steals a star from another player
     * @param currentPlayer index of the current player
     * @throws IOException if a file described in the loaders cannot be found/read/loaded
     */
    public void stealStar(int currentPlayer) throws IOException {
        Player playerUnleasher = this.playerArray[currentPlayer];
        int targetIndex;
        Player playerTarget;

        boolean allEmpty = true;
        for (Player player : this.playerArray) {
            if (player.getStars() > 0 && !player.getName().equals(playerUnleasher.getName())) {
                allEmpty = false;
                break;
            }
        }

        if (!allEmpty) {
            Random random = new Random();
            targetIndex = random.nextInt(playerArray.length);
            playerTarget = playerArray[targetIndex];

            while(playerTarget.getName().equals(playerUnleasher.getName()) || playerTarget.getStars() == 0) {
                targetIndex = random.nextInt(playerArray.length);
            }
        }
        else {
            if (currentPlayer != 0) {
                targetIndex = currentPlayer--;
            }
            else {
                targetIndex = 1;
            }
        }
        playerTarget = playerArray[targetIndex];

        Stage stealStarWindow = new Stage();
        FXMLLoader loader = new FXMLLoader();

        String path;
        if (!allEmpty) {
            path = "StealStar.fxml";
        }
        else {
            path = "NoStealStar.fxml";
        }

        loader.setLocation(getClass().getResource(path));
        Parent stealStarParent = loader.load();
        Scene stealStarScene = new Scene(stealStarParent);

        stealStarWindow.initModality(Modality.APPLICATION_MODAL);
        stealStarWindow.setTitle("Steal Star Event");
        stealStarWindow.setResizable(false);

        if (!allEmpty) {
            StealStarController controller = loader.getController();
            controller.initData(playerTarget.getName());

            playerUnleasher.updateStars(1);
            playerTarget.updateStars(-1);
            this.starsArray[currentPlayer].setText(Integer.toString(playerUnleasher.getStars()));
            this.starsArray[targetIndex].setText(Integer.toString(playerTarget.getStars()));
        }
        else {
            NoStealStarController controller = loader.getController();
            controller.initData(playerTarget.getName());
        }
       stealStarWindow.setScene(stealStarScene);
       stealStarWindow.showAndWait();
    }

    /**
     * The player that activates this event swaps positions with other player
     * @param currentPlayer index of the current player
     */
    public void swap(int currentPlayer) throws IOException {
        Player playerUnleasher = this.playerArray[currentPlayer];
        int targetIndex;
        Player playerTarget;

        Random random = new Random();
        targetIndex = random.nextInt(playerArray.length);
        playerTarget = playerArray[targetIndex];

        while(playerTarget.getName().equals(playerUnleasher.getName())) {
            targetIndex = random.nextInt(playerArray.length);
            playerTarget = playerArray[targetIndex];
        }

        Stage swapWindow = new Stage();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Swap.fxml"));
        Parent swapParent = loader.load();
        Scene swapScene = new Scene(swapParent);

        SwapController controller = loader.getController();
        controller.initData(playerTarget.getName());

        swapWindow.initModality(Modality.APPLICATION_MODAL);
        swapWindow.setTitle("Star!");
        swapWindow.setResizable(false);

        boolean onMain1 = playerUnleasher.getOnMain();
        playerUnleasher.setOnMain(playerTarget.getOnMain());
        playerTarget.setOnMain(onMain1);

        boolean backwards1 = playerUnleasher.getBackwards();
        playerUnleasher.setBackwards(playerTarget.getBackwards());
        playerTarget.setBackwards(backwards1);

        Square pos1 = playerUnleasher.getPosition();
        playerUnleasher.setPosition(playerTarget.getPosition());
        playerTarget.setPosition(pos1);

        int row1 = playerUnleasher.getPosition().getRow();
        int col1 = playerUnleasher.getPosition().getCol();
        this.boardGrid.getChildren().remove(this.imageArray[currentPlayer]);
        this.boardGrid.add(this.imageArray[currentPlayer], col1, row1);

        int row2 = playerTarget.getPosition().getRow();
        int col2 = playerTarget.getPosition().getCol();
        this.boardGrid.getChildren().remove(this.imageArray[targetIndex]);
        this.boardGrid.add(this.imageArray[targetIndex], col2, row2);

        swapWindow.setScene(swapScene);
        swapWindow.showAndWait();
    }

    /**
     * Teleports the player to a random position
     * @param currentPlayer index of the current player
     */
    public void teleport(int currentPlayer) throws IOException {
        Stage teleportWindow = new Stage();
        Player playerUnleasher = this.playerArray[currentPlayer];

        Random index = new Random();
        int pathInd = index.nextInt(this.pathArray.length);
        List destination = this.pathArray[pathInd];

        int pos = index.nextInt(destination.getLength());
        Square newPos = destination.getElement(pos);
        playerUnleasher.setPosition(newPos);

        playerUnleasher.setOnMain(true);
        playerUnleasher.setBackwards(false);

        String path = "Teleport.fxml";
        if (pathInd != 0) {
            playerUnleasher.setOnMain(false);
            if (pathInd == 3 || pathInd == 4) {
                path = "AskTeleport.fxml";
            }
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        Parent teleportParent = loader.load();
        Scene teleportScene = new Scene(teleportParent);

        teleportWindow.initModality(Modality.APPLICATION_MODAL);
        teleportWindow.setTitle("Teleport!");
        teleportWindow.setResizable(false);

        teleportWindow.setScene(teleportScene);
        teleportWindow.showAndWait();

        if (pathInd == 3 || pathInd == 4) {
            TeleportController controller = loader.getController();
            boolean answer = controller.getAnswer();
            if (!answer) {
                playerUnleasher.setBackwards(true);
            }
        }

        int row = playerUnleasher.getPosition().getRow();
        int col = playerUnleasher.getPosition().getCol();
        this.boardGrid.getChildren().remove(this.imageArray[currentPlayer]);
        this.boardGrid.add(this.imageArray[currentPlayer], col, row);
    }

    /**
     * Checks if the event stack is empty, if it its it calls the stackRefill method.
     */
    public void checkLength(){
        if (this.eventStack.getLength() == 0){
            this.stackRefill();
        }
    }

    /**
     * Refills the events stack with a new randomized order of events.
     */
    public void stackRefill(){
        Collections.shuffle(this.eventList);

        for (Integer event : this.eventList) {
            this.eventStack.prepend(event, 0 , 0);
        }
    }

    /**
     * This opens a new window when the bomb minigame is executed
     * @throws IOException if the file to load cannot be accessed
     */
    public void startBombGame() throws IOException{
        Stage bombWindow = new Stage();

        FXMLLoader bombLoader = new FXMLLoader();
        bombLoader.setLocation(getClass().getResource("/com/minigames/bombGame/bombGame.fxml"));
        Parent bombParent = bombLoader.load();
        Scene bombScene = new Scene(bombParent);

        BombController bombController = bombLoader.getController();
        bombController.initData(playerArray);
        //this modality is meant to transform the minigame window into the only interaction-allowed one for the user
        //thus the players can only exit the window by playing the minigame
        bombWindow.initModality(Modality.APPLICATION_MODAL);
        bombWindow.setTitle("Detonators Minigame");
        bombWindow.setResizable(false);

        bombWindow.setOnCloseRequest(Event :: consume);

        bombWindow.setScene(bombScene);
        bombWindow.showAndWait();
    }

    /**
     * This opens a new window when the react minigame is executed
     * @throws IOException if the file to load cannot be accessed
     */
    public void startReactionGame() throws IOException{
        Stage reactWindow = new Stage();

        FXMLLoader reactLoader = new FXMLLoader();
        reactLoader.setLocation(getClass().getResource("/com/minigames/reactGame/reactGame.fxml"));
        Parent reactParent = reactLoader.load();
        Scene reactScene = new Scene(reactParent);

        reactGameController reactController = reactLoader.getController();
        reactController.initData(playerArray);

        //this modality is meant to transform the minigame window into the only interaction-allowed one for the user.
        //thus the players can only exit the window by playing the minigame.
        reactWindow.initModality(Modality.APPLICATION_MODAL);
        reactWindow.setTitle("Reaction Minigame");
        reactWindow.setResizable(false);

        reactWindow.setOnCloseRequest(Event :: consume);

        reactWindow.setScene(reactScene);
        reactWindow.showAndWait();
    }

    /**
     * This opens a new window when the memory minigame is executed
     * @throws IOException if the file to load cannot be accessed
     */
    public void startMemoryGame() throws IOException{
        Stage memoryWindow = new Stage();

        FXMLLoader memoryLoader = new FXMLLoader();
        memoryLoader.setLocation(getClass().getResource("/com/minigames/memoryGame/MemoryMain.fxml"));
        Parent memoryParent = memoryLoader.load();
        Scene memoryScene = new Scene(memoryParent);

        MemoryMainController memoryController = memoryLoader.getController();
        memoryController.initData(playerArray);

        //this modality is meant to transform the minigame window into the only interaction-allowed one for the user.
        //thus the players can only exit the window by playing the minigame.
        memoryWindow.initModality(Modality.APPLICATION_MODAL);
        memoryWindow.setTitle("Memory Minigame");
        memoryWindow.setResizable(false);

        memoryWindow.setOnCloseRequest(Event :: consume);

        memoryWindow.setScene(memoryScene);
        memoryWindow.showAndWait();
    }

    /**
     * This opens a new window when the mental minigame is executed
     * @throws IOException if the file to load cannot be accessed
     */
    public void startMentalGame() throws IOException{
        Stage memtalWindow = new Stage();

        FXMLLoader mentalLoader = new FXMLLoader();
        mentalLoader.setLocation(getClass().getResource("/com/minigames/mentalGame/MentalMain.fxml"));
        Parent mentalParent = mentalLoader.load();
        Scene mentalScene = new Scene(mentalParent);

        MentalMainController mentalController = mentalLoader.getController();
        mentalController.initData(playerArray);

        //this modality is meant to transform the minigame window into the only interaction-allowed one for the user.
        //thus the players can only exit the window by playing the minigame.
        memtalWindow.initModality(Modality.APPLICATION_MODAL);
        memtalWindow.setTitle("Mental Clock Minigame");
        memtalWindow.setResizable(false);

        memtalWindow.setOnCloseRequest(Event :: consume);

        memtalWindow.setScene(mentalScene);
        memtalWindow.showAndWait();
    }

    /**
     * This opens a new window when the potato minigame is executed
     * @throws IOException if the file to load cannot be accessed
     */
    public void startPotatoGame() throws IOException{
        Stage potatoWindow = new Stage();

        FXMLLoader potatoLoader = new FXMLLoader();
        potatoLoader.setLocation(getClass().getResource("/com/minigames/potatoGame/PotatoMain.fxml"));
        Parent potatoParent = potatoLoader.load();
        Scene potatoScene = new Scene(potatoParent);

        PotatoMainController potatoController = potatoLoader.getController();
        potatoController.initData(playerArray);

        //this modality is meant to transform the minigame window into the only interaction-allowed one for the user.
        //thus the players can only exit the window by playing the minigame.
        potatoWindow.initModality(Modality.APPLICATION_MODAL);
        potatoWindow.setTitle("Hot Potato Minigame");
        potatoWindow.setResizable(false);

        potatoWindow.setOnCloseRequest(Event :: consume);

        potatoWindow.setScene(potatoScene);
        potatoWindow.showAndWait();
    }

    /**
     * This opens a new window when the press minigame is executed
     * @throws IOException if the file to load cannot be accessed
     */
    public void startPressGame(Player[] playerArray) throws IOException{
        Stage pressWindow = new Stage();

        FXMLLoader pressLoader = new FXMLLoader();
        pressLoader.setLocation(getClass().getResource("/com/minigames/pressGame/AmountMain.fxml"));
        Parent pressParent = pressLoader.load();
        Scene pressScene = new Scene(pressParent);

        AmountMainController pressController = pressLoader.getController();
        pressController.initData(playerArray);

        //this modality is meant to transform the minigame window into the only interaction-allowed one for the user.
        //thus the players can only exit the window by playing the minigame.
        pressWindow.initModality(Modality.APPLICATION_MODAL);
        pressWindow.setTitle("Press the key Minigame");
        pressWindow.setResizable(false);

        pressWindow.setOnCloseRequest(Event :: consume);

        pressWindow.setScene(pressScene);
        pressWindow.showAndWait();
    }

    /**
     * This opens a new window when the duel minigame is executed
     * @throws IOException if the file to load cannot be accessed
     */
    public Player startDuelGame(Player[] playerArray) throws IOException{
        Stage duelWindow = new Stage();

        FXMLLoader duelLoader = new FXMLLoader();
        duelLoader.setLocation(getClass().getResource("/com/minigames/duelGame/Duel.fxml"));
        Parent duelParent = duelLoader.load();
        Scene duelScene = new Scene(duelParent);

        DuelController duelController = duelLoader.getController();
        duelController.initData(playerArray);

        //this modality is meant to transform the minigame window into the only interaction-allowed one for the user.
        //thus the players can only exit the window by playing the minigame.
        duelWindow.initModality(Modality.APPLICATION_MODAL);
        duelWindow.setTitle("Duel");
        duelWindow.setResizable(false);

        duelWindow.setOnCloseRequest(Event :: consume);

        duelWindow.setScene(duelScene);
        duelWindow.showAndWait();

        return duelController.getWinner();
    }
}
