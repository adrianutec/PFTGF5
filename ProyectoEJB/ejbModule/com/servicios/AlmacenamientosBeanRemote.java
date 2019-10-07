package com.servicios;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Almacenamiento;
import com.exception.ServiciosException;

@Remote
public interface AlmacenamientosBeanRemote {
	void crear(Almacenamiento almacenamiento) throws ServiciosException;
	void actualizar(Almacenamiento almacenamiento) throws ServiciosException;
	void borrar(int id) throws ServiciosException;
	List<Almacenamiento>obtenerAlmacenamientos() throws ServiciosException;
	List<Almacenamiento>obtenerAlmacenamientos(String nombre) throws ServiciosException;

}
