package com.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import com.entities.Movimiento;
import com.entities.Producto;
import com.entities.Usuario;
import com.daos.MovimientoDAO;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class ProductosBean
 */
@Stateless
@LocalBean
public class MovimientosBean implements MovimientosBeanRemote {


	@EJB
	private MovimientoDAO movimientoDAO;
	
    /**
     * Default constructor. 
     */
    public MovimientosBean() {
        }

    	@Override
    	public void crear(Movimiento movimiento) throws ServiciosException {
    		try{
    			movimientoDAO.crear(movimiento);
    		}catch(PersistenceException e){
    			throw new ServiciosException("No se pudo crear el movimiento");
    		}
    	}

    	@Override
    	public void actualizar(Movimiento movimiento) throws ServiciosException {
    		try{
    			movimientoDAO.actualizar(movimiento);
    		}catch(PersistenceException e){
    			throw new ServiciosException("No se pudo actualizar el movimiento");
    		}
    	}
    	
    	
    	@Override
    	public void borrar(int id) throws ServiciosException {
    		try{
    			movimientoDAO.borrar(id);
    		}catch(PersistenceException e){
    			throw new ServiciosException("No se pudo borrar el movimiento");
    		}
    	}
    	
    	@Override
    	public List<Movimiento> obtenerMovimientosPorFecha(String FDesde, String FHasta) throws ServiciosException {
    		try{ 
    			return movimientoDAO.obtenerMovimientosPorFecha(FDesde, FHasta);
    		}catch(PersistenceException e){
    			throw new ServiciosException("No se pudo obtener lista de Movimientos");
    		}
    	}    	

    	
    	@Override
    	public List<Movimiento> obtenerMovimientos() throws ServiciosException {
    		try{ 
    			return movimientoDAO.obtenerTodos();
    		}catch(PersistenceException e){
    			throw new ServiciosException("No se pudo obtener lista de movimientos");
    		}
    	}

    	@Override
    	public List<Movimiento> obtenerMovimientos(String nombre) throws ServiciosException {
    		try{ 
    			return movimientoDAO.obtenerTodos(nombre);
    		}catch(PersistenceException e){
    			throw new ServiciosException("No se pudo obtener lista de movimientos");
    		}
    	}    	

    	
}
