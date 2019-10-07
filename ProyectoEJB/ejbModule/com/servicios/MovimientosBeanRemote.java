package com.servicios;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Movimiento;
import com.exception.ServiciosException;

@Remote
public interface MovimientosBeanRemote {
	void crear(Movimiento movimiento) throws ServiciosException;
	void actualizar(Movimiento movimiento) throws ServiciosException;
	void borrar(int id) throws ServiciosException;
	List<Movimiento>obtenerMovimientosPorFecha(String FDesde, String FHasta) throws ServiciosException;
	List<Movimiento>obtenerMovimientos() throws ServiciosException;
	List<Movimiento>obtenerMovimientos(String nombre) throws ServiciosException;
}
