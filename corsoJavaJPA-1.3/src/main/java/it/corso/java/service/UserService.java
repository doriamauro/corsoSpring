package it.corso.java.service;

import it.corso.java.entity.User;

public interface UserService {

	public User leggiDatiUtente(Integer userId);
	public void registraUtente(User u);
	public String test(); 
}
