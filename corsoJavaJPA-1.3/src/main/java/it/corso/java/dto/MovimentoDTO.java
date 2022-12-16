package it.corso.java.dto;

import java.util.Date;

import it.corso.java.entity.TipoMovimento;

public class MovimentoDTO {
	
	private Integer id;
	private TipoMovimento tipo;
	private double importo;
	private Date dataOperazione;
	private Integer idOperatore;
	
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
	
	public Integer getIdOperatore() {
		return idOperatore;
	}
	public void setIdOperatore(Integer idOperatore) {
		this.idOperatore = idOperatore;
	}
	@Override
	public String toString() {
		return "MovimentoDTO [id=" + id + ", tipo=" + tipo + ", importo=" + importo + ", dataOperazione="
				+ dataOperazione + ", idOperatore=" + idOperatore + "]";
	}
	
	
}
