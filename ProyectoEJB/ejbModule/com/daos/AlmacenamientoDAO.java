package com.daos;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Almacenamiento;
import com.exception.ServiciosException;


/**
 * Session Bean implementation class AlmacenamientoDAO
 */
@Stateless
@LocalBean
public class AlmacenamientoDAO {

	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public AlmacenamientoDAO() {
        // TODO Auto-generated constructor stub
    }
    
    public void crear(Almacenamiento almacenamiento) throws ServiciosException {
		try{
			em.persist(almacenamiento);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo crear el almacenamiento");
		}
    }

    public void actualizar(Almacenamiento almacenamiento) throws ServiciosException {
		try{
			em.merge(almacenamiento);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo actualizar el almacenamiento");
		}	
    }
	
    public void borrar(int id) throws ServiciosException {
		try{
			Almacenamiento almacenamiento = em.find(Almacenamiento.class, id);
			em.remove(almacenamiento);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo borrar el almacenamiento");
		}
    }
	
    public List<Almacenamiento> obtenerTodos() throws ServiciosException {
		try{		
			TypedQuery<Almacenamiento> query = em.createQuery("SELECT f FROM Almacenamiento f",Almacenamiento.class); 
			return query.getResultList();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo obtener lista de almacenamientos");
		}
    }

   	public List<Almacenamiento> obtenerTodos(String nombre) throws ServiciosException {
		try{
			TypedQuery<Almacenamiento> query = em.createQuery("SELECT f FROM Almacenamiento f WHERE f.nombre LIKE :nombre",Almacenamiento.class)
    				.setParameter("nombre", nombre); 
    		return query.getResultList();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo obtener almacenamiento");
    	}
   	}
  		
}
