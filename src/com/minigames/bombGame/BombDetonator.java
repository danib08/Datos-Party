package com.minigames.bombGame;
import com.gameLogic.Player;
import com.minigames.Minigame;
import java.util.Random;

public class BombDetonator extends Minigame {
    int pAmount;

    /**
     *  this method is the only constructor this class consists of.
     * @param players the main player array used in the game logic.
     */
    public BombDetonator(Player[] players) {
        super(players);
        pAmount = players.length;
    }

    @Override
    public void startGame() {
        Detonator[] detonators = new Detonator[pAmount];
        Detonator detonator1 = new Detonator(players[0]);
        Detonator detonator2 = new Detonator(players[1]);
        detonators[0] = detonator1;
        detonators[1] = detonator2;

        if (pAmount >=3){
            Detonator detonator3 = new Detonator(players[2]);
            detonators[2] = detonator3;
            if (pAmount  == 4){
                Detonator detonator4 = new Detonator(players[3]);
                detonators[3] = detonator4;
            }
        }
        Player[] toReward = new Player[pAmount];
        int i = pAmount - 1;
        Random toBlowUp;
        toBlowUp = new Random();
        while (i != -1){
            int x = toBlowUp.nextInt(pAmount);
            if (x == (pAmount - 1)){
                x = pAmount;
            }
            if (!detonators[x].detonated()){
                detonators[x].setExploded(true);
                toReward[i] = detonators[x].getSelector();
            }
            i--;
        }
        //TODO tie the logic to the minigame UI.
        this.reward(toReward);
    }
}
