package it.corso.java.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile("test")
public class TestServiceController {
	
	@RequestMapping(path="/echo")
	public String testEcho(String msg) {
		return "echo " + msg;
	}

}
