package cat.ehh.ws;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.ws.rs.PathParam;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Controller
@RequestMapping( value = "/RestWSController" )
public class RestWSController{

	Logger log = LoggerFactory.getLogger(RestWSController.class);


	@RequestMapping( value = "/getMedicalCentersList", method = RequestMethod.GET )
	@ResponseBody
	public String getMedicalCenters( ){

		InputStream jsonStream = this.getClass().getClassLoader().getResourceAsStream("META-INF/medicalCenters.json");
		StringWriter writer = new StringWriter();
		String jsonString = "";
		try {
			IOUtils.copy(jsonStream, writer, "UTF-8");

			jsonString = writer.toString();

		} catch (IOException e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			log.error(sw.toString());
		}
		return jsonString;
	}

	@RequestMapping( value = "/getMedicalCenter/{medicalCenterId}", method = RequestMethod.GET )
	@ResponseBody
	public String getMedicalCenter(@PathVariable String medicalCenterId ){

		InputStream jsonStream = this.getClass().getClassLoader().getResourceAsStream("META-INF/medicalCenters.json");
		StringWriter writer = new StringWriter();
		String jsonString = "";
		String returnJsonString  = "";
		try {
			IOUtils.copy(jsonStream, writer, "UTF-8");
			JsonElement foundElement = null;
			jsonString = writer.toString();

			JsonParser parser = new JsonParser();
			Object obj = parser.parse(jsonString);

			JsonObject jsonObject = (JsonObject) obj;

			JsonArray jarray = jsonObject.getAsJsonArray("medicalCenters");

			for(JsonElement medicalCenter :jarray){
				JsonObject medicalCenterJsonObject = (JsonObject) medicalCenter;
				String id = medicalCenterJsonObject.get("id").getAsString();
				if(id.equals(medicalCenterId)){
					foundElement =medicalCenter; 
					break;
				}
			}
			
			if(foundElement!=null){
				returnJsonString = foundElement.toString();
			}

		} catch (IOException e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			log.error(sw.toString());
		}
		return returnJsonString;
	}


}
