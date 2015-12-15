package cat.ehh.web.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cat.ehh.web.dao.LanguageDAO;
import cat.ehh.web.model.Language;

@Controller
public class LanguageController {

	protected HashMap<String, Object> JSONROOT = new HashMap<String, Object>();
	Logger log = LoggerFactory.getLogger(LanguageController.class);

	@Autowired
	LanguageDAO langDao;

	@RequestMapping(value = "language/editLanguage", method = RequestMethod.POST)
	public void editLanguage(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
		try {
			String idStr = (String)request.getParameter("id");
			String codigo = (String)request.getParameter("code");
			String nombre = (String)request.getParameter("name");

			Language lang = langDao.read(new Long(idStr));
			lang.setCode(codigo);
			lang.setName(nombre);

			lang = langDao.update(lang);


			response.sendRedirect(request.getContextPath()+"/language");
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "language/add", method = RequestMethod.GET)
	public String redirectToAddLanguage(ModelMap model,HttpServletRequest request) {
		return "language/languageAdd";
	}

	@RequestMapping(value = "language/addLanguage", method = RequestMethod.POST)
	public void addLanguage(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
		try {
			String codigo = (String)request.getParameter("code");
			String nombre = (String)request.getParameter("name");

			Language lang = new Language();
			lang.setCode(codigo);
			lang.setName(nombre);

			langDao.create(lang);

			response.sendRedirect(request.getContextPath()+"/language");
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "language/remove", method = RequestMethod.GET)
	public void removeLanguage(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
		try {
			String langId = request.getParameter("id");
			Language lang = langDao.read(new Long(langId));
			langDao.delete(lang);

			response.sendRedirect(request.getContextPath()+"/language");
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "language/read", method = RequestMethod.GET)
	public String readLanguage(ModelMap model,HttpServletRequest request) {

		try{
			String langId = request.getParameter("id");

			Language lang = langDao.read(new Long(langId));

			request.getSession().setAttribute("language", lang);

		}catch(Exception e){
			log.error(e.getMessage());
		}
		return "language/languageEdit";
	}
	
	
	@RequestMapping(value = "language/read2", method = RequestMethod.GET)
	public void readLanguage2(ModelMap model,HttpServletRequest request, HttpServletResponse response) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try	{
			String langId = request.getParameter("id");
			Language lang = langDao.read(new Long(langId));
			request.getSession().setAttribute("language", lang);
			
			response.setContentType("text/plain");
			
			JSONROOT.put("Lang", lang);		
			
			String jsonArray = gson.toJson(JSONROOT);
		    System.out.println(jsonArray);

		    response.getWriter().print(jsonArray);

		}catch(Exception e){
			JSONROOT.put("Result", "ERROR");
            JSONROOT.put("Message", e.getMessage());
            String error = gson.toJson(JSONROOT);
			response.getWriter().print(error);
		}
		
	}
}
