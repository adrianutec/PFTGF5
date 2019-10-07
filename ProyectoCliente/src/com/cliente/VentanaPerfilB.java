package com.cliente;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.entities.Perfil;
import com.enumerated.tipoPerfil;
import com.exception.ServiciosException;
import com.servicios.PerfilesBeanRemote;

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

public class VentanaPerfilB extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldBuscar;
	private JTable tablaPerfiles;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPerfilB frame = new VentanaPerfilB();
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
	public VentanaPerfilB() throws ServiciosException {
		setTitle("Perfiles");
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
					cargarPerfiles();
				} catch (ServiciosException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		panel.add(btnBuscar);
		
		tablaPerfiles = new JTable();
		try {
			cargarPerfiles();
		} catch (ServiciosException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		tablaPerfiles.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				int row = tablaPerfiles.getSelectedRow();
				
				int idPerfil = (int)tablaPerfiles.getModel().getValueAt(row, 0);
				tipoPerfil tipoPer = (tipoPerfil)tablaPerfiles.getModel().getValueAt(row, 1);
				
				Perfil perfil = new Perfil();
				perfil.setId(idPerfil);
				perfil.setPerfil(tipoPer);
				

				VentanaBMPerfil ventanaBMPerfil = new VentanaBMPerfil(perfil);
				ventanaBMPerfil.setVisible(true);
			}
			});

		contentPane.add(tablaPerfiles, BorderLayout.CENTER);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaEditarPerfil ventanaEditarPerfil = new VentanaEditarPerfil(new Perfil());
				ventanaEditarPerfil.setVisible(true);
			}
		});
		contentPane.add(btnCrear, BorderLayout.SOUTH);
	}
	
	private void cargarPerfiles() throws ServiciosException{
		try {
			PerfilesBeanRemote perfilesBeanRemote = EJBLocator.getInstance().lookup(PerfilesBeanRemote.class);
			List<Perfil> perfiles= new ArrayList<>();			
			if(textFieldBuscar.getText().isEmpty()){
				perfiles = perfilesBeanRemote.obtenerPerfiles();
			}else{
				perfiles = perfilesBeanRemote.obtenerPerfiles(textFieldBuscar.getText()+"%");
			}
			String[] columnNames = {"Id","Pefil"};
		    DefaultTableModel model = new DefaultTableModel();
		    tablaPerfiles.setModel(model);

			model.setColumnIdentifiers(columnNames);
			for(Perfil perfil : perfiles){
				Object[] fila = new Object[2];
				fila[0]=perfil.getId();
				fila[1]=perfil.getPerfil();
				model.addRow(fila);
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}