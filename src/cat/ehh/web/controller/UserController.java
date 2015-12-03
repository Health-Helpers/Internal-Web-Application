package cat.ehh.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class UserController {

	
	@RequestMapping(value = "user/edit", method = RequestMethod.GET)
	public String editUser(ModelMap model,HttpServletRequest request) {
		model.addAttribute("message", "Spring 3 MVC Hello World");
		return "hello";
	}
	
	@RequestMapping(value = "user/add", method = RequestMethod.GET)
	public String redirectAddUser(HttpServletRequest request) {
		request.getParameter("input");
		return "user/addUser";
	}
	
	@RequestMapping(value = "user/add", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request) {
		request.getParameter("input");
		return "user/addUser";
	}
	
	@RequestMapping(value = "user/remove", method = RequestMethod.GET)
	public String removeUser(ModelMap model,HttpServletRequest request) {

		String userId = request.getParameter("id");
		
		//TODO: Remove the user data from de DB
		
		return "hello";
	}
	
	@RequestMapping(value = "user/read", method = RequestMethod.GET)
	public String readUser(ModelMap model,HttpServletRequest request) {
		String userId = request.getParameter("id");
		//TODO: Read the user data from de DB
		return "user/editUser";
	}
}
