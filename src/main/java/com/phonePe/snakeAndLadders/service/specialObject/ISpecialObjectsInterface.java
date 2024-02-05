package com.phonePe.snakeAndLadders.service.specialObject;

import com.phonePe.snakeAndLadders.structs.Board;
import com.phonePe.snakeAndLadders.structs.Player;

import java.util.Random;

public interface ISpecialObjectsInterface {
    void generateSpecialObject(Board board);
    int getFinalPosition(Board board, int position, Player player);
    default int generateRandomPosition(int lowerLimit, int upperLimit, Random random) {
        return random.nextInt(upperLimit - lowerLimit) + lowerLimit;
    }

}
