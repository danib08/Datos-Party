package com.minigames.pressGame;

import java.time.Duration;
import java.time.Instant;

public class AmountThread extends Thread{
    final String keyAssigned;
    private int totalStrokes;
    final int time;

    public AmountThread(String key){
        this.keyAssigned = key;
        this.totalStrokes = 0;
        this.time = 10;
    }



    @Override
    public void run() {
        super.run();
        Instant a = Instant.now();
        Instant b = Instant.now();
        long c = Duration.between(a,b).toMillis();
        while (c / 1000 < this.time){
            //TODO key handle
            b = Instant.now();
            c = Duration.between(a,b).toMillis();
        }


    }

    public int getTotalStrokes(){
        return this.totalStrokes;
    }
}
