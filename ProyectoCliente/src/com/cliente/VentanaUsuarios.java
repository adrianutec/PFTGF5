package com.cliente;

import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.entities.Perfil;
import com.entities.Usuario;
import com.enumerated.tipoPerfil;
import com.exception.ServiciosException;
import com.servicios.UsuariosBeanRemote;
import com.servicios.PerfilesBeanRemote;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.naming.NamingException;
import javax.swing.FocusManager;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaUsuarios extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtNomAcceso;
	private JTextField txtCorreo;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaUsuarios frame = new VentanaUsuarios(new Usuario());
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
	public VentanaUsuarios(Usuario usuario) {
		setTitle("Usuarios");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 322);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("ID");
		label.setBounds(84, 26, 46, 14);
		contentPane.add(label);
		
		txtID = new JTextField();
		txtID.setText("0");
		txtID.setEnabled(false);
		txtID.setColumns(10);
		txtID.setBounds(185, 23, 150, 20);
		contentPane.add(txtID);
		
		JLabel label_1 = new JLabel("Nombre");
		label_1.setBounds(84, 57, 70, 14);
		contentPane.add(label_1);
		
		txtNombre = new JTextField();
		txtNombre.setText((String) null);
		txtNombre.setColumns(10);
		txtNombre.setBounds(185, 54, 150, 20);
		contentPane.add(txtNombre);
		
		JComboBox cmbPerfil = new JComboBox();
		cmbPerfil.setBounds(185, 210, 150, 20);
		List<Perfil> perfil = traigoPerfiles();
		for (Perfil P : perfil) {
			cmbPerfil.addItem(P.getId() + " - " + P.getPerfil());	
		}
		
		
		contentPane.add(cmbPerfil);
		
		JButton button = new JButton("ACEPTAR");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.addMouseListener(new MouseAdapter() {
		@Override
			public void mouseReleased(MouseEvent arg0) {
				usuario.setNombre(txtNombre.getText());
				usuario.setApellido(txtApellido.getText());
				usuario.setNomAcceso(txtNomAcceso.getText());
				usuario.setContrasena(String.valueOf(txtPassword.getPassword()));
				usuario.setCorreo(txtCorreo.getText());
				
				String aux = String.valueOf(cmbPerfil.getItemAt(cmbPerfil.getSelectedIndex()));
				String[] parts = aux.split(" - ");
				
				int id = Integer.valueOf(parts[0].replace(" ", ""));
				String nombre = parts[1].replace(" ", ""); 
				
				Perfil perfUsu = new Perfil();
				tipoPerfil tipoperf = tipoPerfil.valueOf(nombre);
				perfUsu.setId(id);
				perfUsu.setPerfil(tipoperf);
				
				usuario.setPerfil(perfUsu);
				
				UsuariosBeanRemote usuarioBeanRemote;
				try {
					usuarioBeanRemote = EJBLocator.getInstance().lookup(UsuariosBeanRemote.class);
					if(usuario.getId()==0){
						usuarioBeanRemote.crear(usuario);
					}else {
						usuarioBeanRemote.actualizar(usuario);
					}
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ServiciosException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			limpioCajas();
			}
		});
		button.setBounds(120, 250, 100, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("CANCELAR");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Window ventana = FocusManager.getCurrentManager().getActiveWindow(); 
				ventana.dispose();
			}
		});
		button_1.setBounds(234, 250, 100, 23);
		contentPane.add(button_1);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(84, 85, 70, 14);
		contentPane.add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setText((String) null);
		txtApellido.setColumns(10);
		txtApellido.setBounds(185, 82, 150, 20);
		contentPane.add(txtApellido);
		
		JLabel lblAlias = new JLabel("Alias");
		lblAlias.setBounds(84, 116, 46, 14);
		contentPane.add(lblAlias);
		
		txtNomAcceso = new JTextField();
		txtNomAcceso.setText((String) null);
		txtNomAcceso.setColumns(10);
		txtNomAcceso.setBounds(185, 113, 150, 20);
		contentPane.add(txtNomAcceso);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(84, 148, 91, 14);
		contentPane.add(lblContrasea);
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(84, 182, 70, 14);
		contentPane.add(lblCorreo);
		
		txtCorreo = new JTextField();
		txtCorreo.setText((String) null);
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(185, 179, 150, 20);
		contentPane.add(txtCorreo);
		
		JLabel lblPerfil = new JLabel("Perfil");
		lblPerfil.setBounds(84, 213, 70, 14);
		contentPane.add(lblPerfil);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(185, 145, 150, 20);
		contentPane.add(txtPassword);
	}

	public static List<Perfil> traigoPerfiles() {
		PerfilesBeanRemote perfilBeanRemote;
		try {
			perfilBeanRemote = EJBLocator.getInstance().lookup(PerfilesBeanRemote.class);
			return perfilBeanRemote.obtenerPerfiles();

		} catch (NamingException | ServiciosException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void limpioCajas() {
		txtNombre.setText("");
		txtApellido.setText("");
		txtNomAcceso.setText("");
		txtCorreo.setText("");
		txtPassword.setText("");
	}
	
	
}
