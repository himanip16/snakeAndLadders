package com.phonePe.snakeAndLadders.structs;

import com.phonePe.snakeAndLadders.constants.Actionable;
import com.phonePe.snakeAndLadders.constants.NonActionables;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
public class Cell {
    private int cellNum;
    private Actionable actionable;
    private List<NonActionables> nonActionables= new ArrayList<>();

    public boolean hasActionable(){
        return Objects.nonNull(actionable);
    }
}
