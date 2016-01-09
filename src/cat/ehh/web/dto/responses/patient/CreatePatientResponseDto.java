package cat.ehh.web.dto.responses.patient;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import cat.ehh.web.dto.responses.ResponseDTO;
import cat.ehh.web.model.Patient;
import cat.ehh.web.util.DateUtil;
import cat.ehh.web.util.XMLUtil;

public class CreatePatientResponseDto extends ResponseDTO{

	Logger log = LoggerFactory.getLogger(CreatePatientResponseDto.class);
	
	Patient patient;

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Override
	public String createXMLString(){
		return StringEscapeUtils.unescapeXml(XMLUtil.fromXMLToString(this.createXML()));
	}

	@Override
	public Document createXML(){
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder;
		Document doc = null;
		try {
			docBuilder = docFactory.newDocumentBuilder();


			// root elements
			doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("createPatientResponse");
			doc.appendChild(rootElement);

			// code element
			Element code = doc.createElement("code");
			code.appendChild(doc.createTextNode(this.getCode()));
			rootElement.appendChild(code);

			Element message = doc.createElement("message");
			message.appendChild(doc.createTextNode(this.getMessage()));
			rootElement.appendChild(message);

			if(this.getPatient()!=null){
				Element patient = doc.createElement("patient");

				// patient elements
				Element patientId = doc.createElement("patientId");
				patientId.appendChild(doc.createTextNode(String.valueOf(this.getPatient().getPatientId())));
				patient.appendChild(patientId);
				
				Element patientDoc = doc.createElement("idDoc");
				patientDoc.appendChild(doc.createTextNode(String.valueOf(this.getPatient().getUser().getIddoc())));
				patient.appendChild(patientDoc);
				
				Element patientName = doc.createElement("name");
				patientName.appendChild(doc.createTextNode(String.valueOf(this.getPatient().getUser().getName())));
				patient.appendChild(patientName);
				
				Element patientSurname = doc.createElement("surname");
				patientSurname.appendChild(doc.createTextNode(String.valueOf(this.getPatient().getUser().getSurname())));
				patient.appendChild(patientSurname);
				
				if(this.getPatient().getUser().getBirthdate()!=null){
					Element patientBirth = doc.createElement("birthdate");
					patientBirth.appendChild(doc.createTextNode(DateUtil.getStringFromDate(this.getPatient().getUser().getBirthdate())));
					patient.appendChild(patientBirth);
				}
				
				Element patientAddress = doc.createElement("address");
				patientAddress.appendChild(doc.createTextNode(String.valueOf(this.getPatient().getUser().getAdress())));
				patient.appendChild(patientAddress);
				
				Element patientPhone = doc.createElement("phone");
				patientPhone.appendChild(doc.createTextNode(String.valueOf(this.getPatient().getUser().getPhone())));
				patient.appendChild(patientPhone);
				
				
				Element disease = doc.createElement("disease");
				disease.appendChild(doc.createTextNode(String.valueOf(this.getPatient().getDisease())));
				patient.appendChild(disease);
				
				Element depGrade = doc.createElement("depGrade");
				depGrade.appendChild(doc.createTextNode(String.valueOf(this.getPatient().getDependencyGrade())));
				patient.appendChild(depGrade);

				rootElement.appendChild(patient);
			}


		} catch (ParserConfigurationException e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			log.error(sw.toString());
		}


		return doc;

	}
}
