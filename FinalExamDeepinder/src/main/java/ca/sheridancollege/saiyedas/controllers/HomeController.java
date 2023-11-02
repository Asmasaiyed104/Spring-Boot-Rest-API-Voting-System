package ca.sheridancollege.saiyedas.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import ca.sheridancollege.saiyedas.beans.Capstone;

@Controller
public class HomeController {

	

	final String REST_URL = "http://localhost:8080/capstones/";

	@GetMapping("/")
	public String index(Model model, RestTemplate restTemplate) {
		ResponseEntity<Capstone[]> responseEntity = restTemplate.getForEntity(REST_URL, Capstone[].class);
		model.addAttribute("capstoneList", responseEntity.getBody());
		model.addAttribute("capstone", new Capstone());
		// model.addAttribute("capstoneList", da.getCapstoneList());

		return "index";
	}

	@GetMapping(value = "/getCapstone/{id}", produces = "application/json")
	@ResponseBody
	public Capstone getCapstone(@PathVariable int id, RestTemplate restTemplate) {
		ResponseEntity<Capstone> responseEntity = restTemplate.getForEntity(REST_URL + id, Capstone.class);
		return responseEntity.getBody();
	}

	@PostMapping("/insertCapstone")
	public String insertCapstone(Model model, @ModelAttribute Capstone capstone, RestTemplate restTemplate) {
	//	capstone.setInformation(assignFinalis(capstone.getVote()));

		restTemplate.postForLocation(REST_URL, capstone);
		ResponseEntity<Capstone[]> responseEntity = restTemplate.getForEntity(REST_URL, Capstone[].class);
		model.addAttribute("capstoneList", responseEntity.getBody());

		model.addAttribute("capstone", new Capstone());
	
		
		return "index";
	}
	/**@GetMapping("/editCapstoneById/{id}")
	public String editCapstoneById(Model model, @ModelAttribute Capstone capstone, RestTemplate restTemplate) {
    
		capstone.setInformation(assignFinalists(capstone.getVote()));

		
		ResponseEntity<Capstone[]> responseEntity = restTemplate.getForEntity(REST_URL, Capstone[].class);
		model.addAttribute("capstone", new Capstone());
		model.addAttribute("capstoneList", responseEntity.getBody());
		return "index";
	} **/

	@GetMapping("/deleteCapstoneById/{id}")
	public String deleteCapstoneById(Model model, @PathVariable Long id, RestTemplate restTemplate) {
	    restTemplate.delete(REST_URL + id);
		//capstone.setInformation(assignFinalists(capstone.getVote()));

	
		ResponseEntity<Capstone[]> responseEntity = restTemplate.getForEntity(REST_URL, Capstone[].class);
		model.addAttribute("capstone", new Capstone());
		model.addAttribute("capstoneList", responseEntity.getBody());
		return "index";
	}
	@GetMapping("/VoteUpCapstoneById/{id}")
	public String voteUpCapstoneById(Model model, @PathVariable Long id, RestTemplate restTemplate) {
	    
		ResponseEntity<Capstone> responseEntity = restTemplate.getForEntity(REST_URL + id, Capstone.class);
		Capstone capstone = responseEntity.getBody();
		capstone.setVote(capstone.getVote()+1);
		restTemplate.put(REST_URL + id, capstone);
		//capstone.setInformation(assignFinalists(capstone.getVote()));

	
		ResponseEntity<Capstone[]> responseEntity2 = restTemplate.getForEntity(REST_URL, Capstone[].class);
		model.addAttribute("capstone", new Capstone());
		model.addAttribute("capstoneList", responseEntity2.getBody());
		return "index";
	}
	
/**
	public String assignFinalists(Integer vote) {
		if (vote >= 6 && vote <= 14) {
			return "Second winner";
		} else if (vote >= 15 && vote <= 19) { 
			return "Third winner";
		} else if (vote >= 20 && vote <= 31) { 
			return "First winner";
		} else if (vote <= 5) { 
			return "try next time";
		}
		return "Sorry you loose"; 
	}
**/
}
