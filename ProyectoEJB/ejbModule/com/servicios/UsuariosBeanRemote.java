package com.servicios;

import java.util.List;
import javax.ejb.Remote;
import com.entities.Usuario;
import com.exception.ServiciosException;

@Remote
public interface UsuariosBeanRemote {
	void crear(Usuario usuario) throws ServiciosException;
	void actualizar(Usuario usuario) throws ServiciosException;
	void borrar(int  id) throws ServiciosException;
	List<Usuario>obtenerUsuarios() throws ServiciosException;
	List<Usuario>obtenerUsuarios(String nombre) throws ServiciosException;
	Usuario obtenerPorNomAcceso(String nomAcceso) throws ServiciosException;
	boolean validarContrasena(String nombreUsuario, String contrasena) throws ServiciosException;

}
