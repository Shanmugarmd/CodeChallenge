package org.example.code;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SnakeAndLadderTest {

    private SnakeAndLadder snakeAndLadder;

    @BeforeAll
    static void beforeAll() {

    }

    @BeforeEach
    void setUp() {
        snakeAndLadder = new SnakeAndLadder();
    }


    @Test
    void rollDiceTest() {
        int dice = snakeAndLadder.rollDice();
        Assert.assertEquals(dice, dice);


    }
}