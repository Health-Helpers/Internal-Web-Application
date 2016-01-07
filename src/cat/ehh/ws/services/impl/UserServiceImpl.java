package cat.ehh.ws.services.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.github.kevinsawicki.http.HttpRequest;

import cat.ehh.web.dao.AuxiliarDataDAO;
import cat.ehh.web.dao.PatientDAO;
import cat.ehh.web.dao.UserDAO;
import cat.ehh.web.dto.responses.user.CreateUserResponseDto;
import cat.ehh.web.model.UserEHH;
import cat.ehh.ws.services.UserService;

public class UserServiceImpl extends SpringBeanAutowiringSupport implements UserService {

	Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	PatientDAO patientDao;

	@Autowired
	UserDAO userDao;


	@Autowired 
	AuxiliarDataDAO auxiliarDataDao;

	@Override
	public String registerUser(String idDoc, String phone,String parseInstallationId) {
		CreateUserResponseDto responseDto = new CreateUserResponseDto();
		UserEHH user = new UserEHH();

		try{

			//TODO: Comprovar que no n'existeixi cap amb aquest DNI + telf
			if(!userDao.checkUserExistence(idDoc,phone)){

				user.setPhone(phone);
				user.setIddoc(idDoc);
				user.setInstallationId(parseInstallationId);

				user = userDao.create(user);

				//Enviar una PUSH a l'usuari recient creat mitjantçant l'API REST del parse.com
				sendPushNotification(parseInstallationId,"Welcome");

				responseDto.setCode("0");
				responseDto.setMessage("Create User OK");
				responseDto.setUser(user);
			}else{
				responseDto.setCode("1");
				responseDto.setMessage("Create User NOK :  User with same idDoc and phone already exist!");
			}
		}catch(Exception e){
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			log.error(sw.toString());

			responseDto.setCode("-1");
			responseDto.setMessage("Create User Error");
		}
		return responseDto.createXMLString();
	}


	private void sendPushNotification(String parseInstallationId,String message) {
		try {
			{
				String url = "https://api.parse.com/1/push";
				URL obj = new URL(url);

				//Attempt to use HttpRequest to send post request to parse cloud
				HttpRequest request = HttpRequest.post(obj).contentType("application/json");
				request.header("X-Parse-Application-Id", "l3gHQ5XzYri2jGFACJkZKiupEHe3xDF2MgoRbrz4");
				request.header("X-Parse-REST-API-Key", "fUxVj5tD2UlVUisT4z7tiHD8iTusK8v1NWZcbvlG");

				String input = "{"+
						"\"where\": {"+
						"\"deviceType\": \"android\","+
						"\"installationId\": \""+parseInstallationId+"\"},"+
						"\"data\": {"+
						"\"alert\": \""+message+"\""+
						"}}";

				request.send(input.getBytes("UTF8"));

				if (request.ok())
					log.debug("HttpRequest WORKED");
				else
					log.error("HttpRequest FAILED " + request.code() + request.body());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


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
