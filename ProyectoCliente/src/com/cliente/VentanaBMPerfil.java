package com.cliente;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.entities.Perfil;
import com.enumerated.tipoPerfil;
import com.exception.ServiciosException;
import com.servicios.PerfilesBeanRemote;

import javax.swing.JComboBox;

public class VentanaBMPerfil extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldId;
	private JTextField txttipoPer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEditarPerfil frame = new VentanaEditarPerfil(new Perfil());
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
	public VentanaBMPerfil(Perfil perfil) {
		setTitle("Perfil");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 506, 529);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button_1 = new JButton("CANCELAR");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		button_1.setBounds(321, 431, 118, 23);
		contentPane.add(button_1);
		
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(106, 17, 46, 14);
		contentPane.add(lblId);
		
		textFieldId = new JTextField();
		textFieldId.setText("0");
		textFieldId.setEnabled(false);
		textFieldId.setColumns(10);
		textFieldId.setBounds(231, 11, 184, 20);
		contentPane.add(textFieldId);
		
		textFieldId.setText(perfil.getId()==0?"":String.valueOf(perfil.getId()));
		
		txttipoPer = new JTextField();
		txttipoPer.setColumns(10);
		txttipoPer.setBounds(231, 329, 46, 20);
		contentPane.add(txttipoPer);				
		
		JComboBox<tipoPerfil> cmbtipoPer = new JComboBox<tipoPerfil>();
		cmbtipoPer.setToolTipText("Lista para seleccionar el tipo de perfil");
		cmbtipoPer.setBounds(286, 329, 129, 20);
		cmbtipoPer.addItem(tipoPerfil.ADMINISTRADOR);
		cmbtipoPer.addItem(tipoPerfil.OPERARIO);
		cmbtipoPer.addItem(tipoPerfil.SUPERVISOR);
		contentPane.add(cmbtipoPer);
		
		tipoPerfil aux = perfil.getPerfil();
		txttipoPer.setText(aux.toString());
		
		JButton btnModificar = new JButton("MODIFICAR");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnModificar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				String aux = String.valueOf(cmbtipoPer.getSelectedItem());				
				tipoPerfil tipoPer = null;
				tipoPer = tipoPerfil.valueOf(aux);
				perfil.setPerfil(tipoPer);
								
				
				PerfilesBeanRemote perfilesBeanRemote;
				try {
					perfilesBeanRemote = EJBLocator.getInstance().lookup(PerfilesBeanRemote.class);
					if(perfil.getId()==0){
						JOptionPane.showMessageDialog(null, "EL Perfil no Existe para Modificar'");
						//perfilesBeanRemote.crear(perfil);
					}else {
						perfilesBeanRemote.actualizar(perfil);
						JOptionPane.showMessageDialog(null, "Perfil " + perfil.getPerfil() + " Actualizado'");
					}
					limpioCajasTexto();
					
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ServiciosException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			
			}
		});
		btnModificar.setBounds(63, 431, 118, 23);
		contentPane.add(btnModificar);
		
		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
			
				PerfilesBeanRemote perfilesBeanRemote;
				try {
					perfilesBeanRemote = EJBLocator.getInstance().lookup(PerfilesBeanRemote.class);
					if(perfil.getId()==0){
						JOptionPane.showMessageDialog(null, "EL Perfil no Existe para Eliminar'");
						//perfilesBeanRemote.crear(perfil);
					}else {
						perfilesBeanRemote.borrar(perfil.getId());
						JOptionPane.showMessageDialog(null, "Perfil " + perfil.getPerfil() + " fue Eliminado'");
					}
					limpioCajasTexto();
					
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ServiciosException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
			}
		});
		btnEliminar.setBounds(193, 431, 118, 23);
		contentPane.add(btnEliminar);
		
		JButton btnBuscar_1 = new JButton("BUSCAR");
		btnBuscar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnBuscar_1.addMouseListener(new MouseAdapter() {
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
		btnBuscar_1.setBounds(63, 457, 376, 23);
		contentPane.add(btnBuscar_1);
		
		JLabel label = new JLabel("Usuario");
		label.setBounds(106, 396, 91, 14);
		contentPane.add(label);
				
		JLabel label_2 = new JLabel("Perfil");
		label_2.setBounds(106, 335, 113, 14);
		contentPane.add(label_2);
		
		
		JButton btnBuscar = new JButton("BUSCAR");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					VentanaPerfilB ventanaPerfilB = new VentanaPerfilB();
					ventanaPerfilB.setVisible(true);
					ventanaPerfilB.setTitle("BUSCAR PERFIL");
				
				} catch (ServiciosException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		

	}

	public void limpioCajasTexto() {
		textFieldId.setText("");
		txttipoPer.setText("");
	}

	
}
