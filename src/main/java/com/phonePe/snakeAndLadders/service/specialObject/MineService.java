package com.phonePe.snakeAndLadders.service.specialObject;

import com.phonePe.snakeAndLadders.config.GameProperties;
import com.phonePe.snakeAndLadders.constants.Actionable;
import com.phonePe.snakeAndLadders.structs.Board;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Component
@AllArgsConstructor
public class MineService implements ISpecialObjectsInterface {

    private GameProperties gameProperties;
    @Override
    public void generateSpecialObject(Board board) {
        int boardSize = board.getSize();
        Set<Integer> minePositions = new HashSet<>();
        int upperLimitForMines = boardSize * boardSize;

        Random random = new Random();

        for (int i = 0; i < gameProperties.getMineCount(); i++) {
            int minePosition;
            do {
                minePosition = generateRandomPosition(0, upperLimitForMines, random);
            } while (minePositions.contains(minePosition) || board.getCells().get(minePosition).hasActionable());

            board.getCells().get(minePosition).setActionable(Actionable.MINE);
            minePositions.add(minePosition);
        }
    }



    @Override
    public int getFinalPosition(int position) {
        return 0;
    }
}
