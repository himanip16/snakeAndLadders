package com.phonePe.snakeAndLadders.service;

import com.phonePe.snakeAndLadders.config.GameProperties;
import com.phonePe.snakeAndLadders.service.specialObject.SpecialObjectService;
import com.phonePe.snakeAndLadders.structs.Board;
import com.phonePe.snakeAndLadders.structs.Cell;
import com.phonePe.snakeAndLadders.structs.Ladder;
import com.phonePe.snakeAndLadders.structs.Snake;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@AllArgsConstructor
public class BoardService {
    private SpecialObjectService specialObjectService;
    private GameProperties gameProperties;
    public Board initializeRandomBoard(List<Integer> playerIds) {

        Board board = new Board();
        board.setSize(gameProperties.getBoardSize());
        Cell[] cells = new Cell[board.getSize() * board.getSize()];
        for (int i = 0; i < cells.length; i++) {
            cells[i] = new Cell(); // Assuming Cell has a default constructor
        }
        board.setCells(cells);

        Map<Integer, Integer> playerPosMap = new HashMap<>();
        for(int id: playerIds){
            playerPosMap.put(id, 0);
        }
        specialObjectService.generateSpecialObjects(board);
        board.setPlayerPositions(playerPosMap);
        return board;
    }

}
