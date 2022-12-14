package it.corso.java.dto;

public class UserDTO {

	private String nome;
	private String cognome;
	private Integer userId;
	private String password;
	private String mail;
	private String telefono;

	public UserDTO(String nome, String cognome, Integer userId, String password, String mail, String telefono) {
		this.nome = nome;
		this.cognome = cognome;
		this.userId = userId;
		this.password = password;
		this.mail = mail;
		this.telefono = telefono;
	}

	public UserDTO() {}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Override
	public String toString() {
		return "UserDTO [nome=" + nome + ", cognome=" + cognome + ", userId=" + userId + ", password=" + password
				+ ", mail=" + mail + ", telefono=" + telefono + "]";
	}

	
}
