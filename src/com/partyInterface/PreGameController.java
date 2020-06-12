package com.partyInterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class PreGameController {

    protected int numberOfPlayers;
    protected int numberOfRounds;

    protected String playerName1 = "";
    protected String playerName2 = "";
    protected String playerName3 = "";
    protected String playerName4 = "";

    protected boolean canChange = true;

    @FXML RadioButton radio2;
    @FXML RadioButton radio3;
    @FXML RadioButton radio4;
    @FXML TextField player1;
    @FXML TextField player2;
    @FXML TextField player3;
    @FXML TextField player4;
    @FXML private ComboBox<Integer> roundBox;

    /**
     * This method check that all of the correct values are entered on the PreGame Menu and
     * loads the GameBoard scene
     * @param event Receives a click on the button located in the PreGame Menu
     * @throws IOException
     */
    public void startGame(ActionEvent event) throws IOException {
        if (!roundBox.getSelectionModel().isEmpty() && !player1.getText().equals("") && !player2.getText().equals("")){
            this.numberOfRounds = roundBox.getValue();
            this.playerName1 = player1.getText();
            this.playerName2 = player2.getText();

            if (radio2.isSelected()) {
                this.numberOfPlayers = 2;
                this.canChange = true;
            }
            else if (radio3.isSelected() && !player3.getText().equals("")) {
                this.numberOfPlayers = 3;
                this.playerName3 = player3.getText();
                this.canChange = true;
            }
            else if (radio4.isSelected() && !player3.getText().equals("") && !player4.getText().equals("")) {
                this.numberOfPlayers = 4;
                this.playerName3 = player3.getText();
                this.playerName4 = player4.getText();
                this.canChange = true;
            }
            else {
                this.canChange = false;
            }
        }
        else {
            this.canChange = false;
        }

        if (this.canChange) {
            this.changeScene(event);
        }
        else {
            this.showError();
        }
    }

    /**
     * Changes the scene to the GameBoard one, and passes information to it
     * @param event
     * @throws IOException
     */
    public void changeScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("GameBoard.fxml"));
        Parent gameBoardParent = loader.load();

        Scene gameBoardScene = new Scene(gameBoardParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        // Accessing the Interface controller
        GameBoardController controller = loader.getController();
        controller.initData(this.numberOfPlayers, this.numberOfRounds, this.playerName1, this.playerName2, this.playerName3, this.playerName4);

        window.setScene(gameBoardScene);
        window.show();
    }

    /**
     * If the user does not enter the correct data, this method displays an alert window
     * @throws IOException
     */
    public void showError() throws IOException {
        Stage errorWindow = new Stage();
        Parent errorParent = FXMLLoader.load(getClass().getResource("DataError.fxml"));
        Scene pathScene = new Scene(errorParent);

        errorWindow.initModality(Modality.APPLICATION_MODAL);
        errorWindow.setTitle("Error");
        errorWindow.setResizable(false);
        errorWindow.setScene(pathScene);
        errorWindow.showAndWait();
    }
}
