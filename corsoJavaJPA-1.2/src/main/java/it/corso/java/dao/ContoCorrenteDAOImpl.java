package it.corso.java.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import it.corso.java.entity.ContoCorrenteOLD;
import it.corso.java.entity.MovimentoOLD;
import it.corso.java.entity.User;

@Repository
public class ContoCorrenteDAOImpl implements ContoCorrenteDAO {

	private static Map<Integer, ContoCorrenteOLD> mappa = new HashMap<>();
	
	@Override
	public ContoCorrenteOLD insert(ContoCorrenteOLD cc) {
		//solo exception unckecked
		if(mappa.containsKey(cc.getNumero())) throw new RuntimeException("conto corrente già presente");
		
		mappa.put(cc.getNumero(), cc);
		return cc;
	}

	@Override
	public ContoCorrenteOLD select(int idConto) {
		return mappa.get(idConto);
	}

	@Override
	public ContoCorrenteOLD update(ContoCorrenteOLD cc) {
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
	public List<ContoCorrenteOLD> selectAllConti() {
		return new ArrayList<ContoCorrenteOLD>(mappa.values());
	}

	@Override
	public List<MovimentoOLD> selectAllMovimenti(int idConto) {
		if(!mappa.containsKey(idConto)) throw new RuntimeException("conto corrente non presente");
		
		return mappa.get(idConto).getListaOperazioni();
	}

	@Override
	public User selectUtente(int idConto) {
		if(!mappa.containsKey(idConto)) throw new RuntimeException("conto corrente non presente");
		return mappa.get(idConto).getProprietario();
	}

}
