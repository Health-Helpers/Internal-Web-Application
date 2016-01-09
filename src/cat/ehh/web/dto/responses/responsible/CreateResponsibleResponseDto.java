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
import cat.ehh.web.util.DateUtil;
import cat.ehh.web.util.XMLUtil;

public class CreateResponsibleResponseDto extends ResponseDTO{

	Logger log = LoggerFactory.getLogger(CreateResponsibleResponseDto.class);

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
			Element rootElement = doc.createElement("createResponsibleResponse");
			doc.appendChild(rootElement);

			// code element
			Element code = doc.createElement("code");
			code.appendChild(doc.createTextNode(this.getCode()));
			rootElement.appendChild(code);

			Element message = doc.createElement("message");
			message.appendChild(doc.createTextNode(this.getMessage()));
			rootElement.appendChild(message);

			if(this.getResponsible()!=null){
				Element responsible = doc.createElement("responsible");

				// responsible elements
				Element responsibleId = doc.createElement("responsibleId");
				responsibleId.appendChild(doc.createTextNode(String.valueOf(this.getResponsible().getResponsibleId())));
				responsible.appendChild(responsibleId);

				Element responsibleDoc = doc.createElement("idDoc");
				responsibleDoc.appendChild(doc.createTextNode(String.valueOf(this.getResponsible().getUser().getIddoc())));
				responsible.appendChild(responsibleDoc);

				Element responsibleName = doc.createElement("name");
				responsibleName.appendChild(doc.createTextNode(String.valueOf(this.getResponsible().getUser().getName())));
				responsible.appendChild(responsibleName);

				Element responsibleSurname = doc.createElement("surname");
				responsibleSurname.appendChild(doc.createTextNode(String.valueOf(this.getResponsible().getUser().getSurname())));
				responsible.appendChild(responsibleSurname);

				if(this.getResponsible().getUser().getBirthdate()!=null){
					Element responsibleBirth = doc.createElement("birthdate");
					responsibleBirth.appendChild(doc.createTextNode(DateUtil.getStringFromDate(this.getResponsible().getUser().getBirthdate())));
					responsible.appendChild(responsibleBirth);
				}

				Element responsibleAddress = doc.createElement("address");
				responsibleAddress.appendChild(doc.createTextNode(String.valueOf(this.getResponsible().getUser().getAdress())));
				responsible.appendChild(responsibleAddress);

				Element responsiblePhone = doc.createElement("phone");
				responsiblePhone.appendChild(doc.createTextNode(String.valueOf(this.getResponsible().getUser().getPhone())));
				responsible.appendChild(responsiblePhone);

				rootElement.appendChild(responsible);
			}


		} catch (ParserConfigurationException e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			log.error(sw.toString());
		}


		return doc;

	}
}
