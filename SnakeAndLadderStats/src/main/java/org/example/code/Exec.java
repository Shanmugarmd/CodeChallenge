package org.example.code;

public class Exec {

    public static void main(String[] args) {
//        SnakeAndLadder game = SnakeAndLadder
//                .getBoard()
//                .setTiles(10)
//                .setLadder(3, 6)
//                .setSnake(4, 2)
//                .setLadder(5, 8)
//                .setSnake(7, 1)
//                .simulate(5);

        SnakeAndLadder game = SnakeAndLadder
                .getBoard()
                .setTiles(100)
                .setLadder(4, 25)
                .setLadder(13, 46)
                .setLadder(33, 49)
                .setLadder(42, 63)
                .setLadder(50, 69)
                .setLadder(62, 81)
                .setLadder(74, 92)
                .setSnake(99, 41)
                .setSnake(89, 53)
                .setSnake(76, 58)
                .setSnake(66, 45)
                .setSnake(54, 31)
                .setSnake(43, 18)
                .setSnake(40, 3)
                .setSnake(27, 5)
                .simulate(10);

        game.printStats();
    }
}
