package cat.ehh.web.dto.responses.patient;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

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
import cat.ehh.web.util.XMLUtil;

public class GetResponsiblePatientsResponseDto extends ResponseDTO{

	Logger log = LoggerFactory.getLogger(GetResponsiblePatientsResponseDto.class);

	List<Patient> responsiblePatients;

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



	public List<Patient> getResponsiblePatients() {
		return responsiblePatients;
	}
	public void setResponsiblePatients(List<Patient> responsiblePatients) {
		this.responsiblePatients = responsiblePatients;
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

			if(this.getResponsiblePatients()!=null){
				// responsible elements
				for(Patient patient : this.getResponsiblePatients()){
					Element patientElement = doc.createElement("patient");
					
					Element responsibleId = doc.createElement("patientId");
					responsibleId.appendChild(doc.createTextNode(String.valueOf(patient.getPatientId())));
					patientElement.appendChild(responsibleId);
					
					Element responsibleName = doc.createElement("patientName");
					responsibleName.appendChild(doc.createTextNode(String.valueOf(patient.getUser().getName())));
					patientElement.appendChild(responsibleName);

					rootElement.appendChild(patientElement);
				}
			}


		} catch (ParserConfigurationException e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			log.error(sw.toString());
		}


		return doc;

	}
}
