package com.phonePe.snakeAndLadders.config;

import com.phonePe.snakeAndLadders.constants.MovementStrategy;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "game")
public class GameProperties {
    private int playerCount;
    private int boardSize;
    private int snakeCount;
    private int ladderCount;
    private int diceCount;
    private MovementStrategy movementStrategy;
    public int mineCount;

    public int crocodileCount;
}
