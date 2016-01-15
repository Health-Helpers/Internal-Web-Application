package cat.ehh.ws;

import javax.annotation.PostConstruct;
import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import cat.ehh.ws.services.PatientService;
import cat.ehh.ws.services.ResponsibleService;
import cat.ehh.ws.services.UserService;

@WebService
public class SoapWSController extends SpringBeanAutowiringSupport{

	@Autowired
	PatientService patientService;
	
	@Autowired
	ResponsibleService responsibleService;
	
	@Autowired
	UserService userService;
	
	@PostConstruct
	public void init() {
	    SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	@WebMethod
	public String registerUser(String idDoc, String phone,String parseInstallationId){
		return userService.registerUser(idDoc, phone,parseInstallationId);
	}
	
	/************************************Patient***********************************************/
	@WebMethod
	public String createPatient(String name, String surname,String idDoc, String phone, String birthdate, String adress,String disease, String dependencyGrade,int responsibleId){
		return patientService.createPatient(name, surname, idDoc, phone, birthdate, adress, disease, dependencyGrade, responsibleId);
	}
	
	@WebMethod
	public String updatePatient(int patientId,String name, String surname,String idDoc, String phone, String birthdate, String adress,String disease, String dependencyGrade,int responsibleId){
		return patientService.updatePatient(patientId,name, surname, idDoc, phone, birthdate, adress, disease, dependencyGrade, responsibleId);
	}
	
	@WebMethod
	public String readPatient(int patientId){
		return patientService.readPatient(patientId);
	}
	
	@WebMethod
	public String deletePatient(int patientId){
		return patientService.deletePatient(patientId);
	}
	
	@WebMethod
	public String addResponsibleToPatient(int patientId,int responsibleId){
		return patientService.addResponsibleToPatient(patientId, responsibleId);
	}
	
	@WebMethod
	public String deleteResponsibleFromPatient(int patientId,int responsibleId){
		return patientService.deleteResponsibleFromPatient(patientId, responsibleId);
	}
	
	@WebMethod
	public String getPatientResponsibles(int patientId){
		return patientService.getPatientResponsibles(patientId);
	}
	
	@WebMethod
	public String sendPatientLocation(int patientId,String locationDate, float latitude, float longitude){
		return patientService.sendPatientLocation(patientId,locationDate,latitude,longitude);
	}
	
	@WebMethod
	public String getPatientLocation(int patientId){
		return patientService.getPatientLocation(patientId);
	}
	
	@WebMethod
	public String getPatientGeofences(int patientId){
		return patientService.getPatientGeofences(patientId);
	}
	
	@WebMethod
	public String addPatientGeofence(int patientId,int radius, double geofenceLatitude,double geofenceLongitude){
		return patientService.addPatientGeofence(patientId,radius,geofenceLatitude,geofenceLongitude);
	}
	
	@WebMethod
	public String updatePatientGeofence(int patientId,long geofenceId,int radius, double geofenceLatitude,double geofenceLongitude){
		return patientService.updatePatientGeofence(patientId,geofenceId,radius,geofenceLatitude,geofenceLongitude);
	}
	
	@WebMethod
	public String deletePatientGeofence(int patientId,long geofenceId){
		return patientService.deletePatientGeofence(patientId,geofenceId);
	}
	
	/************************************Responsible***********************************************/
	@WebMethod
	public String createResponsible(String name, String surname,String idDoc, String phone, String birthdate, String adress, String langId){
		return responsibleService.createResponsible(name, surname, idDoc, phone, birthdate, adress, langId);
	}
	
	@WebMethod
	public String updateResponsible(int responsibleId,String name, String surname,String idDoc, String phone, String birthdate, String adress,String langId){
		return responsibleService.updateResponsible(responsibleId, name, surname, idDoc, phone, birthdate, adress, langId);
	}
	
	@WebMethod
	public String readResponsible(int responsibleId){
		return responsibleService.readResponsible(responsibleId);
	}
	
	@WebMethod
	public String deleteResponsible(int responsibleId){
		return responsibleService.deleteResponsible(responsibleId);
	}
	
	@WebMethod
	public String addPatientToResponsible(int patientId,int responsibleId){
		return responsibleService.addPatientToResponsible(responsibleId, patientId);
	}
	
	@WebMethod
	public String deletePatientFromResponsible(int patientId,int responsibleId){
		return responsibleService.deletePatientFromResponsible(patientId, responsibleId);
	}
	
	@WebMethod
	public String getResponsiblePatients(int responsibleId){
		return responsibleService.getResponsiblePatients(responsibleId);
	}
}
