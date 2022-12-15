package it.corso.java.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//@Entity
public class MovimentoOLD {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Enumerated(EnumType.ORDINAL)
	private TipoMovimento tipo;
	private double importo;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataOperazione;

	public MovimentoOLD() {}
	
	public MovimentoOLD(Integer id, TipoMovimento tipo, double importo, Date dataOperazione) {
		this.id = id;
		this.tipo = tipo;
		this.importo = importo;
		this.dataOperazione = dataOperazione;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	
	@Override
	public String toString() {
		return "Movimento [id=" + id + ", tipo=" + tipo + ", importo=" + importo + ", dataOperazione=" + dataOperazione
				+ "]";
	}
}
