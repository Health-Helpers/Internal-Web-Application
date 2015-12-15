package cat.ehh.web.dto;

import org.w3c.dom.Document;

public abstract class ResponseDTO {

	public abstract String createXMLString();
	public abstract Document createXML();
}
