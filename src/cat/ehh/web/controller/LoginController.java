package cat.ehh.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	Logger log = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String checkLogin(ModelMap model,HttpServletRequest request) {
		if(request.getSession().getAttribute("username")!=null && !request.getSession().getAttribute("username").equals("")){
			return "welcome";	
		}else{
			return "login";
		}	
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void doLogin(ModelMap model,HttpServletRequest request,HttpServletResponse response) {

		try{
			String username = (String) request.getParameter("username");
			String password = (String) request.getParameter("password");

			//TODO: Check login against DB

			if(username.equals("admin") && password.equals("1234")){
				request.getSession().setAttribute("username",username);
				response.sendRedirect(request.getContextPath()+"/welcome");
			}else{
				response.sendRedirect(request.getContextPath()+"/login");
			}
		}catch(Exception e){
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}

}
