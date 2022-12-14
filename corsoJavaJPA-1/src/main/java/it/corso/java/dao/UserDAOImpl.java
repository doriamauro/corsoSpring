package it.corso.java.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import it.corso.java.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {

	public static Map<Integer, User> mappa = new HashMap<>();
	
	static {
		mappa.put(1, new User(1,"Sandra","Milo","32433","milo@gmail.com","34333"));
	}
	
	@Override
	public User insert(User u) {
		if(mappa.containsKey(u.getUserId())) throw new RuntimeException("utente già presente");
		return mappa.put(u.getUserId(), u);
	}

	@Override
	public User select(int idUtente) {
		return mappa.get(idUtente);
	}

}
