package com.league.model;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SeasonTest {

	@Test
	public void testToString() {
		assertTrue(new Season(2013L).toString().contains("2013"));
	}

}
