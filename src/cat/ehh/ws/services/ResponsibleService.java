package cat.ehh.ws.services;

public interface ResponsibleService {


	public String createResponsible(String name, String surname,String idDoc, String phone, String birthdate, String adress,String langId);
	public String updateResponsible(int responsibleId,String name, String surname,String idDoc, String phone, String birthdate, String adress,String langId);
	public String readResponsible(int responsibleId);
	public String deleteResponsible(int responsibleId);
	
	

	public String addPatientToResponsible(int responsibleId,int patientId);
	public String deletePatientFromResponsible(int responsibleId,int patientId);
	public String getResponsiblePatients(int responsibleId);
	//TODO: Define more methods, i.e. addPatient, removePatient,getPatients,...
}
