package it.corso.java.service;

import java.util.List;

import it.corso.java.entity.ContoCorrente;
import it.corso.java.entity.Movimento;
import it.corso.java.entity.User;

public interface ContoCorrenteService {
	
	public ContoCorrente apriConto(double saldoIniziale, int userId);
	public ContoCorrente modificaSaldo(int numeroConto, double importo);
	public ContoCorrente leggiConto(int numeroConto);
	public List<Movimento> getMovimentiConto(int numeroConto);
	public boolean cancellaConto(int numeroConto);
	public User getProprietarioConto(int numeroConto);

}
