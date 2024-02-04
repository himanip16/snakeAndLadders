package com.phonePe.snakeAndLadders.service;

import com.phonePe.snakeAndLadders.config.GameProperties;
import com.phonePe.snakeAndLadders.service.specialObject.SnakeAndLadderInterface;
import com.phonePe.snakeAndLadders.structs.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@AllArgsConstructor
public class GameService {

    private DiceService diceService;
    private GameProperties gameProperties;
    private BoardService boardService;
    private SnakeAndLadderInterface snakeAndLadderService;

    public void startGame(Board board, Queue<Player> players)
    {
        List<Player> winners= new ArrayList<>();
        while(players.size()>=2)
        {
            StringJoiner eventLog = new StringJoiner(" ");
            Player player= players.poll();
            int skips = player.getSkipTurns();
            if(skips > 0){
                player.setSkipTurns(skips-1);
                players.add(player);
                continue;
            }
            eventLog.add(player.getName());
            Map<Integer, Integer> playerPositions = board.getPlayerPositions();
            int move= diceService.roll(gameProperties.getDiceCount());
            eventLog.add("rolled a").add(String.valueOf(move));
            int currentPos = playerPositions.get(player.getId());

            int newPos= currentPos + move;
            int boardSize = gameProperties.getBoardSize();
            if(newPos>=boardSize*boardSize)
            {
                winners.add(player);
                eventLog.add(String.valueOf(newPos));
                System.out.println(eventLog);
                continue;
            }

            Cell cell = board.getCells().get(newPos);

            int finalPosition = boardService.getFinalPosition(newPos, );
            if(finalPosition<newPos)eventLog.add("bitten by snake at").add(String.valueOf(newPos));
            else if(finalPosition>newPos) eventLog.add("climbed the ladder at").add(String.valueOf(newPos));
            else newPos = currentPos;
            eventLog.add("and moved from").add(String.valueOf(newPos)).add("to").add(String.valueOf(finalPosition));
            System.out.println(eventLog);
            playerPositions.put(player.getId(), finalPosition);
            players.add(player);

        }

    }

}
