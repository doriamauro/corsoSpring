package it.corso.java.entity;

import java.sql.Clob;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

@Entity
@Table(name="utente")
@Access(AccessType.FIELD)
public class User {
	
	@Id
	@Column(name="id")
	private Integer userId;
	
	private String nome;
	private String congome;
	private String password;
	
	private String mail;
	
	@Transient
	private String telefono;

	public User() {}
	
	public User(Integer userId, String nome, String congome, String password, String mail, String telefono) {
		this.userId = userId;
		this.nome = nome;
		this.congome = congome;
		this.password = password;
		this.mail = mail;
		this.telefono = telefono;
	}

	@Access(AccessType.PROPERTY)
	@Column(name = "telefono")
	public String getTelefonoConPrefisso() {
		if(!telefono.startsWith("+"))
			return "+39"+telefono;
		return telefono;
	}

	public void setTelefonoConPrefisso(String telefono) {
		if(!telefono.startsWith("+"))
			this.telefono = "+39"+telefono;
		else
			this.telefono = telefono;
		
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCongome() {
		return congome;
	}

	public void setCongome(String congome) {
		this.congome = congome;
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
		return "User [userId=" + userId + ", nome=" + nome + ", congome=" + congome + ", password=" + password
				+ ", mail=" + mail + ", telefono=" + telefono + ", getTelefono()=" + getTelefonoConPrefisso() + ", getUserId()="
				+ getUserId() + ", getNome()=" + getNome() + ", getCongome()=" + getCongome() + ", getPassword()="
				+ getPassword() + ", getMail()=" + getMail() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

	

}
