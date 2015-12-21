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
import cat.ehh.web.model.Responsible;
import cat.ehh.web.util.XMLUtil;

public class GetPatientResponsiblesResponseDto extends ResponseDTO{

	Logger log = LoggerFactory.getLogger(GetPatientResponsiblesResponseDto.class);

	List<Responsible> patientResponsibles;

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



	public List<Responsible> getPatientResponsibles() {
		return patientResponsibles;
	}
	public void setPatientResponsibles(List<Responsible> patientResponsibles) {
		this.patientResponsibles = patientResponsibles;
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
			Element rootElement = doc.createElement("getPatientResponsiblesPatientResponse");
			doc.appendChild(rootElement);

			// code element
			Element code = doc.createElement("code");
			code.appendChild(doc.createTextNode(this.getCode()));
			rootElement.appendChild(code);

			Element message = doc.createElement("message");
			message.appendChild(doc.createTextNode(this.getMessage()));
			rootElement.appendChild(message);

			if(this.getPatientResponsibles()!=null){
				// responsible elements
				
				Element responsableElements = doc.createElement("responsables");
				
				for(Responsible resp : this.getPatientResponsibles()){
					Element responsible = doc.createElement("responsible");
					
					Element responsibleId = doc.createElement("responsibleId");
					responsibleId.appendChild(doc.createTextNode(String.valueOf(resp.getResponsibleId())));
					responsible.appendChild(responsibleId);
					
					Element responsibleName = doc.createElement("responsibleName");
					responsibleName.appendChild(doc.createTextNode(String.valueOf(resp.getUser().getName())));
					responsible.appendChild(responsibleName);

					responsableElements.appendChild(responsible);
				}
				rootElement.appendChild(responsableElements);
			}


		} catch (ParserConfigurationException e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			log.error(sw.toString());
		}


		return doc;

	}
}
