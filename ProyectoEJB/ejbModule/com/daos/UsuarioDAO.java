package com.daos;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Usuario;
import com.exception.ServiciosException;


/**
 * Session Bean implementation class UsuarioDAO
 */
@Stateless
@LocalBean
public class UsuarioDAO {
	
	@PersistenceContext
	private EntityManager em;
	/**
    * Default constructor. 
    */
	public UsuarioDAO() {
	       // TODO Auto-generated constructor stub
	}
	   
    public void crear(Usuario usuario) throws ServiciosException {
		try{
			em.persist(usuario);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo crear el usuario");
		}
    }

    public void actualizar(Usuario usuario) throws ServiciosException {
		try{
			em.merge(usuario);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo actualizar el usuario");
		}	
    }
	
    public void borrar(int id) throws ServiciosException {
		try{
			Usuario usuario = em.find(Usuario.class, id);
			em.remove(usuario);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo borrar el usuario");
		}
    }
	
    public List<Usuario> obtenerTodos() throws ServiciosException {
		try{		
			TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u",Usuario.class); 
			return query.getResultList();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo obtener lista de usuarios");
		}
    }

   	public List<Usuario> obtenerTodos(String nombre) throws ServiciosException {
		try{
			TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.nombre LIKE :nombre",Usuario.class)
    				.setParameter("nombre", nombre); 
    		return query.getResultList();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo obtener usuario..");
    	}
   	}
	
   	public Usuario obtenerPorNomAcceso(String nomAcceso) throws ServiciosException {
   		
   		System.out.println(nomAcceso);
   		
		try{
			TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.nomAcceso LIKE :nomAcceso", Usuario.class)
    				.setParameter("nomAcceso", nomAcceso); 
    	
	   		System.out.println(query);
			
			
			return query.getSingleResult();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo obtener usuario por NomAcceso");
    	}
   	}
	
	public boolean validarContrasena(String nombreUsuario, String contrasena) throws ServiciosException {
		try{
			Usuario usuario = em.find(Usuario.class,nombreUsuario);
			return usuario.getContrasena() == contrasena;
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo encontrar el usuario");
		}
    }

}
