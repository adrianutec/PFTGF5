package com.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;

import com.daos.CiudadDAO;
import com.entities.Ciudad;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class CiudadsBean
 */
@Stateless
public class CiudadesBean implements CiudadesBeanRemote {

	@EJB
	private CiudadDAO ciudadDAO;
	
    /**
     * Default constructor. 
     */
    public CiudadesBean() {
        }

    	@Override
    	public void crear(Ciudad ciudad) throws ServiciosException {
    		try{
    			ciudadDAO.crear(ciudad);
    		}catch(PersistenceException e){
    			throw new ServiciosException("No se pudo crear la ciudad");
    		}
    	}

    	@Override
    	public void actualizar(Ciudad ciudad) throws ServiciosException {
    		try{
    			ciudadDAO.actualizar(ciudad);
    		}catch(PersistenceException e){
    			throw new ServiciosException("No se pudo actualizar la ciudad");
    		}
    	}

    	@Override
    	public void borrar(int id) throws ServiciosException {
    		try{
    			ciudadDAO.borrar(id);
    		}catch(PersistenceException e){
    			throw new ServiciosException("No se pudo borrar la ciudad");
    		}
    	}

    	@Override
    	public List<Ciudad> obtenerCiudades() throws ServiciosException {
    		try{ 
    			return ciudadDAO.obtenerTodos();
    		}catch(PersistenceException e){
    			throw new ServiciosException("No se pudo obtener lista de ciudades");
    		}
    	}

    	@Override
    	public List<Ciudad> obtenerCiudades(String nombre) throws ServiciosException {
    		try{ 
    			return ciudadDAO.obtenerTodos(nombre);
    		}catch(PersistenceException e){
    			throw new ServiciosException("No se pudo obtener lista de ciudades");
    		}
    	}    	
    

}
