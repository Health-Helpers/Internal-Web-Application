package cat.ehh.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cat.ehh.web.model.User;


@Controller
public class WelcomeController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String redirectWelcomePage(ModelMap model,HttpServletRequest request) {
		List<User> listadoUsuarios = new ArrayList<User>();
		User user1 = new User();
		user1.setName("Usuario1");
		user1.setUserId(1);
		User user2 = new User();
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
	public String manageLanguages(ModelMap model) {
		return "language/language";
	}

	
}
