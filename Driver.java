package com.workattech.snl;

import com.workattech.snl.models.Ladder;
import com.workattech.snl.models.Player;
import com.workattech.snl.models.Snake;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int noOfSnakes = scanner.nextInt();
        List<Snake> snakes = new ArrayList<Snake>();
        for (int i = 0; i < noOfSnakes; i++) {
            snakes.add(new Snake(scanner.nextInt(), scanner.nextInt()));
        }

        int noOfLadders = scanner.nextInt();
        List<Ladder> ladders = new ArrayList<Ladder>();
        for (int i = 0; i < noOfLadders; i++) {
            ladders.add(new Ladder(scanner.nextInt(), scanner.nextInt()));
        }

        int noOfPlayers = scanner.nextInt();
        List<Player> players = new ArrayList<Player>();
        for (int i = 0; i < noOfPlayers; i++) {
            players.add(new Player(scanner.next()));
        }

        SnakeAndLadder snakeAndLadder = new SnakeAndLadder();
        snakeAndLadder.setPlayers(players);
        snakeAndLadder.setSnakes(snakes);
        snakeAndLadder.setLadders(ladders);

        snakeAndLadder.startGame();
    }
}