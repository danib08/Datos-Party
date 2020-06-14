package com.minigames.reactGame;

import com.gameLogic.Player;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class reactGame2Control implements Initializable{
    @FXML Label label1;
    @FXML Label label2;
    @FXML Label label3;
    @FXML Label label4;
    @FXML Label labelMain;

    @FXML TextField textField1;
    @FXML TextField textField2;
    @FXML TextField textField3;
    @FXML TextField textField4;

    @FXML Button startButton;


    private boolean expectingClick;
    private boolean isClicked;

    Player[] players;
    long [] pTimes;

    long start;
    long finish;

    int pAmount;
    int i = 0;
    Random timestart;



    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textField1.setText(players[0].getName());
        textField2.setText(players[1].getName());

        if (pAmount >=3 ){
            textField3.setText(players[2].getName());
            if (pAmount == 4){
                textField4.setText(players[3].getName());
            }
        }

        this.labelMain.setDisable(true);

        if (pAmount <= 3){
            textField4.setVisible(false);
            label4.setVisible(false);
            if (pAmount == 2){
                textField3.setVisible(false);
                label3.setVisible(false);
            }
        }
        pTimes = new long[pAmount];
        labelMain.setStyle("-fx-background-color: gray");
    }

    public void initData(Player[] players){
        this.players = players;
    }

    /**
     * this method, bound to the start button, starts counting time for the player in question to register their
     * reaction time.
     * @throws InterruptedException if the thread is suddenly interrupted by a higher-priority process.
     */
    public void startRound() throws InterruptedException{
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                timestart = new Random();
                if (i == pAmount - 1) {
                    int t = timestart.nextInt(4) + 1;
                    Thread.sleep(1000 * t);
                    labelMain.setDisable(false);
                    expectingClick = true;
                    isClicked = false;
                    Platform.runLater(()->{
                        labelMain.setText("click now!");
                        labelMain.setStyle("-fx-background-color: #00ff00");
                        startButton.setText("finish");
                    });
                    start = System.currentTimeMillis();
                } else if (i == pAmount) {
                    startButton.setDisable(true);
                    Player[] toReward = new Player[pAmount];
                    for (int j = 0; j < pAmount; j++) {
                    toReward[j] = players[getSmallest(pTimes)];
                    //reward(toReward);
                    }
                } else {
                    int t = timestart.nextInt(4) + 1;
                    labelMain.setDisable(true);
                    Thread.sleep(1000*t);
                    labelMain.setDisable(false);
                    expectingClick = true;
                    isClicked = false;
                    start = System.currentTimeMillis();
                    Platform.runLater(()->{
                        labelMain.setText("test");
                        labelMain.setStyle("-fx-background-color: #00ff00");
                    });
                }
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * method bound to the center label that listens for click events.
     * @param click event caught by the MouseEvent.
     */
    public void listenClick(MouseEvent click){
        System.out.println("clicked");
        if (expectingClick && !isClicked){
            expectingClick = false;
            isClicked = true;
        }
        finish = System.currentTimeMillis();
        long time = finish-start;
        labelMain.setStyle("-fx-background-color: gray");
        labelMain.setDisable(true);
        pTimes[i] = time;
        switch (i) {
            case 0:
                label1.setText("time: " + time);
                break;
            case 1:
                label2.setText("time: " + time);
                break;
            case 2:
                label3.setText("time: " + time);
                break;
            case 3:
                label4.setText("time: " + time);
                break;
        }
        this.i++;
    }


    /**
     * method to obtain the smallest value in a specific array, change it to a bigger value that on a next
     * iteration, cannot be confused for a small value
     * @param pTimes a long-type array containing the player registered times.
     * @return the index-positional value for the smallest value in the ptimes array.
     */
    public int getSmallest(long[] pTimes){
        int index = 0;
        int result = 0;
        long value = pTimes[0];
        while (index < pAmount-1){
            long nextVal = pTimes[index+1];
            if (nextVal < value){
                value = nextVal;
                result = index;
            }
            index++;
        }
        pTimes[result] = 500000;
        return result;
    }
}
