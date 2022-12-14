package it.corso.java.dao;

import java.util.List;

import it.corso.java.entity.ContoCorrente;
import it.corso.java.entity.Movimento;
import it.corso.java.entity.User;

public interface ContoCorrenteDAO {
	
	public ContoCorrente insert(ContoCorrente cc);
	public ContoCorrente select(int idConto);
	public ContoCorrente update(ContoCorrente cc);
	public boolean delete(int idConto);
	
	public List<ContoCorrente> selectAllConti();
	public List<Movimento> selectAllMovimenti(int idConto);
	public User selectUtente(int idConto);

}
