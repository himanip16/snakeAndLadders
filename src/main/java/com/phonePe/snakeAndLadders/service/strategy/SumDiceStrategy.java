package com.phonePe.snakeAndLadders.service.strategy;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SumDiceStrategy implements DiceStrategy {

    @Override
    public int rollDice(int diceCount) {
        int count=0;
        int value=0;
        while(count< diceCount)
        {
            value += ThreadLocalRandom.current().nextInt(1, 7);
            count++;
        }
        return value;
    }
}
