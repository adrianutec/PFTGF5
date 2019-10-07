package com.cliente;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.entities.Ciudad;
import com.exception.ServiciosException;
import com.servicios.CiudadesBeanRemote;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.naming.NamingException;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaCiudades extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtNombre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCiudades frame = new VentanaCiudades(new Ciudad());
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
	public VentanaCiudades(Ciudad ciudad) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 229);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("ID");
		label.setBounds(92, 27, 46, 14);
		contentPane.add(label);
		
		txtID = new JTextField();
		txtID.setText("0");
		txtID.setEnabled(false);
		txtID.setColumns(10);
		txtID.setBounds(193, 24, 127, 20);
		contentPane.add(txtID);
		
		JLabel label_1 = new JLabel("Nombre");
		label_1.setBounds(92, 58, 46, 14);
		contentPane.add(label_1);
		
		txtNombre = new JTextField();
		txtNombre.setText((String) null);
		txtNombre.setColumns(10);
		txtNombre.setBounds(193, 55, 127, 20);
		contentPane.add(txtNombre);
		
		JButton button = new JButton("ACEPTAR");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button.addMouseListener(new MouseAdapter() {
		@Override
			public void mouseReleased(MouseEvent arg0) {
			
				if (txtNombre.getText().length()>0 ) {
					ciudad.setId(Integer.valueOf(txtID.getText()));
					ciudad.setNombre(txtNombre.getText());
												
					CiudadesBeanRemote ciudadBeanRemote;
					try {
						ciudadBeanRemote = EJBLocator.getInstance().lookup(CiudadesBeanRemote.class);
						if(ciudad.getId()==0){
							ciudadBeanRemote.crear(ciudad);
						}else {
							ciudadBeanRemote.actualizar(ciudad);
						}
					} catch (NamingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ServiciosException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					JOptionPane.showMessageDialog(null, "Deben ingresarse todos los datos de la Ciudad");
					txtNombre.setText("");
				}
					
			}
		});
		button.setBounds(92, 157, 89, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("CANCELAR");
		button_1.setBounds(206, 157, 89, 23);
		contentPane.add(button_1);
		
	}
}
