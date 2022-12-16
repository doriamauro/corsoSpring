package it.corso.java.entity;

import java.sql.Clob;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

@Entity
@Table(name="utente")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "disc")
@DiscriminatorValue(value="user")
@Access(AccessType.FIELD)
public class User {
	
	@Id
	@Column(name="id")
	private Integer userId;
	
	@Version
	private int versione = 0;
	
	private String nome;
	private String congome;
	private String password;
	
	private String mail;
	
	@Transient
	private String telefono;
	
	@ElementCollection
	@Column(name="alias")
	@CollectionTable(name = "alias")
	private List<String> alias = new ArrayList<>();
	
	@Embedded
	private Fido fido;

	public User() {}

	public User(Integer userId, String nome, String congome, String password, String mail, String telefono, Fido fido) {
		this.userId = userId;
		this.nome = nome;
		this.congome = congome;
		this.password = password;
		this.mail = mail;
		this.telefono = telefono;
		this.fido = fido;
	}

	
	public int getVersione() {
		return versione;
	}

	@Access(AccessType.PROPERTY)
	@Column(name = "telefono")
	public String getTelefonoConPrefisso() {
		if(telefono==null) return null;
		
		System.out.println(this);
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<String> getAlias() {
		return alias;
	}

	public void setAlias(List<String> alias) {
		this.alias = alias;
	}

	public Fido getFido() {
		return fido;
	}

	public void setFido(Fido fido) {
		this.fido = fido;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", nome=" + nome + ", congome=" + congome + ", password=" + password
				+ ", mail=" + mail + ", telefono=" + telefono + ", alias=" + alias + ", fido=" + fido + "]";
	}

	public void setVersione(int versione) {
		this.versione = versione;
		
	}
}
