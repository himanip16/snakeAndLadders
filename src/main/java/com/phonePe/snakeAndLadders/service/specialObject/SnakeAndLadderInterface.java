package com.phonePe.snakeAndLadders.service.specialObject;

import com.phonePe.snakeAndLadders.config.GameProperties;
import com.phonePe.snakeAndLadders.structs.Board;
import com.phonePe.snakeAndLadders.structs.Ladder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SnakeAndLadderInterface implements ISpecialObjectsInterface {
    private GameProperties gameProperties;

    public SnakeAndLadderInterface(GameProperties gameProperties){
        this.gameProperties = gameProperties;
    }




    @Override
    public void generateSpecialObject(Board board) {
        int boardSize = gameProperties.getBoardSize();

        Set<Integer> ladderBottomSet = new HashSet<>();

        int upperLimitForTop = boardSize * boardSize;
        int upperLimitForBottom = boardSize * (boardSize - 1);
        int lowerLimitForBottom = 2;
        List<Ladder> ladders = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < gameProperties.getLadderCount(); i++) {
            int bottom;
            do {
                bottom = random.nextInt(upperLimitForBottom - lowerLimitForBottom) + lowerLimitForBottom;
            } while (ladderBottomSet.contains(bottom));
            int lowerLimitForTop = bottom + boardSize;
            int top = random.nextInt(upperLimitForTop - lowerLimitForTop) + lowerLimitForTop;
            ladders.add(new Ladder(top, bottom));
            ladderBottomSet.add(bottom);
        }
        board.setLadders(ladders);

    }



    @Override
    public int getFinalPosition(int position) {
        return 0;
    }
}
