package cat.ehh.ws.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import cat.ehh.web.dao.PatientDAO;
import cat.ehh.web.dao.UserDAO;
import cat.ehh.ws.services.UserService;

public class UserServiceImpl extends SpringBeanAutowiringSupport implements UserService {

	Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	PatientDAO patientDao;

	@Autowired
	UserDAO userDao;
	
	@Override
	public String createUser(String name, String surname, String idDoc, String phone, String birthdate, String adress,
			String type, String langId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateUser(String name, String surname, String idDoc, String phone, String birthdate, String adress,
			String type, String langId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String readUser(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteUser(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
