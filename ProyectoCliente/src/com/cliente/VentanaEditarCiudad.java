package com.cliente;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;

import com.entities.Ciudad;
import com.servicios.CiudadesBeanRemote;
import com.exception.ServiciosException;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.naming.NamingException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaEditarCiudad extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldId;
	private JTextField textFieldNombre;

		
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEditarCiudad frame = new VentanaEditarCiudad(new Ciudad());
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
	public VentanaEditarCiudad(Ciudad ciudad) {
		setTitle("Ciudad");
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
		
		
		textFieldId.setText(ciudad.getId()==0?"":String.valueOf(ciudad.getId()));
		textFieldNombre.setText(ciudad.getNombre());
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ciudad.setNombre(textFieldNombre.getText());
				
				CiudadesBeanRemote ciudadsBeanRemote;
				try {
					ciudadsBeanRemote = EJBLocator.getInstance().lookup(CiudadesBeanRemote.class);
					if(ciudad.getId()==0){
						ciudadsBeanRemote.crear(ciudad);
					}else {
						ciudadsBeanRemote.actualizar(ciudad);
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
				CiudadesBeanRemote ciudadsBeanRemote;
				try {
					ciudadsBeanRemote = EJBLocator.getInstance().lookup(CiudadesBeanRemote.class);
					ciudadsBeanRemote.borrar(ciudad.getId());
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ServiciosException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		btnBorrar.setVisible(ciudad.getId()!=0);
		contentPane.add(btnBorrar);
	}

}