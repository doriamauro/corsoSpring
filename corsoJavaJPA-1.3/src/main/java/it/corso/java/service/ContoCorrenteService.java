package it.corso.java.service;

import java.util.List;

import it.corso.java.entity.ContoCorrente;
import it.corso.java.entity.Movimento;
import it.corso.java.entity.User;

public interface ContoCorrenteService {
	
	public ContoCorrente apriConto(double saldoIniziale, Integer userIdIntestatario, Integer userIdCointestatario );
	public ContoCorrente modificaSaldo(int numeroConto, double importo, Integer userId, Integer versione);
	public ContoCorrente leggiConto(int numeroConto);
	public List<Movimento> getMovimentiConto(int numeroConto);
	public boolean cancellaConto(int numeroConto);
	public List<User> getProprietariConto(int numeroConto);
	public ContoCorrente rimuoviMovimenti(int numeroConto);
	public ContoCorrente modificaConto(ContoCorrente cc);

}
