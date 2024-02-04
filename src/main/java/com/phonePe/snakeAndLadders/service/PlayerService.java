package com.phonePe.snakeAndLadders.service;

import com.phonePe.snakeAndLadders.structs.Player;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class PlayerService {
    public List<Player> createPlayers(int playerCount) {
        Scanner scanner = new Scanner(System.in);
        List<Player> players = new ArrayList<>();

        for (int i = 1; i <= playerCount; i++) {
            System.out.print("Enter name for Player " + i + ": ");
            String playerName = scanner.nextLine();
            players.add(new Player(i, playerName));
        }

        return players;
    }
}
