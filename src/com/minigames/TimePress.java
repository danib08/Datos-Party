package com.minigames;
import com.gameLogic.Player;
import java.util.Random;

public class TimePress extends Minigame{

    double time = 0;
    public TimePress(Player[] arr){
        super(arr);
    }



    @Override
    public void startGame() {
        Random pressTime = new Random();
        time = pressTime.nextInt(4) + 6;
        int pAmount = this.players.length;

        PlayerThread[] threadArr = new PlayerThread[pAmount];
        PlayerThread thread1 = new PlayerThread("A");
        PlayerThread thread2 = new PlayerThread("T");

        threadArr[0] = thread1;
        threadArr[1] = thread2;

        if (pAmount >= 3) {
            PlayerThread thread3 = new PlayerThread("N");
            threadArr[2] = thread3;
            if (pAmount == 4) {
                PlayerThread thread4 = new PlayerThread("P");
                threadArr[3] = thread4;
            }
        }
        for (PlayerThread thread : threadArr) {
            thread.start();
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long[] playerTimes = new long[pAmount];
        int i = 0;
        for (PlayerThread thread :threadArr) {
            long value = (long) Math.abs(time-thread.getTotalTime());
            playerTimes[i] = value;
            i++;
        }
        Player[] toReward = new Player[pAmount];
        for (int j = 0; j< pAmount; j++) {
            toReward[j] = this.players[index(playerTimes)];
        }
        this.reward(toReward);
    }

    public int index(long[] playerTimes){
        int ind = 0;
        long tmp = playerTimes[0];
        for (int i = 1; i < playerTimes.length; i++) {
            if (playerTimes[i] < tmp){
                ind = i;
                tmp = playerTimes[i];
            }
        }
        playerTimes[ind] = 100;
        return ind;
    }
}
