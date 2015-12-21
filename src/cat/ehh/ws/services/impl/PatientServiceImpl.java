package cat.ehh.ws.services.impl;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import cat.ehh.web.constants.Constants;
import cat.ehh.web.dao.AuxiliarDataDAO;
import cat.ehh.web.dao.PatientDAO;
import cat.ehh.web.dao.PatientResponsibleDAO;
import cat.ehh.web.dao.UserDAO;
import cat.ehh.web.dto.responses.patient.AddResponsibleToPatientResponseDto;
import cat.ehh.web.dto.responses.patient.CreatePatientResponseDto;
import cat.ehh.web.dto.responses.patient.DeletePatientResponseDto;
import cat.ehh.web.dto.responses.patient.DeleteResponsibleFromPatientResponseDto;
import cat.ehh.web.dto.responses.patient.GetPatientLocationResponseDto;
import cat.ehh.web.dto.responses.patient.GetPatientResponsiblesResponseDto;
import cat.ehh.web.dto.responses.patient.ReadPatientResponseDto;
import cat.ehh.web.dto.responses.patient.SendPatientLocationResponseDto;
import cat.ehh.web.dto.responses.patient.UpdatePatientResponseDto;
import cat.ehh.web.model.Auxiliar_data;
import cat.ehh.web.model.Patient;
import cat.ehh.web.model.PatientResponsible;
import cat.ehh.web.model.Responsible;
import cat.ehh.web.model.UserEHH;
import cat.ehh.web.util.DateUtil;
import cat.ehh.ws.services.PatientService;

public class PatientServiceImpl extends SpringBeanAutowiringSupport implements PatientService {

	Logger log = LoggerFactory.getLogger(PatientServiceImpl.class);

	@Autowired
	PatientDAO patientDao;

	@Autowired
	UserDAO userDao;

	@Autowired 
	PatientResponsibleDAO patientResponsibleDao;

	@Autowired 
	AuxiliarDataDAO auxiliarDataDao;

	@Override
	public String createPatient(String name, String surname,String idDoc, String phone, String birthdate, String adress,String disease, String dependencyGrade,String langId) {
		CreatePatientResponseDto responseDto = new CreatePatientResponseDto();

		try{

			BigDecimal langIdBigDeci = new BigDecimal(langId);
			Date birthD = DateUtil.getDateFromString(birthdate);

			UserEHH user = new UserEHH(adress, birthD, idDoc, langIdBigDeci, name, phone, surname, Constants.PATIENT);
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
	public String updatePatient(int patientId,String name, String surname, String idDoc, String phone, String birthdate,
			String adress, String disease, String dependencyGrade, String langId) {

		UpdatePatientResponseDto responseDto = new UpdatePatientResponseDto();

		try{

			BigDecimal langIdBigDeci = new BigDecimal(langId);
			Date birthD = DateUtil.getDateFromString(birthdate);

			Patient patient = patientDao.read(new Long(patientId));
			UserEHH user = userDao.read(patient.getUserId().longValue());
			user.setAdress(adress);
			user.setBirthdate(birthD);
			user.setIddoc(idDoc);
			user.setLangid(langIdBigDeci);
			user.setName(name);
			user.setSurname(surname);
			user.setPhone(phone);

			userDao.update(user);

			patient.setDependencyGrade(dependencyGrade);
			patient.setDisease(disease);

			patient = patientDao.update(patient);

			responseDto.setCode("0");
			responseDto.setMessage("Update Patient OK");
			responseDto.setPatient(patient);

		}catch(Exception e){
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			log.error(sw.toString());

			responseDto.setCode("-1");
			responseDto.setMessage("Update Patient Error");

		}
		return responseDto.createXMLString();
	}

	@Override
	public String readPatient(int patientId) {
		ReadPatientResponseDto responseDto = new ReadPatientResponseDto();
		try{

			Patient patient = patientDao.read(new Long(patientId));

			responseDto.setCode("0");
			responseDto.setMessage("Read Patient OK");
			responseDto.setPatient(patient);

		}catch(Exception e){
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			log.error(sw.toString());

			responseDto.setCode("-1");
			responseDto.setMessage("Read Patient Error");

		}
		return responseDto.createXMLString();
	}

	@Override
	public String deletePatient(int patientId) {
		DeletePatientResponseDto responseDto = new DeletePatientResponseDto();
		try{

			Patient patient = patientDao.read(new Long(patientId));
			patientDao.delete(patient);

			responseDto.setCode("0");
			responseDto.setMessage("Delete Patient OK");
			responseDto.setPatient(patient);

		}catch(Exception e){
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			log.error(sw.toString());

			responseDto.setCode("-1");
			responseDto.setMessage("Delete Patient Error");

		}
		return responseDto.createXMLString();
	}

	@Override
	public String addResponsibleToPatient(int patientId, int responsibleId) {
		AddResponsibleToPatientResponseDto responseDto = new AddResponsibleToPatientResponseDto();

		try{
			PatientResponsible patientResponsible = new PatientResponsible();
			patientResponsible.setResponsibleId(new BigDecimal(responsibleId));
			patientResponsible.setPatientId(new BigDecimal(patientId));

			patientResponsibleDao.create(patientResponsible);


			responseDto.setCode("0");
			responseDto.setMessage("addResponsibleToPatient OK");

		}catch(Exception e){
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			log.error(sw.toString());

			responseDto.setCode("-1");
			responseDto.setMessage("addResponsibleToPatient Error");
		}

		return responseDto.createXMLString();
	}

	@Override
	public String deleteResponsibleFromPatient(int patientId, int responsibleId) {
		DeleteResponsibleFromPatientResponseDto responseDto = new DeleteResponsibleFromPatientResponseDto();


		try{
			PatientResponsible patientResponsible  = patientResponsibleDao.findByPatientAndResponsible(patientId, responsibleId);
			patientResponsibleDao.delete(patientResponsible);

			responseDto.setCode("0");
			responseDto.setMessage("deleteResponsibleFromPatient OK");

		}catch(Exception e){
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			log.error(sw.toString());

			responseDto.setCode("-1");
			responseDto.setMessage("deleteResponsibleFromPatient Error");
		}

		return responseDto.createXMLString();
	}

	@Override
	public String getPatientResponsibles(int patientId) {

		GetPatientResponsiblesResponseDto responseDto = new GetPatientResponsiblesResponseDto();

		try{
			Patient patient = patientDao.read(new Long(patientId));


			responseDto.setCode("0");
			responseDto.setMessage("getPatientResponsibles OK");


			List<PatientResponsible> patientResponsibles = patient.getPatientResponsibles();
			List<Responsible> responsibleList = new ArrayList<Responsible>();

			if(patientResponsibles!=null){
				for(PatientResponsible patResp : patientResponsibles){
					responsibleList.add(patResp.getResponsible());
				}
				responseDto.setPatientResponsibles(responsibleList);
			}
		}catch(Exception e){
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			log.error(sw.toString());

			responseDto.setCode("-1");
			responseDto.setMessage("getPatientResponsibles Error");
		}

		return responseDto.createXMLString();
	}

	@Override
	public String sendPatientLocation(int patientId, String date, float latitude, float longitude) {
		SendPatientLocationResponseDto responseDto = new SendPatientLocationResponseDto();
		try{
			Auxiliar_data auxData = auxiliarDataDao.getPatientAuxiliarData(patientId);

			if(auxData!=null){
				JsonParser parser = new JsonParser();
				Object obj = parser.parse(auxData.getAuxiliar_data());

				JsonObject jsonObject = (JsonObject) obj;

				JsonArray jsonLocations = jsonObject.getAsJsonArray("locations");


				JsonObject newLocation = new JsonObject();
				if(date==null){
					date = DateUtil.getStringFromDate(new Date());
				}
				newLocation.addProperty("date", date);
				newLocation.addProperty("latitude", date);
				newLocation.addProperty("longitude", date);

				jsonLocations.add(newLocation);

				auxData.setAuxiliar_data(jsonLocations.getAsString());

				auxiliarDataDao.update(auxData);

			}else{
				//Hem de crear un json nou
				JsonObject newLocation = new JsonObject();
				if(date==null){
					date = DateUtil.getStringFromDate(new Date());
				}
				newLocation.addProperty("date", date);
				newLocation.addProperty("latitude", latitude);
				newLocation.addProperty("longitude", longitude);

				JsonArray locationsArray = new JsonArray();
				JsonObject locationsObject = new JsonObject();
				locationsArray.add(newLocation);
				locationsObject.add("locations", locationsArray);

				auxData = new Auxiliar_data();
				auxData.setPatientId(new BigDecimal(patientId));
				auxData.setAuxiliar_data(locationsObject.toString());

				auxiliarDataDao.create(auxData);
			}


			responseDto.setCode("0");
			responseDto.setMessage("sendPatientLocation OK");

		}catch(Exception e){
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			log.error(sw.toString());

			responseDto.setCode("-1");
			responseDto.setMessage("sendPatientLocation Error");
		}
		return responseDto.createXMLString();
	}

	@Override
	public String getPatientLocation(int patientId) {
		GetPatientLocationResponseDto responseDto = new GetPatientLocationResponseDto();
		
		try{
			Auxiliar_data auxData = auxiliarDataDao.getPatientAuxiliarData(patientId);
			JsonParser parser = new JsonParser();
			Object obj = parser.parse(auxData.getAuxiliar_data());
			JsonObject jsonObject = (JsonObject) obj;

			JsonArray jsonLocations = jsonObject.getAsJsonArray("locations");

			JsonObject locationJsonObject = (JsonObject) jsonLocations.get(0);
			String locationDate = locationJsonObject.get("date").getAsString();
			String locationLat = locationJsonObject.get("latitude").getAsString();
			String locationLon = locationJsonObject.get("longitude").getAsString();

			responseDto.setDate(locationDate);
			responseDto.setLatitude(locationLat);
			responseDto.setLongitude(locationLon);
			
			responseDto.setCode("0");
			responseDto.setMessage("getPatientLocation OK");

		}catch(Exception e){
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			log.error(sw.toString());

			responseDto.setCode("-1");
			responseDto.setMessage("getPatientLocation Error");
		}
		return responseDto.createXMLString();
	}
}
