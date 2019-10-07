package com.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Almacenamiento
 *
 */
@Entity
@Table(name="ALMACENAMIENTOS")

public class Almacenamiento implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ALMA_ID")
	private int id;
	
	@Column(name = "ALMA_VOLUMEN", nullable =  false)
	private int volumen;

	@Column(name = "ALMA_DESCRIPCION", length=250)
	private String nombre;

	
	@Column(name = "ALMA_COSTOOP", nullable = false, columnDefinition = "FLOAT(5,2)")
	private double costoop;

	@Column(name = "ALMA_CAPESTIBA", nullable = false, columnDefinition = "FLOAT(5,2)")
	private double capestiba;

	@Column(name = "ALMA_CAPPESO", nullable = false, columnDefinition = "FLOAT(5,2)")
	private double cappeso;

	@ManyToOne(optional=false)
	@JoinColumn(name = "ALMA_LOC_ID")
	private Local local;
	
	
	public Almacenamiento() {
		super();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getVolumen() {
		return volumen;
	}


	public void setVolumen(int volumen) {
		this.volumen = volumen;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public double getCostoop() {
		return costoop;
	}


	public void setCostoop(double costoop) {
		this.costoop = costoop;
	}


	public double getCapestiba() {
		return capestiba;
	}


	public void setCapestiba(double capestiba) {
		this.capestiba = capestiba;
	}


	public double getCappeso() {
		return cappeso;
	}


	public void setCappeso(double cappeso) {
		this.cappeso = cappeso;
	}


	public Local getLocal() {
		return local;
	}


	public void setLocal(Local local) {
		this.local = local;
	}

	
	   
}
