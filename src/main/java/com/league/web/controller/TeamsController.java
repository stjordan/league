package com.league.web.controller;

import static com.google.common.collect.FluentIterable.from;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.league.model.Week;
import com.league.model.dao.WeeksDao;

@Controller
public class TeamsController {

	private final WeeksDao weeksDao;

	@Autowired
	public TeamsController(final WeeksDao weeksDao) {
		this.weeksDao = weeksDao;
	}

	/**
	 * 
	 * @param weekId
	 * @param model
	 * @return the teams view for the week of ID {@code weekId}.
	 * @throws NoSuchRequestHandlingMethodException
	 *             if no week with id {@code weekId} exists
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/manager/weeks/{weekId}/teams")
	public String get(@PathVariable final Long weekId, final Model model)
			throws NoSuchRequestHandlingMethodException {

		final List<Week> weeks = weeksDao.get();
		final Optional<Week> week = from(weeks).filter(isOfId(weekId)).first();

		if (week.isPresent()) {
			// do more
			model.addAttribute("weeks", weeks);
		} else {
			throw new NoSuchRequestHandlingMethodException("/manager/weeks/"
					+ weekId + "/teams", "get", null);
		}

		return "manager/teams";
	}

	private Predicate<Week> isOfId(final Long weekId) {
		return new Predicate<Week>() {

			@Override
			public boolean apply(final Week input) {
				return Long.valueOf(input.getId()).equals(weekId);
			}

		};
	}

}
