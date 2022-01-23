package org.example.code;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SnakeAndLadderV3 {

    private List<Integer> tiles;

    private int timesToSimulate = 1;

    private int unluckyState = 0;

    private Counter rollsTakenToWin;

    public SnakeAndLadderV3 setTiles(int noOfTiles) {
        this.tiles = IntStream.range(0, noOfTiles + 1).boxed().collect(Collectors.toList());
        this.unluckyState = (noOfTiles - 1);
        return this;
    }

    public SnakeAndLadderV3 setSnake(int head, int tail) {
        this.tiles.set(head, tail);
        return this;
    }

    public SnakeAndLadderV3 setLadder(int first, int last) {
        this.tiles.set(first, last);
        return this;
    }

    public SnakeAndLadderV3 simulate(int timesToSimulate) {
        this.timesToSimulate = timesToSimulate;
        int counter = 0;
        while (counter < timesToSimulate) {
            int rollsTakenToWin = playGame().getRollsTakenToWin();
            //rollsTakenToWinList.add(rollsTakenToWin);
            counter += 1;
        }

        return this;
    }

    public int getRollsTakenToWin() {
        return rollsTakenToWin.result();
    }

    public SnakeAndLadderV3 playGame() {
        rollsTakenToWin = Counter.start(0);
        printBoard();
        int playerState = 0;
        int noOfTilesInBoard = tiles.size() - 1;
        while (playerState < noOfTilesInBoard) {
            int initialPosition = playerState;
            playerState = jumpByDiceRolls(playerState);
            if (playerState > noOfTilesInBoard) {
                playerState = initialPosition;
            }
            playerState = tiles.get(playerState);
            System.out.print("" + initialPosition + "->[" + playerState + "] " + ((playerState == noOfTilesInBoard) ? "" : ", "));
        }
        System.out.println("\n" + rollsTakenToWin.result() + "\n");
        return this;
    }

    private void printBoard() {
        System.out.print("[");
        for (int i = 0; i < tiles.size(); i++) {
            System.out.print(i + ((i == tiles.size() - 1) ? "" : ", "));
        }
        System.out.print("]");
        System.out.println("\n" + Arrays.toString(tiles.toArray()));
    }

    private int jumpByDiceRolls(int playerState) {
        int diceResult = rollDice();
        int afterRollPosition = playerState + diceResult;
        System.out.print("(" + diceResult + ") ");

        if (playerState == 0 && diceResult == 1) {
            System.out.print("\n" + "BEGINS >> " + "\n");
            return diceResult;
        } else if (playerState == 0) {
            if (hasBonusRoll(diceResult)) {
                playerState = jumpByDiceRolls(playerState);
            }
            return playerState;
        }

        if (playerState > 0 && hasBonusRoll(diceResult)) {
            afterRollPosition = jumpByDiceRolls(afterRollPosition);
        }

        if (afterRollPosition == this.unluckyState) {
            return playerState;
        }
        return afterRollPosition;
    }

    private boolean hasBonusRoll(int diceResult) {
        return diceResult == 5 || diceResult == 6 || diceResult == 1;
    }

    private int rollDice() {
        rollsTakenToWin.count();
        return new Random().nextInt(6) + 1;
    }

    static SnakeAndLadderV3 getBoard() {
        return new SnakeAndLadderV3();
    }

    public void stats() {

    }
}