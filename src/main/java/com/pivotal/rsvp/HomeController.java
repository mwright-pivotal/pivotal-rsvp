package com.pivotal.rsvp;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.github.sendgrid.SendGrid;
import com.pivotal.rsvp.model.Registrant;
import com.pivotal.rsvp.model.RegistrantRepository;

/**
 * Handles requests for the application home page.
 */
@Controller
@EnableAutoConfiguration
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private static String envJSON = null;
	private static String servicesJSON = null;
	private static JsonNode rootEnvNode = null;
	private static JsonNode rootServicesNode = null;

	@Autowired
	private CrudRepository<Registrant, Long> registrantRepository;

	@Value("${invite.eventId:test}")
	private String assignedEventId = "0000";

	@Value("${invite.adminEmail:test}")
	private String adminEmail = "mwright@pivotal.io";
//	@Autowired
//    private RegistrantRepository personRepository;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
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
	
	@Transactional
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String register(@ModelAttribute("Registrant") Registrant registrant ,Locale locale, Model model) {
		
		if (this.getEnv()!=null) {
			model.addAttribute("instance_id",this.getEnv().path("instance_id").getTextValue());
			model.addAttribute("instance_index",this.getEnv().path("instance_index").getBigIntegerValue());
			//model.addAttribute("application_uris",this.getEnv().path("application_uris").getElements().next().asText());
		}
		
		this.alertRegistration(registrant);
		this.registrantRepository.save(registrant);
		//personRepository.save(registrant);
		
		return "thanks";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String register(Locale locale, Map<String, Object> model, @RequestHeader(name="host") String hostStr) {
		String eventId=assignedEventId;
		if (assignedEventId.equals("0")) {
			String[] parts = hostStr.split("[.]");
			eventId = parts[0];
			logger.info("Using host name for the event ID: " + eventId);
		}
		model.put("eventId", eventId);
		model.put("eventDescription", eventId+".html");
		return "invite";
	}
	
	private JsonNode getEnv() {
		if (envJSON==null) {
			envJSON = System.getenv("VCAP_APPLICATION");
			if (envJSON==null)
					return null;
			
			ObjectMapper mapper = new ObjectMapper();
		
		 	try {
				rootEnvNode = mapper.readTree(envJSON);
				logger.info("ENV: " + rootEnvNode);
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
	
	private JsonNode getCloudServicesInfo() {
		if (servicesJSON==null) {
			servicesJSON = System.getenv("VCAP_SERVICES");
			if (servicesJSON==null)
					return null;
			
			ObjectMapper mapper = new ObjectMapper();
		
		 	try {
				rootServicesNode = mapper.readTree(servicesJSON);
				logger.info("SERVICES: " + rootServicesNode);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return rootServicesNode;
	}
	
	private void alertRegistration (Registrant registrant) {
		if (this.getCloudServicesInfo().path("sendgrid")==null || this.getCloudServicesInfo().path("sendgrid").size()==0) {
			logger.info("Sendgrid service binding not found, email not sent.");
			return;
		}
		String usr = this.getCloudServicesInfo().path("sendgrid").get(0).getPath("credentials").getPath("username").getTextValue();
		String pswd = this.getCloudServicesInfo().path("sendgrid").get(0).getPath("credentials").getPath("password").getTextValue();
		SendGrid sendgrid = new SendGrid(usr, pswd);
		
		sendgrid.addTo(this.adminEmail);
		sendgrid.setFrom(registrant.getEmailAddr());
		sendgrid.setSubject("Workshop registration received!");
		sendgrid.setText("Name: " + registrant.getName() + "\n"
				+ "Email Addr: " + registrant.getEmailAddr() + "\n"
				+ "Phone Nbr: " + registrant.getPhoneNbr() + "\n"
				+ "Event ID: " + registrant.getEventID());

		sendgrid.send();
	}
}
