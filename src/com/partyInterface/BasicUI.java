package com.partyInterface;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BasicUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Creating the loader, the pane and all details
        Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));

        // Create scene
        primaryStage.setScene(new Scene(root));

        // Set the title to the stage
        primaryStage.setTitle("Main Board");

        // Set resizable options
        primaryStage.setResizable(false);

        // doesn't display stage until method invoked
        primaryStage.hide();
        //TODO add the menu interface and script the .fxml file to make it follow the nodes and update the showed screen as expected.
    }
}
