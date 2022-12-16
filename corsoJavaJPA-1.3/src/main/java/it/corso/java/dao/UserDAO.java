package it.corso.java.dao;

import java.util.List;

import it.corso.java.entity.User;

public interface UserDAO {
	
	public User insert(User u);
	public User select(int idUtente);
	public String selectAll();
	
}
