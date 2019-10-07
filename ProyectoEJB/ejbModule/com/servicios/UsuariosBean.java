package com.servicios;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import com.daos.UsuarioDAO;
import com.entities.Usuario;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class UsuarioBean
 */
@Stateless
@LocalBean
public class UsuariosBean implements UsuariosBeanRemote {

	@EJB
	private UsuarioDAO usuarioDAO;
	
    /**
     * Default constructor. 
     */
    public UsuariosBean() {
        // TODO Auto-generated constructor stub
    }
    
   	@Override
	public void crear(Usuario usuario) throws ServiciosException {
		try{
			usuarioDAO.crear(usuario);
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo crear el usuario");
		}
	}

	@Override
	public void actualizar(Usuario usuario) throws ServiciosException {
		try{
			usuarioDAO.actualizar(usuario);
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo actualizar el usuario");
		}
	}

	@Override
	public void borrar(int id) throws ServiciosException {
		try{
			usuarioDAO.borrar(id);
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo borrar el usuario");
		}
	}

	@Override
	public List<Usuario> obtenerUsuarios() throws ServiciosException {
		try{ 
			return usuarioDAO.obtenerTodos();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo obtener lista de usuarios");
		}
	}

	@Override
	public List<Usuario> obtenerUsuarios(String nombre) throws ServiciosException {
		try{ 
			return usuarioDAO.obtenerTodos(nombre);
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo obtener lista de usuarios");
		}
	}    	

	@Override
	public Usuario obtenerPorNomAcceso(String nomAcceso) throws ServiciosException {
		try{ 
			return usuarioDAO.obtenerPorNomAcceso(nomAcceso);
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo obtener el usuario");
		}
	}    	

    @Override
	public boolean validarContrasena(String nombreUsuario, String contrasena) throws ServiciosException {
		try{
			return usuarioDAO.validarContrasena(nombreUsuario, contrasena);

		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo validar usuario");
		}
	}
    

}
