package com.cliente;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.entities.Ciudad;
import com.entities.Local;
import com.enumerated.tipoLocal;
import com.exception.ServiciosException;
import com.servicios.LocalesBeanRemote;

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

public class VentanaLocalB extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldBuscar;
	private JTable tablaLocales;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLocalB frame = new VentanaLocalB();
					frame.setVisible(true);
					
					TITULO = frame.getTitle();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static String TITULO;
	
	/**
	 * Create the frame.
	 */
	public VentanaLocalB() throws ServiciosException {
		setTitle("Locales");
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
					cargarLocales();
				} catch (ServiciosException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		panel.add(btnBuscar);
		
		tablaLocales = new JTable();
		try {
			cargarLocales();
		} catch (ServiciosException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		tablaLocales.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				int row = tablaLocales.getSelectedRow();
				
				int idLocal = (int)tablaLocales.getModel().getValueAt(row, 0);
				int codigo = (int)tablaLocales.getModel().getValueAt(row, 1);
				String nombre = (String)tablaLocales.getModel().getValueAt(row, 2);
				String direccion = (String)tablaLocales.getModel().getValueAt(row, 3);
				tipoLocal tipoLoc = (tipoLocal)tablaLocales.getModel().getValueAt(row, 4);
				
				Ciudad ciudad = new Ciudad();
				int aux = (int)tablaLocales.getModel().getValueAt(row, 5);
				ciudad.setId(aux);

				
				Local local = new Local();
				local.setId(idLocal);
				local.setCodigo(codigo);
				local.setNombre(nombre);
				local.setDireccion(direccion);
				local.setTipoloc(tipoLoc);
				local.setCiudad(ciudad);
				

				VentanaBMLocal ventanaBMLocal = new VentanaBMLocal(local);
				ventanaBMLocal.setVisible(true);
			}
			});

		contentPane.add(tablaLocales, BorderLayout.CENTER);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaEditarLocal ventanaEditarLocal = new VentanaEditarLocal(new Local());
				ventanaEditarLocal.setVisible(true);
			}
		});
		contentPane.add(btnCrear, BorderLayout.SOUTH);
	}
	
	private void cargarLocales() throws ServiciosException{
		try {
			LocalesBeanRemote localesBeanRemote = EJBLocator.getInstance().lookup(LocalesBeanRemote.class);
			List<Local> locales= new ArrayList<>();			
			if(textFieldBuscar.getText().isEmpty()){
				locales = localesBeanRemote.obtenerLocales();
			}else{
				locales = localesBeanRemote.obtenerLocales(textFieldBuscar.getText()+"%");
			}
			String[] columnNames = {"Id","Codigo","Nombre","Direccion","Tipo","Ciudad"};
		    DefaultTableModel model = new DefaultTableModel();
		    tablaLocales.setModel(model);

			model.setColumnIdentifiers(columnNames);
			for(Local local : locales){
				Object[] fila = new Object[6];
				fila[0]=local.getId();
				fila[1]=local.getCodigo();
				fila[2]=local.getNombre();
				fila[3]=local.getDireccion();
				fila[4]=local.getTipoloc();
				fila[5]=local.getCiudad().getId();
				model.addRow(fila);
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}