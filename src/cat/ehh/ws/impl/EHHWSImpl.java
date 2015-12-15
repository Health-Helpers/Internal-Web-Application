package cat.ehh.ws.impl;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebService;

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
import cat.ehh.ws.PatientWS;
import cat.ehh.ws.ResponsibleWS;

@WebService
public class EHHWSImpl extends SpringBeanAutowiringSupport implements PatientWS, ResponsibleWS {


	Logger log = LoggerFactory.getLogger(EHHWSImpl.class);

	@Autowired
	PatientDAO patientDao;

	@Autowired
	UserDAO userDao;

	@WebMethod
	@Override
	public String createPatient(String name, String surname,String idDoc, String phone, String birthdate, String adress,String type,String disease, String dependencyGrade,String langId) {
		CreatePatientResponseDto responseDto = new CreatePatientResponseDto();

		try{

			Integer typeInteger = type!=null&&type.equals("1")?new Integer(1):new Integer(0);
			BigDecimal langIdBigDeci = new BigDecimal(langId);
			Date birthD = DateUtil.getDateFromString(birthdate);

			UserEHH user = new UserEHH(adress, birthD, idDoc, langIdBigDeci, name, phone, surname, typeInteger);
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

}
