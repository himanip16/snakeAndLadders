package com.phonePe.snakeAndLadders.service;

import com.phonePe.snakeAndLadders.config.GameProperties;
import com.phonePe.snakeAndLadders.constants.MovementStrategy;
import com.phonePe.snakeAndLadders.service.strategy.DiceStrategy;
import com.phonePe.snakeAndLadders.service.strategy.MaxDiceStrategy;
import com.phonePe.snakeAndLadders.service.strategy.MinDiceStrategy;
import com.phonePe.snakeAndLadders.service.strategy.SumDiceStrategy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
@AllArgsConstructor
public class DiceService {

    private GameProperties gameProperties;

    public int roll(int diceCount) {
        DiceStrategy instance = createDieStrategy(gameProperties.getMovementStrategy());
        return instance.rollDice(diceCount);
    }

    private DiceStrategy createDieStrategy(MovementStrategy movementStrategy) {
        switch (movementStrategy) {
            case SUM:
                return new SumDiceStrategy();
            case MAX:
                return new MaxDiceStrategy();
            case MIN:
                return new MinDiceStrategy();
            default:
                throw new IllegalArgumentException("Invalid movement strategy: " + movementStrategy);
        }
    }


}
