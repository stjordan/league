package com.league.model.util;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

@Component
public class DateGenerator {

	/**
	 * Returns a list of game day dates around the seed date.
	 * 
	 * @param seed the date around which game day dates are calculated
	 * @param gameDayOfWeek the day of the week that is game day, e.g.
	 *            {@link Calendar#SATURDAY}
	 * @param before the number of game day dates to generate before the seed
	 *            date's game day
	 * @param after the number of game day dates to generate after the seed
	 *            date's game day
	 * @return a list of game day dates
	 */
	public List<Date> generateGameDays(final Date seed,
			final int gameDayOfWeek, final int before, final int after) {

		final Date seedGameDay;
		{
			final Calendar working = getWorkingCalendar(seed);

			setToMidnight(working);

			/*
			 * if seed is a game day, working is seed, else working is next game
			 * day
			 */
			working.add(Calendar.DAY_OF_WEEK,
					gameDayOfWeek - working.get(Calendar.DAY_OF_WEEK));

			seedGameDay = working.getTime();
		}

		final List<Date> befores = Lists.newArrayList();
		{
			final Calendar working = getWorkingCalendar(seedGameDay);
			// working.add(Calendar.DAY_OF_MONTH,
			// -(before + 1) * working.getMaximum(Calendar.DAY_OF_WEEK));
			for (int i = 0; i < before; i++) {
				working.add(Calendar.DAY_OF_MONTH,
						-working.getMaximum(Calendar.DAY_OF_WEEK));
				befores.add(working.getTime());
			}
		}

		final List<Date> afters = Lists.newArrayList();
		{
			final Calendar working = getWorkingCalendar(seedGameDay);

			for (int i = 0; i < after; i++) {
				working.add(Calendar.DAY_OF_MONTH,
						working.getMaximum(Calendar.DAY_OF_WEEK));
				afters.add(working.getTime());
			}
		}

		return ImmutableList.copyOf(Iterables.concat(Lists.reverse(befores),
				ImmutableList.of(seedGameDay), afters));

	}

	private void setToMidnight(final Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
	}

	private Calendar getWorkingCalendar(final Date date) {

		final Calendar working = Calendar.getInstance(
				TimeZone.getTimeZone("America/New_York"), Locale.US);
		working.setTime(date);
		return working;
	}
}
