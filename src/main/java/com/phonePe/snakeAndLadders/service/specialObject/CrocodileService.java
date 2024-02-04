package com.phonePe.snakeAndLadders.service.specialObject;

import com.phonePe.snakeAndLadders.config.GameProperties;
import com.phonePe.snakeAndLadders.structs.Board;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import com.phonePe.snakeAndLadders.constants.Actionable;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Component
@AllArgsConstructor
public class CrocodileService implements ISpecialObjectsInterface {
    private GameProperties gameProperties;
    @Override
    public void generateSpecialObject(Board board) {
        int boardSize = gameProperties.getBoardSize();
        Set<Integer> crocodilePositions = new HashSet<>();
        int upperLimitForCrocodiles = boardSize * boardSize;

        Random random = new Random();

        for (int i = 0; i < gameProperties.getCrocodileCount(); i++) {
            int crocodilePosition;
            do {
                crocodilePosition = generateRandomPosition(0, upperLimitForCrocodiles, random);
            } while (crocodilePositions.contains(crocodilePosition) || board.getCells().get(crocodilePosition).hasActionable());

            board.getCells().get(crocodilePosition).setActionable(Actionable.CROCODILE);
            crocodilePositions.add(crocodilePosition);
        }
    }


    @Override
    public int getFinalPosition(int position) {
        return 0;
    }
}
