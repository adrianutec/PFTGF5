package com.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Familia
 *
 */
@Entity
@Table(name="FAMILIAS")

public class Familia implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "FAMI_CODI", nullable=false)
	private int id;
	
	@Column(name = "FAMI_NOMBRE", length=50, nullable=false)
	private String nombre;
	
	@Column(name = "FAMI_DESCRIP", length=100, nullable=false)
	private String descrip;
	
	@Column(name = "FAMI_INCOMPAT", length=60, nullable=false)
	private String incompat;
	
		public Familia() {
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

		public String getDescrip() {
			return descrip;
		}

		public void setDescrip(String descrip) {
			this.descrip = descrip;
		}

		public String getIncompat() {
			return incompat;
		}

		public void setIncompat(String incompat) {
			this.incompat = incompat;
		}

	
}
