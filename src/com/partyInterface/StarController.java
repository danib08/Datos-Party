package com.partyInterface;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class StarController extends Application{

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
            Parent star = FXMLLoader.load(getClass().getResource("StarInterface.fxml"));
        }

}
