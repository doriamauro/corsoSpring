package it.corso.java.dao;

import it.corso.java.entity.User;

public interface UserDAO {
	
	public User insert(User u);
	public User select(int idUtente);

}
