package cat.ehh.web.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cat.ehh.web.dao.LanguageDAO;
import cat.ehh.web.dao.UserDAO;
import cat.ehh.web.model.UserEHH;
import cat.ehh.web.util.DateUtil;



@Controller
public class UserController {
	
	protected HashMap<String, Object> JSONROOT = new HashMap<String, Object>();

	@Autowired
	UserDAO userDao;// = new UserDAO();//UserDAO.getInstance();

	@Autowired
	LanguageDAO langDao;

	@RequestMapping(value = "user/edit", method = RequestMethod.POST)
	public void editUser(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
		String userId= request.getParameter("id");
		String name= request.getParameter("name");
		String dni = request.getParameter("iddoc");
		String surname = request.getParameter("surname");
		String birthdate = request.getParameter("birthdate");
		String phone = request.getParameter("phone");
		String typeStr = request.getParameter("tipo");
		String address = request.getParameter("adress");
		String language = request.getParameter("language");

		int type = 0;
		switch (typeStr) {
			case "Paciente":
				type = 0;
				break;
			case "Responsable":
				type = 1;
				break;
			default:
				break;
		}
		UserEHH user = userDao.read(new Long(userId));
		user.setName(name);
		user.setBirthdate(new Date());
		user.setName(name);
		user.setIddoc(dni);
		user.setBirthdate(DateUtil.getDateFromString(birthdate));
		user.setPhone(phone);
		user.setSurname(surname);
		user.setType(type);
		user.setAdress(address);
		if(language!=null){
			user.setLangid(new BigDecimal(language));
		}else{
			user.setLangid(new BigDecimal(1));
		}
		userDao.update(user);
		try {
			response.sendRedirect(request.getContextPath()+"/user");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	@RequestMapping(value = "user/add", method = RequestMethod.GET)
	public String redirectAddUser(HttpServletRequest request,HttpServletResponse response) {
		request.getParameter("input");
		request.getSession().setAttribute("languages", langDao.findAll());
		return "user/addUser";
	}

	@RequestMapping(value = "user/addUser", method = RequestMethod.POST)
	public void addUser(HttpServletRequest request,HttpServletResponse response) {
		String name= request.getParameter("name");
		String dni = request.getParameter("iddoc");
		String surname = request.getParameter("surname");
		String birthdate = request.getParameter("birthdate");
		String phone = request.getParameter("phone");
		String typeStr = request.getParameter("tipo");
		String address = request.getParameter("adress");
		String language = request.getParameter("language");
		int type = 0;
		switch (typeStr) {
			case "Paciente":
				type = 0;
				break;
			case "Responsable":
				type = 1;
				break;
			default:
				break;
		}
		UserEHH user = new UserEHH();
		user.setName(name);
		user.setBirthdate(new Date());
		user.setName(name);
		user.setIddoc(dni);
		user.setBirthdate(DateUtil.getDateFromString(birthdate));
		user.setPhone(phone);
		user.setSurname(surname);
		user.setType(type);
		user.setAdress(address);
		if(language==null)
			user.setLangid(new BigDecimal(1));
		else
			user.setLangid(new BigDecimal(language));
		userDao.create(user);
		
		try {
			response.sendRedirect(request.getContextPath()+"/user");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "user/remove", method = RequestMethod.GET)
	public void removeUser(ModelMap model,HttpServletRequest request,HttpServletResponse response) {

		String userId = request.getParameter("id");
		Long dni = 0L;
		if(userId != null){
			dni = Long.parseLong(userId);
		}
		UserEHH user = userDao.read(dni);
		if(user!=null)
			userDao.delete(user);
		try {
			response.sendRedirect(request.getContextPath()+"/user");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "user/read", method = RequestMethod.GET)
	public String readUser(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
		String userId = request.getParameter("id");
		Long id = 0L;
		if(userId != null){
			id = Long.parseLong(userId);
		}
		UserEHH user = userDao.read(id);
		request.getSession().setAttribute("user", user);
		request.getSession().setAttribute("languages", langDao.findAll());
		return "user/editUser";
	}
	
	@RequestMapping(value = "user/read2", method = RequestMethod.GET)
	public void readUser2(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try	{
		
		String userId = request.getParameter("id");
		Long id = 0L;
		if(userId != null){
			id = Long.parseLong(userId);
		}
		UserEHH user = userDao.read(id);
		request.getSession().setAttribute("user", user);
		request.getSession().setAttribute("languages", langDao.findAll());
		
		response.setContentType("text/plain");
        //response.getWriter().write(String.format("{\"result\":%s,\"name\":\"%s\"}", "false","Login or Pass wrong!"));
        
        JSONROOT.put("User", user);		          
   
	        // Convert Java Object to Json
        String jsonArray = gson.toJson(JSONROOT);
        System.out.println(jsonArray);

        response.getWriter().print(jsonArray);
	   }
	   catch(Exception ex){
	           JSONROOT.put("Result", "ERROR");
	           JSONROOT.put("Message", ex.getMessage());
	           String error = gson.toJson(JSONROOT);
	           response.getWriter().print(error);
	   }                               
				
		
		
	}

}
