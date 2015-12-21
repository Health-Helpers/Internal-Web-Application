package cat.ehh.ws.services;


public interface PatientService {

	public String createPatient(String name, String surname,String idDoc, String phone, String birthdate, String adress,String disease, String dependencyGrade,String langId);
	public String updatePatient(int patientId,String name, String surname,String idDoc, String phone, String birthdate, String adress,String disease, String dependencyGrade,String langId);
	public String readPatient(int patientId);
	public String deletePatient(int patientId);
	
	
	public String addResponsibleToPatient(int patientId,int responsibleId);
	public String deleteResponsibleFromPatient(int patientId,int responsibleId);
	public String getPatientResponsibles(int patientId);
	//TODO: Define more methods, i.e. getAdjustements, getAuxiliarData,...
	
	public String sendPatientLocation(int patientId, String date, float latitude, float longitude);
	public String getPatientLocation(int patientId);
}
