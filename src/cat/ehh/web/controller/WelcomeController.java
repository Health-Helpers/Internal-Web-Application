package cat.ehh.web.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cat.ehh.web.dao.LanguageDAO;
import cat.ehh.web.dao.UserDAO;
import cat.ehh.web.model.Language;
import cat.ehh.web.model.UserEHH;


@Controller
public class WelcomeController {
	
	@Autowired
	LanguageDAO langDao;
	
	@Autowired
	UserDAO userDao;
	
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String redirectWelcomePage(ModelMap model,HttpServletRequest request) {
		return "welcome";
	}
	
	@RequestMapping(value = "user", method = RequestMethod.GET)
	public String manageUsers(ModelMap model,HttpServletRequest request) {
	
		List<UserEHH> listadoUsuarios = userDao.findAll();
		
		request.getSession().setAttribute("usuarios", listadoUsuarios);
		
		return "user/user";
	}
	
	@RequestMapping(value = "language", method = RequestMethod.GET)
	public String manageLanguages(ModelMap model,HttpServletRequest request) {
		List<Language> listadoTotal = langDao.findAll();
		
		request.getSession().setAttribute("languages", listadoTotal);
		
		return "language/language";
	}

	
}
