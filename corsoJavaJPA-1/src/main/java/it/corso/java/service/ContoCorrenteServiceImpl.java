package it.corso.java.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.corso.java.dao.ContoCorrenteDAO;
import it.corso.java.dao.UserDAO;
import it.corso.java.entity.ContoCorrente;
import it.corso.java.entity.Movimento;
import it.corso.java.entity.TipoMovimento;
import it.corso.java.entity.User;

@Service
//@Transactional
public class ContoCorrenteServiceImpl implements ContoCorrenteService {
	
	private static int progressivoConto = 0;
	private static int progressivoMovimento = 0;
	
	@Autowired
	private ContoCorrenteDAO daoCC;
	
	@Autowired
	private UserDAO daoU;
	
	@Override
	public synchronized ContoCorrente apriConto(double saldoIniziale, int userId) {
		User u = daoU.select(userId);
		if(saldoIniziale<0 || u==null) throw new RuntimeException("mancano le condizioni per l'apertura del conto");
		
		ContoCorrente cc = new ContoCorrente();
		cc.setProprietario(u);
		cc.setDataApertura(new Date());
		cc.setSaldo(saldoIniziale);
		cc.setNumero(++progressivoConto);
		
		if(saldoIniziale>0) {
			Movimento m = new Movimento();
			m.setTipo(TipoMovimento.VERSAMENTO);
			m.setDataOperazione(new Date());
			m.setImporto(saldoIniziale);
			m.setId(++progressivoMovimento);
			
			//cc.getListaOperazioni().add(m);
			cc.addOperazione(m);
		}
		
		daoCC.insert(cc);
		return cc;
	}

	@Override
	public ContoCorrente modificaSaldo(int numeroConto, double importo) {
		ContoCorrente cc = daoCC.select(numeroConto);
		if(cc==null || importo <0) throw new RuntimeException("non ci sono i presupposti per operare");
		double diff = cc.getSaldo()-importo;
		
		if(diff!=0) {
			Movimento m = new Movimento();
			m.setId(++progressivoMovimento); 
			m.setDataOperazione(new Date());
			
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
	public User getProprietarioConto(int numeroConto) {
		ContoCorrente cc = daoCC.select(numeroConto);
		if(cc!= null) 
			return cc.getProprietario();
		
		return null;
	}

	@Override
	public ContoCorrente leggiConto(int numeroConto) {
		return daoCC.select(numeroConto);
	}

}
