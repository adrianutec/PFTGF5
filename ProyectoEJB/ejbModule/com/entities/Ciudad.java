package com.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Ciudad
 *
 */
@Entity
@Table(name="CIUDADES")

public class Ciudad implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "CIU_ID")
	private int id;
	
	@Column(name = "CIU_NOMBRE", length=50, nullable=false, unique=true)
	private String nombre;
	
	
	public Ciudad() {
		super();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
   
}
