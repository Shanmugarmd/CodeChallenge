package org.example.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SnakeAndLadder {

    private int noOfTiles;
    private List<Integer> tiles;
    private List<Integer> actualPositionsInTiles = new ArrayList<>();
    private int timesToSimulate = 1;
    private Stats _stats;

    static SnakeAndLadder getBoard() {
        return new SnakeAndLadder();
    }

    public SnakeAndLadder setTiles(int noOfTiles) {
        this.noOfTiles = noOfTiles;
        this.tiles = IntStream.range(0, noOfTiles + 1).boxed().collect(Collectors.toList());
        for (int i = 0; i < tiles.size(); i++) {
            actualPositionsInTiles.add(i);
        }
        return this;
    }

    public SnakeAndLadder setSnake(int head, int tail) {
        this.tiles.set(head, tail);
        return this;
    }

    public SnakeAndLadder setLadder(int start, int stop) {
        this.tiles.set(start, stop);
        return this;
    }

    public SnakeAndLadder simulate(int timesToSimulate) {
        _stats = new Stats(timesToSimulate);
        this.timesToSimulate = timesToSimulate;
        int time = 0;
        while (time < timesToSimulate) {
            time += 1;
            initStatsCounters();
            this.playGame(time);
            captureStatsCounter();
        }
        return this;
    }

    private void playGame(int time) {
        printBoard(time);
        int playerState = 0;
        while (playerState < noOfTiles) {
            _stats.getTurnsTakenToWin().count();
            int stateBeforeTurn = playerState;
            Counter _consecutiveRollsCounter = Counter.start(0);
            int stateAfterTurn = jumpByDiceRolls(playerState, _consecutiveRollsCounter);
            _stats.getConsecutiveRollsList().add(_consecutiveRollsCounter.result());
            if (stateAfterTurn > noOfTiles) {
                stateAfterTurn = stateBeforeTurn;
            }
            playerState = tiles.get(stateAfterTurn);
            System.out.print("" + stateBeforeTurn + "->[" + playerState + "] " + ((playerState == noOfTiles) ? "" : ", "));

            feedForStats(playerState, stateBeforeTurn, stateAfterTurn);
        }
        System.out.print("\n");
    }

    private int jumpByDiceRolls(int playerState, Counter _consecutiveRollsCounter) {
        _consecutiveRollsCounter.count();
        int diceResult = rollDice();
        System.out.print("(" + diceResult + ") ");
        int afterRollPosition = playerState + diceResult;
        if (hasAdditionalRoll(diceResult)) {
            afterRollPosition = jumpByDiceRolls(afterRollPosition, _consecutiveRollsCounter);
        }
        return afterRollPosition;
    }

    private boolean hasAdditionalRoll(int diceResult) {
        return diceResult == 6;
    }

    public int rollDice() {
        _stats.getRollsTakenToWin().count();
        return new Random().nextInt(6) + 1;
    }

    private void captureStatsCounter() {
        int _turnsTakenToWin = _stats.getTurnsTakenToWinResult();
        int _rollsTakenToWin = _stats.getRollsTakenToWinResult();
        System.out.println("turnsTakenToWin = " + _turnsTakenToWin);
        System.out.println("rollsTakenToWin = " + _rollsTakenToWin);
        System.out.println("");
        _stats.getTurnsTakenToWinList().add(_turnsTakenToWin);
        _stats.getRollsTakenToWinList().add(_rollsTakenToWin);
        _stats.getLandsOnLadderList().add(_stats.getLandsOnLadderResult());
        _stats.getLandsOnSnakeList().add(_stats.getLandsOnSnakeResult());
    }

    private void initStatsCounters() {
        _stats.setRollsTakenToWin(Counter.start(0));
        _stats.setTurnsTakenToWin(Counter.start(0));
        _stats.setLandsOnSnake(Counter.start(0));
        _stats.setLandsOnLadder(Counter.start(0));
    }

    private void feedForStats(int playerState, int stateBeforeTurn, int stateAfterTurn) {
        Integer _positionWithSnakeAndLadder = tiles.get(stateAfterTurn);
        Integer _actualPosition = actualPositionsInTiles.get(stateAfterTurn);
        int isSnakeOrLadderResult = Integer.valueOf(_positionWithSnakeAndLadder).compareTo(_actualPosition);
        if (isSnakeOrLadderResult > 0) { // climb with ladder
            int _stepsClimbedInLadder = _positionWithSnakeAndLadder - _actualPosition;
            _stats.getLandsOnLadder().count();
            _stats.getStepsClimbedInLadderList().add(_stepsClimbedInLadder);
        }
        if (isSnakeOrLadderResult < 0) { // slide by snake
            int _stepsSlidedWithLadder = _actualPosition - _positionWithSnakeAndLadder;
            _stats.getLandsOnSnake().count();
            _stats.getStepsSlidedList().add(_stepsSlidedWithLadder);
        }
        int climbOrSlideResult = Integer.valueOf(playerState).compareTo(Integer.valueOf(stateBeforeTurn));
        if (climbOrSlideResult > 0) { // climb
            _stats.getStepsClimbedList().add(playerState - stateBeforeTurn);
        }
        if (climbOrSlideResult < 0) { // slide
            _stats.getStepsSlidedList().add(stateBeforeTurn - playerState);
        }
    }

    private void printBoard(int time) {
        System.out.print("Simulation : " + time);
        System.out.print("\nActual series \t\t\t:" + Arrays.toString(actualPositionsInTiles.toArray()));
        System.out.println("\nWith snake & ladder \t:" + Arrays.toString(tiles.toArray()));
    }

    public void printStats() {
        _stats.computeAndPrint();
    }

}