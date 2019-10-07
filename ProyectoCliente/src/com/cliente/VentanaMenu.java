package com.cliente;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.entities.Almacenamiento;
import com.entities.Ciudad;
import com.entities.Familia;
import com.entities.Local;
import com.entities.Movimiento;
import com.entities.Perfil;
import com.entities.Producto;
import com.entities.Usuario;
import com.exception.ServiciosException;
import com.servicios.UsuariosBeanRemote;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.FocusManager;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;

public class VentanaMenu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	//private final Action action = new SwingAction();
	private JTextField txtUsuario;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMenu frame = new VentanaMenu();
					frame.revalidate();
					frame.repaint();
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
	public VentanaMenu() {
		setTitle("SISTEMA DE LOGISTICA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 641, 328);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
// ================================================== L O G I N ==================================================
		
		JMenu mnLogin = new JMenu("LogIn");
		menuBar.add(mnLogin);
		
		JMenuItem mntmLoginUsuario = new JMenuItem("LogIn Usuario");
		mntmLoginUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				VentanaLogin ventanaLogin = new VentanaLogin();
				ventanaLogin.revalidate();
				ventanaLogin.repaint();
				ventanaLogin.setVisible(true);
			}
		});
		mnLogin.add(mntmLoginUsuario);

// ================================================== P R O D U C T O S ==================================================
		
		JMenu mnProductos = new JMenu("Productos");
		menuBar.add(mnProductos);
		
		JMenuItem mntmAltaProductos = new JMenuItem("Alta Productos");
		mntmAltaProductos.setEnabled(false);
		mntmAltaProductos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				VentanaEditarProducto3 VentanaEditarProducto3 = new VentanaEditarProducto3(new Producto());
				VentanaEditarProducto3.setVisible(true);
					
			}
		});
		mnProductos.add(mntmAltaProductos);
				
		JMenuItem mntmBajaProductos = new JMenuItem("Baja Productos");
		mntmBajaProductos.setEnabled(false);
		mntmBajaProductos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				//VentanaBMProducto ventanaBMProducto = new VentanaBMProducto(new Producto());
				//ventanaBMProducto.setVisible(true);
				//ventanaBMProducto.setTitle("ELIMINAR PRODUCTO");
			}
		});
		mnProductos.add(mntmBajaProductos);

		JMenuItem mntmModificacionProductos = new JMenuItem("Modificaci\u00F3n Productos");
		mntmModificacionProductos.setEnabled(false);
		mntmModificacionProductos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
					
				if (mntmModificacionProductos.isEnabled()==true) {
					//VentanaBMProducto ventanaBMProducto = new VentanaBMProducto(new Producto());
					//ventanaBMProducto.setVisible(true);
				}	
			
			}
		});
		mnProductos.add(mntmModificacionProductos);
		
		JMenuItem mntmBuscarProductos = new JMenuItem("Buscar Productos");
		mntmBuscarProductos.setEnabled(false);
		mntmBuscarProductos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
	
				VentanaProductoB ventanaProductoB;
				try {
					ventanaProductoB = new VentanaProductoB();
					ventanaProductoB.setVisible(true);
					ventanaProductoB.setTitle("BUSCAR PRODUCTO");
					
				} catch (ServiciosException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnProductos.add(mntmBuscarProductos);

// ================================================== M O V I M I E N T O S ==================================================
		
		JMenu mnMovimientos = new JMenu("Movimientos");
		menuBar.add(mnMovimientos);

		JMenuItem mntmAltaMovimientos = new JMenuItem("Alta Movimientos");
		mntmAltaMovimientos.setEnabled(false);
		mntmAltaMovimientos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				VentanaEditarMovimiento VentanaEditarMovimiento = new VentanaEditarMovimiento(new Movimiento());
				VentanaEditarMovimiento.setVisible(true);
					
			}
		});
		mnMovimientos.add(mntmAltaMovimientos);
				
		JMenuItem mntmBajaMovimientos = new JMenuItem("Baja Movimientos");
		mntmBajaMovimientos.setEnabled(false);
		mntmBajaMovimientos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				//VentanaBMMovimiento ventanaBMMovimiento = new VentanaBMMovimiento(new Movimiento());
				//ventanaBMMovimiento.setVisible(true);
				//ventanaBMMovimiento.setTitle("ELIMINAR MOVIMIENTO");
			}
		});
		mnMovimientos.add(mntmBajaMovimientos);

		JMenuItem mntmModificacionMovimientos = new JMenuItem("Modificaci\u00F3n Movimientos");
		mntmModificacionMovimientos.setEnabled(false);
		mntmModificacionMovimientos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
					
				if (mntmModificacionMovimientos.isEnabled()==true) {
					//VentanaBMMovimiento ventanaBMMovimiento = new VentanaBMMovimiento(new Movimiento());
					//ventanaBMMovimiento.setVisible(true);
				}	
			
			}
		});
		mnMovimientos.add(mntmModificacionMovimientos);
		
		JMenuItem mntmBuscarMovimientos = new JMenuItem("Buscar Movimientos");
		mntmBuscarMovimientos.setEnabled(false);
		mntmBuscarMovimientos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
	
				VentanaMovimientoB ventanaMovimientoB;
				try {
					ventanaMovimientoB = new VentanaMovimientoB();
					ventanaMovimientoB.setVisible(true);
					ventanaMovimientoB.setTitle("BUSCAR MOVIMIENTO");
					
				} catch (ServiciosException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnMovimientos.add(mntmBuscarMovimientos);
		
		
// ================================================== F U E R A  A L C A N C E ==================================================

		JMenu mnFueraAlcance = new JMenu("Fuera de Alcance");
		menuBar.add(mnFueraAlcance);
		
// -------------------------------------------------- A L M A C E N A M I E N T O --------------------------------------------------

		JMenuItem mntmAltaAlmacenamientos = new JMenuItem("Alta Almacenamientos");
		mntmAltaAlmacenamientos.setEnabled(false);
		mntmAltaAlmacenamientos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				VentanaEditarAlmacenamiento VentanaEditarAlmacenamiento = new VentanaEditarAlmacenamiento(new Almacenamiento());
				VentanaEditarAlmacenamiento.setVisible(true);
					
			}
		});
		mnFueraAlcance.add(mntmAltaAlmacenamientos);
				
		JMenuItem mntmBajaAlmacenamientos = new JMenuItem("Baja Almacenamientos");
		mntmBajaAlmacenamientos.setEnabled(false);
		mntmBajaAlmacenamientos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				//VentanaBMAlmacenamiento ventanaBMAlmacenamiento = new VentanaBMAlmacenamiento(new Almacenamiento());
				//ventanaBMAlmacenamiento.setVisible(true);
				//ventanaBMAlmacenamiento.setTitle("ELIMINAR PRODUCTO");
			}
		});
		mnFueraAlcance.add(mntmBajaAlmacenamientos);

		JMenuItem mntmModificacionAlmacenamientos = new JMenuItem("Modificaci\u00F3n Almacenamientos");
		mntmModificacionAlmacenamientos.setEnabled(false);
		mntmModificacionAlmacenamientos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
					
				if (mntmModificacionAlmacenamientos.isEnabled()==true) {
					//VentanaBMAlmacenamiento ventanaBMAlmacenamiento = new VentanaBMAlmacenamiento(new Almacenamiento());
					//ventanaBMAlmacenamiento.setVisible(true);
				}	
			
			}
		});
		mnFueraAlcance.add(mntmModificacionAlmacenamientos);
		
		JMenuItem mntmBuscarAlmacenamientos = new JMenuItem("Buscar Almacenamientos");
		mntmBuscarAlmacenamientos.setEnabled(false);
		mntmBuscarAlmacenamientos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
	
				VentanaAlmacenamientoB ventanaAlmacenamientoB;
				try {
					ventanaAlmacenamientoB = new VentanaAlmacenamientoB();
					ventanaAlmacenamientoB.setVisible(true);
					ventanaAlmacenamientoB.setTitle("BUSCAR ALMACENAMIENTO");
					
				} catch (ServiciosException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnFueraAlcance.add(mntmBuscarAlmacenamientos);
		
// -------------------------------------------------- C I U D A D --------------------------------------------------

		JMenuItem mntmAltaCiudades = new JMenuItem("Alta Ciudad");
		mntmAltaCiudades.setEnabled(false);
		mntmAltaCiudades.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				VentanaCiudades ventanaCiudades= new VentanaCiudades(new Ciudad());
				ventanaCiudades.setVisible(true);
			}
		});
		mnFueraAlcance.add(mntmAltaCiudades);

		JMenuItem mntmBuscarCiudades = new JMenuItem("Buscar Ciudad");
		mntmBuscarCiudades.setEnabled(false);
		mntmBuscarCiudades.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				VentanaCiudad ventanaCiudad;
				try {
					ventanaCiudad = new VentanaCiudad();
					ventanaCiudad.setVisible(true);
				} catch (ServiciosException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnFueraAlcance.add(mntmBuscarCiudades);

// -------------------------------------------------- F A M I L I A --------------------------------------------------
		
		JMenuItem mntmAltaFamilias = new JMenuItem("Alta Familia");
		mntmAltaFamilias.setEnabled(false);
		mntmAltaFamilias.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				VentanaFamilias ventanaFamilias = new VentanaFamilias(new Familia());
				ventanaFamilias.setVisible(true);
			}
		});
		mnFueraAlcance.add(mntmAltaFamilias);

// -------------------------------------------------- L O C A L --------------------------------------------------

		JMenuItem mntmAltaLocales = new JMenuItem("Alta Local");
		mntmAltaLocales.setEnabled(false);
		mntmAltaLocales.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				VentanaLocales ventanaLocales = new VentanaLocales(new Local());
				ventanaLocales.setVisible(true);
			}
		});
		mnFueraAlcance.add(mntmAltaLocales);

		JMenuItem mntmBajaLocales = new JMenuItem("Baja Locales");
		mntmBajaLocales.setEnabled(false);
		mntmBajaLocales.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				//VentanaBMLocal ventanaBMLocal = new VentanaBMLocal(new Local());
				//ventanaBMLocal.setVisible(true);
				//ventanaBMLocal.setTitle("ELIMINAR LOCAL");
			}
		});
		mnFueraAlcance.add(mntmBajaLocales);

		JMenuItem mntmModificacionLocales = new JMenuItem("Modificaci\u00F3n Locales");
		mntmModificacionLocales.setEnabled(false);
		mntmModificacionLocales.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
					
				if (mntmModificacionLocales.isEnabled()==true) {
					//VentanaBMLocal ventanaBMLocal = new VentanaBMLocal(new Local());
					//ventanaBMLocal.setVisible(true);
				}	
			}
		});
		mnFueraAlcance.add(mntmModificacionLocales);
		
		JMenuItem mntmBuscarLocales = new JMenuItem("Buscar Locales");
		mntmBuscarLocales.setEnabled(false);
		mntmBuscarLocales.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
	
				VentanaLocalB ventanaLocalB;
				try {
					ventanaLocalB = new VentanaLocalB();
					ventanaLocalB.setVisible(true);
					ventanaLocalB.setTitle("BUSCAR LOCAL");
					
				} catch (ServiciosException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnFueraAlcance.add(mntmBuscarLocales);
	
// -------------------------------------------------- P E R F I L --------------------------------------------------

		JMenuItem mntmAltaPerfiles = new JMenuItem("Alta Perfiles");
		mntmAltaPerfiles.setEnabled(false);
		mntmAltaPerfiles.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				VentanaEditarPerfil VentanaEditarPerfil = new VentanaEditarPerfil(new Perfil());
				VentanaEditarPerfil.setVisible(true);
					
			}
		});
		mnFueraAlcance.add(mntmAltaPerfiles);
				
		JMenuItem mntmBajaPerfiles = new JMenuItem("Baja Perfiles");
		mntmBajaPerfiles.setEnabled(false);
		mntmBajaPerfiles.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				VentanaBMPerfil ventanaBMPerfil = new VentanaBMPerfil(new Perfil());
				ventanaBMPerfil.setVisible(true);
				ventanaBMPerfil.setTitle("ELIMINAR PERFIL");
			}
		});
		mnFueraAlcance.add(mntmBajaPerfiles);

		JMenuItem mntmModificacionPerfiles = new JMenuItem("Modificaci\u00F3n Perfiles");
		mntmModificacionPerfiles.setEnabled(false);
		mntmModificacionPerfiles.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
					
				if (mntmModificacionPerfiles.isEnabled()==true) {
					VentanaBMPerfil ventanaBMPerfil = new VentanaBMPerfil(new Perfil());
					ventanaBMPerfil.setVisible(true);
				}	
			
			}
		});
		mnFueraAlcance.add(mntmModificacionPerfiles);
		
		JMenuItem mntmBuscarPerfiles = new JMenuItem("Buscar Perfiles");
		mntmBuscarPerfiles.setEnabled(false);
		mntmBuscarPerfiles.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
	
				VentanaPerfilB ventanaPerfilB;
				try {
					ventanaPerfilB = new VentanaPerfilB();
					ventanaPerfilB.setVisible(true);
					ventanaPerfilB.setTitle("BUSCAR PERFIL");
					
				} catch (ServiciosException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnFueraAlcance.add(mntmBuscarPerfiles);
		

// -------------------------------------------------- U S U A R I O --------------------------------------------------

		JMenuItem mntmAltaUsuarios = new JMenuItem("Alta Usuarios");
		mntmAltaUsuarios.setEnabled(false);
		mntmAltaUsuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				VentanaUsuarios ventanaUsuarios= new VentanaUsuarios(new Usuario());
				ventanaUsuarios.setVisible(true);
			}
		});
		mnFueraAlcance.add(mntmAltaUsuarios);
		
		
		

// ================================================== R E P O R T E S ==================================================

		JMenu mnReportes = new JMenu("Reportes");
		menuBar.add(mnReportes);
		
		JMenuItem mntmReporte = new JMenuItem("Reporte Pedidos x Fecha");
		mntmReporte.setEnabled(false);
		mntmReporte.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				ReportePedidos reportePedidos = new ReportePedidos();
				reportePedidos.setVisible(true);
			}
		});
		mnReportes.add(mntmReporte);
		
// ================================================== F I N A L  M E N U ==================================================

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(141, 41, 158, 27);
		contentPane.add(txtUsuario);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setIcon(new ImageIcon("C:\\workspace\\ProyectoCliente\\images\\user.png"));
		lblUsuario.setFont(new Font("Lucida Bright", Font.PLAIN, 11));
		lblUsuario.setBounds(10, 41, 121, 27);
		contentPane.add(lblUsuario);
				
		JLabel message = new JLabel();
		
		JLabel lblClave = new JLabel("Clave");
		lblClave.setIcon(new ImageIcon("C:\\workspace\\ProyectoCliente\\images\\candado.png"));
		lblClave.setFont(new Font("Lucida Bright", Font.PLAIN, 11));
		lblClave.setBounds(10, 79, 94, 27);
		contentPane.add(lblClave);
		
		JButton btnIngresar = new JButton("Ingresar");
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
							limpioCajas();
								
							try {
								
								Perfil perfil = usuarioBD.getPerfil();
								switch (String.valueOf(perfil.getPerfil())) {
									case "ADMINISTRADOR":
										// habilitar menu
										mntmLoginUsuario.setEnabled(true);
										mntmAltaProductos.setEnabled(true);
										mntmBajaProductos.setEnabled(true);
										mntmModificacionProductos.setEnabled(true);
										mntmBuscarProductos.setEnabled(true);
										mntmAltaMovimientos.setEnabled(true);
										mntmBajaMovimientos.setEnabled(true);
										mntmModificacionMovimientos.setEnabled(true);
										mntmBuscarMovimientos.setEnabled(true);
										mntmAltaAlmacenamientos.setEnabled(true);
										mntmBajaAlmacenamientos.setEnabled(true);
										mntmModificacionAlmacenamientos.setEnabled(true);
										mntmBuscarAlmacenamientos.setEnabled(true);
										mntmAltaCiudades.setEnabled(true);
										mntmBuscarCiudades.setEnabled(true);
										mntmAltaFamilias.setEnabled(true);
										mntmAltaLocales.setEnabled(true);
										mntmBajaLocales.setEnabled(true);
										mntmModificacionLocales.setEnabled(true);
										mntmBuscarLocales.setEnabled(true);
										mntmAltaPerfiles.setEnabled(true);
										mntmBajaPerfiles.setEnabled(true);
										mntmModificacionPerfiles.setEnabled(true);
										mntmBuscarPerfiles.setEnabled(true);
										mntmAltaUsuarios.setEnabled(true);
										mntmReporte.setEnabled(true);
										// visualizar menu
										mntmLoginUsuario.setVisible(true);
										mntmAltaProductos.setVisible(true);
										mntmBajaProductos.setVisible(true);
										mntmModificacionProductos.setVisible(true);
										mntmBuscarProductos.setVisible(true);
										mntmAltaMovimientos.setVisible(true);
										mntmBajaMovimientos.setVisible(true);
										mntmModificacionMovimientos.setVisible(true);
										mntmBuscarMovimientos.setVisible(true);
										mntmAltaAlmacenamientos.setVisible(true);
										mntmBajaAlmacenamientos.setVisible(true);
										mntmModificacionAlmacenamientos.setVisible(true);
										mntmBuscarAlmacenamientos.setVisible(true);
										mntmAltaCiudades.setVisible(true);
										mntmBuscarCiudades.setVisible(true);
										mntmAltaFamilias.setVisible(true);
										mntmAltaLocales.setVisible(true);
										mntmBajaLocales.setVisible(true);
										mntmModificacionLocales.setVisible(true);
										mntmBuscarLocales.setVisible(true);
										mntmAltaPerfiles.setVisible(true);
										mntmBajaPerfiles.setVisible(true);
										mntmModificacionPerfiles.setVisible(true);
										mntmBuscarPerfiles.setVisible(true);
										mntmAltaUsuarios.setVisible(true);
										mntmReporte.setVisible(true);
										
									case "OPERARIO":
										// habilitar menu
										mntmLoginUsuario.setEnabled(true);
										mntmAltaProductos.setEnabled(false);
										mntmBajaProductos.setEnabled(false);
										mntmModificacionProductos.setEnabled(false);
										mntmBuscarProductos.setEnabled(true);
										mntmAltaMovimientos.setEnabled(true);
										mntmBajaMovimientos.setEnabled(false);
										mntmModificacionMovimientos.setEnabled(false);
										mntmBuscarMovimientos.setEnabled(true);
										mntmAltaAlmacenamientos.setEnabled(false);
										mntmBajaAlmacenamientos.setEnabled(false);
										mntmModificacionAlmacenamientos.setEnabled(false);
										mntmBuscarAlmacenamientos.setEnabled(true);
										mntmAltaCiudades.setEnabled(false);
										mntmBuscarCiudades.setEnabled(true);
										mntmAltaFamilias.setEnabled(false);
										mntmAltaLocales.setEnabled(false);
										mntmBajaLocales.setEnabled(false);
										mntmModificacionLocales.setEnabled(false);
										mntmBuscarLocales.setEnabled(true);
										mntmAltaPerfiles.setEnabled(false);
										mntmBajaPerfiles.setEnabled(false);
										mntmModificacionPerfiles.setEnabled(false);
										mntmBuscarPerfiles.setEnabled(false);
										mntmAltaUsuarios.setEnabled(false);
										mntmReporte.setEnabled(true);
										// visualizar menu
										mntmLoginUsuario.setVisible(true);
										mntmAltaProductos.setVisible(false);
										mntmBajaProductos.setVisible(false);
										mntmModificacionProductos.setVisible(false);
										mntmBuscarProductos.setVisible(true);
										mntmAltaMovimientos.setVisible(true);
										mntmBajaMovimientos.setVisible(false);
										mntmModificacionMovimientos.setVisible(false);
										mntmBuscarMovimientos.setVisible(true);
										mntmAltaAlmacenamientos.setVisible(false);
										mntmBajaAlmacenamientos.setVisible(false);
										mntmModificacionAlmacenamientos.setVisible(false);
										mntmBuscarAlmacenamientos.setVisible(true);
										mntmAltaCiudades.setVisible(false);
										mntmBuscarCiudades.setVisible(true);
										mntmAltaFamilias.setVisible(false);
										mntmAltaLocales.setVisible(false);
										mntmBajaLocales.setVisible(false);
										mntmModificacionLocales.setVisible(false);
										mntmBuscarLocales.setVisible(true);
										mntmAltaPerfiles.setVisible(false);
										mntmBajaPerfiles.setVisible(false);
										mntmModificacionPerfiles.setVisible(false);
										mntmBuscarPerfiles.setVisible(false);
										mntmAltaUsuarios.setVisible(false);
										mntmReporte.setVisible(true);

									case "SUPERVISOR":
										// habilitar menu
										mntmLoginUsuario.setEnabled(true);
										mntmAltaProductos.setEnabled(true);
										mntmBajaProductos.setEnabled(true);
										mntmModificacionProductos.setEnabled(true);
										mntmBuscarProductos.setEnabled(true);
										mntmAltaMovimientos.setEnabled(true);
										mntmBajaMovimientos.setEnabled(true);
										mntmModificacionMovimientos.setEnabled(true);
										mntmBuscarMovimientos.setEnabled(true);
										mntmAltaAlmacenamientos.setEnabled(true);
										mntmBajaAlmacenamientos.setEnabled(true);
										mntmModificacionAlmacenamientos.setEnabled(true);
										mntmBuscarAlmacenamientos.setEnabled(true);
										mntmAltaCiudades.setEnabled(true);
										mntmBuscarCiudades.setEnabled(true);
										mntmAltaFamilias.setEnabled(true);
										mntmAltaLocales.setEnabled(true);
										mntmBajaLocales.setEnabled(true);
										mntmModificacionLocales.setEnabled(true);
										mntmBuscarLocales.setEnabled(true);
										mntmAltaPerfiles.setEnabled(true);
										mntmBajaPerfiles.setEnabled(true);
										mntmModificacionPerfiles.setEnabled(true);
										mntmBuscarPerfiles.setEnabled(true);
										mntmAltaUsuarios.setEnabled(true);
										mntmReporte.setEnabled(true);
										// visualizar menu
										mntmLoginUsuario.setVisible(true);
										mntmAltaProductos.setVisible(true);
										mntmBajaProductos.setVisible(true);
										mntmModificacionProductos.setVisible(true);
										mntmBuscarProductos.setVisible(true);
										mntmAltaMovimientos.setVisible(true);
										mntmBajaMovimientos.setVisible(true);
										mntmModificacionMovimientos.setVisible(true);
										mntmBuscarMovimientos.setVisible(true);
										mntmAltaAlmacenamientos.setVisible(true);
										mntmBajaAlmacenamientos.setVisible(true);
										mntmModificacionAlmacenamientos.setVisible(true);
										mntmBuscarAlmacenamientos.setVisible(true);
										mntmAltaCiudades.setVisible(true);
										mntmBuscarCiudades.setVisible(true);
										mntmAltaFamilias.setVisible(true);
										mntmAltaLocales.setVisible(true);
										mntmBajaLocales.setVisible(true);
										mntmModificacionLocales.setVisible(true);
										mntmBuscarLocales.setVisible(true);
										mntmAltaPerfiles.setVisible(true);
										mntmBajaPerfiles.setVisible(true);
										mntmModificacionPerfiles.setVisible(true);
										mntmBuscarPerfiles.setVisible(true);
										mntmAltaUsuarios.setVisible(true);
										mntmReporte.setVisible(true);
									
									default :
										// habilitar menu
										mntmLoginUsuario.setEnabled(true);
										mntmAltaProductos.setEnabled(false);
										mntmBajaProductos.setEnabled(false);
										mntmModificacionProductos.setEnabled(false);
										mntmBuscarProductos.setEnabled(false);
										mntmAltaMovimientos.setEnabled(false);
										mntmBajaMovimientos.setEnabled(false);
										mntmModificacionMovimientos.setEnabled(false);
										mntmBuscarMovimientos.setEnabled(false);
										mntmAltaAlmacenamientos.setEnabled(false);
										mntmBajaAlmacenamientos.setEnabled(false);
										mntmModificacionAlmacenamientos.setEnabled(false);
										mntmBuscarAlmacenamientos.setEnabled(false);
										mntmAltaCiudades.setEnabled(false);
										mntmBuscarCiudades.setEnabled(false);
										mntmAltaFamilias.setEnabled(false);
										mntmAltaLocales.setEnabled(false);
										mntmBajaLocales.setEnabled(false);
										mntmModificacionLocales.setEnabled(false);
										mntmBuscarLocales.setEnabled(false);
										mntmAltaPerfiles.setEnabled(false);
										mntmBajaPerfiles.setEnabled(false);
										mntmModificacionPerfiles.setEnabled(false);
										mntmBuscarPerfiles.setEnabled(false);
										mntmAltaUsuarios.setEnabled(false);
										mntmReporte.setEnabled(false);
										// visualizar menu
										mntmLoginUsuario.setVisible(true);
										mntmAltaProductos.setVisible(false);
										mntmBajaProductos.setVisible(false);
										mntmModificacionProductos.setVisible(false);
										mntmBuscarProductos.setVisible(false);
										mntmAltaMovimientos.setVisible(false);
										mntmBajaMovimientos.setVisible(false);
										mntmModificacionMovimientos.setVisible(false);
										mntmBuscarMovimientos.setVisible(false);
										mntmAltaAlmacenamientos.setVisible(false);
										mntmBajaAlmacenamientos.setVisible(false);
										mntmModificacionAlmacenamientos.setVisible(false);
										mntmBuscarAlmacenamientos.setVisible(false);
										mntmAltaCiudades.setVisible(false);
										mntmBuscarCiudades.setVisible(false);
										mntmAltaFamilias.setVisible(false);
										mntmAltaLocales.setVisible(false);
										mntmBajaLocales.setVisible(false);
										mntmModificacionLocales.setVisible(false);
										mntmBuscarLocales.setVisible(false);
										mntmAltaPerfiles.setVisible(false);
										mntmBajaPerfiles.setVisible(false);
										mntmModificacionPerfiles.setVisible(false);
										mntmBuscarPerfiles.setVisible(false);
										mntmAltaUsuarios.setVisible(false);
										mntmReporte.setVisible(false);
									
								
								}
						
								
								
							} catch (Exception e) {
								e.printStackTrace();
							}
							
						} else {
							message.setText(" Contrase�a no V�lidas, por favor revise los datos ingresados. ");
							limpioCajas();
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}

			
			}
		});
		btnIngresar.setFont(new Font("Lucida Bright", Font.PLAIN, 11));
		btnIngresar.setBounds(55, 143, 100, 23);
		contentPane.add(btnIngresar);
	
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Window ventana = FocusManager.getCurrentManager().getActiveWindow(); 
				ventana.dispose();
			}
		});
		btnCancelar.setFont(new Font("Lucida Bright", Font.PLAIN, 11));
		btnCancelar.setBounds(177, 143, 100, 23);
		contentPane.add(btnCancelar);
		
		message.setForeground(Color.RED);
		message.setBounds(125, 238, 342, 27);
		contentPane.add(message);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(141, 79, 158, 27);
		contentPane.add(txtPassword);
	
	}
	
	
	public void limpioCajas() {
		txtUsuario.setText("");
		txtPassword.setText("");				
	}
}
