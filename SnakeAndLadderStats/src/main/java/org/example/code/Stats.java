package org.example.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Stats {

    private int timesToSimulate;

    private Stats() {
    }

    public Stats(int timesToSimulate) {
        this.timesToSimulate = timesToSimulate;
    }

    private Counter _rollsTakenToWin;

    public Counter getRollsTakenToWin() {
        return _rollsTakenToWin;
    }

    public void setRollsTakenToWin(Counter _rollsTakenToWin) {
        this._rollsTakenToWin = _rollsTakenToWin;
    }

    public void setTurnsTakenToWin(Counter _turnsTakenToWin) {
        this._turnsTakenToWin = _turnsTakenToWin;
    }

    public void setLandsOnSnake(Counter _landsOnSnake) {
        this._landsOnSnake = _landsOnSnake;
    }

    public void setLandsOnSnakeList(List<Integer> _landsOnSnakeList) {
        this._landsOnSnakeList = _landsOnSnakeList;
    }

    public void setLandsOnLadder(Counter _landsOnLadder) {
        this._landsOnLadder = _landsOnLadder;
    }

    public void setLandsOnLadderList(List<Integer> _landsOnLadderList) {
        this._landsOnLadderList = _landsOnLadderList;
    }

    public void setTurnsTakenToWinList(List<Integer> _turnsTakenToWinList) {
        this._turnsTakenToWinList = _turnsTakenToWinList;
    }

    public void setRollsTakenToWinList(List<Integer> _rollsTakenToWinList) {
        this._rollsTakenToWinList = _rollsTakenToWinList;
    }

    public void setStepsClimbedInLadderList(List<Integer> _stepsClimbedInLadderList) {
        this._stepsClimbedInLadderList = _stepsClimbedInLadderList;
    }

    public void setStepsClimbedList(List<Integer> _stepsClimbedList) {
        this._stepsClimbedList = _stepsClimbedList;
    }

    public void setStepsSlidedList(List<Integer> _stepsSlidedList) {
        this._stepsSlidedList = _stepsSlidedList;
    }

    public void setConsecutiveRollsList(List<Integer> _consecutiveRollsList) {
        this._consecutiveRollsList = _consecutiveRollsList;
    }

    public int getRollsTakenToWinResult() {
        return _rollsTakenToWin.result();
    }

    private Counter _turnsTakenToWin;

    public Counter getTurnsTakenToWin() {
        return _turnsTakenToWin;
    }

    public int getTurnsTakenToWinResult() {
        return _turnsTakenToWin.result();
    }

    private Counter _landsOnSnake;

    public Counter getLandsOnSnake() {
        return _landsOnSnake;
    }

    public int getLandsOnSnakeResult() {
        return _landsOnSnake.result();
    }

    private List<Integer> _landsOnSnakeList = new ArrayList<>();

    public List<Integer> getLandsOnSnakeList() {
        return _landsOnSnakeList;
    }

    private Counter _landsOnLadder;

    public Counter getLandsOnLadder() {
        return _landsOnLadder;
    }

    public int getLandsOnLadderResult() {
        return _landsOnLadder.result();
    }

    private List<Integer> _landsOnLadderList = new ArrayList<>();

    public List<Integer> getLandsOnLadderList() {
        return _landsOnLadderList;
    }

    private List<Integer> _turnsTakenToWinList = new ArrayList<>();

    public List<Integer> getTurnsTakenToWinList() {
        return _turnsTakenToWinList;
    }

    private List<Integer> _rollsTakenToWinList = new ArrayList<>();

    public List<Integer> getRollsTakenToWinList() {
        return _rollsTakenToWinList;
    }

    private List<Integer> _stepsClimbedInLadderList = new ArrayList<>();

    public List<Integer> getStepsClimbedInLadderList() {
        return _stepsClimbedInLadderList;
    }

    private List<Integer> _stepsClimbedList = new ArrayList<>();

    public List<Integer> getStepsClimbedList() {
        return _stepsClimbedList;
    }

    private List<Integer> _stepsSlidedList = new ArrayList<>();

    public List<Integer> getStepsSlidedList() {
        return _stepsSlidedList;
    }

    private List<Integer> _consecutiveRollsList = new ArrayList<>();

    public List<Integer> getConsecutiveRollsList() {
        return _consecutiveRollsList;
    }

    public void computeAndPrint() {
        System.out.println("Stats across all simulations:\n");
        rollsNeededToWin();
        laddersClimbedDuringTheGame();
        snakeSlidesDuringTheGame();
        biggestClimbInATurn();
        biggestSlideInATurn();
        longestConsecutiveRolls();
        unluckyRollsBySnakeSlide();
        luckyRollsWithLadderClimb();
    }

    private void luckyRollsWithLadderClimb() {
        System.out.println("Lucky rolls during the game (player lands on a ladder) : " + Arrays.toString(_landsOnLadderList.toArray()));
        Integer minLandsOnLadder = _landsOnLadderList.stream().min((o1, o2) -> o1.compareTo(o2)).orElse(0);
        System.out.println("Minimum = " + minLandsOnLadder);

        int averageLandsOnLadder = _landsOnLadderList.stream().mapToInt(Integer::valueOf).sum() / timesToSimulate;
        System.out.println("Average = " + averageLandsOnLadder);

        Integer maxLandsOnLadder = _landsOnLadderList.stream().max((o1, o2) -> o1.compareTo(o2)).orElse(0);
        System.out.println("Minimum = " + maxLandsOnLadder);
        System.out.println("-----------------------------------------------");
    }

    private void unluckyRollsBySnakeSlide() {
        System.out.println("Unlucky rolls during the game (player lands on a snake) : " + Arrays.toString(_landsOnSnakeList.toArray()));
        Integer minLandsOnSnake = _landsOnSnakeList.stream().min((o1, o2) -> o1.compareTo(o2)).orElse(0);
        System.out.println("Minimum = " + minLandsOnSnake);

        int averageLandsOnSnake = _landsOnSnakeList.stream().mapToInt(Integer::valueOf).sum() / timesToSimulate;
        System.out.println("Average = " + averageLandsOnSnake);

        Integer maxLandsOnSnake = _landsOnSnakeList.stream().max((o1, o2) -> o1.compareTo(o2)).orElse(0);
        System.out.println("Minimum = " + maxLandsOnSnake);
        System.out.println("-----------------------------------------------");
    }

    private void longestConsecutiveRolls() {
        System.out.println(Arrays.toString(_consecutiveRollsList.toArray()));
        Integer longestTurn = _consecutiveRollsList.stream().max((o1, o2) -> o1.compareTo(o2)).orElse(0);
        System.out.println("Longest consecutive turn = " + longestTurn);
        System.out.println("-----------------------------------------------");
    }

    private void biggestSlideInATurn() {
        System.out.println(Arrays.toString(_stepsSlidedList.toArray()));
        Integer maxSlides = _stepsSlidedList.stream().max((o1, o2) -> o1.compareTo(o2)).orElse(0);
        System.out.println("The biggest slide in a single turn = " + maxSlides);
        System.out.println("-----------------------------------------------");
    }

    private void biggestClimbInATurn() {
        System.out.println(Arrays.toString(_stepsClimbedList.toArray()));
        Integer maxClimbs = _stepsClimbedList.stream().max((o1, o2) -> o1.compareTo(o2)).orElse(0);
        System.out.println("The biggest climb in a single turn = " + maxClimbs);
        System.out.println("-----------------------------------------------");
    }

    private void snakeSlidesDuringTheGame() {
        System.out.println("Number of snake slides during the game : " + Arrays.toString(_stepsSlidedList.toArray()));
        Integer minSlidesBySnake = _stepsSlidedList.stream().min((o1, o2) -> o1.compareTo(o2)).orElse(0);
        System.out.println("Minimum = " + minSlidesBySnake);

        int averageSlidesBySnake = _stepsSlidedList.stream().mapToInt(Integer::valueOf).sum() / timesToSimulate;
        System.out.println("Average = " + averageSlidesBySnake);

        Integer maxSlidesBySnake = _stepsSlidedList.stream().max((o1, o2) -> o1.compareTo(o2)).orElse(0);
        System.out.println("Maximum = " + maxSlidesBySnake);
        System.out.println("-----------------------------------------------");
    }

    private void laddersClimbedDuringTheGame() {
        System.out.println("Number of ladder climbs during the game : " + Arrays.toString(_stepsClimbedInLadderList.toArray()));
        Integer minClimbsInLadder = _stepsClimbedInLadderList.stream().min((o1, o2) -> o1.compareTo(o2)).orElse(0);
        System.out.println("Minimum = " + minClimbsInLadder);

        int averageClimbsInLadder = _stepsClimbedInLadderList.stream().mapToInt(Integer::valueOf).sum() / timesToSimulate;
        System.out.println("Average = " + averageClimbsInLadder);

        Integer maxClimbsInLadder = _stepsClimbedInLadderList.stream().max((o1, o2) -> o1.compareTo(o2)).orElse(0);
        System.out.println("Maximum = " + maxClimbsInLadder);
        System.out.println("-----------------------------------------------");
    }

    private void rollsNeededToWin() {
        System.out.println("Number of rolls needed to win : " + Arrays.toString(_rollsTakenToWinList.toArray()));
        Integer minRolls = _rollsTakenToWinList.stream().min((o1, o2) -> o1.compareTo(o2)).get();
        System.out.println("Minimum  = " + minRolls);

        int averageRolls = _rollsTakenToWinList.stream().mapToInt(Integer::valueOf).sum() / timesToSimulate;
        System.out.println("Average = " + averageRolls);

        Integer maxRolls = _rollsTakenToWinList.stream().max((o1, o2) -> o1.compareTo(o2)).get();
        System.out.println("Maximum = " + maxRolls);
        System.out.println("-----------------------------------------------");
    }

}