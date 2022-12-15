package it.corso.java.entity;

import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class Movimento {
	
	@Enumerated(EnumType.ORDINAL)
	private TipoMovimento tipo;
	private double importo;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataOperazione;

	@ManyToOne
	@JoinColumn(name="fk_user")
	private User operatore;
	
	public Movimento() {}
	
	public Movimento(TipoMovimento tipo, double importo, Date dataOperazione, User operatore) {
		this.tipo = tipo;
		this.importo = importo;
		this.dataOperazione = dataOperazione;
		this.operatore = operatore;
	}



	public TipoMovimento getTipo() {
		return tipo;
	}
	public void setTipo(TipoMovimento tipo) {
		this.tipo = tipo;
	}
	public double getImporto() {
		return importo;
	}
	public void setImporto(double importo) {
		this.importo = importo;
	}
	public Date getDataOperazione() {
		return dataOperazione;
	}
	public void setDataOperazione(Date dataOperazione) {
		this.dataOperazione = dataOperazione;
	}
	
	public User getOperatore() {
		return operatore;
	}

	public void setOperatore(User operatore) {
		this.operatore = operatore;
	}

	@Override
	public String toString() {
		return "Movimento [tipo=" + tipo + ", importo=" + importo + ", dataOperazione=" + dataOperazione
				+ ", operatore=" + operatore + "]";
	}

	
}
