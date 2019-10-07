package com.servicios;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Ciudad;
import com.exception.ServiciosException;

@Remote
public interface CiudadesBeanRemote {
	void crear(Ciudad ciudad) throws ServiciosException;
	void actualizar(Ciudad ciudad) throws ServiciosException;
	void borrar(int id) throws ServiciosException;
	List<Ciudad>obtenerCiudades() throws ServiciosException;
	List<Ciudad>obtenerCiudades(String nombre) throws ServiciosException;

}
