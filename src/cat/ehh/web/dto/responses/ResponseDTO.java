package cat.ehh.web.dto.responses;

import org.w3c.dom.Document;

public abstract class ResponseDTO {

	public abstract String createXMLString();
	public abstract Document createXML();
}
