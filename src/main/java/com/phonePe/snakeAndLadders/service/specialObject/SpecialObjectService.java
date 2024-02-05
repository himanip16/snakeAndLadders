package com.phonePe.snakeAndLadders.service.specialObject;

import com.phonePe.snakeAndLadders.constants.Actionable;
import com.phonePe.snakeAndLadders.constants.SpecialObject;
import com.phonePe.snakeAndLadders.structs.Board;
import com.phonePe.snakeAndLadders.structs.Player;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@AllArgsConstructor
@Component
public class SpecialObjectService {
    private MineService mineService;
    private CrocodileService crocodileService;
    private LadderService ladderService;
    private SnakeService snakeService;

    public void generateSpecialObjects(Board board){
        for(SpecialObject sp: SpecialObject.values()){
            ISpecialObjectsInterface instance = getInstance(sp);
            if(Objects.nonNull(instance)) instance.generateSpecialObject(board);
        }
    }

    public int getFinalPosition(Actionable sp, int position, Board board, Player player){

        ISpecialObjectsInterface instance = Objects.nonNull(sp) ? getInstance(sp): null;
        return (Objects.nonNull(instance)) ? instance.getFinalPosition(board, position, player): position;

    }

    private ISpecialObjectsInterface getInstance(SpecialObject sp) {
        switch (sp){
            case MINE: return mineService;
            case SNAKE:
                return snakeService;
            case LADDER: return ladderService;
            case CROCODILE: return crocodileService;
            default: return null;
        }
    }
    private ISpecialObjectsInterface getInstance(Actionable actionable) {
        switch (actionable){
            case MINE: return mineService;
            case SNAKE_HEAD:
                return snakeService;
            case LADDER_BOTTOM: return ladderService;
            case CROCODILE: return crocodileService;
            default: return null;
        }
    }


}
