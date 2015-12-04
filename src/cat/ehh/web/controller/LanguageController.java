package cat.ehh.web.controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cat.ehh.web.dao.LanguageDAO;
import cat.ehh.web.model.Language;

@Controller
public class LanguageController {

	

	
	@Autowired
	LanguageDAO langDao;

	@RequestMapping(value = "language/editLanguage", method = RequestMethod.POST)
	public void editLanguage(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
		String idStr = (String)request.getParameter("id");
		String codigo = (String)request.getParameter("codigo");
		String nombre = (String)request.getParameter("nombre");

		Language lang = langDao.read(new Long(idStr));
		lang.setCode(codigo);
		lang.setName(nombre);

		lang = langDao.update(lang);

		List<Language> listadoTotal = langDao.findAll();

		request.getSession().setAttribute("languages", listadoTotal);


		try {
			response.sendRedirect(request.getContextPath()+"/language");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "language/add", method = RequestMethod.GET)
	public String redirectToAddLanguage(ModelMap model,HttpServletRequest request) {
		return "language/languageAdd";
	}

	@RequestMapping(value = "language/addLanguage", method = RequestMethod.POST)
	public void addLanguage(ModelMap model,HttpServletRequest request,HttpServletResponse response) {

		String codigo = (String)request.getParameter("codigo");
		String nombre = (String)request.getParameter("nombre");

		Language lang = new Language();
		lang.setCode(codigo);
		lang.setName(nombre);

		langDao.create(lang);

		List<Language> listadoTotal = langDao.findAll();

		request.getSession().setAttribute("languages", listadoTotal);

		try {
			response.sendRedirect(request.getContextPath()+"/language");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "language/remove", method = RequestMethod.GET)
	public void removeLanguage(ModelMap model,HttpServletRequest request,HttpServletResponse response) {

		String langId = request.getParameter("id");
		Language lang = langDao.read(new Long(langId));
		langDao.delete(lang);
		
		try {
			response.sendRedirect(request.getContextPath()+"/language");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "language/read", method = RequestMethod.GET)
	public String readLanguage(ModelMap model,HttpServletRequest request) {


		String langId = request.getParameter("id");

		Language lang = langDao.read(new Long(langId));

		request.getSession().setAttribute("language", lang);

		return "language/languageEdit";
	}
}
