package org.example.code;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.powermock.api.mockito.PowerMockito.doReturn;

import org.apache.commons.lang3.Range;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ SnakeAndLadder.class })
class SnakeAndLadderTest {

	public SnakeAndLadderTest() {
		super();
	}

	private SnakeAndLadder snakeAndLadder;

	@Test
	void rollDiceTest() throws Exception {
		// given
		snakeAndLadder = SnakeAndLadder.getBoard().setTiles(10);
		Range<Integer> possibleRange = Range.between(1, 6);

		// when
		int result1 = Whitebox.invokeMethod(snakeAndLadder, "rollDice");
		// then
		assertTrue(possibleRange.contains(result1));

		// when
		int result2 = Whitebox.invokeMethod(snakeAndLadder, "rollDice");
		// then
		assertTrue(possibleRange.contains(result2));

		// when
		int result3 = Whitebox.invokeMethod(snakeAndLadder, "rollDice");
		// then
		assertTrue(possibleRange.contains(result3));

		// when
		int result4 = Whitebox.invokeMethod(snakeAndLadder, "rollDice");
		// then
		assertTrue(possibleRange.contains(result4));

	}

	@Test
	void hasAdditionalRollTest() throws Exception {
		// given
		snakeAndLadder = SnakeAndLadder.getBoard().setTiles(10);

		// when
		boolean value1 = Whitebox.invokeMethod(snakeAndLadder, "hasAdditionalRoll", 1);
		// then
		assertFalse(value1);

		boolean value2 = Whitebox.invokeMethod(snakeAndLadder, "hasAdditionalRoll", 6);
		// then
		assertTrue(value2);

		boolean value3 = Whitebox.invokeMethod(snakeAndLadder, "hasAdditionalRoll", 4);
		// then
		assertFalse(value3);
	}

	@Test
	void jumpByDiceRollsTest() throws Exception {
		// given
		SnakeAndLadder mock = Mockito.spy(SnakeAndLadder.class);
		mock.setTiles(10);

		// when
		doReturn(1).when(mock, "rollDice");
		int afterRollPosition = Whitebox.invokeMethod(mock, "jumpByDiceRolls", 4, Counter.start(0));

		// then
		assertTrue(afterRollPosition == 5);

	}

}