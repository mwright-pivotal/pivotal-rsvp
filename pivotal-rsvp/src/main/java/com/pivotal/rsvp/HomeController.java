package com.pivotal.rsvp;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pivotal.rsvp.model.Registrant;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private static String envJSON = null;
	private static JsonNode rootEnvNode = null;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(@RequestParam(value="eventID", defaultValue="0000") String eventID ,Locale locale, Model model) {
		String msg = "Event id is " + eventID;
		
		logger.info("Thank you for registering for the event.  Please provide your contact information below. The client locale is {}.", locale);
		model.addAttribute("message", msg);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("eventID", eventID);
		
		Registrant registrant = new Registrant();
		registrant.setEventID(eventID);
		model.addAttribute("registrant", registrant);
		
		return "register";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String register(@ModelAttribute("Registrant") Registrant registrant ,Locale locale, Model model) {
		
		if (this.getEnv()!=null) {
			model.addAttribute("instance_id",this.getEnv().path("instance_id").getTextValue());
			model.addAttribute("instance_index",this.getEnv().path("instance_index").getBigIntegerValue());
			//model.addAttribute("application_uris",this.getEnv().path("application_uris").getElements().next().asText());
		}
		return "thanks";
	}
	
	private JsonNode getEnv() {
		if (envJSON==null) {
			envJSON = System.getenv("VCAP_APPLICATION");
			if (envJSON==null)
					return null;
			
			ObjectMapper mapper = new ObjectMapper();
		
		 	try {
				rootEnvNode = mapper.readTree(envJSON);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return rootEnvNode;
	}
}
