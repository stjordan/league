package com.league.model.util;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertSame;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.league.model.Week;

public class WeeksUtilTest {

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
			"M/d/y h:m:s");

	@Test
	public void testGetDefaultWeekWhenContainingWeek() {
		final Date target = date("9/13/13 10:00:00");
		final Week first = week("9/4/13 0:0:0", "9/10/13 23:59:59");
		final Week containing = week("9/11/13 0:0:0", "9/17/13 23:59:59");
		final Week third = week("9/18/13 0:0:0", "9/24/13 23:59:59");

		final ImmutableList<Week> weeks = ImmutableList.of(first, containing,
				third);
		assertSame(containing, new WeeksUtil().getDefaultWeek(weeks, target));

	}

	@Test
	public void testGetDefaultWeekWhenAllAfter() {

		final Date target = date("9/3/13 23:59:59");
		final Week first = week("9/4/13 0:0:0", "9/10/13 23:59:59");
		final Week second = week("9/11/13 0:0:0", "9/17/13 23:59:59");
		final Week third = week("9/18/13 0:0:0", "9/24/13 23:59:59");

		final ImmutableList<Week> weeks = ImmutableList
				.of(first, second, third);
		assertSame(first, new WeeksUtil().getDefaultWeek(weeks, target));

	}

	@Test
	public void testGetDefaultWeekWhenAllBefore() {

		final Date target = date("9/25/13 0:00:00");
		final Week first = week("9/4/13 0:0:0", "9/10/13 23:59:59");
		final Week second = week("9/11/13 0:0:0", "9/17/13 23:59:59");
		final Week third = week("9/18/13 0:0:0", "9/24/13 23:59:59");

		final ImmutableList<Week> weeks = ImmutableList
				.of(first, second, third);
		assertSame(third, new WeeksUtil().getDefaultWeek(weeks, target));

	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetDefaultWeekWhenEmpty() {
		new WeeksUtil().getDefaultWeek(Collections.<Week> emptyList(),
				new Date());
	}

	private Date date(final String pattern) {
		try {
			return DATE_FORMAT.parse(pattern);
		} catch (final ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}

	private Week week(final String startPattern, final String endPattern) {
		final Date start = date(startPattern);
		final Date end = date(endPattern);

		final Week week = createMock(Week.class);
		expect(week.getStart()).andReturn(start).anyTimes();
		expect(week.getEnd()).andReturn(end).anyTimes();
		replay(week);

		return week;
	}
}
