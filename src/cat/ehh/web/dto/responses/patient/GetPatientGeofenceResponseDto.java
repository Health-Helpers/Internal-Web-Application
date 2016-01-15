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

import cat.ehh.web.dto.GeofenceDTO;
import cat.ehh.web.dto.responses.ResponseDTO;
import cat.ehh.web.util.XMLUtil;

public class GetPatientGeofenceResponseDto extends ResponseDTO{

	Logger log = LoggerFactory.getLogger(GetPatientLocationResponseDto.class);

	List<GeofenceDTO> geofenceList;


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
	

	public List<GeofenceDTO> getGeofenceList() {
		return geofenceList;
	}
	public void setGeofenceList(List<GeofenceDTO> geofenceList) {
		this.geofenceList = geofenceList;
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
			Element rootElement = doc.createElement("getPatientGeofenceResponse");
			doc.appendChild(rootElement);

			// code element
			Element code = doc.createElement("code");
			code.appendChild(doc.createTextNode(this.getCode()));
			rootElement.appendChild(code);

			Element message = doc.createElement("message");
			message.appendChild(doc.createTextNode(this.getMessage()));
			rootElement.appendChild(message);

			Element geofences = doc.createElement("geofences");

			if(this.getGeofenceList()!=null){
				//geofence Elements
				for(GeofenceDTO geofence : this.getGeofenceList()){
					Element geofenceElement = doc.createElement("latitude");
					
					Element idElement = doc.createElement("id");
					if(geofence.getId()!=null){
						idElement.appendChild(doc.createTextNode(String.valueOf(geofence.getId())));
					}
					geofenceElement.appendChild(idElement);
					
					Element radiusElement = doc.createElement("radius");
					if(geofence.getRadius()!=null){
						radiusElement.appendChild(doc.createTextNode(String.valueOf(geofence.getRadius())));
					}
					geofenceElement.appendChild(radiusElement);
					
					Element latitudeElement = doc.createElement("latitude");
					if(geofence.getLatitude()!=null){
						radiusElement.appendChild(doc.createTextNode(String.valueOf(geofence.getLatitude())));
					}
					geofenceElement.appendChild(latitudeElement);
					
					Element longitudeElement = doc.createElement("longitude");
					if(geofence.getLongitude()!=null){
						longitudeElement.appendChild(doc.createTextNode(String.valueOf(geofence.getLongitude())));
					}
					geofenceElement.appendChild(longitudeElement);
					
					geofences.appendChild(geofenceElement);
				}
			}
			rootElement.appendChild(geofences);

		} catch (ParserConfigurationException e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			log.error(sw.toString());
		}


		return doc;

	}
}
