package com.servicios;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Perfil;
import com.exception.ServiciosException;

@Remote
public interface PerfilesBeanRemote {
	void crear(Perfil perfil) throws ServiciosException;
	void actualizar(Perfil perfil) throws ServiciosException;
	void borrar(int id) throws ServiciosException;
	List<Perfil>obtenerPerfiles() throws ServiciosException;
	List<Perfil>obtenerPerfiles(String nombre) throws ServiciosException;
	long traigoUltimoIDPerfil() throws ServiciosException;

}
