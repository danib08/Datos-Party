package com.minigames.mentalGame;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

public class MentalGameController implements Initializable {

    IntegerProperty secondsProperty = new SimpleIntegerProperty(this, "Seconds", 0);

    @FXML
    private Label secondsLabel;
    @FXML
    private Label milisecondsLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        secondsLabel.textProperty().bind(secondsProperty.asString());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {
                for (int i = 0; i < 9; i++) {
                    int newSeconds = i;

                    try {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException ignored) {
                    }
                    // Update the GUI on the JavaFX Application Thread
                    Platform.runLater(() -> secondsProperty.setValue(newSeconds));
                }
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }
}
