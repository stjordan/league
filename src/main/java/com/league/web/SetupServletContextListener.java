package com.league.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.league.model.Season;
import com.league.model.dao.SeasonsDao;

/**
 * Creates the 2013 Season on instance startup if it no such season already
 * exists.
 */
public class SetupServletContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(final ServletContextEvent sce) {

		final WebApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(sce.getServletContext());

		final SeasonsDao seasonDao = context.getBean(SeasonsDao.class);

		/* e.g. touch /(Season 2013) */
		seasonDao.put(new Season(2013L));

	}

	@Override
	public void contextDestroyed(final ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

}
