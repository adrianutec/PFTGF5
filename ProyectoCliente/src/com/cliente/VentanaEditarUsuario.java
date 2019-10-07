package com.cliente;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;

import com.entities.Perfil;
import com.entities.Usuario;
import com.enumerated.tipoPerfil;
import com.servicios.UsuariosBeanRemote;
import com.exception.ServiciosException;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.naming.NamingException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaEditarUsuario extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldId;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldNombreUsuario;
	private JTextField textFieldContrasena;
	private JTextField textFieldCorreo;
	private JTextField textFieldPerfil;
		
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEditarUsuario frame = new VentanaEditarUsuario(new Usuario());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaEditarUsuario(Usuario usuario) {
		setTitle("Usuario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 375, 169);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(8, 2, 0, 0));
		
		JLabel lblId = new JLabel("Id:");
		contentPane.add(lblId);
		
		textFieldId = new JTextField();
		textFieldId.setEnabled(false);
		contentPane.add(textFieldId);
		textFieldId.setColumns(20);
		
		JLabel lblNombre = new JLabel("Nombre:");
		contentPane.add(lblNombre);
		textFieldNombre = new JTextField();
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(20);
		
		JLabel lblApellido = new JLabel("Apellido:");
		contentPane.add(lblApellido);
		textFieldApellido = new JTextField();
		contentPane.add(textFieldApellido);
		textFieldApellido.setColumns(20);
		
		JLabel lblNombreUsuario = new JLabel("Nombre de Usuario:");
		contentPane.add(lblNombreUsuario);
		textFieldNombreUsuario = new JTextField();
		contentPane.add(textFieldNombreUsuario);
		textFieldNombreUsuario.setColumns(20);
		
		JLabel lblContrasena = new JLabel("Contraseña:");
		contentPane.add(lblContrasena);
		textFieldContrasena = new JTextField();
		contentPane.add(textFieldContrasena);
		textFieldContrasena.setColumns(20);
		
		JLabel lblCorreo = new JLabel("Correo:");
		contentPane.add(lblCorreo);
		textFieldCorreo = new JTextField();
		contentPane.add(textFieldCorreo);
		textFieldCorreo.setColumns(20);
		
		JLabel lblPerfil = new JLabel("Perfil:");
		contentPane.add(lblPerfil);
		textFieldPerfil = new JTextField();
		contentPane.add(textFieldPerfil);
		textFieldPerfil.setColumns(20);
		
		textFieldId.setText(usuario.getId()==0?"":String.valueOf(usuario.getId()));
		textFieldNombre.setText(usuario.getNombre());
		textFieldApellido.setText(usuario.getApellido());
		textFieldNombreUsuario.setText(usuario.getNomAcceso());
		textFieldContrasena.setText(usuario.getContrasena());
		textFieldCorreo.setText(usuario.getCorreo());
		textFieldPerfil.setText(usuario.getPerfil().toString());
		
		
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			private Perfil perfil;

			public void actionPerformed(ActionEvent arg0) {
				usuario.setNombre(textFieldNombre.getText());
				usuario.setApellido(textFieldApellido.getText());
				usuario.setNomAcceso(textFieldNombreUsuario.getText());
				usuario.setContrasena(textFieldContrasena.getText());
				usuario.setCorreo(textFieldCorreo.getText());
				//usuario.setPerfil(textFieldPerfil.getText());
				
				tipoPerfil tipoPerf = null;
				tipoPerf = tipoPerfil.valueOf(textFieldPerfil.getText());
				perfil = null;
				perfil.setPerfil(tipoPerf);
				usuario.setPerfil(perfil);
				
				
				UsuariosBeanRemote usuariosBeanRemote;
				try {
					usuariosBeanRemote = EJBLocator.getInstance().lookup(UsuariosBeanRemote.class);
					if(usuario.getId()==0){
						usuariosBeanRemote.crear(usuario);
					}else {
						usuariosBeanRemote.actualizar(usuario);
					}
					
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ServiciosException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		contentPane.add(btnGuardar);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuariosBeanRemote usuariosBeanRemote;
				try {
					usuariosBeanRemote = EJBLocator.getInstance().lookup(UsuariosBeanRemote.class);
					usuariosBeanRemote.borrar(usuario.getId());
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ServiciosException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		btnBorrar.setVisible(usuario.getId()!=0);
		contentPane.add(btnBorrar);
	}

}