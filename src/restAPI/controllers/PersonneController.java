package restAPI.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import restAPI.domain.Contracts;
import restAPI.domain.Suricats;

@RestController
@RequestMapping("/insurance/personne")
public class PersonneController {

	@RequestMapping(value = {"", "/"})
    public String index() {
		return "What else ??";
    }
	
	@RequestMapping(value="ID*")
    public ResponseEntity<Contracts> getContract(HttpServletRequest request) {
		 String id = request.getRequestURI().split("/")[3];
		 return new ResponseEntity<Contracts>(new Contracts(id), HttpStatus.OK);
    }
	
	@RequestMapping(value="ID*/risk")
    public ResponseEntity<Contracts> getCouverture(HttpServletRequest request) {
		 String id = request.getRequestURI().split("/")[3];
		 System.out.println("dans getCouverture");
		 System.out.println(id);
		 return new ResponseEntity<Contracts>(new Contracts(id), HttpStatus.OK);
    }
	
	
    @RequestMapping("object")
    public ResponseEntity<Suricats> getSuricats(@RequestParam(value="name", defaultValue="Suricats Consulting") String name,
    											@RequestParam(value="location", defaultValue="29 rue Buffon") String location) {
        return new ResponseEntity<Suricats>(new Suricats(name, location), HttpStatus.OK);
    }
    
    @RequestMapping("json")
    public ResponseEntity<String> getJSonSuricats(@RequestParam(value="name", defaultValue="Suricats Consulting") String name,
    														  @RequestParam(value="location", defaultValue="29 rue Buffon") String location) {
    	Suricats suri = new Suricats(name, location);
		ObjectMapper mapper = new ObjectMapper();
		String jsonSuri;
		try {
			jsonSuri = mapper.writeValueAsString(suri);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<String>(jsonSuri, HttpStatus.OK);
    }
    
}