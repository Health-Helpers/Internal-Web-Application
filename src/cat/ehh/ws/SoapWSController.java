package cat.ehh.ws;

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
	
	@WebMethod
	public String createPatient(String name, String surname,String idDoc, String phone, String birthdate, String adress,String disease, String dependencyGrade,String langId){
		return patientService.createPatient(name, surname, idDoc, phone, birthdate, adress, disease, dependencyGrade, langId);
	}
	
	@WebMethod
	public String updatePatient(int patientId,String name, String surname,String idDoc, String phone, String birthdate, String adress,String disease, String dependencyGrade,String langId){
		return patientService.updatePatient(patientId,name, surname, idDoc, phone, birthdate, adress, disease, dependencyGrade, langId);
	}
	
	@WebMethod
	public String readPatient(int patientId){
		return patientService.readPatient(patientId);
	}
	
	@WebMethod
	public String deletePatient(int patientId){
		return patientService.deletePatient(patientId);
	}
	
}
