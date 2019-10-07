package com.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;

import com.daos.FamiliaDAO;
import com.entities.Familia;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class FamiliasBean
 */
@Stateless
public class FamiliasBean implements FamiliasBeanRemote {

	@EJB
	private FamiliaDAO familiaDAO;
	
    /**
     * Default constructor. 
     */
    public FamiliasBean() {
        }

    	@Override
    	public void crear(Familia familia) throws ServiciosException {
    		try{
    			familiaDAO.crear(familia);
    		}catch(PersistenceException e){
    			throw new ServiciosException("No se pudo crear la familia");
    		}
    	}

    	@Override
    	public void actualizar(Familia familia) throws ServiciosException {
    		try{
    			familiaDAO.actualizar(familia);
    		}catch(PersistenceException e){
    			throw new ServiciosException("No se pudo actualizar la familia");
    		}
    	}

    	@Override
    	public void borrar(int id) throws ServiciosException {
    		try{
    			familiaDAO.borrar(id);
    		}catch(PersistenceException e){
    			throw new ServiciosException("No se pudo borrar la familia");
    		}
    	}

    	@Override
    	public List<Familia> obtenerFamilias() throws ServiciosException {
    		try{ 
    			return familiaDAO.obtenerTodos();
    		}catch(PersistenceException e){
    			throw new ServiciosException("No se pudo obtener lista de familias");
    		}
    	}

    	@Override
    	public List<Familia> obtenerFamilias(String nombre) throws ServiciosException {
    		try{ 
    			return familiaDAO.obtenerTodos(nombre);
    		}catch(PersistenceException e){
    			throw new ServiciosException("No se pudo obtener lista de familias");
    		}
    	}    	
    
    
    

}
