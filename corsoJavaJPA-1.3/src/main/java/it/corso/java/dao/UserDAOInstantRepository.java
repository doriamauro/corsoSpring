package it.corso.java.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.corso.java.entity.User;

public interface UserDAOInstantRepository extends JpaRepository<User, Integer>{
	//NIENTE!!
//	@Query("select u from User u where u.cognome = :cognome")
//	public List<User> findCongnome(String cognome);
}
