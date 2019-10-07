package com.daos;

import java.sql.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import com.entities.Movimiento;
import com.exception.ServiciosException;



/**
 * Session Bean implementation class ProductoDAO
 */
@Stateless
@LocalBean
public class MovimientoDAO {

	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public MovimientoDAO() {
        // TODO Auto-generated constructor stub
    }
    
    public void crear(Movimiento movimiento) throws ServiciosException {
		try{
			em.persist(movimiento);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo crear el producto");
		}
    }

    public void actualizar(Movimiento movimiento) throws ServiciosException {
		try{
			em.merge(movimiento);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo actualizar el movimiento");
		}	
    }
	
    public void borrar(int id) throws ServiciosException {
		try{
			Movimiento movimiento = em.find(Movimiento.class, id);
			em.remove(movimiento);
			em.flush();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo borrar el producto");
		}
    }
    
    
	public List<Movimiento> obtenerMovimientosPorFecha(String fdesde, String fhasta) throws ServiciosException {
		
		try{
			
			
			//TypedQuery<Movimiento> query = em.createQuery("select m FROM Movimiento m where m.fecha > :fdesde ",Movimiento.class)
    			
			TypedQuery<Movimiento> query = em.createQuery("select m FROM Movimiento m where m.fecha >= :fdesde AND m.fecha <= :fhasta ",Movimiento.class)
					.setParameter("fdesde", new java.util.Date(fdesde), TemporalType.DATE)
    				.setParameter("fhasta", new java.util.Date(fhasta), TemporalType.DATE); 
		// WHERE m.MOV_FECHA BETWEEN LIKE ?fdesde AND m.MOV_FECHA LIKE ?fhasta
			
    		return query.getResultList();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo obtener los Movimientos");
    	}
   	}


	
    public List<Movimiento> obtenerTodos() throws ServiciosException {
		try{		
			TypedQuery<Movimiento> query = em.createQuery("SELECT m FROM Movimiento m",Movimiento.class); 
			return query.getResultList();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo obtener lista de Movimientos");
		}
    }

   	public List<Movimiento> obtenerTodos(String nombre) throws ServiciosException {
		try{
			TypedQuery<Movimiento> query = em.createQuery("SELECT m FROM Movimiento m WHERE m.nombre LIKE :nombre",Movimiento.class)
    				.setParameter("nombre", nombre); 
    		return query.getResultList();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo obtener los Movimientos");
    	}
   	}
   	
	
}