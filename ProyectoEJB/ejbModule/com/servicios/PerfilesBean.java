package com.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;

import com.daos.PerfilDAO;
import com.entities.Perfil;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class PerfilesBean
 */
@Stateless
public class PerfilesBean implements PerfilesBeanRemote {

	@EJB
	private PerfilDAO perfilDAO;
	
    /**
     * Default constructor. 
     */
    public PerfilesBean() {
        }

    	@Override
    	public void crear(Perfil perfil) throws ServiciosException {
    		try{
    			perfilDAO.crear(perfil);
    		}catch(PersistenceException e){
    			throw new ServiciosException("No se pudo crear el perfil");
    		}
    	}

    	@Override
    	public void actualizar(Perfil perfil) throws ServiciosException {
    		try{
    			perfilDAO.actualizar(perfil);
    		}catch(PersistenceException e){
    			throw new ServiciosException("No se pudo actualizar el perfil");
    		}
    	}

    	@Override
    	public void borrar(int id) throws ServiciosException {
    		try{
    			perfilDAO.borrar(id);
    		}catch(PersistenceException e){
    			throw new ServiciosException("No se pudo borrar el perfil");
    		}
    	}

    	@Override
    	public List<Perfil> obtenerPerfiles() throws ServiciosException {
    		try{ 
    			return perfilDAO.obtenerTodos();
    		}catch(PersistenceException e){
    			throw new ServiciosException("No se pudo obtener lista de perfiles");
    		}
    	}

    	@Override
    	public List<Perfil> obtenerPerfiles(String nombre) throws ServiciosException {
    		try{ 
    			return perfilDAO.obtenerTodos(nombre);
    		}catch(PersistenceException e){
    			throw new ServiciosException("No se pudo obtener lista de perfiles");
    		}
    	}    	
    
    
      	@Override
    	public long traigoUltimoIDPerfil() throws ServiciosException {
    		try{ 
    			return perfilDAO.traigoUltimoIDPerfil();
    		}catch(PersistenceException e){
    			throw new ServiciosException("No se pudo obtener el �ltimo ID de perfiles");
    		}
    	}    	
    	
    
}
