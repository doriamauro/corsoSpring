package it.corso.java.dao;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import it.corso.java.dto.DatiSalientiUserDTO;
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

	@Override
	public String selectAll() {
		Query usersQuery = manager.createQuery("SELECT DISTINCT cc FROM ContoCorrente cc JOIN cc.listaOperazioni op");
		List l =  usersQuery.getResultList(); //effettiva esecuzione della query
		System.out.println(l);
//		for (Object object : l) {
//			Object[] u = (Object[]) object;
//			System.out.println(u[0] + " " + u[1]);
//		}
		System.out.println(l.size());
		return l.toString();
	}

}
