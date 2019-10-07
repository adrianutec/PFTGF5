package com.servicios;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Familia;
import com.exception.ServiciosException;

@Remote
public interface FamiliasBeanRemote {
	void crear(Familia familia) throws ServiciosException;
	void actualizar(Familia familia) throws ServiciosException;
	void borrar(int id) throws ServiciosException;
	List<Familia>obtenerFamilias() throws ServiciosException;
	List<Familia>obtenerFamilias(String nombre) throws ServiciosException;

}
