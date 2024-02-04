package com.phonePe.snakeAndLadders.structs;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Board {

    private int size;
    private List<Snake> snakes;
    private List<Ladder> ladders;
    private Map<Integer, Integer> playerPositions;
    private List<Cell> cells;

}
