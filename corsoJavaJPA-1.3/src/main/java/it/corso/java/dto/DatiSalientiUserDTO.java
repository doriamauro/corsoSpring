package it.corso.java.dto;

public class DatiSalientiUserDTO {
	private String nome;
	private String cognome;
	
	public DatiSalientiUserDTO() {}
	
	public DatiSalientiUserDTO(String nome, String cognome) {
		this.nome = nome;
		this.cognome = cognome;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	@Override
	public String toString() {
		return "DatiSalientiUserDTO [nome=" + nome + ", cognome=" + cognome + "]";
	}
	
	

}
