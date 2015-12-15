package cat.ehh.web.util;

import java.io.StringWriter;
import java.io.Writer;

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;

public class XMLUtil {

	public static String fromXMLToString(Document xmlDOc){
		String outputString = "";
		try{
			
			OutputFormat format = new OutputFormat(xmlDOc);
            format.setLineWidth(65);
            format.setIndenting(true);
            format.setIndent(2);
            Writer out = new StringWriter();
            XMLSerializer serializer = new XMLSerializer(out, format);
            serializer.serialize(xmlDOc);

            outputString =  out.toString();
		}catch (Exception e){
			e.printStackTrace();
		}
		return outputString;
	}
}
