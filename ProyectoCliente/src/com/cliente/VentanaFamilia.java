package com.cliente;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.entities.Familia;
import com.exception.ServiciosException;
import com.servicios.FamiliasBeanRemote;

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

public class VentanaFamilia extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldBuscar;
	private JTable tablaFamilias;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaFamilia frame = new VentanaFamilia();
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
	public VentanaFamilia() throws ServiciosException {
		setTitle("Familias");
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
					cargarFamilias();
				} catch (ServiciosException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		panel.add(btnBuscar);
		
		tablaFamilias = new JTable();
		try {
			cargarFamilias();
		} catch (ServiciosException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		tablaFamilias.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = tablaFamilias.getSelectedRow();
				int idFamilia = (int)tablaFamilias.getModel().getValueAt(row, 0);
				String nombre = (String)tablaFamilias.getModel().getValueAt(row, 1);
				
				Familia familia = new Familia();
				familia.setId(idFamilia);
				familia.setNombre(nombre);
				
				VentanaEditarFamilia ventanaEditarFamilia = new VentanaEditarFamilia(familia);
				ventanaEditarFamilia.setVisible(true);
			}
			});

		contentPane.add(tablaFamilias, BorderLayout.CENTER);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaEditarFamilia ventanaEditarFamilia = new VentanaEditarFamilia(new Familia());
				ventanaEditarFamilia.setVisible(true);
			}
		});
		contentPane.add(btnCrear, BorderLayout.SOUTH);
	}
	
	private void cargarFamilias() throws ServiciosException{
		try {
			FamiliasBeanRemote familiasBeanRemote = EJBLocator.getInstance().lookup(FamiliasBeanRemote.class);
			List<Familia> familias= new ArrayList<>();			
			if(textFieldBuscar.getText().isEmpty()){
				familias = familiasBeanRemote.obtenerFamilias();
			}else{
				familias = familiasBeanRemote.obtenerFamilias(textFieldBuscar.getText()+"%");
			}
			String[] columnNames = {"Id","Nombre"};
		    DefaultTableModel model = new DefaultTableModel();
		    tablaFamilias.setModel(model);

			model.setColumnIdentifiers(columnNames);
			for(Familia familia : familias){
				Object[] fila = new Object[2];
				fila[0]=familia.getId();
				fila[1]=familia.getNombre();
				model.addRow(fila);
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}