package com.league.web;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import com.league.model.Season;
import com.league.model.dao.SeasonsDao;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({ @ContextConfiguration })
public class SetupServletContextListenerTest {

	@Configuration
	static class LocalConfig {
		@Bean
		SeasonsDao getSeasonsDao() {
			return createMock(SeasonsDao.class);
		}

	}

	@Autowired
	SeasonsDao seasonsDao;

	@Autowired
	ApplicationContext context;

	@After
	public void after() {
		reset(seasonsDao);
	}

	@Test
	public void testContextInitialized() {

		final ServletContext servletContext = createMock(ServletContext.class);
		expect(
				servletContext
						.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE))
				.andReturn(context);
		replay(servletContext);

		final ServletContextEvent event = createMock(ServletContextEvent.class);
		expect(event.getServletContext()).andReturn(servletContext);
		replay(event);

		final Season season = new Season(2013L);

		seasonsDao.put(season);
		replay(seasonsDao);

		new SetupServletContextListener().contextInitialized(event);

		verify(servletContext, event, seasonsDao);
	}
}
