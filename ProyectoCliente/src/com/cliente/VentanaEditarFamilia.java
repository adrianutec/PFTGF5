package com.cliente;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;

import com.entities.Familia;
import com.servicios.FamiliasBeanRemote;
import com.exception.ServiciosException;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.naming.NamingException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaEditarFamilia extends JFrame {

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
					VentanaEditarFamilia frame = new VentanaEditarFamilia(new Familia());
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
	public VentanaEditarFamilia(Familia familia) {
		setTitle("Familia");
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
		
		
		textFieldId.setText(familia.getId()==0?"":String.valueOf(familia.getId()));
		textFieldNombre.setText(familia.getNombre());
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				familia.setNombre(textFieldNombre.getText());
				
				FamiliasBeanRemote familiasBeanRemote;
				try {
					familiasBeanRemote = EJBLocator.getInstance().lookup(FamiliasBeanRemote.class);
					if(familia.getId()==0){
						familiasBeanRemote.crear(familia);
					}else {
						familiasBeanRemote.actualizar(familia);
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
				FamiliasBeanRemote familiasBeanRemote;
				try {
					familiasBeanRemote = EJBLocator.getInstance().lookup(FamiliasBeanRemote.class);
					familiasBeanRemote.borrar(familia.getId());
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ServiciosException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		btnBorrar.setVisible(familia.getId()!=0);
		contentPane.add(btnBorrar);
	}

}