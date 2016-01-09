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
import cat.ehh.web.util.XMLUtil;

public class GetPatientLocationResponseDto  extends ResponseDTO{

	Logger log = LoggerFactory.getLogger(GetPatientLocationResponseDto.class);

	private String latitude;
	private String longitude;
	private String date;


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

	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
			Element rootElement = doc.createElement("getPatientLocationResponse");
			doc.appendChild(rootElement);

			// code element
			Element code = doc.createElement("code");
			code.appendChild(doc.createTextNode(this.getCode()));
			rootElement.appendChild(code);

			Element message = doc.createElement("message");
			message.appendChild(doc.createTextNode(this.getMessage()));
			rootElement.appendChild(message);

			Element location = doc.createElement("location");

			// patient elements
			Element dateElement = doc.createElement("date");
			if(this.getDate()!=null){
				dateElement.appendChild(doc.createTextNode(String.valueOf(this.getDate())));
			}
			location.appendChild(dateElement);

			Element latitudeElement = doc.createElement("latitude");
			if(this.getLatitude()!=null){
				latitudeElement.appendChild(doc.createTextNode(String.valueOf(this.getLatitude())));
			}
			location.appendChild(latitudeElement);

			Element longitudeElement = doc.createElement("longitude");
			if(this.getLongitude()!=null){
				longitudeElement.appendChild(doc.createTextNode(String.valueOf(this.getLongitude())));
			}
			location.appendChild(longitudeElement);

			rootElement.appendChild(location);

		} catch (ParserConfigurationException e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			log.error(sw.toString());
		}


		return doc;

	}
}
