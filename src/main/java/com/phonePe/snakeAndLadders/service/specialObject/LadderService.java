package com.phonePe.snakeAndLadders.service.specialObject;

import com.phonePe.snakeAndLadders.config.GameProperties;
import com.phonePe.snakeAndLadders.constants.Actionable;
import com.phonePe.snakeAndLadders.constants.NonActionables;
import com.phonePe.snakeAndLadders.structs.Board;
import com.phonePe.snakeAndLadders.structs.Ladder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@AllArgsConstructor
public class LadderService implements ISpecialObjectsInterface {
    private GameProperties gameProperties;
    @Override
    public void generateSpecialObject(Board board) {
        int boardSize = board.getSize();
        Set<Integer> ladderBottomSet = new HashSet<>();
        int upperLimitForTop = boardSize * boardSize;
        int upperLimitForBottom = boardSize * (boardSize - 1);
        List<Ladder> ladders = new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < gameProperties.getLadderCount(); i++) {
            int bottom = generateRandomPosition(2, upperLimitForBottom, random);
            int top = generateRandomPosition(bottom + boardSize, upperLimitForTop, random);

            ladders.add(new Ladder(top, bottom));
            ladderBottomSet.add(bottom);
        }

        setLadderPositions(board, ladders);
        board.setLadders(ladders);
    }

    @Override
    public int getFinalPosition(int position) {
        return 0;
    }

    private void setLadderPositions(Board board, List<Ladder> ladders) {
        for (Ladder ladder : ladders) {
            board.getCells().get(ladder.getTop()).getNonActionables().add(NonActionables.LADDER_TOP);
            board.getCells().get(ladder.getBottom()).setActionable(Actionable.LADDER_BOTTOM);
        }
    }



}
