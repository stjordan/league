package com.league.model.util;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.FluentIterable.from;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Range;
import com.league.model.Week;

@Component
public class WeeksUtil {

	/**
	 * Returns the default week from {@code weeks}. Assumes weeks are ordered
	 * naturally by ascending lock date
	 * 
	 * @param weeks
	 *            weeks from each default week member is determined
	 * @param date
	 *            date that defines default
	 * @return the default week
	 */
	public Week getDefaultWeek(final List<Week> weeks, final Date date) {

		checkArgument(weeks.isEmpty() == false, "There are no weeks");

		/*
		 * a predicate that transforms a week to a Range and defines Ranges that
		 * contains now
		 */
		final Predicate<Week> containsNow = Predicates.compose(contains(date),
				Week.TO_RANGE);
		final Optional<Week> containingWeek = from(weeks).filter(containsNow)
				.first();

		if (containingWeek.isPresent()) {
			// get the containing week if it is present
			return containingWeek.get();
		} else if (weeks.get(0).getStart().after(date)) {
			// get the first week if it is after now
			return weeks.get(0);
		} else {
			// get the last week
			return weeks.get(weeks.size() - 1);
		}
	}

	private Predicate<Range<Date>> contains(final Date date) {
		return new Predicate<Range<Date>>() {

			@Override
			public boolean apply(final Range<Date> input) {
				return input.contains(date);
			}

		};
	}

}
