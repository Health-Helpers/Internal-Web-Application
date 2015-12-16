package cat.ehh.ws.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import cat.ehh.web.dao.ResponsibleDAO;
import cat.ehh.web.dao.UserDAO;
import cat.ehh.ws.services.ResponsibleService;

public class ResponsibleServiceImpl extends SpringBeanAutowiringSupport implements ResponsibleService {

	Logger log = LoggerFactory.getLogger(ResponsibleServiceImpl.class);

	@Autowired
	ResponsibleDAO responsibleDao;

	@Autowired
	UserDAO userDao;
	
	@Override
	public String createResponsible(String name, String surname, String idDoc, String phone, String birthdate,
			String adress, String langId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateResponsible(String name, String surname, String idDoc, String phone, String birthdate,
			String adress, String langId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String readResponsible(int responsibleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteResponsible(int responsibleId) {
		// TODO Auto-generated method stub
		return null;
	}
}
