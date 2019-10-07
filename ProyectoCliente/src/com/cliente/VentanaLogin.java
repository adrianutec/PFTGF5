package com.cliente;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.entities.Usuario;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.servicios.UsuariosBeanRemote;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class VentanaLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	private JTextField txtUsuario;
	private JTextField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLogin frame = new VentanaLogin();
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
	public VentanaLogin() {
		setFont(new Font("Lucida Bright", Font.PLAIN, 16));
		setTitle("    LOGIN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblUsuario = new JLabel("USUARIO");
		lblUsuario.setFont(new Font("Lucida Bright", Font.PLAIN, 16));
		lblUsuario.setBounds(40, 39, 84, 27);
		contentPanel.add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(134, 39, 248, 27);
		contentPanel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblClave = new JLabel("CLAVE");
		lblClave.setFont(new Font("Lucida Bright", Font.PLAIN, 16));
		lblClave.setBounds(40, 87, 84, 27);
		contentPanel.add(lblClave);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(134, 87, 248, 26);
		contentPanel.add(txtPassword);
		txtPassword.setColumns(10);
		
		JLabel message = new JLabel();
		message.setForeground(Color.RED);
		message.setSize(342, 40);
		message.setLocation(40, 237);
		contentPanel.add(message);

	        
		JButton btnIngresar = new JButton("INGRESAR");
		btnIngresar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
		});
		btnIngresar.setFont(new Font("Lucida Bright", Font.PLAIN, 16));
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String nomAcceso = txtUsuario.getText();
				String contrasena = txtPassword.getText();
								
				UsuariosBeanRemote usuarioBeanRemote;
					try {
						usuarioBeanRemote = EJBLocator.getInstance().lookup(UsuariosBeanRemote.class);
						Usuario usuarioBD = new Usuario();

						try {
							usuarioBD = usuarioBeanRemote.obtenerPorNomAcceso(nomAcceso);
						} catch (Exception e) {
							message.setText(" Usuario o Contrase�a no V�lidas, por favor revise los datos ingresados. ");
							e.printStackTrace();
						}

						String usuarioDBcontrasena = usuarioBD.getContrasena();
						
						if (usuarioDBcontrasena.equals(contrasena)) {
							message.setText(" Bienvenido " + nomAcceso);
							try {
								VentanaMenu frame = new VentanaMenu();
								frame.revalidate();
								frame.repaint();
								frame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
							
						} else {
							message.setText(" Contrase�a no V�lidas, por favor revise los datos ingresados. ");
				        }
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		
		btnIngresar.setBounds(40, 136, 342, 36);
		contentPanel.add(btnIngresar);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});
		btnCancelar.setFont(new Font("Lucida Bright", Font.PLAIN, 16));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		btnCancelar.setBounds(40, 189, 342, 36);
		contentPanel.add(btnCancelar);
	}

	
}
