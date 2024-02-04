package com.phonePe.snakeAndLadders.service;

import com.phonePe.snakeAndLadders.service.specialObject.ISpecialObjectsInterface;
import com.phonePe.snakeAndLadders.service.specialObject.SnakeAndLadderInterface;
import com.phonePe.snakeAndLadders.structs.Board;
import com.phonePe.snakeAndLadders.structs.Ladder;
import com.phonePe.snakeAndLadders.structs.Snake;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@AllArgsConstructor
public class BoardService {
    private SnakeAndLadderInterface snakeAndLadderService;
    private ISpecialObjectsInterface iSpecialObjectsInterface;
    public Board initializeRandomBoard(List<Integer> playerIds) {

        Board board = new Board();

        Map<Integer, Integer> playerPosMap = new HashMap<>();
        for(int id: playerIds){
            playerPosMap.put(id, 0);
        }
        board.setLadders(ladders);
        board.setSnakes(snakes);
        board.setPlayerPositions(playerPosMap);
        return board;
    }

    public int getFinalPosition(int position, Map<Integer, Snake> snakeHeadMap, Map<Integer, Ladder> ladderBottomMap) {
        int finalPosition= position;
        Snake snake = snakeHeadMap.get(position);
        if(Objects.nonNull(snake)) {
            finalPosition= snake.getTail();
        }
        else {
            Ladder ladder = ladderBottomMap.get(position);
            if(Objects.nonNull(ladder)) {
                finalPosition = ladder.getTop();
            }
        }
        return finalPosition;
    }
}
