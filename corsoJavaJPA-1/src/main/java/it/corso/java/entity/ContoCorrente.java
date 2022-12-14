package it.corso.java.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class ContoCorrente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer numero;
	
	private double saldo;
	private Date dataApertura;
	
	//dati correlati al conto
	@Transient
	private List<Movimento> listaOperazioni = new ArrayList<>();
	
	@Transient
	private User proprietario;

	public ContoCorrente() {}
			
	public ContoCorrente(Integer numero, double saldo, Date dataApertura, List<Movimento> listaOperazioni,
			User proprietario) {
		this.numero = numero;
		this.saldo = saldo;
		this.dataApertura = dataApertura;
		this.listaOperazioni = listaOperazioni;
		this.proprietario = proprietario;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public Date getDataApertura() {
		return dataApertura;
	}

	public void setDataApertura(Date dataApertura) {
		this.dataApertura = dataApertura;
	}

	public List<Movimento> getListaOperazioni() {
		return listaOperazioni;
	}

	public void setListaOperazioni(List<Movimento> listaOperazioni) {
		this.listaOperazioni = listaOperazioni;
	}

	public User getProprietario() {
		return proprietario;
	}

	public void setProprietario(User proprietario) {
		this.proprietario = proprietario;
	}

	@Override
	public String toString() {
		return "ContoCorrente [numero=" + numero + ", saldo=" + saldo + ", dataApertura=" + dataApertura
				+ ", listaOperazioni=" + listaOperazioni + ", proprietario=" + proprietario + "]";
	}

	public void addOperazione(Movimento m) {
		this.listaOperazioni.add(m);
		
	}
	
	

}
