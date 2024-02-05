package com.phonePe.snakeAndLadders.service.specialObject;

import com.phonePe.snakeAndLadders.config.GameProperties;
import com.phonePe.snakeAndLadders.structs.Board;
import com.phonePe.snakeAndLadders.structs.Cell;
import com.phonePe.snakeAndLadders.structs.Player;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import com.phonePe.snakeAndLadders.constants.Actionable;

import java.util.HashSet;
import java.util.Objects;
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
            } while (crocodilePositions.contains(crocodilePosition) || (Objects.nonNull(board.getCells()[crocodilePosition]) && board.getCells()[crocodilePosition].hasActionable()));

            board.getCells()[crocodilePosition].setActionable(Actionable.CROCODILE);
            crocodilePositions.add(crocodilePosition);
        }
    }

    @Override
    public int getFinalPosition(Board board, int position, Player player) {
        return Math.max(position - 5, 1);
    }


}
