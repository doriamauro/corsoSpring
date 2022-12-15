package it.corso.java.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.corso.java.dao.ContoCorrenteDAO;
import it.corso.java.dao.UserDAO;
import it.corso.java.entity.ContoCorrente;
import it.corso.java.entity.ContoCorrenteOLD;
import it.corso.java.entity.Movimento;
import it.corso.java.entity.MovimentoOLD;
import it.corso.java.entity.TipoMovimento;
import it.corso.java.entity.User;

@Service
@Transactional
public class ContoCorrenteServiceImpl implements ContoCorrenteService {
	
//	private static int progressivoConto = 0;
//	private static int progressivoMovimento = 0;
	
	@Autowired
	private ContoCorrenteDAO daoCC;
	
	@Autowired
	private UserDAO daoU;
	
	@Override
	public ContoCorrente apriConto(double saldoIniziale, Integer userIdIntestatario, Integer userIdCointestatario) {
		//in che stato si trova l'oggetto u dopo la select? --> managed
		User u = daoU.select(userIdIntestatario);
		if(saldoIniziale<0 || u==null) throw new RuntimeException("mancano le condizioni per l'apertura del conto");
		
		//in che stato si trova l'oggetto u dopo la new? --> transient
		ContoCorrente cc = new ContoCorrente();
		cc.addIntestatario(u);
		if(userIdCointestatario!=null) {
			User u2 = daoU.select(userIdCointestatario);
			if(u2==null) throw new RuntimeException("mancano le condizioni per l'apertura del conto");
			cc.addIntestatario(u2);
		}
		cc.setDataApertura(new Date());
		cc.setSaldo(saldoIniziale);
//		cc.setNumero(++progressivoConto); //exception a runtime
		
		if(saldoIniziale>0) {
			//in che stato si trova l'oggetto m dopo la new? --> transient
			Movimento m = new Movimento();
			m.setTipo(TipoMovimento.VERSAMENTO);
			m.setDataOperazione(new Date());
			m.setImporto(saldoIniziale);
			m.setOperatore(u);
//			m.setId(++progressivoMovimento); //exception a runtime
			
			//cc.getListaOperazioni().add(m);
			cc.addOperazione(m);
		}
		
		daoCC.insert(cc); // persist
		return cc;
	}

	@Override
	public ContoCorrente modificaSaldo(int numeroConto, double importo, Integer userId) {
		ContoCorrente cc = daoCC.select(numeroConto);
		if(cc==null || importo <0) throw new RuntimeException("non ci sono i presupposti per operare");
		double diff = cc.getSaldo()-importo;
		
		if(diff!=0) {
			Movimento m = new Movimento();
//			m.setId(++progressivoMovimento); 
			m.setDataOperazione(new Date());
			User u2 = daoU.select(userId);
			if(u2==null) 
				throw new RuntimeException("mancano le condizioni per modificare il conto");
			
			m.setOperatore(u2);
			if(diff>0) m.setTipo(TipoMovimento.PRELIEVO);
			else m.setTipo(TipoMovimento.VERSAMENTO);
			
			m.setImporto(Math.abs(diff));
			cc.addOperazione(m);
			cc.setSaldo(importo);
			
		}
		
		return cc;
	}

	@Override
	public List<Movimento> getMovimentiConto(int numeroConto) {
		ContoCorrente cc = daoCC.select(numeroConto);
		if(cc!= null) return cc.getListaOperazioni();
		else return null;
			
	}

	@Override
	public boolean cancellaConto(int numeroConto) {
		ContoCorrente cc = daoCC.select(numeroConto);
		if(cc!= null) {
			daoCC.delete(numeroConto);
			return true;
		}
		return false;
	}

	@Override
	public List<User> getProprietariConto(int numeroConto) {
		ContoCorrente cc = daoCC.select(numeroConto);
		if(cc!= null) 
			return cc.getProprietari();
		
		return null;
	}

	@Override
	public ContoCorrente leggiConto(int numeroConto) {
		return daoCC.select(numeroConto);
	}

	@Override
	public ContoCorrente rimuoviMovimenti(int numeroConto) {
		ContoCorrente cc = daoCC.select(numeroConto);
		if(cc!=null)
			cc.getListaOperazioni().clear();
//			cc.rimuoviMovimenti()
		
		return cc;
	}

	@Override
	public ContoCorrente modificaConto(ContoCorrente cc) {
		return daoCC.update(cc);
	}

}
