package com.league.web.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.league.model.Week;
import com.league.model.dao.WeeksDao;
import com.league.model.util.DateGenerator;

@Controller
public class WeeksController {

	private final WeeksDao weeksDao;
	private final DateGenerator dateGenerator;
	private final CustomDateEditor dateEditor;

	@Autowired
	public WeeksController(final WeeksDao weeksDao,
			final DateGenerator dateGenerator,
			@Qualifier("month-day") final CustomDateEditor dateEditor) {
		this.weeksDao = weeksDao;
		this.dateGenerator = dateGenerator;
		this.dateEditor = dateEditor;
	}

	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		binder.setAllowedFields("id", "lock");
		binder.registerCustomEditor(Date.class, dateEditor);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/manager/weeks")
	public String get(final Model model) {

		final List<Week> weeks = weeksDao.get();

		final Date now = new Date();
		final List<Date> gameDays = dateGenerator.generateGameDays(now,
				Calendar.SATURDAY, 0, 15);

		final long id = weeks.size() + 1;
		final Date lock = gameDays.get(0);

		final Week newWeek = new Week(id, lock);

		model.addAttribute("weeks", weeks);
		model.addAttribute("newWeek", newWeek);
		model.addAttribute("gameDays", gameDays);
		return "manager/weeks";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/manager/weeks")
	public String get(@ModelAttribute("newWeek") final Week newWeek,
			final BindingResult bindingResult, final Model model) {

		if (bindingResult.hasErrors()) {
			final List<Week> weeks = weeksDao.get();

			final Date now = new Date();
			final List<Date> gameDays = dateGenerator.generateGameDays(now,
					Calendar.SATURDAY, 0, 15);

			model.addAttribute("weeks", weeks);
			// model.addAttribute("newWeek", newWeek);
			model.addAttribute("gameDays", gameDays);
			return "manager/weeks";
		}

		return "redirect:/manager/weeks";
	}

}
