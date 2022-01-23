package org.example.code;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SnakeAndLaderV2 {

    static List<Integer> listOfRollsPerTurn = new ArrayList<>();
    static List<Integer> listOfClimbedSteps = new ArrayList<>();
    static List<Integer> listOfSlidedSteps = new ArrayList<>();
    static int timesToSimulate = 10;
    static int biggestClimb = 0;
    static int biggestSlide = 0;

    public static void main(String[] args) {

        int noOfTiles = 10;
        List<Integer> tiles = IntStream.range(0, noOfTiles + 1).boxed().collect(Collectors.toList());

        // Snakes
        tiles.set(7, 3);
        tiles.set(5, 1);
//        tiles.set(30, 20);
//        tiles.set(31, 21);
//        tiles.set(33, 23);
//        tiles.set(34, 24);
//        tiles.set(35, 25);
//        tiles.set(11, 1);

        //Ladders
        tiles.set(2, 6);
        tiles.set(4, 8);

        System.out.println(Arrays.toString(tiles.toArray()));
        List<Integer> rollsTakenToWinList = new ArrayList<>();

        int counter = 0;
        while (counter < timesToSimulate) {
            int rollsTakenToWin = playGame(tiles);
            rollsTakenToWinList.add(rollsTakenToWin);
            counter += 1;
        }

        Integer minRolls = rollsTakenToWinList.stream().min((o1, o2) -> o1.compareTo(o2)).get();
        System.out.println("Minimum number of rolls needed to win = " + minRolls);

        int averageRolls = rollsTakenToWinList.stream().mapToInt(Integer::valueOf).sum() / timesToSimulate;
        System.out.println("Average number of rolls needed to win = " + averageRolls);

        Integer maxRolls = rollsTakenToWinList.stream().max((o1, o2) -> o1.compareTo(o2)).get();
        System.out.println("Maximum number of rolls needed to win = " + maxRolls);

        System.out.println("-----------------------------------------------");

//        Integer minLength = rollsTakenToWinList.stream().min((o1, o2) -> o1.compareTo(o2)).get();
//        System.out.println("Minimum number of rolls needed to win = " + minLength);
//
//        int averageSteps = totalRollsTaken / timesToSimulate;
//        System.out.println("Average number of rolls needed to win = " + averageSteps);
//
//        Integer maxLength = rollsTakenToWinList.stream().max((o1, o2) -> o1.compareTo(o2)).get();
//        System.out.println("Maximum number of rolls needed to win = " + maxLength);
//
//        System.out.println("-----------------------------------------------");

//        Integer minClimb = listOfClimbedSteps.stream().min((o1, o2) -> o1.compareTo(o2)).get();
//        System.out.println("Minimum amount of climbs during the game = " + minClimb);
//
//        int averageClimb = listOfClimbedSteps.stream().mapToInt(Integer::valueOf).sum() / listOfClimbedSteps.size();
//        System.out.println("Average amount of climbs during the game = " + averageClimb);
//
//        Integer maxClimb = listOfClimbedSteps.stream().max((o1, o2) -> o1.compareTo(o2)).get();
//        System.out.println("Maximum amount of climbs during the game = " + maxClimb);
//
//        System.out.println("-----------------------------------------------");
//
//        Integer minSlide = listOfSlidedSteps.stream().min((o1, o2) -> o1.compareTo(o2)).orElse(0);
//        System.out.println("Minimum amount of slides during the game = " + minSlide);
//
//        int averageSlide = listOfSlidedSteps.stream().mapToInt(Integer::valueOf).sum() / listOfSlidedSteps.size();
//        System.out.println("Average amount of slides during the game = " + averageSlide);
//
//        Integer maxSlide = listOfSlidedSteps.stream().max((o1, o2) -> o1.compareTo(o2)).get();
//        System.out.println("Maximum amount of slides during the game = " + maxSlide);


    }

    private static int playGame(List<Integer> tiles) {
        Counter rollsTakenToWin = new Counter();
        int playerState = 0;
        int noOfTilesInBoard = tiles.size() - 1;
        while (playerState < noOfTilesInBoard) {
            int initialPosition = playerState;
            playerState = jumpByDiceCount(playerState, rollsTakenToWin);
            if (playerState > noOfTilesInBoard) {
                playerState = initialPosition;
            }
            int afterRollPosition = tiles.get(playerState);
            int slideCount = 0;
            int climbCount = 0;
            if (initialPosition < afterRollPosition) {
                int climbedSteps = afterRollPosition - initialPosition;
                listOfClimbedSteps.add(climbedSteps);
                climbCount += 1;
            } else {
                int slidedSteps = initialPosition - afterRollPosition;
                listOfSlidedSteps.add(slidedSteps);
                slideCount += 1;
            }
            System.out.print("" + initialPosition + "->[" + afterRollPosition + "] ");
        }
        System.out.println("\n" + rollsTakenToWin.result());
        return rollsTakenToWin.result();
    }

    private static int jumpByDiceCount(int initialPosition, Counter rollsTakenToWin) {
        int diceResult = rollDice(rollsTakenToWin);
        int afterRollPosition = initialPosition + diceResult;
        if (diceResult == 5 || diceResult == 6 || diceResult == 1) {
            jumpByDiceCount(afterRollPosition, rollsTakenToWin);
        }
        return afterRollPosition;
    }

    private static int rollDice(Counter rollsTakenToWin) {
        rollsTakenToWin.count();
        return new Random().nextInt(6) + 1;
    }

    static class Counter {

        private Integer value = 0;

        public void count() {
            value += 1;
        }

        public int result() {
            return value.intValue();
        }
    }

}
