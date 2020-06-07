package com.partyInterface;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class MenuController extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param stage the primary stage for this application, onto which
     *                     the application scene can be set. The primary stage will be embedded in
     *                     the browser if the application was launched as an applet.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages and will not be embedded in the browser.
     */
    @Override
    public void start(Stage stage) throws Exception {
        // Creating the loader, the pane and all details
        Parent menu = FXMLLoader.load(getClass().getResource("MenuUI.fxml"));
        // Create scene
        stage.setScene(new Scene(menu));

        // Set the title to the stage
        stage.setTitle("Main Menu");

        // Set resizable options
        stage.setResizable(false);

        // Displays the stage
        stage.show();
        //TODO make the button summon the main board.

        /*
        Button showButton = new Button("game");
        showButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.hide();
                try{
                    Parent game = FXMLLoader.load(getClass().getResource("Sample.fxml"));
                    Stage gameSt = new Stage();
                    gameSt.setScene(new Scene(game));
                    gameSt.setTitle("gameboard");
                    gameSt.setResizable(false);
                    gameSt.show();
                }
                catch(Exception e){
                    System.out.println(e);
                }
            }
        });
        */
    }

    public void ButtonClick(){
        try{
            Parent game = FXMLLoader.load(getClass().getResource("Sample.fxml"));
            Stage gameSt = new Stage();
            gameSt.setScene(new Scene(game));
            gameSt.setTitle("gameboard");
            gameSt.setResizable(false);
            gameSt.show();

        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
