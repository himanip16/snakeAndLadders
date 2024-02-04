package com.phonePe.snakeAndLadders.structs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    private List<Player> winners;
    private Board board;
    private List<Player> players;
    private int numDies;

}
