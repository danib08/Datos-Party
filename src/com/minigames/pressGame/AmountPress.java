package com.minigames.pressGame;

import com.gameLogic.Player;
import com.minigames.Minigame;

public class AmountPress extends Minigame {

    double time = 0;
    public AmountPress(Player[] arr){
        super(arr);
    }

    @Override
    public void startGame() {
        int time = 10;
        int pAmount = this.players.length;

        AmountThread[] threadArr = new AmountThread[pAmount];
        AmountThread thread1 = new AmountThread("A");
        AmountThread thread2 = new AmountThread("T");

        threadArr[0] = thread1;
        threadArr[1] = thread2;

        if (pAmount >= 3) {
            AmountThread thread3 = new AmountThread("N");
            threadArr[2] = thread3;
            if (pAmount == 4) {
                AmountThread thread4 = new AmountThread("P");
                threadArr[3] = thread4;
            }
        }
        for (AmountThread thread : threadArr) {
            thread.start();
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int[] playerStrokes = new int[pAmount];
        int i = 0;
        for (AmountThread thread :threadArr) {
            playerStrokes[i] = thread.getTotalStrokes();
            i++;
        }

        Player[] toReward = new Player[pAmount];
        for (int j = 0; j< pAmount; j++) {
            toReward[j] = this.players[index(playerStrokes)];
        }
        this.reward(toReward);
    }

    public int index(int[] playerTimes){
        int ind = 0;
        int tmp = playerTimes[0];
        for (int i = 1; i < playerTimes.length; i++) {
            if (playerTimes[i] > tmp){
                ind = i;
                tmp = playerTimes[i];
            }
        }
        playerTimes[ind] = -1;
        return ind;
    }
}
