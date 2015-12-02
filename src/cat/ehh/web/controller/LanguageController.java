package cat.ehh.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LanguageController {

	@RequestMapping(value = "language/edit", method = RequestMethod.GET)
	public String editLanguage(ModelMap model,HttpServletRequest request) {
		model.addAttribute("message", "Spring 3 MVC Hello World");
		return "hello";
	}
	
	@RequestMapping(value = "language/add", method = RequestMethod.GET)
	public String addLanguage(ModelMap model,HttpServletRequest request) {

		model.addAttribute("message", "Spring 3 MVC Hello World");
		return "hello";
	}
	
	@RequestMapping(value = "language/remove", method = RequestMethod.GET)
	public String removeLanguage(ModelMap model,HttpServletRequest request) {

		model.addAttribute("message", "Spring 3 MVC Hello World");
		return "hello";
	}
	
	@RequestMapping(value = "language/read", method = RequestMethod.GET)
	public String readLanguage(ModelMap model,HttpServletRequest request) {

		model.addAttribute("message", "Spring 3 MVC Hello World");
		return "hello";
	}
}
