package it.corso.java.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.persistence.Version;

@Entity
public class ContoCorrente {
	
	@Version
	private int versione = 0;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer numero;
	
	private double saldo;
	private Date dataApertura;
	
	//dati correlati al conto
	@ElementCollection //rappresenta una relazione di composizione in java 1 a n
//	@Embedded //rappresenta una relazione di composizione in java 1 a 1	
	private List<Movimento> listaOperazioni = new ArrayList<>();
	
//	@ManyToMany
//	@JoinTable(name="conti_user",
//			   joinColumns = @JoinColumn(name="fk_c"),
//			   inverseJoinColumns = @JoinColumn(name="fk_u"))
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="user_conti",
			   joinColumns = @JoinColumn(name="fk_conto"),
			   inverseJoinColumns = @JoinColumn(name="fk_user"))
	private List<User> proprietari = new ArrayList<>();

	public ContoCorrente() {}
			
	public ContoCorrente(Integer numero, double saldo, Date dataApertura, List<Movimento> listaOperazioni) {
		this.numero = numero;
		this.saldo = saldo;
		this.dataApertura = dataApertura;
		this.listaOperazioni = listaOperazioni;
		
	}

	public int getVersione() {
		return versione;
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

	public List<User> getProprietari() {
		return proprietari;
	}

	public void setProprietari(List<User> proprietari) {
		this.proprietari = proprietari;
	}

	@Override
	public String toString() {
		return "ContoCorrente [numero=" + numero + ", saldo=" + saldo + ", dataApertura=" + dataApertura
				+ ", listaOperazioni=" + listaOperazioni + ", proprietari=" + proprietari + "]";
	}

	public void addOperazione(Movimento m) {
		this.listaOperazioni.add(m);
		
	}

	public void addIntestatario(User u) {
		proprietari.add(u);
	}

	public void setVersione(Integer versione) {
		this.versione = versione;
		
	}
	
	

}
