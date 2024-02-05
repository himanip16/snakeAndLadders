package com.phonePe.snakeAndLadders.service.specialObject;

import com.phonePe.snakeAndLadders.config.GameProperties;
import com.phonePe.snakeAndLadders.constants.Actionable;
import com.phonePe.snakeAndLadders.constants.NonActionables;
import com.phonePe.snakeAndLadders.structs.*;
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
    public int getFinalPosition(Board board, int position, Player player) {
        Ladder ladder = getLadder(board.getLadders(), position);

        if (Objects.nonNull(ladder)) {
            return ladder.getTop();
        }

        return position;
    }

    private Ladder getLadder(List<Ladder> ladders, int bottomPosition) {
        return ladders.stream()
                .filter(l -> l.getBottom() == bottomPosition)
                .findFirst()
                .orElse(null);
    }



    private void setLadderPositions(Board board, List<Ladder> ladders) {
        for (Ladder ladder : ladders) {
            Cell bottomCell = board.getCells()[ladder.getBottom()];
            if(Objects.isNull(bottomCell)) {
                bottomCell = new Cell();
                bottomCell.setNonActionables(new ArrayList<>());
            };
            bottomCell.getNonActionables().add(NonActionables.LADDER_TOP);
            board.getCells()[ladder.getBottom()] = bottomCell;
            Cell topCell = board.getCells()[ladder.getTop()];
            if(Objects.isNull(topCell))
                topCell = new Cell();

            topCell.setActionable(Actionable.LADDER_BOTTOM);
            board.getCells()[ladder.getBottom()] = bottomCell;
            board.getCells()[ladder.getTop()] = topCell;
        }
    }

}
