package com.daos;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Movimiento;
import com.entities.Producto;
import com.exception.ServiciosException;


/**
 * Session Bean implementation class ProductoDAO
 */
@Stateless
@LocalBean
public class ProductoDAO {

	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public ProductoDAO() {
        // TODO Auto-generated constructor stub
    }
    
    public void crear(Producto producto) throws ServiciosException {
		try{
			em.persist(producto);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo crear el producto");
		}
    }

    public void actualizar(Producto producto) throws ServiciosException {
		try{
			em.merge(producto);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo actualizar el producto");
		}	
    }
	
    public void borrar(int id) throws ServiciosException {
		try{
			Producto producto = em.find(Producto.class, id);
			em.remove(producto);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo borrar el producto");
		}
    }
	
    public List<Producto> obtenerTodos() throws ServiciosException {
		try{		
			TypedQuery<Producto> query = em.createQuery("SELECT p FROM Producto p",Producto.class); 
			return query.getResultList();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo obtener lista de productos");
		}
    }

   	public List<Producto> obtenerTodos(String nombre) throws ServiciosException {
		try{
			TypedQuery<Producto> query = em.createQuery("SELECT p FROM Producto p WHERE p.nombre LIKE :nombre",Producto.class)
    				.setParameter("nombre", nombre); 
    		return query.getResultList();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo obtener producto");
    	}
   	}
 
   	
   	public Producto obtenerProductoPorNombre(String nombre) throws ServiciosException {
		try{
			TypedQuery<Producto> query = em.createQuery("SELECT p FROM Producto p WHERE p.nombre LIKE :nombre",Producto.class)
    				.setParameter("nombre", nombre); 
    		return query.getSingleResult();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo obtener el Producto");
    	}
   	}

   	
   	
}
