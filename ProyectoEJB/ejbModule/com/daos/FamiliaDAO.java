package com.daos;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Familia;
import com.exception.ServiciosException;


/**
 * Session Bean implementation class FamiliaDAO
 */
@Stateless
@LocalBean
public class FamiliaDAO {

	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public FamiliaDAO() {
        // TODO Auto-generated constructor stub
    }
    
    public void crear(Familia familia) throws ServiciosException {
		try{
			em.persist(familia);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo crear la familia");
		}
    }

    public void actualizar(Familia familia) throws ServiciosException {
		try{
			em.merge(familia);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo actualizar la familia");
		}	
    }
	
    public void borrar(int id) throws ServiciosException {
		try{
			Familia familia = em.find(Familia.class, id);
			em.remove(familia);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo borrar la familia");
		}
    }
	
    public List<Familia> obtenerTodos() throws ServiciosException {
		try{		
			TypedQuery<Familia> query = em.createQuery("SELECT f FROM Familia f",Familia.class); 
			return query.getResultList();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo obtener lista de familias");
		}
    }

   	public List<Familia> obtenerTodos(String nombre) throws ServiciosException {
		try{
			TypedQuery<Familia> query = em.createQuery("SELECT f FROM Familia f WHERE f.nombre LIKE :nombre",Familia.class)
    				.setParameter("nombre", nombre); 
    		return query.getResultList();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo obtener la familia");
    	}
   	}
  		
}
