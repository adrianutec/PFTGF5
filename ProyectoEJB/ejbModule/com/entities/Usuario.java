package com.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Usuario
 *
 */
@Entity
@Table(name="USUARIOS")
public class Usuario implements Serializable {
			
		private static final long serialVersionUID = 1L;
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		@Column(name = "USU_CODIGO")
		private int id;
		
		@Column(length=50,name = "USU_NOMBRE",nullable=false)
		private String nombre;
		
		@Column(length=50,name = "USU_APELLIDO",nullable=false)
		private String apellido;
		
		@Column(length=50,name = "USU_NOMACCESO",nullable=false)
		private String nomAcceso;
		
		@Column(length=16,name = "USU_CONTRASENA",nullable=false)
		private String contrasena;
		
		@Column(length=50,name = "USU_CORREO", nullable=false)
		private String correo;
		
		@ManyToOne (optional =  false)
		@JoinColumn(name = "USU_PERF_CODIGO",nullable=false)
		private Perfil perfil;

	public Usuario() {
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNomAcceso() {
		return nomAcceso;
	}

	public void setNomAcceso(String nomAcceso) {
		this.nomAcceso = nomAcceso;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

		
}
