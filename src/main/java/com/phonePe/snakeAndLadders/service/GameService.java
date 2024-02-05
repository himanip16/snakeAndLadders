package com.phonePe.snakeAndLadders.service;

import com.phonePe.snakeAndLadders.config.GameProperties;
import com.phonePe.snakeAndLadders.constants.Actionable;
import com.phonePe.snakeAndLadders.service.specialObject.SpecialObjectService;
import com.phonePe.snakeAndLadders.structs.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@AllArgsConstructor
public class GameService {

    private DiceService diceService;
    private GameProperties gameProperties;
    private SpecialObjectService specialObjectService;

    public void startGame(Board board, Queue<Player> players) {
        List<Player> winners = new ArrayList<>();

        while (players.size() >= 2) {
            Player player = players.poll();
            int skips = player.getSkipTurns();

            if (skips > 0) {
                player.setSkipTurns(skips - 1);
                players.add(player);
                continue;
            }

            String eventLog = processPlayerTurn(board, player);
            System.out.println(eventLog);

            if (playerHasWon(board, player)) {
                winners.add(player);
            } else {
                players.add(player);
            }
        }
    }

    private String processPlayerTurn(Board board, Player player) {
        StringJoiner eventLog = new StringJoiner(" ");
        eventLog.add(player.getName());

        int move = diceService.roll(gameProperties.getDiceCount());
        eventLog.add("rolled a").add(String.valueOf(move));

        int currentPos = board.getPlayerPositions().get(player.getId());
        int newPos = currentPos + move;

        if (newPos >= board.getSize() * board.getSize()) {
            eventLog.add("and moved from").add(String.valueOf(currentPos)).add("to").add(String.valueOf(newPos));
            board.getPlayerPositions().put(player.getId(), newPos);
            return eventLog.toString();
        }

        Cell cell = board.getCells()[newPos];
        int finalPosition = specialObjectService.getFinalPosition(cell.getActionable(), newPos, board, player);

        if (newPos != finalPosition) {
            currentPos = newPos;
            processSpecialObjectEvent(eventLog, cell.getActionable(), currentPos);
        }

        eventLog.add("and moved from").add(String.valueOf(currentPos)).add("to").add(String.valueOf(finalPosition));

        updateBoardState(board, player, finalPosition);

        return eventLog.toString();
    }

    private void processSpecialObjectEvent(StringJoiner eventLog, Actionable action, int position) {
        switch (action) {
            case SNAKE_HEAD:
                eventLog.add("bitten by snake at").add(String.valueOf(position));
                break;
            case LADDER_BOTTOM:
                eventLog.add("climbed the ladder at").add(String.valueOf(position));
                break;
            case CROCODILE:
                eventLog.add("encountered a crocodile at").add(String.valueOf(position));
                break;
        }
    }

    private void updateBoardState(Board board, Player player, int finalPosition) {

        for(Map.Entry<Integer, Integer> playerAtCell: board.getPlayerPositions().entrySet()){
            if(playerAtCell.getValue().equals(finalPosition)){
                board.getPlayerPositions().put(playerAtCell.getKey(), 1);
            }
        }
        board.getPlayerPositions().put(player.getId(), finalPosition);
    }


    private boolean playerHasWon(Board board, Player player) {
        return board.getPlayerPositions().get(player.getId()) >= board.getSize() * board.getSize();
    }


}
