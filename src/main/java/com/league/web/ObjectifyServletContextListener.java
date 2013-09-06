package com.league.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.googlecode.objectify.ObjectifyService;
import com.league.model.Season;
import com.league.model.Week;

public class ObjectifyServletContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(final ServletContextEvent sce) {
		ObjectifyService.register(Season.class);
		ObjectifyService.register(Week.class);
	}

	@Override
	public void contextDestroyed(final ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

}
