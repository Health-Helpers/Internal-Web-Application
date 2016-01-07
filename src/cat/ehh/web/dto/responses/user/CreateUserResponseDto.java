package cat.ehh.web.dto.responses.user;

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
import cat.ehh.web.model.UserEHH;
import cat.ehh.web.util.DateUtil;
import cat.ehh.web.util.XMLUtil;

public class CreateUserResponseDto extends ResponseDTO{

	Logger log = LoggerFactory.getLogger(CreateUserResponseDto.class);

	UserEHH user;
	
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
	public UserEHH getUser() {
		return user;
	}
	public void setUser(UserEHH user) {
		this.user = user;
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

			if(this.getUser()!=null){
				Element userElement = doc.createElement("user");

				// responsible elements
				Element responsibleId = doc.createElement("userId");
				responsibleId.appendChild(doc.createTextNode(String.valueOf(this.getUser().getUserId())));
				userElement.appendChild(responsibleId);

				Element userDoc = doc.createElement("idDoc");
				userDoc.appendChild(doc.createTextNode(String.valueOf(this.getUser().getIddoc())));
				userElement.appendChild(userDoc);
				
				Element userName = doc.createElement("name");
				userName.appendChild(doc.createTextNode(String.valueOf(this.getUser().getName())));
				userElement.appendChild(userName);
				
				Element userSurname = doc.createElement("surname");
				userSurname.appendChild(doc.createTextNode(String.valueOf(this.getUser().getSurname())));
				userElement.appendChild(userSurname);
				
				Element userBirth = doc.createElement("birthdate");
				userBirth.appendChild(doc.createTextNode(DateUtil.getStringFromDate(this.getUser().getBirthdate())));
				userElement.appendChild(userBirth);
				
				Element userAddress = doc.createElement("address");
				userAddress.appendChild(doc.createTextNode(String.valueOf(this.getUser().getAdress())));
				userElement.appendChild(userAddress);
				
				Element userPhone = doc.createElement("phone");
				userPhone.appendChild(doc.createTextNode(String.valueOf(this.getUser().getPhone())));
				userElement.appendChild(userPhone);
				
				rootElement.appendChild(userElement);
			}


		} catch (ParserConfigurationException e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			log.error(sw.toString());
		}


		return doc;

	}
}
