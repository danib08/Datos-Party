package com.minigames.mentalGame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Test extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Creating the loader, the pane and all details
        Parent menu = FXMLLoader.load(getClass().getResource("MentalMain.fxml"));

        // Create scene
        Scene menuScene = new Scene(menu, 1366, 768);

        // Set the scene
        stage.setScene(menuScene);

        // Set the title to the stage
        stage.setTitle("Main Menu");

        // Set resizable options
        stage.setResizable(false);

        // Displays the stage
        stage.show();
    }
}
