package com.hibernate.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="serie")
public class Serie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idserie")
	private int id;
	
	@Column(name="nombre")
	private String nomserie;
	
	@Column(name="temporadas")
	private int temporadas;
	
	@Column(name="capitulos")
	private int capitulos;
	
	public Serie() {
		
	}
	
	public Serie(String nomserie, int temporadas, int capitulos) {
		super();
		this.nomserie = nomserie;
		this.temporadas = temporadas;
		this.capitulos = capitulos;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomserie() {
		return nomserie;
	}
	public void setNomserie(String nomserie) {
		this.nomserie = nomserie;
	}
	public int getTemporadas() {
		return temporadas;
	}
	public void setTemporadas(int temporadas) {
		this.temporadas = temporadas;
	}
	public int getCapitulos() {
		return capitulos;
	}
	public void setCapitulos(int capitulos) {
		this.capitulos = capitulos;
	}
	
}
