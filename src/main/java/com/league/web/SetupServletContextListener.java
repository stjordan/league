package com.league.web;

import static com.googlecode.objectify.ObjectifyService.ofy;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.googlecode.objectify.VoidWork;
import com.league.model.Season;

/**
 * Creates the 2013 Season on instance startup if it no such season already
 * exists.
 */
public class SetupServletContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(final ServletContextEvent sce) {
		ofy().transact(new VoidWork() {

			@Override
			public void vrun() {
				final Season season = ofy().load().type(Season.class).id(2013L)
						.now();
				if (season == null) {
					ofy().save().entity(new Season(2013L)).now();
				}
			}
		});

	}

	@Override
	public void contextDestroyed(final ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

}
