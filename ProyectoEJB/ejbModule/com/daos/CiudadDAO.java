package com.daos;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Ciudad;
import com.exception.ServiciosException;


/**
 * Session Bean implementation class CiudadDAO
 */
@Stateless
@LocalBean
public class CiudadDAO {

	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public CiudadDAO() {
        // TODO Auto-generated constructor stub
    }
    
    public void crear(Ciudad ciudad) throws ServiciosException {
		try{
			em.persist(ciudad);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo crear la ciudad");
		}
    }

    public void actualizar(Ciudad ciudad) throws ServiciosException {
		try{
			em.merge(ciudad);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo actualizar la ciudad");
		}	
    }
	
    public void borrar(int id) throws ServiciosException {
		try{
			Ciudad ciudad = em.find(Ciudad.class, id);
			em.remove(ciudad);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo borrar la ciudad");
		}
    }
	
    public List<Ciudad> obtenerTodos() throws ServiciosException {
		try{		
			TypedQuery<Ciudad> query = em.createQuery("SELECT c FROM Ciudad c",Ciudad.class); 
			return query.getResultList();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo obtener lista de ciudads");
		}
    }

   	public List<Ciudad> obtenerTodos(String nombre) throws ServiciosException {
		try{
			TypedQuery<Ciudad> query = em.createQuery("SELECT c FROM Ciudad c WHERE c.nombre LIKE :nombre",Ciudad.class)
    				.setParameter("nombre", nombre); 
    		return query.getResultList();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo obtener la ciudad");
    	}
   	}
  		
}
