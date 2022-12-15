package it.corso.java.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import it.corso.java.entity.ContoCorrente;
import it.corso.java.entity.ContoCorrenteOLD;
import it.corso.java.entity.Movimento;
import it.corso.java.entity.MovimentoOLD;
import it.corso.java.entity.User;

@Repository
@Primary
public class ContoCorrenteDAOJpa implements ContoCorrenteDAO {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public ContoCorrente insert(ContoCorrente cc) {
		manager.persist(cc); //persist del conto corrente --> quante operazioni SQL corrisponde
		return cc;
	}

	@Override
	public ContoCorrente select(int idConto) {
		return manager.find(ContoCorrente.class, idConto);
	}

	@Override
	public ContoCorrente update(ContoCorrente cc) {
//		ContoCorrente c = manager.find(ContoCorrente.class, cc.getNumero());
//		if(c!=null) {
//			c.setSaldo(cc.getSaldo());
//			c.setDataApertura(cc.getDataApertura());
//		}
//		return c;
		
		return manager.merge(cc);
	}

	@Override
	public boolean delete(int idConto) {
		ContoCorrenteOLD c = manager.find(ContoCorrenteOLD.class, idConto);
		if(c!=null) {
			manager.remove(c);
			return true;
		}
		return false;
	}

	@Override
	public List<ContoCorrente> selectAllConti() {
		TypedQuery<ContoCorrente> tq = manager.createQuery("SELECT cc FROM ContoCorrente cc", ContoCorrente.class);
		return tq.getResultList();
	}

	@Override
	public List<Movimento> selectAllMovimenti(int idConto) {
		ContoCorrente c = manager.find(ContoCorrente.class, idConto); //prima select SQL
		if(c!=null) {
			return c.getListaOperazioni(); //seconda select SQL
		}
		return null;
	}

	@Override
	public User selectUtente(int idConto) {
		ContoCorrenteOLD c = manager.find(ContoCorrenteOLD.class, idConto); //prima select SQL JOIN
		if(c!=null) {
			return c.getProprietario(); 
		}
		return null;
	}

}
