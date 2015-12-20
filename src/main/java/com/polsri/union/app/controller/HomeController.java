package com.polsri.union.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = HomeController.BASE_PATH)
public class HomeController {

	public static final String BASE_PATH = "/";
	public static final String BASE_PATH_HOME = BASE_PATH + "home";

	@RequestMapping(method = RequestMethod.GET)
	public String showIndex(Model model) {
		return "index";
	}

	@RequestMapping(value = BASE_PATH_HOME, method = RequestMethod.GET)
	public String showHomepage(Model model) {
		return "home";
	}
}
