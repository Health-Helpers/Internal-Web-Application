package cat.ehh.ws;


public interface PatientWS {

	public String createPatient(String name, String surname,String idDoc, String phone, String birthdate, String adress,String type,String disease, String dependencyGrade,String langId);
}
