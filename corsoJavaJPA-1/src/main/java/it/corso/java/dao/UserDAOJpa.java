package it.corso.java.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import it.corso.java.entity.User;

@Repository
@Primary
public class UserDAOJpa implements UserDAO {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public User insert(User u) {
		manager.persist(u);
		return u;
	}

	@Override
	public User select(int idUtente) {
		return manager.find(User.class, idUtente);
	}

}
