package com.cliente;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.entities.Ciudad;
import com.exception.ServiciosException;
import com.servicios.CiudadesBeanRemote;

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

public class VentanaCiudad extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldBuscar;
	private JTable tablaCiudades;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCiudad frame = new VentanaCiudad();
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
	public VentanaCiudad() throws ServiciosException {
		setTitle("Ciudades");
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
					cargarCiudades();
				} catch (ServiciosException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		panel.add(btnBuscar);
		
		tablaCiudades = new JTable();
		try {
			cargarCiudades();
		} catch (ServiciosException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		tablaCiudades.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = tablaCiudades.getSelectedRow();
				int idCiudad = (int)tablaCiudades.getModel().getValueAt(row, 0);
				String nombre = (String)tablaCiudades.getModel().getValueAt(row, 1);
				
				Ciudad ciudad = new Ciudad();
				ciudad.setId(idCiudad);
				ciudad.setNombre(nombre);
				
				VentanaEditarCiudad ventanaEditarCiudad = new VentanaEditarCiudad(ciudad);
				ventanaEditarCiudad.setVisible(true);
			}
			});

		contentPane.add(tablaCiudades, BorderLayout.CENTER);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaEditarCiudad ventanaEditarCiudad = new VentanaEditarCiudad(new Ciudad());
				ventanaEditarCiudad.setVisible(true);
			}
		});
		contentPane.add(btnCrear, BorderLayout.SOUTH);
	}
	
	private void cargarCiudades() throws ServiciosException{
		try {
			CiudadesBeanRemote ciudadesBeanRemote = EJBLocator.getInstance().lookup(CiudadesBeanRemote.class);
			List<Ciudad> ciudades= new ArrayList<>();			
			if(textFieldBuscar.getText().isEmpty()){
				ciudades = ciudadesBeanRemote.obtenerCiudades();
			}else{
				ciudades = ciudadesBeanRemote.obtenerCiudades(textFieldBuscar.getText()+"%");
			}
			String[] columnNames = {"Id","Nombre"};
		    DefaultTableModel model = new DefaultTableModel();
		    tablaCiudades.setModel(model);

			model.setColumnIdentifiers(columnNames);
			for(Ciudad ciudad : ciudades){
				Object[] fila = new Object[2];
				fila[0]=ciudad.getId();
				fila[1]=ciudad.getNombre();
				model.addRow(fila);
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}