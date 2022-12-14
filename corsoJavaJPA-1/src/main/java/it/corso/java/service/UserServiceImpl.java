package it.corso.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.corso.java.dao.UserDAO;
import it.corso.java.entity.User;

@Service // --> Component
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO daoU;
	
	@Override
	public User leggiDatiUtente(Integer userId) {
		return daoU.select(userId);
	}

	@Override
	public void registraUtente(User u) {
		daoU.insert(u);
	}

}
