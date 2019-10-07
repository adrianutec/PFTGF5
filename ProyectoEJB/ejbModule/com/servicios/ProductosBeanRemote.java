package com.servicios;

import java.util.List;
import javax.ejb.Remote;

import com.entities.Producto;
import com.exception.ServiciosException;

@Remote
public interface ProductosBeanRemote {
	void crear(Producto producto) throws ServiciosException;
	void actualizar(Producto producto) throws ServiciosException;
	void borrar(int id) throws ServiciosException;
	Producto obtenerProductoPorNombre(String nombre) throws ServiciosException; 
	List<Producto>obtenerProductos() throws ServiciosException;
	List<Producto>obtenerProductos(String nombre) throws ServiciosException;

}
