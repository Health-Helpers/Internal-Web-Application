package cat.ehh.ws.services.impl;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import cat.ehh.web.constants.Constants;
import cat.ehh.web.dao.ResponsibleDAO;
import cat.ehh.web.dao.UserDAO;
import cat.ehh.web.dto.responses.responsible.CreateResponsibleResponseDto;
import cat.ehh.web.dto.responses.responsible.DeleteResponsibleResponseDto;
import cat.ehh.web.dto.responses.responsible.ReadResponsibleResponseDto;
import cat.ehh.web.dto.responses.responsible.UpdateResponsibleResponseDto;
import cat.ehh.web.model.Patient;
import cat.ehh.web.model.Responsible;
import cat.ehh.web.model.UserEHH;
import cat.ehh.web.util.DateUtil;
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
		
		CreateResponsibleResponseDto responseDto = new CreateResponsibleResponseDto();
		
		try{

			BigDecimal langIdBigDeci = new BigDecimal(langId);
			Date birthD = DateUtil.getDateFromString(birthdate);

			UserEHH user = new UserEHH(adress, birthD, idDoc, langIdBigDeci, name, phone, surname, Constants.RESPONSIBLE);
			user = userDao.create(user);

			Responsible responsible = new Responsible();
			responsible.setUser(user);
			responsible.setUserId(new BigDecimal(user.getUserId()));

			responsible = responsibleDao.create(responsible);


			responseDto.setCode("0");
			responseDto.setMessage("Create Responsible OK");
			responseDto.setResponsible(responsible);
		}catch(Exception e){
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			log.error(sw.toString());

			responseDto.setCode("-1");
			responseDto.setMessage("Create Responsible Error");

		}
		return responseDto.createXMLString();
	}

	@Override
	public String updateResponsible(int responsibleId, String name, String surname, String idDoc, String phone, String birthdate,
			String adress, String langId) {
		
		UpdateResponsibleResponseDto responseDto = new UpdateResponsibleResponseDto();

		try{

			BigDecimal langIdBigDeci = new BigDecimal(langId);
			Date birthD = DateUtil.getDateFromString(birthdate);

			Responsible responsible = responsibleDao.read(new Long(responsibleId));
			UserEHH user = userDao.read(responsible.getUserId().longValue());
			user.setAdress(adress);
			user.setBirthdate(birthD);
			user.setIddoc(idDoc);
			user.setLangid(langIdBigDeci);
			user.setName(name);
			user.setSurname(surname);
			user.setPhone(phone);

			userDao.update(user);


			responseDto.setCode("0");
			responseDto.setMessage("Update Responsible OK");
			responseDto.setResponsible(responsible);

		}catch(Exception e){
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			log.error(sw.toString());

			responseDto.setCode("-1");
			responseDto.setMessage("Update Responsible Error");

		}
		return responseDto.createXMLString();
	}

	@Override
	public String readResponsible(int responsibleId) {
		ReadResponsibleResponseDto responseDto = new ReadResponsibleResponseDto();
		try{

			Responsible responsible = responsibleDao.read(new Long(responsibleId));

			responseDto.setCode("0");
			responseDto.setMessage("Read Responsible OK");
			responseDto.setResponsible(responsible);

		}catch(Exception e){
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			log.error(sw.toString());

			responseDto.setCode("-1");
			responseDto.setMessage("Read Responsible Error");

		}
		return responseDto.createXMLString();
	}

	@Override
	public String deleteResponsible(int responsibleId) {
		DeleteResponsibleResponseDto responseDto = new DeleteResponsibleResponseDto();
		try{

			Responsible responsible = responsibleDao.read(new Long(responsibleId));
			responsibleDao.delete(responsible);

			responseDto.setCode("0");
			responseDto.setMessage("Delete Responsible OK");
			responseDto.setResponsible(responsible);

		}catch(Exception e){
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			log.error(sw.toString());

			responseDto.setCode("-1");
			responseDto.setMessage("Delete Responsible Error");

		}
		return responseDto.createXMLString();
	}

	@Override
	public String addPatientToResponsible(int responsibleId, int patientId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deletePatientFromResponsible(int responsibleId, int patientId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getResponsiblePatients(int responsibleId) {
		// TODO Auto-generated method stub
		return null;
	}
}
