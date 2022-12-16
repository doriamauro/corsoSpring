package it.corso.java.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="us")
public class UtenteSenior extends User {
	
	private Double bonus;

	public UtenteSenior() {}
	
	public Double getBonus() {
		return bonus;
	}

	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}

	@Override
	public String toString() {
		return "UtenteSenior [bonus=" + bonus + ", toString()=" + super.toString() + "]";
	}
	

}
