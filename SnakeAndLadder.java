package com.workattech.snl;

import com.workattech.snl.models.*;

import java.util.*;

public class SnakeAndLadder {
    private Board Board;
    private int initialNumberOfPlayers;
    private Queue<Player> players; 
    private boolean isGameCompleted;

    private int noOfDices; 
    private boolean shouldGameContinueTillLastPlayer; 
    private boolean shouldAllowMultipleDiceRollOnSix; 

    private static final int DEFAULT_BOARD_SIZE = 100; 
    private static final int DEFAULT_NO_OF_DICES = 1;

    public SnakeAndLadder(int boardSize) {
        this.Board = new Board(boardSize);  //Optional Rule 2
        this.players = new LinkedList<Player>();
        this.noOfDices = SnakeAndLadder.DEFAULT_NO_OF_DICES;
    }

    public SnakeAndLadder() {
        this(SnakeAndLadder.DEFAULT_BOARD_SIZE);
    }

    public void setNoOfDices(int noOfDices) {
        this.noOfDices = noOfDices;
    }

    public void setShouldGameContinueTillLastPlayer(boolean shouldGameContinueTillLastPlayer) {
        this.shouldGameContinueTillLastPlayer = shouldGameContinueTillLastPlayer;
    }

    public void setShouldAllowMultipleDiceRollOnSix(boolean shouldAllowMultipleDiceRollOnSix) {
        this.shouldAllowMultipleDiceRollOnSix = shouldAllowMultipleDiceRollOnSix;
    }

    public void setPlayers(List<Player> players) {
        this.players = new LinkedList<Player>();
        this.initialNumberOfPlayers = players.size();
        Map<String, Integer> playerPieces = new HashMap<String, Integer>();
        for (Player player : players) {
            this.players.add(player);
            playerPieces.put(player.getId(), 0); 
        }
        Board.setPlayerPieces(playerPieces); 
    }

    public void setSnakes(List<Snake> snakes) {
        Board.setSnakes(snakes); 
    }

    public void setLadders(List<Ladder> ladders) {
        Board.setLadders(ladders); 
    }

    private int getNewPositionAfterGoingThroughSnakesAndLadders(int newPosition) {
        int previousPosition;

        do {
            previousPosition = newPosition;
            for (Snake snake : snakeAndLadderBoard.getSnakes()) {
                if (snake.getStart() == newPosition) {
                    newPosition = snake.getEnd(); 
            }

            for (Ladder ladder : Board.getLadders()) {
                if (ladder.getStart() == newPosition) {
                    newPosition = ladder.getEnd(); 
                }
            }
        } while (newPosition != previousPosition); 
        return newPosition;
    }

    private void movePlayer(Player player, int positions) {
        int oldPosition = Board.getPlayerPieces().get(player.getId());
        int newPosition = oldPosition + positions; 

        int boardSize = Board.getSize();

        if (newPosition > boardSize) {
            newPosition = oldPosition; 
        } else {
            newPosition = getNewPositionAfterGoingThroughSnakesAndLadders(newPosition);
        }

        Board.getPlayerPieces().put(player.getId(), newPosition);

        System.out.println(player.getName() + " rolled a " + positions + " and moved from " + oldPosition +" to " + newPosition);
    }

    private int getTotalValueAfterDiceRolls(){
        return Dice.roll();
    }

    private boolean hasPlayerWon(Player player) {
        int playerPosition = Board.getPlayerPieces().get(player.getId());
        int winningPosition = Board.getSize();
        return playerPosition == winningPosition; 
    }

    private boolean isGameCompleted() {
        int currentNumberOfPlayers = players.size();
        return currentNumberOfPlayers < initialNumberOfPlayers;
    }

    public void startGame() {
        while (!isGameCompleted()) {
            int totalDiceValue = getTotalValueAfterDiceRolls(); 
            Player currentPlayer = players.poll();
            movePlayer(currentPlayer, totalDiceValue);
            if (hasPlayerWon(currentPlayer)) {
                System.out.println(currentPlayer.getName() + " wins the game");
                Board.getPlayerPieces().remove(currentPlayer.getId());
            } else {
                players.add(currentPlayer);
            }
        }
    }
}



