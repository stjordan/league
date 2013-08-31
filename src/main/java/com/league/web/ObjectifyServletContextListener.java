package com.league.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.googlecode.objectify.ObjectifyService;
import com.league.model.Season;

public class ObjectifyServletContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(final ServletContextEvent sce) {
		ObjectifyService.register(Season.class);
	}

	@Override
	public void contextDestroyed(final ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

}
