package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import com.enumerated.tipoPerfil;

/**
 * Entity implementation class for Entity: Perfil
 *
 */
@Entity
@Table(name="PERFILES")

public class Perfil implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	@Column(name = "PERF_CODIGO",nullable=false)
	private int id;
	
	@Column(name = "PERF_NOMBRE", length = 50, nullable=false, unique=true)
	@Enumerated(value = EnumType.STRING)
	private tipoPerfil perfil;
	
	public Perfil() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public tipoPerfil getPerfil() {
		return perfil;
	}

	public void setPerfil(tipoPerfil perfil) {
		this.perfil = perfil;
	}
	
	
}
