package com.phonePe.snakeAndLadders.structs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    private int id;
    private String name;
    private int skipTurns;
    public Player(int id, String name){
        this.id = id;
        this.name = name;
    }

}
