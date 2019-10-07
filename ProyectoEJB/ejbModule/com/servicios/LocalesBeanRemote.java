package com.servicios;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Local;
import com.exception.ServiciosException;

@Remote
public interface LocalesBeanRemote {
	void crear(Local local) throws ServiciosException;
	void actualizar(Local local) throws ServiciosException;
	void borrar(int id) throws ServiciosException;
	List<Local>obtenerLocales() throws ServiciosException;
	List<Local>obtenerLocales(String nombre) throws ServiciosException;

}
