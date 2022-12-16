package it.corso.java.dto;

public class ErroreStaleObject {
	
	private ContoCorrenteDTO conto;
	private String msg;
	public ContoCorrenteDTO getConto() {
		return conto;
	}
	public void setConto(ContoCorrenteDTO conto) {
		this.conto = conto;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "ErroreStaleObject [conto=" + conto + ", msg=" + msg + "]";
	}
	
	
	

}
