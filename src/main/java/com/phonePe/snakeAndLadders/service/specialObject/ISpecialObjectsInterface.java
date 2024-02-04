package com.phonePe.snakeAndLadders.service.specialObject;

import com.phonePe.snakeAndLadders.structs.Board;

import java.util.Random;

public interface ISpecialObjectsInterface {
    void generateSpecialObject(Board board);
    int getFinalPosition(int position);
    default int generateRandomPosition(int lowerLimit, int upperLimit, Random random) {
        return random.nextInt(upperLimit - lowerLimit) + lowerLimit;
    }

}
