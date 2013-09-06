package com.league.model.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import org.springframework.stereotype.Component;

import com.google.common.collect.Ordering;
import com.league.model.Season;
import com.league.model.Week;

@Component
public class WeeksDao {

	/**
	 * Returns all the weeks of the season in lock ascending order
	 * 
	 * @return all the weeks of the season in lock ascending order
	 */
	public List<Week> get() {

		final Iterable<Week> unsorted = ofy().load().type(Week.class)
				.ancestor(Season.SEASON_KEY);
		final List<Week> sorted = Ordering.natural().onResultOf(Week.GET_LOCK)
				.immutableSortedCopy(unsorted);

		return sorted;

	}

}
