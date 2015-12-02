package cat.ehh.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HelloController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String redirectWelcomePage(ModelMap model) {
		return "welcome";
	}
	
	@RequestMapping(value = "user", method = RequestMethod.GET)
	public String manageUsers(ModelMap model) {
		return "user/user";
	}
	
	@RequestMapping(value = "language", method = RequestMethod.GET)
	public String manageLanguages(ModelMap model) {
		return "language/language";
	}

	
}
