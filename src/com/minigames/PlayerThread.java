package com.minigames;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.time.Duration;
import java.time.Instant;
public class PlayerThread extends Thread{
    private long totalTime;
    final String keyAssigned;

    public PlayerThread(String key){
        this.keyAssigned = key;
    }



    @Override
    public void run() {
        super.run();

        Instant start = Instant.now();
        //TODO players actions and event handling

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Instant finish = Instant.now();
        long time = Duration.between(start,finish).toMillis();
        this.totalTime = time / 1000;
    }

    public long getTotalTime(){
        return this.totalTime;
    }

}
