package cat.ehh.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String checkLogin(ModelMap model,HttpServletRequest request) {
		if(request.getSession().getAttribute("username")!=null && !request.getSession().getAttribute("username").equals("")){
			return "/welcome";	
		}else{
			return "login";
		}	
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String doLogin(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
		try{
			String username = (String) request.getParameter("username");
			String password = (String) request.getParameter("password");

			//TODO: Check login against DB

			if(username.equals("admin") && password.equals("1234")){
				request.getSession().setAttribute("username",username);
				response.sendRedirect(request.getContextPath()+"/welcome");
				return "welcome";
			}else{
				request.getSession().setAttribute("badLogin",true);
				return "login";
			}
		}catch (Exception e){
			e.printStackTrace();
			return "login";
		}
	}

}
