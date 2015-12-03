package cat.ehh.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cat.ehh.web.dao.UserDAO;
import cat.ehh.web.model.User;



@Controller
public class UserController {

	@Autowired
	UserDAO userDao;// UserDAO.getInstance();
	
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
	
	@RequestMapping(value = "user/addUser", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request) {
		String name= request.getParameter("nombre");
		User user = new User();
		user.setName(name);
		userDao.create(user);
		return "user/addUser";
	}
	
	@RequestMapping(value = "user/remove", method = RequestMethod.GET)
	public String removeUser(ModelMap model,HttpServletRequest request) {

		String userId = request.getParameter("id");
		
		//TODO: Remove the user data from de DB
		
		return "user/user";
	}
	
	@RequestMapping(value = "user/read", method = RequestMethod.GET)
	public String readUser(ModelMap model,HttpServletRequest request) {
		String userId = request.getParameter("id");
		//TODO: Read the user data from de DB
		return "user/editUser";
	}
}
