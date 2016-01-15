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
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import cat.ehh.web.constants.Constants;
import cat.ehh.web.dao.AuxiliarDataDAO;
import cat.ehh.web.dao.PatientDAO;
import cat.ehh.web.dao.PatientResponsibleDAO;
import cat.ehh.web.dao.ResponsibleDAO;
import cat.ehh.web.dao.UserDAO;
import cat.ehh.web.dto.GeofenceDTO;
import cat.ehh.web.dto.responses.patient.AddPatientGeofenceResponseDto;
import cat.ehh.web.dto.responses.patient.AddResponsibleToPatientResponseDto;
import cat.ehh.web.dto.responses.patient.CreatePatientResponseDto;
import cat.ehh.web.dto.responses.patient.DeleteGeofenceResponseDto;
import cat.ehh.web.dto.responses.patient.DeletePatientResponseDto;
import cat.ehh.web.dto.responses.patient.DeleteResponsibleFromPatientResponseDto;
import cat.ehh.web.dto.responses.patient.GetPatientGeofenceResponseDto;
import cat.ehh.web.dto.responses.patient.GetPatientLocationResponseDto;
import cat.ehh.web.dto.responses.patient.GetPatientResponsiblesResponseDto;
import cat.ehh.web.dto.responses.patient.ReadPatientResponseDto;
import cat.ehh.web.dto.responses.patient.SendPatientLocationResponseDto;
import cat.ehh.web.dto.responses.patient.UpdateGeofenceResponseDto;
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
	ResponsibleDAO responsibleDao;

	@Autowired
	UserDAO userDao;

	@Autowired 
	PatientResponsibleDAO patientResponsibleDao;

	@Autowired 
	AuxiliarDataDAO auxiliarDataDao;

	@Override
	public String createPatient(String name, String surname,String idDoc, String phone, String birthdate, String adress,String disease, String dependencyGrade,int responsibleId) {
		CreatePatientResponseDto responseDto = new CreatePatientResponseDto();

		try{

			Responsible resp = responsibleDao.read(new Long(responsibleId));


			Date birthD = DateUtil.getDateFromString(birthdate);

			UserEHH user = new UserEHH(adress, birthD, idDoc, new BigDecimal(0), name, phone, surname, Constants.PATIENT);
			user = userDao.create(user);

			Patient patient = new Patient();
			patient.setDependencyGrade(dependencyGrade);
			patient.setDisease(disease);
			patient.setUser(user);
			patient.setUserId(new BigDecimal(user.getUserId()));

			patient = patientDao.create(patient);

			if(resp!=null){
				PatientResponsible patientResponsible = new PatientResponsible();
				patientResponsible.setPatient(patient);
				patientResponsible.setResponsible(resp);
				patientResponsible.setPatientId(new BigDecimal(patient.getPatientId()));
				patientResponsible.setResponsibleId(new BigDecimal(resp.getResponsibleId()));

				patientResponsibleDao.create(patientResponsible);
			}

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
			String adress, String disease, String dependencyGrade, int responsibleId) {

		UpdatePatientResponseDto responseDto = new UpdatePatientResponseDto();

		try{

			Date birthD = DateUtil.getDateFromString(birthdate);

			Patient patient = patientDao.read(new Long(patientId));
			UserEHH user = userDao.read(patient.getUserId().longValue());
			user.setAdress(adress);
			user.setBirthdate(birthD);
			user.setIddoc(idDoc);
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
					date = DateUtil.getTimestampStringFromDate(new Date());
				}
				newLocation.addProperty("date", date);
				newLocation.addProperty("latitude", latitude);
				newLocation.addProperty("longitude", longitude);

				jsonLocations.add(newLocation);

				auxData.setAuxiliar_data(jsonObject.toString());

				auxiliarDataDao.update(auxData);

			}else{
				//Hem de crear un json nou
				JsonObject newLocation = new JsonObject();
				if(date==null){
					date = DateUtil.getTimestampStringFromDate(new Date());
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

			if(jsonLocations!=null && jsonLocations.size()>0){
				JsonObject locationJsonObject = (JsonObject) jsonLocations.get(jsonLocations.size()-1);
				String locationDate = DateUtil.getTimestampStringFromDate(DateUtil.getDateFromString(locationJsonObject.get("date").getAsString()));
				String locationLat = locationJsonObject.get("latitude").getAsString();
				String locationLon = locationJsonObject.get("longitude").getAsString();
				
				responseDto.setDate(locationDate);
				responseDto.setLatitude(locationLat);
				responseDto.setLongitude(locationLon);
			}

			

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

	@Override
	public String getPatientGeofences(int patientId) {
		GetPatientGeofenceResponseDto responseDto = new GetPatientGeofenceResponseDto();

		try{
			Auxiliar_data auxData = auxiliarDataDao.getPatientAuxiliarData(patientId);
			JsonParser parser = new JsonParser();
			Object obj = parser.parse(auxData.getGeofences());

			JsonArray jsonArray = (JsonArray) obj;
			
			if(jsonArray!=null){
				List<GeofenceDTO> geofencesList = new ArrayList<GeofenceDTO>();
				for(JsonElement geofenceJsonElement : jsonArray){
					GeofenceDTO geofenceDto = new GeofenceDTO();
					JsonObject geofenceJsonObj = geofenceJsonElement.getAsJsonObject();
					
					geofenceDto.setId(geofenceJsonObj.get("id").getAsString());
					geofenceDto.setRadius(geofenceJsonObj.get("radius").getAsString());
					geofenceDto.setLatitude(geofenceJsonObj.get("latitude").getAsString());
					geofenceDto.setLongitude(geofenceJsonObj.get("longitude").getAsString());
					
					geofencesList.add(geofenceDto);
				}
				responseDto.setGeofenceList(geofencesList);
			}
			
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

	@Override
	public String addPatientGeofence(int patientId, int radius, double geofenceLatitude, double geofenceLongitude) {
		AddPatientGeofenceResponseDto responseDto = new AddPatientGeofenceResponseDto();
		try{
			Auxiliar_data auxData = auxiliarDataDao.getPatientAuxiliarData(patientId);

			if(auxData!=null){
				
				JsonParser parser = new JsonParser();
				Object obj = null;
				
				if( auxData.getGeofences()!=null){
					obj = parser.parse(auxData.getGeofences());
					JsonArray jsonGeofences = (JsonArray) obj;

					JsonObject newGeofence = new JsonObject();
					
					newGeofence.addProperty("id", System.currentTimeMillis());
					newGeofence.addProperty("radius", radius);
					newGeofence.addProperty("latitude", geofenceLatitude);
					newGeofence.addProperty("longitude", geofenceLongitude);
					
					jsonGeofences.add(newGeofence);
					
					auxData.setGeofences(jsonGeofences.toString());
				}else{
					obj = new Object();
					JsonArray jsonGeofences = new JsonArray();
					JsonObject newGeofence = new JsonObject();
					
					newGeofence.addProperty("id", System.currentTimeMillis());
					newGeofence.addProperty("radius", radius);
					newGeofence.addProperty("latitude", geofenceLatitude);
					newGeofence.addProperty("longitude", geofenceLongitude);


					jsonGeofences.add(newGeofence);
					
					auxData.setGeofences(jsonGeofences.toString());
				}

				auxiliarDataDao.update(auxData);

			}else{
				//Hem de crear un json nou
				JsonObject newGeofence = new JsonObject();
				
				newGeofence.addProperty("id", System.currentTimeMillis());
				newGeofence.addProperty("radius", radius);
				newGeofence.addProperty("latitude", geofenceLatitude);
				newGeofence.addProperty("longitude", geofenceLongitude);

				JsonArray geofenceArray = new JsonArray();
				geofenceArray.add(newGeofence);

				auxData = new Auxiliar_data();
				auxData.setPatientId(new BigDecimal(patientId));
				auxData.setGeofences(geofenceArray.toString());

				auxiliarDataDao.create(auxData);
			}

			responseDto.setCode("0");
			responseDto.setMessage("addPatientGeofence OK");

		}catch(Exception e){
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			log.error(sw.toString());

			responseDto.setCode("-1");
			responseDto.setMessage("addPatientGeofence Error");
		}
		return responseDto.createXMLString();
	}

	@Override
	public String updatePatientGeofence(int patientId, long geofenceId, int radius, double geofenceLatitude,double geofenceLongitude) {
		UpdateGeofenceResponseDto responseDto = new UpdateGeofenceResponseDto();
		try{
			Auxiliar_data auxData = auxiliarDataDao.getPatientAuxiliarData(patientId);

			if(auxData!=null){
				
				JsonParser parser = new JsonParser();
				Object obj = null;
				
				if(auxData.getGeofences()!=null){
					obj = parser.parse(auxData.getGeofences());
					JsonArray jsonGeofences = (JsonArray) obj;

					for(JsonElement geofenceJsonElement : jsonGeofences){
						JsonObject geofenceJsonObj = geofenceJsonElement.getAsJsonObject();
						
						if(geofenceJsonObj.get("id").getAsLong() == geofenceId){
							geofenceJsonObj.addProperty("radius", radius);
							geofenceJsonObj.addProperty("latitude", geofenceLatitude);
							geofenceJsonObj.addProperty("longitude", geofenceLongitude);
						}
					}
					auxData.setGeofences(jsonGeofences.toString());
				}
				auxiliarDataDao.update(auxData);
			}

			responseDto.setCode("0");
			responseDto.setMessage("updatePatientGeofence OK");

		}catch(Exception e){
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			log.error(sw.toString());

			responseDto.setCode("-1");
			responseDto.setMessage("updatePatientGeofence Error");
		}
		return responseDto.createXMLString();
	}

	@Override
	public String deletePatientGeofence(int patientId, long geofenceId) {
		DeleteGeofenceResponseDto responseDto = new DeleteGeofenceResponseDto();
		try{
			Auxiliar_data auxData = auxiliarDataDao.getPatientAuxiliarData(patientId);

			if(auxData!=null){
				
				JsonParser parser = new JsonParser();
				Object obj = null;
				
				if(auxData.getGeofences()!=null){
					obj = parser.parse(auxData.getGeofences());
					JsonArray jsonGeofences = (JsonArray) obj;

					for(JsonElement geofenceJsonElement : jsonGeofences){
						JsonObject geofenceJsonObj = geofenceJsonElement.getAsJsonObject();
						
						if(geofenceJsonObj.get("id").getAsLong() == geofenceId){
							jsonGeofences.remove(geofenceJsonElement);
						}
					}
					auxData.setGeofences(jsonGeofences.toString());
				}
				auxiliarDataDao.update(auxData);
			}

			responseDto.setCode("0");
			responseDto.setMessage("deletePatientGeofence OK");

		}catch(Exception e){
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			log.error(sw.toString());

			responseDto.setCode("-1");
			responseDto.setMessage("deletePatientGeofence Error");
		}
		return responseDto.createXMLString();
	}
}
