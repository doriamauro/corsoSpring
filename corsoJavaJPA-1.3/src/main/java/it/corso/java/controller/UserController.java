package it.corso.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.corso.java.dto.UserDTO;
import it.corso.java.entity.User;
import it.corso.java.service.UserService;

@RestController // --> Component
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService serviceU;
	
	//2 tipi di metodi
	
//	//tipo 1. Servizi rest
	@PostMapping(consumes = {"application/json", "application/xml"})
	public void registraUtente(@RequestBody UserDTO u) {
		
		User ut = new User();
		ut.setUserId(u.getUserId());
		ut.setCongome(u.getCognome());
		ut.setNome(u.getNome());
		ut.setPassword(u.getPassword());
		ut.setMail(u.getMail());
		ut.setTelefonoConPrefisso(u.getTelefono());
		ut.setVersione(u.getVersione());
		serviceU.registraUtente(ut);
	}
	
//	@PutMapping(path = "/{userId}")
//	public UserDTO modificaUtente(@PathVariable Integer userId, UserDTO u) {
//		
//	}
//	
//	@PatchMapping(path = "/pw/{userId}")
//	public UserDTO modificaPassword(@PathVariable Integer userId, String oldPw, String newPw) {
//		
//	}
	
	@GetMapping(path = "/{userId}", produces = {"application/xml", "application/json"})
	public UserDTO leggiDatiUtente(@PathVariable Integer userId) {
		
		User u = serviceU.leggiDatiUtente(userId);
		
		UserDTO dtoU = new UserDTO();
		dtoU.setUserId(u.getUserId());
		dtoU.setNome(u.getNome());
		dtoU.setCognome(u.getCongome());
		dtoU.setPassword(u.getPassword());
		dtoU.setMail(u.getMail());
		dtoU.setTelefono(u.getTelefonoConPrefisso());
		dtoU.setVersione(u.getVersione());
		return dtoU;
	}
	
	@GetMapping(path = "/cognome/{userId}")
	public String getNomeUtente(@PathVariable Integer userId) {
		return "Mario";
	}
	
	@GetMapping("/testQuery")
	public String testSelect() {
		return serviceU.test();
		
	}
	
	@DeleteMapping(path = "/{userId}")
	public UserDTO cancellaDatiUtente(@PathVariable Integer userId) {
		//ritorneremo i dati dell'utente con userId
		UserDTO dtoU = new UserDTO();
		dtoU.setUserId(userId);
		dtoU.setNome("DEMO");
		dtoU.setCognome("DEMO");
		dtoU.setVersione(0);
		return dtoU;
	}
	
	
//	//tipo 2. metodi di gestione degli errori centralizzati (ExceptionHandler)
//	@ExceptionHandler
//	public ResponseEntity<T> exceptionHandler(){
//		
//	}

}
