package it.corso.java.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ContoCorrenteDTO {

	private Integer numeroConto;
	private Double saldo;
	private Date dataApertura;
	private int versione;
	
	private List<MovimentoDTO> listaOperazioni = new ArrayList<>();

	public Integer getNumeroConto() {
		return numeroConto;
	}
	

	public int getVersione() {
		return versione;
	}


	public void setVersione(int versione) {
		this.versione = versione;
	}


	public void setNumeroConto(Integer numeroConto) {
		this.numeroConto = numeroConto;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public Date getDataApertura() {
		return dataApertura;
	}

	public void setDataApertura(Date dataApertura) {
		this.dataApertura = dataApertura;
	}

	public List<MovimentoDTO> getListaOperazioni() {
		return listaOperazioni;
	}

	public void setListaOperazioni(List<MovimentoDTO> listaOperazioni) {
		this.listaOperazioni = listaOperazioni;
	}

	@Override
	public String toString() {
		return "ContoCorrenteDTO [numeroConto=" + numeroConto + ", saldo=" + saldo + ", dataApertura=" + dataApertura
				+ "]";
	}
	
	
}
