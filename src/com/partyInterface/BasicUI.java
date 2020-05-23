package com.partyInterface;

import com.structures.CircularSinglyLinkedList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class BasicUI extends Application {

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws IOException{
        // Creating the loader
        FXMLLoader loader = new FXMLLoader();
        // path to the file
        String filePath = "C:\\Users\\Alejandro José\\IdeaProjects\\Datos-Party\\src\\com\\partyInterface\\Sample.fxml";
        FileInputStream fxmlStream = new FileInputStream(filePath);

        //Create pane and all Details
        AnchorPane root = (AnchorPane) loader.load(fxmlStream);

        //Create Scene
        Scene scene = new Scene(root);
        //set the scene to the stage
        primaryStage.setScene(scene);
        //set the title to the stage
        primaryStage.setTitle("Main Board");
        //display the stage
        primaryStage.show();
    }
}
