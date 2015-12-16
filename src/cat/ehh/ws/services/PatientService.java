package cat.ehh.ws.services;


public interface PatientService {

	public String createPatient(String name, String surname,String idDoc, String phone, String birthdate, String adress,String disease, String dependencyGrade,String langId);
	public String updatePatient(String name, String surname,String idDoc, String phone, String birthdate, String adress,String disease, String dependencyGrade,String langId);
	public String readPatient(int patientId);
	public String deletePatient(int patientId);
	
	//TODO: Define more methods, i.e. getAdjustements, getAuxiliarData,...
}
