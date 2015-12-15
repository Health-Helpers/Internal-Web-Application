package cat.ehh.ws.impl;

import java.math.BigDecimal;
import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import cat.ehh.web.dao.PatientDAO;
import cat.ehh.web.dao.UserDAO;
import cat.ehh.web.dto.CreatePatientResponseDto;
import cat.ehh.web.model.Patient;
import cat.ehh.web.model.UserEHH;
import cat.ehh.web.util.DateUtil;
import cat.ehh.ws.PatientWS;
import cat.ehh.ws.ResponsibleWS;

@WebService
public class EHHWSImpl extends SpringBeanAutowiringSupport implements PatientWS, ResponsibleWS {

	@Autowired
	PatientDAO patientDao;
	
	@Autowired
	UserDAO userDao;
	
	@WebMethod
	@Override
	public String createPatient(String name, String surname,String idDoc, String phone, String birthdate, String adress,String type,String disease, String dependencyGrade,String langId) {
		
		Integer typeInteger = type!=null&&type.equals("1")?new Integer(1):new Integer(0);
		BigDecimal langIdBigDeci = new BigDecimal(langId);
		Date birthD = DateUtil.getDateFromString(birthdate);
		
		UserEHH user = new UserEHH(adress, birthD, idDoc, langIdBigDeci, name, phone, surname, typeInteger);
		user = userDao.create(user);
		
		Patient patient = new Patient();
		patient.setDependencyGrade(dependencyGrade);
		patient.setDisease(disease);
		patient.setUser(user);
		
		patientDao.create(patient);
		CreatePatientResponseDto responseDto = new CreatePatientResponseDto();
		
		responseDto.setCode("0");
		responseDto.setMessage("guai");
		responseDto.setPatient(patient);
		
		return responseDto.createXMLString();
	}

}
