package org.example.code;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CounterTest {

	private Counter counter;

	@Test
	void startTest() {
		// given
		counter = Counter.start(0);

		// when
		counter.count();
		// then
		assertTrue(counter.result() == 1);

		// when
		counter.count();
		// then
		assertTrue(counter.result() == 2);
	}

}