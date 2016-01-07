package cat.ehh.ws.services;


public interface UserService {

	public String createUser(String name, String surname,String idDoc, String phone, String birthdate, String adress,String type,String langId);
	public String updateUser(String name, String surname,String idDoc, String phone, String birthdate, String adress,String type,String langId);
	public String readUser(int userId);
	public String deleteUser(int userId);
	public String registerUser(String idDoc, String phone,String parseInstallationId);
	
	//TODO: Define more methods
}
