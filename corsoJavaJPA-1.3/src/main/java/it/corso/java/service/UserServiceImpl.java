package it.corso.java.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.corso.java.dao.UserDAO;
import it.corso.java.dao.UserDAOInstantRepository;
import it.corso.java.entity.User;

@Service // --> Component
@Transactional
public class UserServiceImpl implements UserService{

//	@Autowired
//	private UserDAO daoU;
	
	@Autowired
	private UserDAOInstantRepository daoU;
	
	@Override
	public User leggiDatiUtente(Integer userId) {
	
		Optional<User> optional= daoU.findById(userId);
		if(optional.isPresent())
			return optional.get();
		
		return null;
	}

	@Override
	public void registraUtente(User u) {
		System.out.println(u);
		daoU.save(u);
		
	}

	@Override
	public String test() {
		List<User> s = daoU.findAll();
		return s.toString();
		
		
	}

}
