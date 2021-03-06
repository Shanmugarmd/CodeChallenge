package org.example.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SnakeAndLaderV1 {

    public static void main(String[] args) {

        int noOfTiles = 10;
        List<Integer> tiles = IntStream.range(0, noOfTiles + 1).boxed().collect(Collectors.toList());

        // Snakes
        tiles.set(7, 3);
        tiles.set(5, 1);

        //Ladders
        tiles.set(2, 6);
        tiles.set(4, 8);

        System.out.println(Arrays.toString(tiles.toArray()));
        List<Integer> listOfGameLength = new ArrayList<>();

        int counter = 0;
        int totalNoOfSteps = 0;
        int timesToSimulate = 5;
        while (counter < timesToSimulate) {
            int noOfSteps = playGame(tiles);
            listOfGameLength.add(noOfSteps);
            totalNoOfSteps += noOfSteps;
            counter += 1;
        }

        Integer minLength = listOfGameLength.stream().min((o1, o2) -> o1.compareTo(o2)).get();
        System.out.println("Minimum number of rolls needed to win = " + minLength);

        int averageSteps = totalNoOfSteps / timesToSimulate;
        System.out.println("Average number of rolls needed to win = " + averageSteps);

        Integer maxLength = listOfGameLength.stream().max((o1, o2) -> o1.compareTo(o2)).get();
        System.out.println("Maximum number of rolls needed to win = " + maxLength);
    }

    private static int playGame(List<Integer> tiles) {
        int steps = 0;
        int tileState = 0;
        int noOfTileInBoard = tiles.size() - 1;
        while (tileState < noOfTileInBoard) {
            steps += 1;
            tileState = tileState + rollDice();
            if (tileState > noOfTileInBoard) {
                tileState = noOfTileInBoard;
            }
            tileState = tiles.get(tileState);
            System.out.print(tileState + " ");
        }
        System.out.println("\n" + steps);
        return steps;
    }

    private static int rollDice() {
        return new Random().nextInt(6) + 1;
    }

}
