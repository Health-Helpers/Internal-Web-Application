package cat.ehh.web.dto.responses;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

public abstract class ResponseDTO {

	public abstract String createXMLString();
	public abstract Document createXML();
}
