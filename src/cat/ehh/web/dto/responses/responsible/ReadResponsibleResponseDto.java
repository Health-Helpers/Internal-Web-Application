package cat.ehh.web.dto.responses.responsible;

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
import cat.ehh.web.model.Responsible;
import cat.ehh.web.util.XMLUtil;

public class ReadResponsibleResponseDto extends ResponseDTO{

	Logger log = LoggerFactory.getLogger(ReadResponsibleResponseDto.class);
	
	Responsible responsible;

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
	

	public Responsible getResponsible() {
		return responsible;
	}
	public void setResponsible(Responsible responsible) {
		this.responsible = responsible;
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

			if(this.getResponsible()!=null){
				Element patient = doc.createElement("responsible");

				// patient elements
				Element patientId = doc.createElement("responsibleId");
				patientId.appendChild(doc.createTextNode(String.valueOf(this.getResponsible().getResponsibleId())));
				patient.appendChild(patientId);

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
