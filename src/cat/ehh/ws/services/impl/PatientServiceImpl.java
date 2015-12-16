package cat.ehh.ws.services.impl;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import cat.ehh.web.dao.PatientDAO;
import cat.ehh.web.dao.UserDAO;
import cat.ehh.web.dto.responses.CreatePatientResponseDto;
import cat.ehh.web.model.Patient;
import cat.ehh.web.model.UserEHH;
import cat.ehh.web.util.DateUtil;
import cat.ehh.ws.services.PatientService;

public class PatientServiceImpl extends SpringBeanAutowiringSupport implements PatientService {

	Logger log = LoggerFactory.getLogger(PatientServiceImpl.class);

	@Autowired
	PatientDAO patientDao;

	@Autowired
	UserDAO userDao;

	@Override
	public String createPatient(String name, String surname,String idDoc, String phone, String birthdate, String adress,String disease, String dependencyGrade,String langId) {
		CreatePatientResponseDto responseDto = new CreatePatientResponseDto();

		try{

			BigDecimal langIdBigDeci = new BigDecimal(langId);
			Date birthD = DateUtil.getDateFromString(birthdate);

			UserEHH user = new UserEHH(adress, birthD, idDoc, langIdBigDeci, name, phone, surname, 0);
			user = userDao.create(user);

			Patient patient = new Patient();
			patient.setDependencyGrade(dependencyGrade);
			patient.setDisease(disease);
			patient.setUser(user);
			patient.setUserId(new BigDecimal(user.getUserId()));

			patient = patientDao.create(patient);


			responseDto.setCode("0");
			responseDto.setMessage("Create Patient OK");
			responseDto.setPatient(patient);
		}catch(Exception e){
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			log.error(sw.toString());

			responseDto.setCode("-1");
			responseDto.setMessage("Create Patient Error");
			
		}
		return responseDto.createXMLString();
	}

	@Override
	public String updatePatient(String name, String surname, String idDoc, String phone, String birthdate,
			String adress, String disease, String dependencyGrade, String langId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String readPatient(int patientId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deletePatient(int patientId) {
		// TODO Auto-generated method stub
		return null;
	}

}
