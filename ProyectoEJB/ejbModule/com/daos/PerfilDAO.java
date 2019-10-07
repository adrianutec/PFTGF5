package com.daos;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Perfil;
import com.exception.ServiciosException;


/**
 * Session Bean implementation class PerfilDAO
 */
@Stateless
@LocalBean
public class PerfilDAO {

	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public PerfilDAO() {
        // TODO Auto-generated constructor stub
    }
    
    public void crear(Perfil perfil) throws ServiciosException {
		try{
			em.persist(perfil);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo crear el perfil");
		}
    }

    public void actualizar(Perfil perfil) throws ServiciosException {
		try{
			em.merge(perfil);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo actualizar el perfil");
		}	
    }
	
    public void borrar(int id) throws ServiciosException {
		try{
			Perfil perfil = em.find(Perfil.class, id);
			em.remove(perfil);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo borrar el perfil");
		}
    }
	
    public List<Perfil> obtenerTodos() throws ServiciosException {
		try{		
			TypedQuery<Perfil> query = em.createQuery("SELECT p FROM Perfil p",Perfil.class); 
			return query.getResultList();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo obtener lista de perfiles");
		}
    }

   	public List<Perfil> obtenerTodos(String nombre) throws ServiciosException {
		try{
			TypedQuery<Perfil> query = em.createQuery("SELECT p FROM Perfil p WHERE p.nombre LIKE :nombre",Perfil.class)
    				.setParameter("nombre", nombre); 
    		return query.getResultList();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo obtener perfil");
    	}
   	}

   	
   	public long traigoUltimoIDPerfil() throws ServiciosException {
		try{
			TypedQuery<Perfil> query = em.createQuery("SELECT MAX(ID) p FROM Perfil",Perfil.class);
    		return query.getSingleResult().getId();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo obtener el �ltimo perfil");
    	}
   	}
   	
}
