package it.corso.java.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Fido {

	@Column
	private Integer rating;
	
	@Column
	private Double importoMax;
	
	public Fido() {}
	
	public Fido(Integer rating, double importoMax) {
		this.rating = rating;
		this.importoMax = importoMax;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public double getImportoMax() {
		return importoMax;
	}

	public void setImportoMax(double importoMax) {
		this.importoMax = importoMax;
	}

	@Override
	public String toString() {
		return "Fido [rating=" + rating + ", importoMax=" + importoMax + "]";
	}
}
