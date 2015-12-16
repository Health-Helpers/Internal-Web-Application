package cat.ehh.ws;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping( value = "/RestWSController" )
public class RestWSController{

	@RequestMapping( value = "/sayHello", method = RequestMethod.GET )
	@ResponseBody
	public String sayHello( ){
		return "Hello Guy!";
	}
}
