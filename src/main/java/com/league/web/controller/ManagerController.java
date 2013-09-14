package com.league.web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.league.model.Week;
import com.league.model.dao.WeeksDao;
import com.league.model.util.WeeksUtil;

@Controller
public class ManagerController {

	private final WeeksDao weeksDao;
	private final WeeksUtil weeksUtil;

	@Autowired
	public ManagerController(final WeeksDao weeksDao, final WeeksUtil weeksUtil) {
		this.weeksDao = weeksDao;
		this.weeksUtil = weeksUtil;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/manager")
	public String get(final Model model) {

		final String redirectLocation;

		final List<Week> weeks = weeksDao.get();
		if (weeks.isEmpty()) {
			redirectLocation = "/manager/weeks";
		} else {
			final Week week = weeksUtil.getDefaultWeek(weeks, new Date());
			redirectLocation = "/manager/weeks/" + week.getId() + "/games";
		}

		return "redirect:" + redirectLocation;
	}
}
