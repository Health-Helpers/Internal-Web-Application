package cat.ehh.ws.services;

public interface ResponsibleService {


	public String createResponsible(String name, String surname,String idDoc, String phone, String birthdate, String adress,String langId);
	public String updateResponsible(String name, String surname,String idDoc, String phone, String birthdate, String adress,String langId);
	public String readResponsible(int responsibleId);
	public String deleteResponsible(int responsibleId);
}
