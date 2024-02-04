package com.phonePe.snakeAndLadders;

import com.phonePe.snakeAndLadders.config.GameProperties;
import com.phonePe.snakeAndLadders.service.BoardService;
import com.phonePe.snakeAndLadders.service.GameService;
import com.phonePe.snakeAndLadders.service.PlayerService;
import com.phonePe.snakeAndLadders.structs.Board;
import com.phonePe.snakeAndLadders.structs.Player;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@SpringBootApplication
@AllArgsConstructor
public class SnakesAndLaddersGame implements CommandLineRunner {

	private GameProperties gameProperties;
	private PlayerService playerService;
	private BoardService boardService;
	private GameService gameService;


	public static void main(String[] args) {
		SpringApplication.run(SnakesAndLaddersGame.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		List<Player> players = playerService.createPlayers(gameProperties.getPlayerCount());
		List<Integer> playerIds = players.stream().map(Player::getId).collect(Collectors.toList());
		Board board = boardService.initializeRandomBoard(playerIds);
		gameService.startGame(board, new LinkedList<>(players));

	}
}
