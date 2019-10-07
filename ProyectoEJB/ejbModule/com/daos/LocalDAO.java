package com.daos;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Local;
import com.exception.ServiciosException;


/**
 * Session Bean implementation class LocalDAO
 */
@Stateless
@LocalBean
public class LocalDAO {

	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public LocalDAO() {
        // TODO Auto-generated constructor stub
    }
    
    public void crear(Local local) throws ServiciosException {
		try{
			em.persist(local);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo crear el local");
		}
    }

    public void actualizar(Local local) throws ServiciosException {
		try{
			em.merge(local);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo actualizar el local");
		}	
    }
	
    public void borrar(int id) throws ServiciosException {
		try{
			Local local = em.find(Local.class, id);
			em.remove(local);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo borrar el local");
		}
    }
	
    public List<Local> obtenerTodos() throws ServiciosException {
		try{		
			TypedQuery<Local> query = em.createQuery("SELECT l FROM Local l",Local.class); 
			return query.getResultList();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo obtener lista de locales");
		}
    }

   	public List<Local> obtenerTodos(String nombre) throws ServiciosException {
		try{
			TypedQuery<Local> query = em.createQuery("SELECT l FROM Local l WHERE l.nombre LIKE :nombre",Local.class)
    				.setParameter("nombre", nombre); 
    		return query.getResultList();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo obtener el local");
    	}
   	}
  		
}
