package it.corso.java.dto;

import java.util.Date;

public class ErroreDTO {
	
	private String messaggio;
	private Date dataErrore;
	
	public String getMessaggio() {
		return messaggio;
	}
	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}
	public Date getDataErrore() {
		return dataErrore;
	}
	public void setDataErrore(Date dataErrore) {
		this.dataErrore = dataErrore;
	}
	@Override
	public String toString() {
		return "ErroreDTO [messaggio=" + messaggio + ", dataErrore=" + dataErrore + "]";
	}
	

}
