package com.partyInterface;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class gameBoardController {


    public boolean pathSel() throws IOException {
        Stage pathWindow = new Stage();
        Parent pathParent = FXMLLoader.load(getClass().getResource("PathSelection.fxml"));
        Scene pathScene = new Scene(pathParent);

        pathWindow.initModality(Modality.APPLICATION_MODAL);
        pathWindow.setTitle("Select Path");

        pathWindow.setOnCloseRequest(Event::consume);

        pathWindow.setScene(pathScene);
        pathWindow.showAndWait();

        boolean response = PathSelController.isResponse();

        System.out.println(response);

        return response;
    }
}
