package com.league.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ObjectifyServletContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(final ServletContextEvent sce) {
		// ObjectifyService.register(Settings.class);
	}

	@Override
	public void contextDestroyed(final ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

}
