package it.corso.java.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="uj")
public class UtenteJunior extends User{

	private String linkSocial;

	public UtenteJunior() {}
	
	public UtenteJunior(String linkSocial) {
		this.linkSocial = linkSocial;
	}

	public String getLinkSocial() {
		return linkSocial;
	}

	public void setLinkSocial(String linkSocial) {
		this.linkSocial = linkSocial;
	}

	@Override
	public String toString() {
		return "UtenteJunior [linkSocial=" + linkSocial + ", toString()=" + super.toString() + "]";
	}
	
}
