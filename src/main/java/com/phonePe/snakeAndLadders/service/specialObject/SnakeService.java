package com.phonePe.snakeAndLadders.service.specialObject;

import com.phonePe.snakeAndLadders.config.GameProperties;
import com.phonePe.snakeAndLadders.constants.Actionable;
import com.phonePe.snakeAndLadders.constants.NonActionables;
import com.phonePe.snakeAndLadders.structs.Board;
import com.phonePe.snakeAndLadders.structs.Cell;
import com.phonePe.snakeAndLadders.structs.Player;
import com.phonePe.snakeAndLadders.structs.Snake;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class SnakeService implements ISpecialObjectsInterface {
    private GameProperties gameProperties;
    public Map<Integer, Snake> getSnakeHeadMap(Board board) {
        return board.getSnakes().stream().
                collect(Collectors.toMap(Snake::getHead, s-> s));
    }


    @Override
    public void generateSpecialObject(Board board) {
        int boardSize = gameProperties.getBoardSize();
        Set<Integer> snakeHead = new HashSet<>();
        int lowerLimitForTail = 2;
        int upperLimitForTail = boardSize * (boardSize - 2);
        int upperLimitForHead = boardSize * boardSize - 1;
        List<Snake> snakes = new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < gameProperties.getSnakeCount(); i++) {
            int tail = generateRandomPosition(lowerLimitForTail, upperLimitForTail, random);
            int head = generateRandomHeadPosition(tail, board, upperLimitForHead, random, snakeHead);
            if (board.getCells()[tail] == null) {
                board.getCells()[tail] = new Cell();
            }
            board.getCells()[tail].getNonActionables().add(NonActionables.SNAKE_TAIL);


            if (board.getCells()[head] == null) {
                board.getCells()[head] = new Cell();
            }
            board.getCells()[head].setActionable(Actionable.SNAKE_HEAD);

            snakes.add(new Snake(head, tail));
            snakeHead.add(head);
        }

        board.setSnakes(snakes);
    }



    private int generateRandomHeadPosition(int tail, Board board, int upperLimitForHead, Random random, Set<Integer> snakeHead) {
        int lowerLimitForHead = tail + board.getSize();
        int head;
        do {
            head = generateRandomPosition(lowerLimitForHead, upperLimitForHead, random);
        } while (snakeHead.contains(head) && Objects.nonNull(board.getCells()[head].getActionable()));
        return head;
    }


    @Override
    public int getFinalPosition(Board board, int position, Player player) {
        Snake snake = getSnake(board.getSnakes(), position);
        if (Objects.nonNull(snake)) {
            return snake.getTail();
        }
        return position;
    }

    private Snake getSnake(List<Snake> snakes, int headPosition) {
        return snakes.stream()
                .filter(s -> s.getHead() == headPosition)
                .findFirst()
                .orElse(null);
    }

}
