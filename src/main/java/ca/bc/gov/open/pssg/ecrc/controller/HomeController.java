package ca.bc.gov.open.pssg.ecrc.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 
 * @author shaunmillargov
 *
 */
@Controller
public class HomeController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@GetMapping("/")
	public String home(HttpServletRequest request) {
		request.setAttribute("pageTitle", "test");
		log.info("You've reached the Home Controller");
		return "home";
	}

}
