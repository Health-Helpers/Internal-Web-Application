package cat.ehh.ws.impl;

import javax.jws.WebMethod;
import javax.jws.WebService;

import cat.ehh.ws.PatientWS;
import cat.ehh.ws.ResponsibleWS;

@WebService
public class EHHWSImpl implements PatientWS, ResponsibleWS {

	@WebMethod
	@Override
	public String createPatient() {
		// TODO Auto-generated method stub
		return null;
	}

}
