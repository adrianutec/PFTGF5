package com.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;

import com.daos.LocalDAO;
import com.entities.Local;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class LocalsBean
 */
@Stateless
public class LocalesBean implements LocalesBeanRemote {

	@EJB
	private LocalDAO localDAO;
	
    /**
     * Default constructor. 
     */
    public LocalesBean() {
        }

    	@Override
    	public void crear(Local local) throws ServiciosException {
    		try{
    			localDAO.crear(local);
    		}catch(PersistenceException e){
    			throw new ServiciosException("No se pudo crear la local");
    		}
    	}

    	@Override
    	public void actualizar(Local local) throws ServiciosException {
    		try{
    			localDAO.actualizar(local);
    		}catch(PersistenceException e){
    			throw new ServiciosException("No se pudo actualizar la local");
    		}
    	}

    	@Override
    	public void borrar(int id) throws ServiciosException {
    		try{
    			localDAO.borrar(id);
    		}catch(PersistenceException e){
    			throw new ServiciosException("No se pudo borrar la local");
    		}
    	}

    	@Override
    	public List<Local> obtenerLocales() throws ServiciosException {
    		try{ 
    			return localDAO.obtenerTodos();
    		}catch(PersistenceException e){
    			throw new ServiciosException("No se pudo obtener lista de locales");
    		}
    	}

    	@Override
    	public List<Local> obtenerLocales(String nombre) throws ServiciosException {
    		try{ 
    			return localDAO.obtenerTodos(nombre);
    		}catch(PersistenceException e){
    			throw new ServiciosException("No se pudo obtener lista de locales");
    		}
    	}    	
    

}
