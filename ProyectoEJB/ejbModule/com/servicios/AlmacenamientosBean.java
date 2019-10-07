package com.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;

import com.daos.AlmacenamientoDAO;
import com.entities.Almacenamiento;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class AlmacenamientosBean
 */
@Stateless
public class AlmacenamientosBean implements AlmacenamientosBeanRemote {

	@EJB
	private AlmacenamientoDAO almacenamientoDAO;
	
    /**
     * Default constructor. 
     */
    public AlmacenamientosBean() {
        }

    	@Override
    	public void crear(Almacenamiento almacenamiento) throws ServiciosException {
    		try{
    			almacenamientoDAO.crear(almacenamiento);
    		}catch(PersistenceException e){
    			throw new ServiciosException("No se pudo crear el almacenamiento");
    		}
    	}

    	@Override
    	public void actualizar(Almacenamiento almacenamiento) throws ServiciosException {
    		try{
    			almacenamientoDAO.actualizar(almacenamiento);
    		}catch(PersistenceException e){
    			throw new ServiciosException("No se pudo actualizar el almacenamiento");
    		}
    	}

    	@Override
    	public void borrar(int id) throws ServiciosException {
    		try{
    			almacenamientoDAO.borrar(id);
    		}catch(PersistenceException e){
    			throw new ServiciosException("No se pudo borrar el almacenamiento");
    		}
    	}

    	@Override
    	public List<Almacenamiento> obtenerAlmacenamientos() throws ServiciosException {
    		try{ 
    			return almacenamientoDAO.obtenerTodos();
    		}catch(PersistenceException e){
    			throw new ServiciosException("No se pudo obtener lista de almacenamientos");
    		}
    	}

    	@Override
    	public List<Almacenamiento> obtenerAlmacenamientos(String nombre) throws ServiciosException {
    		try{ 
    			return almacenamientoDAO.obtenerTodos(nombre);
    		}catch(PersistenceException e){
    			throw new ServiciosException("No se pudo obtener lista de almacenamientos");
    		}
    	}    	
    
    
    

}
