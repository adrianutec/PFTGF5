package com.cliente;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.entities.Perfil;
import com.entities.Usuario;
import com.enumerated.tipoPerfil;
import com.exception.ServiciosException;
import com.servicios.UsuariosBeanRemote;

import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.naming.NamingException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VentanaUsuario extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldBuscar;
	private JTable tablaUsuarios;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaUsuario frame = new VentanaUsuario();
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
	public VentanaUsuario() throws ServiciosException {
		setTitle("Usuarios");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		textFieldBuscar = new JTextField();
		panel.add(textFieldBuscar);
		textFieldBuscar.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					cargarUsuarios();
				} catch (ServiciosException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		panel.add(btnBuscar);
		
		tablaUsuarios = new JTable();
		try {
			cargarUsuarios();
		} catch (ServiciosException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		tablaUsuarios.addMouseListener(new MouseAdapter() {
			private Perfil perf;

			public void mouseClicked(MouseEvent e) {
				int row = tablaUsuarios.getSelectedRow();
				int idUsuario = (int)tablaUsuarios.getModel().getValueAt(row, 0);
				String nombre = (String)tablaUsuarios.getModel().getValueAt(row, 1);
				String apellido = (String)tablaUsuarios.getModel().getValueAt(row, 2);
				String nomAcceso = (String)tablaUsuarios.getModel().getValueAt(row, 3);
				String contrasena = (String)tablaUsuarios.getModel().getValueAt(row, 4);
				String correo = (String)tablaUsuarios.getModel().getValueAt(row, 5);
				String perfil = (String)tablaUsuarios.getModel().getValueAt(row, 6);
				
				Usuario usuario = new Usuario();
				usuario.setId(idUsuario);
				usuario.setNombre(nombre);
				usuario.setApellido(apellido);
				usuario.setNomAcceso(nomAcceso);
				usuario.setContrasena(contrasena);
				usuario.setCorreo(correo);
				
				tipoPerfil tipoPerf = null;
				tipoPerf = tipoPerfil.valueOf(perfil);
				perf = null;
				perf.setPerfil(tipoPerf);
				usuario.setPerfil(perf);
				//usuario.setPerfil(perfil);
				
				VentanaEditarUsuario ventanaEditarUsuario = new VentanaEditarUsuario(usuario);
				ventanaEditarUsuario.setVisible(true);
			}
			});

		contentPane.add(tablaUsuarios, BorderLayout.CENTER);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaEditarUsuario ventanaEditarUsuario = new VentanaEditarUsuario(new Usuario());
				ventanaEditarUsuario.setVisible(true);
			}
		});
		contentPane.add(btnCrear, BorderLayout.SOUTH);
	}
	
	private void cargarUsuarios() throws ServiciosException{
		try {
			UsuariosBeanRemote usuariosBeanRemote = EJBLocator.getInstance().lookup(UsuariosBeanRemote.class);
			List<Usuario> usuarios= new ArrayList<>();			
			if(textFieldBuscar.getText().isEmpty()){
				usuarios = usuariosBeanRemote.obtenerUsuarios();
			}else{
				usuarios = usuariosBeanRemote.obtenerUsuarios(textFieldBuscar.getText()+"%");
			}
			String[] columnNames = {"Id","Nombre","Apellido","Nombre Usuario","Contrasena","Correo","Perfil"};
		    DefaultTableModel model = new DefaultTableModel();
		    tablaUsuarios.setModel(model);

			model.setColumnIdentifiers(columnNames);
			for(Usuario usuario : usuarios){
				Object[] fila = new Object[7];
				fila[0]=usuario.getId();
				fila[1]=usuario.getNombre();
				fila[2]=usuario.getApellido();
				fila[3]=usuario.getNomAcceso();
				fila[4]=usuario.getContrasena();
				fila[5]=usuario.getCorreo();
				fila[6]=usuario.getPerfil();
				model.addRow(fila);
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}