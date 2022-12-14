package it.corso.java.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import it.corso.java.entity.ContoCorrente;
import it.corso.java.entity.Movimento;
import it.corso.java.entity.User;

@Repository
public class ContoCorrenteDAOImpl implements ContoCorrenteDAO {

	private static Map<Integer, ContoCorrente> mappa = new HashMap<>();
	
	@Override
	public ContoCorrente insert(ContoCorrente cc) {
		//solo exception unckecked
		if(mappa.containsKey(cc.getNumero())) throw new RuntimeException("conto corrente già presente");
		
		mappa.put(cc.getNumero(), cc);
		return cc;
	}

	@Override
	public ContoCorrente select(int idConto) {
		return mappa.get(idConto);
	}

	@Override
	public ContoCorrente update(ContoCorrente cc) {
		if(!mappa.containsKey(cc.getNumero())) throw new RuntimeException("conto corrente non presente");
		
		mappa.put(cc.getNumero(), cc);
		return cc;
		
	}

	@Override
	public boolean delete(int idConto) {
		if(mappa.containsKey(idConto)) {
			mappa.remove(idConto);
			return true;
		}
		return false;
	}

	@Override
	public List<ContoCorrente> selectAllConti() {
		return new ArrayList<ContoCorrente>(mappa.values());
	}

	@Override
	public List<Movimento> selectAllMovimenti(int idConto) {
		if(!mappa.containsKey(idConto)) throw new RuntimeException("conto corrente non presente");
		
		return mappa.get(idConto).getListaOperazioni();
	}

	@Override
	public User selectUtente(int idConto) {
		if(!mappa.containsKey(idConto)) throw new RuntimeException("conto corrente non presente");
		return mappa.get(idConto).getProprietario();
	}

}
