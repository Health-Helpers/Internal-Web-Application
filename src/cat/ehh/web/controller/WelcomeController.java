package cat.ehh.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cat.ehh.web.dao.LanguageDAO;
import cat.ehh.web.model.Language;
import cat.ehh.web.model.UserEHH;


@Controller
public class WelcomeController {
	
	@Autowired
	LanguageDAO langDao;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String redirectWelcomePage(ModelMap model,HttpServletRequest request) {
		List<UserEHH> listadoUsuarios = new ArrayList<UserEHH>();
		UserEHH user1 = new UserEHH();
		user1.setName("Usuario1");
		user1.setUserId(1);
		UserEHH user2 = new UserEHH();
		user2.setName("Usuario2");
		user2.setUserId(2);
		listadoUsuarios.add(user1);
		listadoUsuarios.add(user2);
		
		request.getSession().setAttribute("usuarios", listadoUsuarios);
		
		return "welcome";
	}
	
	@RequestMapping(value = "user", method = RequestMethod.GET)
	public String manageUsers(ModelMap model) {
		return "user/user";
	}
	
	@RequestMapping(value = "language", method = RequestMethod.GET)
	public String manageLanguages(ModelMap model,HttpServletRequest request) {
		List<Language> listadoTotal = langDao.findAll();
		
		request.getSession().setAttribute("languages", listadoTotal);
		
		return "language/language";
	}

	
}
