package com.league.model.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import org.springframework.stereotype.Component;

import com.league.model.Season;

@Component
public class SeasonsDao {

	public void put(final Season season) {
		ofy().save().entity(season);
	}
}
