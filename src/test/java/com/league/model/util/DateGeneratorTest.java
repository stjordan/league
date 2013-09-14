package com.league.model.util;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.ImmutableList;

public class DateGeneratorTest {

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
			"M/d/y h:m:s");

	@Test
	public void testGenerateGameDaysAlreadySaturday() {
		final Date seed = date("9/14/13 11:22:15");
		final List<Date> expected = ImmutableList.of(date("9/14/13 0:0:0"));
		final List<Date> gameDays = new DateGenerator().generateGameDays(seed,
				Calendar.SATURDAY, 0, 0);

		assertEquals(expected, gameDays);
	}

	@Test
	public void testGenerateGameDaysTwoAroundSaturday() {
		final Date seed = date("9/14/13 11:22:15");
		final List<Date> expected = ImmutableList.of(date("9/7/13 0:0:0"),
				date("9/14/13 0:0:0"), date("9/21/13 0:0:0"));
		final List<Date> gameDays = new DateGenerator().generateGameDays(seed,
				Calendar.SATURDAY, 1, 1);

		assertEquals(expected, gameDays);
	}

	@Test
	public void testGenerateGameDaysFourAroundWednesday() {
		final Date seed = date("9/11/13 11:22:15");
		final List<Date> expected = ImmutableList.of(date("8/31/13 0:0:0"),
				date("9/7/13 0:0:0"), date("9/14/13 0:0:0"),
				date("9/21/13 0:0:0"), date("9/28/13 0:0:0"));
		final List<Date> gameDays = new DateGenerator().generateGameDays(seed,
				Calendar.SATURDAY, 2, 2);

		assertEquals(expected, gameDays);
	}

	private Date date(final String pattern) {
		try {
			return DATE_FORMAT.parse(pattern);
		} catch (final ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
