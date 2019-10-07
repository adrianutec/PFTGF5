package com.servicios;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;

import com.entities.Producto;
import com.daos.ProductoDAO;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class ProductosBean
 */
@Stateless
@LocalBean
public class ProductosBean implements ProductosBeanRemote {


	@EJB
	private ProductoDAO productoDAO;
	
    /**
     * Default constructor. 
     */
    public ProductosBean() {
        }

    	@Override
    	public void crear(Producto producto) throws ServiciosException {
    		try{
    			productoDAO.crear(producto);
    		}catch(PersistenceException e){
    			throw new ServiciosException("No se pudo crear el producto");
    		}
    	}

    	@Override
    	public void actualizar(Producto producto) throws ServiciosException {
    		try{
    			productoDAO.actualizar(producto);
    		}catch(PersistenceException e){
    			throw new ServiciosException("No se pudo actualizar el producto");
    		}
    	}

    	@Override
    	public void borrar(int id) throws ServiciosException {
    		try{
    			productoDAO.borrar(id);
    		}catch(PersistenceException e){
    			throw new ServiciosException("No se pudo borrar el producto");
    		}
    	}

    	@Override
    	public List<Producto> obtenerProductos() throws ServiciosException {
    		try{ 
    			return productoDAO.obtenerTodos();
    		}catch(PersistenceException e){
    			throw new ServiciosException("No se pudo obtener lista de productos");
    		}
    	}

    	@Override
    	public List<Producto> obtenerProductos(String nombre) throws ServiciosException {
    		try{ 
    			return productoDAO.obtenerTodos(nombre);
    		}catch(PersistenceException e){
    			throw new ServiciosException("No se pudo obtener lista de productos");
    		}
    	}    	
    

    	@Override
    	public Producto obtenerProductoPorNombre(String nombre) throws ServiciosException {
    		try{ 
    			return productoDAO.obtenerProductoPorNombre(nombre);
    		}catch(PersistenceException e){
    			throw new ServiciosException("No se pudo obtener el producto");
    		}
    	}    	

    	
    	
}
